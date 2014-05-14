/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cruxframework.mediamanager.core.reuse;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.Validate;
import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.mediamanager.core.client.enums.ErrorType;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.core.exception.ValidationException;
import org.cruxframework.mediamanager.core.utils.EntityUtils;
import org.cruxframework.mediamanager.core.utils.Filter;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: Abstract superclass for REST based services.
 * @param <D> DTO type class
 * @param <E> Entity type class
 * @see AbstractDTO
 * @see AbstractEntity
 * 
 * @author alexandre.costa
 */
public abstract class AbstractService<D extends AbstractDTO>
{
	private final static Logger LOGGER = Logger
		.getLogger(AbstractService.class.getName());
	
	/**
	 * Retrieves a record form persistence mechanism by id.
	 * @param id register id
	 * @return DTO representation from an entity record
	 * @throws RestException
	 */
	public D get(Integer id) throws RestException
	{
		try
		{
			AbstractEntity<D> entity = getDao().find(id);
			return entity.getDTORepresentation();
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao recuperar objeto da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			throw new RestException(e);
		} 
	}

	/**
	 * Update a record on persistence mechanism.
	 * @param id entity id
	 * @param dto DTO representation for updating an entity record. 
	 */
	public EditOperation update(Integer id, D dto) throws RestException
	{
		try
		{
			Validate.isTrue(id != null && dto != null && id.equals(dto.getId()), "");
			doSave(false, dto);
			return new EditOperation();
		} catch (RestException e)
		{
			throw e;
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao atualizar objeto da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			throw new RestException(e);
		}
	}

	/**
	 * Insert a new register on database.
	 * @param dto new instance
	 * @return instance ID
	 * @throws RestException
	 */
	public EditOperation insert(D dto) throws RestException
	{
		try
		{
			Validate.isTrue(dto != null && dto.getId() == null, "");
			Integer id = doSave(true, dto);
			EditOperation edit = new EditOperation();
			edit.setId(id);
			return edit;
		} catch (RestException e)
		{
			throw e;
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao inserir objeto da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			throw new RestException(e);
		}
	}
	
	/**
	 * delete a register on database.
	 * @param abstractBO new instance
	 * @return instance ID
	 * @throws RestException
	 */
	public EditOperation delete(D dto) throws RestException
	{
		try
		{
			AbstractEntity<D> entity = getDao().find(dto.getId());
			validateDelete(entity);
			getDao().delete(entity);
			return new EditOperation();
		} catch (ValidationException e) 
		{
			EditOperation operation = new EditOperation();
			operation.setId(null);
			operation.setType(ErrorType.DELETE);
			return operation;
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao excluir objeto da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			throw new RestException("Erro ao excluir um registro", e);
		} 
	}
	
	/****************************************************
	 * Utilities
	 ***************************************************/
	
	protected List<D> doSearch(List<Filter> filters) 
		throws RestException
	{
		return doSearch(filters, orderBy());
	}
	
	protected List<D> doSearch(List<Filter> filters, List<OrderBy> orderBy) 
		throws RestException
	{
		try
		{
			List<AbstractEntity<D>> entities = getDao().search(filters, orderBy);
			return EntityUtils.convert(entities);
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao pesquisar objetos da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			
			throw new RestException(e);
		} 
	}
	
	protected Integer doSave(boolean insert, D dto) throws RestException
	{
		try
		{
			AbstractEntity<D> entity = getCorrectInstance(insert, dto);
			prepareEntity(entity, dto);
			
			if (insert)
			{
				validateInsert(entity);
			} else
			{
				validateUpdate(entity);
			}

			getDao().save(entity);
			return entity.getId();
		} catch (Exception e)
		{
			LOGGER.severe("Erro ao salvar um objeto da class ["
				+ getDao().getEntityClass().getCanonicalName() + "]");
			
			throw new RestException(e);
		} 
	}
	
	@SuppressWarnings("unchecked")
	protected AbstractEntity<D> getCorrectInstance(boolean insert, D dto)
	{
		if (insert)
		{
			try
			{
				return (AbstractEntity<D>) getDao().getEntityClass().newInstance();
			} catch (InstantiationException e)
			{
				throw new RuntimeException(e);
			} catch (IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		} else 
		{
			return getDao().find(dto.getId());
		}
	}

	/*****************************************************
	 * Abstract or empty methods
	 *****************************************************/
	
	/**
	 * Get DAO object
	 * @return DAO object
	 */
	protected abstract Dao<D> getDao();
	
	/**
	 * Copy values from DTO to entity
	 * @param entity target entity
	 * @param dto source DTO
	 */
	protected void prepareEntity(AbstractEntity<D> entity, D dto)
	{
		
	}
	
	/**
	 * Performer business rules validation on insert operation.
	 * @param object entity to be validated
	 */
	protected void validateInsert(AbstractEntity<D> object)
	{
		
	}
	
	/**
	 * Performer business rules validation on update operation.
	 * @param object entity to be validated
	 */
	protected void validateUpdate(AbstractEntity<D> object)
	{
		
	}
	
	/**
	 * Performer business rules validation on delete operation.
	 * @param object entity to be validated
	 */
	protected void validateDelete(AbstractEntity<D> object)
	{
		
	}
	
	/**
	 * Get the default ordering for search operation.
	 * @return ordering
	 */
	protected List<OrderBy> orderBy()
	{
		return null;
	}
}
