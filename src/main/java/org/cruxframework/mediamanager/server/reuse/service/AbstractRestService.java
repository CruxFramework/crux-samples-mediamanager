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
package org.cruxframework.mediamanager.server.reuse.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.Validate;
import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.crux.core.shared.rest.annotation.DELETE;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.POST;
import org.cruxframework.crux.core.shared.rest.annotation.PUT;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;
import org.cruxframework.crux.core.shared.rest.annotation.StateValidationModel;
import org.cruxframework.mediamanager.client.reuse.controller.EditOperation;
import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;
import org.cruxframework.mediamanager.server.reuse.entity.dao.AbstractDAO;
import org.cruxframework.mediamanager.server.utils.EntityUtils;
import org.cruxframework.mediamanager.server.utils.Filter;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.enums.ErrorType;
import org.cruxframework.mediamanager.shared.exception.ValidationException;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class description: Abstract superclass for REST based services.
 * 
 * @param <D> DTO type class
 * @param <E> Entity type class
 * @see AbstractDTO
 * @see AbstractEntity
 * 
 * @author alexandre.costa
 */
public abstract class AbstractRestService<D extends AbstractDTO, E extends AbstractEntity<D>>
{
	private static final Logger LOGGER = Logger.getLogger(AbstractRestService.class.getName());

	private static final String RIGHT_BRACKET = "]";

	/**
	 * Retrieves a record form persistence mechanism by id.
	 * 
	 * @param id register id
	 * @return DTO representation from an entity record
	 * @throws RestException unexpected exception
	 */
	@GET
	/* (cacheTime = GET.ONE_DAY) */
	@Path("{id}")
	public D get(@PathParam("id") Integer id) throws RestException
	{
		try
		{
			E entity = getDao().find(id);
			return entity.getDTORepresentation();
		}
		catch (PersistenceException e)
		{
			LOGGER.severe("Erro ao recuperar objeto da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);
			throw new RestException(e);
		}
	}

	/**
	 * Update a record on persistence mechanism.
	 * 
	 * @param id entity id
	 * @param dto DTO representation for updating an entity record.
	 * @return result of operation
	 * @throws RestException unexpected exception
	 */
	@PUT(validatePreviousState = StateValidationModel.ENSURE_STATE_MATCHES)
	@Path("{id}")
	@Transactional
	public EditOperation update(@PathParam("id") Integer id, D dto) throws RestException
	{
		try
		{
			Validate.isTrue(id != null && dto != null && id.equals(dto.getId()), "");
			doSave(false, dto);
			return new EditOperation();
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			LOGGER.severe("Erro ao atualizar objeto da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);
			throw new RestException(e);
		}
	}

	/**
	 * Insert a new register on database.
	 * 
	 * @param dto new instance
	 * @return result of operation
	 * @throws RestException exceptions 
	 */
	@POST
	@Transactional
	public EditOperation insert(D dto) throws RestException
	{
		try
		{
			Validate.isTrue(dto != null && dto.getId() == null, "");
			Integer id = doSave(true, dto);
			EditOperation edit = new EditOperation();
			edit.setId(id);
			return edit;
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			LOGGER.severe("Erro ao inserir objeto da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);
			throw new RestException(e);
		}
	}

	/**
	 * Remove an business object from the database.
	 * 
	 * @param dto DTO representation of the entity to be removed.
	 * @param id id of the entity to be removed.
	 * @return result of operation
	 * @throws RestException unexpected exception
	 */
	@DELETE
	@Path("{id}")
	@Transactional
	public EditOperation delete(@PathParam("id") Integer id, D dto) throws RestException
	{
		try
		{
			E entity = getDao().find(dto.getId());
			validateDelete(entity);
			getDao().delete(entity);
			return new EditOperation();
		}
		catch (PersistenceException e)
		{
			LOGGER.severe("Erro ao excluir objeto da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);
			throw new RestException("Erro ao excluir um registro", e);
		}
		catch (ValidationException e)
		{
			EditOperation operation = new EditOperation();
			operation.setId(null);
			operation.setType(ErrorType.DELETE);
			return operation;
		}
	}

	/****************************************************
	 * Utilities
	 ***************************************************/

	protected List<D> doSearch(List<Filter> filters) throws RestException
	{
		return doSearch(filters, orderBy());
	}

	protected List<D> doSearch(List<Filter> filters, List<OrderBy> orderBy) throws RestException
	{
		try
		{
			List<E> entities = getDao().search(filters, orderBy);
			return EntityUtils.convert(entities);
		}
		catch (PersistenceException e)
		{
			LOGGER.severe("Erro ao pesquisar objetos da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);

			throw new RestException(e);
		}
	}

	protected Integer doSave(boolean insert, D dto) throws RestException
	{
		try
		{
			E entity = getCorrectInstance(insert, dto);
			prepareEntity(entity, dto);

			if (insert)
			{
				validateInsert(entity);
			}
			else
			{
				validateUpdate(entity);
			}

			getDao().save(entity);
			return entity.getId();
		}
		catch (PersistenceException e)
		{
			LOGGER.severe("Erro ao salvar um objeto da class [" + getDao().getEntityClass().getCanonicalName() + RIGHT_BRACKET);

			throw new RestException(e);
		}
	}

	protected E getCorrectInstance(boolean insert, D dto)
	{
		if (insert)
		{
			try
			{
				return getDao().getEntityClass().newInstance();
			}
			catch (InstantiationException e)
			{
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		}
		else
		{
			return getDao().find(dto.getId());
		}
	}

	/*****************************************************
	 * Abstract or empty methods
	 *****************************************************/

	/**
	 * Get DAO object
	 * 
	 * @return DAO object
	 */
	protected abstract AbstractDAO<D, E> getDao();

	/**
	 * Copy values from DTO to entity
	 * 
	 * @param entity target entity
	 * @param dto source DTO
	 */
	protected void prepareEntity(E entity, D dto)
	{

	}

	/**
	 * Performer business rules validation on insert operation.
	 * 
	 * @param object entity to be validated
	 */
	protected void validateInsert(E object)
	{

	}

	/**
	 * Performer business rules validation on update operation.
	 * 
	 * @param object entity to be validated
	 */
	protected void validateUpdate(E object)
	{

	}

	/**
	 * Performer business rules validation on delete operation.
	 * 
	 * @param object entity to be validated
	 */
	protected void validateDelete(E object)
	{

	}

	/**
	 * Get the default ordering for search operation.
	 * 
	 * @return ordering
	 */
	protected List<OrderBy> orderBy()
	{
		return null;
	}
}
