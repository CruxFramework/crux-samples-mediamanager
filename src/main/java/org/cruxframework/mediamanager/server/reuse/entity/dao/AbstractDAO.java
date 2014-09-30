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
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;
import org.cruxframework.mediamanager.server.utils.Filter;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.server.utils.QueryBuilder;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Class description: Data Access Object pattern.
 * 
 * @author alexandre.costa
 * 
 * @param <DTO> DTO class
 * @param <E> entity class
 */
public abstract class AbstractDAO<DTO extends AbstractDTO, E extends AbstractEntity<DTO>>
{
	private EntityManager entityManager;

	/* Abstract BO class */
	private Class<E> entityClass;

	{
		init();
	}

	/**
	 * Finds an entity by id.
	 * @param id entity id
	 * @return entity whose id is equals 
	 */
	public E find(Integer id)
	{
		E entity = entityManager.find(entityClass, id);
		return entity;
	}

	/**
	 * Saves an entity in the database.
	 * @param entity entity to be saved.
	 */
	public void save(E entity)
	{
		entityManager.persist(entity);
	}

	/**
	 * Removes an entity from the database.
	 * @param entity entity to be removed.
	 */
	public void delete(E entity)
	{
		entityManager.remove(entity);
	}

	/**
	 * Search method.
	 * 
	 * @param filters search filters
	 * @param orderings ordering parameters
	 * @return entities list
	 */
	public List<E> search(List<Filter> filters, List<OrderBy> orderings)
	{
		return search(filters, orderings, null, null);
	}

	/**
	 * Search method.
	 * 
	 * @param filters search filters
	 * @param orderings ordering parameters
	 * @param firstResult first result
	 * @param pageSize page size
	 * @return entities list
	 */
	@SuppressWarnings("unchecked")
	public List<E> search(List<Filter> filters, List<OrderBy> orderings, Integer firstResult, Integer pageSize)
	{
		QueryBuilder queryBuilder = new QueryBuilder(entityClass);
		queryBuilder.addFilters(filters);
		queryBuilder.addOrderings(orderings);

		String sql = queryBuilder.buildJPAQuery();
		Map<String, Object> valuesMap = queryBuilder.getValuesMap();

		Query query = buildJPAQuery(sql, valuesMap, firstResult, pageSize);
		List<E> resultList = query.getResultList();
		return resultList;
	}

	/**************************************
	 * Utilities
	 **************************************/

	private Query buildJPAQuery(String sql, Map<String, Object> valuesMap, Integer firstResult, Integer pageSize)
	{
		Query query = entityManager.createQuery(sql);
		Set<String> keys = valuesMap.keySet();
		for (String chave : keys)
		{
			query.setParameter(chave, valuesMap.get(chave));
		}
		
		if (firstResult != null && pageSize != null)
		{
			query.setFirstResult(firstResult);
			query.setMaxResults(pageSize);
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	protected void init()
	{
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type types[] = parameterizedType.getActualTypeArguments();
		entityClass = (Class<E>) types[1];
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
