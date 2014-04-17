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
package org.cruxframework.mediamanager.server.reuse.entity.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;
import org.cruxframework.mediamanager.server.utils.Filter;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractDAO<DTO extends AbstractDTO, 
	E extends AbstractEntity<DTO>>
{
	@PersistenceContext
	private EntityManager entityManager;

	/* Abstract BO class */
	private Class<E> entityClass;
	
	{
		init();
	}
	
	public E find(Integer id)
	{
		E entity = entityManager.find(entityClass, id);
		return entity;
	}
	
	public void save(E entity)
	{
		entityManager.persist(entity);
	}
	
	public void delete(E entity)
	{
		entityManager.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> search(List<Filter> filters, OrderBy orderBy) 
	{
		String sql = getStringSearch(filters, orderBy);
		Query query = entityManager.createQuery(sql);
		
		if (CollectionUtils.isNotEmpty(filters))
		{
			for (Filter filter : filters)
			{
				query.setParameter(filter.getField(), filter.getValue());
			}
		}
		
		List<E> boResultList = query.getResultList();
		return boResultList;
	}
	
	/**************************************
	 * Utilities
	 **************************************/
	
	protected String getStringSearch(List<Filter> filters, OrderBy orderBy)
	{
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("from ");
		sqlBuilder.append(entityClass.getSimpleName()); 
		
		if (CollectionUtils.isNotEmpty(filters))
		{
			sqlBuilder.append(" where ");
			Iterator<Filter> iterator = filters.iterator();
			while (iterator.hasNext())
			{
				Filter filter = iterator.next();
				sqlBuilder.append(filter.getField());
				sqlBuilder.append(" = :");
				sqlBuilder.append(filter.getField());
				
				if (iterator.hasNext())
				{
					sqlBuilder.append(" and ");
				}
			}
		}
		
		if (orderBy != null)
		{
			sqlBuilder.append(" order by ");
			sqlBuilder.append(orderBy.getField());
		}

		return sqlBuilder.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected void init()
	{
		ParameterizedType classeParametrizada = (ParameterizedType) this.getClass()
			.getGenericSuperclass();

		Type tipo[] = classeParametrizada.getActualTypeArguments();
		entityClass = (Class<E>) tipo[1];
	}
	
	/*****************************************
	 * Getters and setters
	 *****************************************/

	/**
	 * @param entityManager the entityManager to set
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	/**
	 * @return the entityClass
	 */
	public Class<E> getEntityClass()
	{
		return entityClass;
	}
}
