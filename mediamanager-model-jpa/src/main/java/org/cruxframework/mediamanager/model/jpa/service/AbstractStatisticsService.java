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
package org.cruxframework.mediamanager.model.jpa.service;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cruxframework.mediamanager.core.client.dto.StatisticsDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.client.service.StatisticsService;
import org.cruxframework.mediamanager.model.jpa.utils.PersistenceUtils;

/**
 * Class description: RPC implementation for {@link StatisticsService} 
 * interface.
 * 
 * @author alexandre.costa
 */
public abstract class AbstractStatisticsService implements StatisticsService
{
	private static final Logger LOGGER = Logger.getLogger(AbstractStatisticsService.class.getName());
	
	private static final String QUERY_COUNT = 
		"SELECT count(m) from Media m where m.type = :type";
	
	private static final String QUERY_COUNT_BORROWED = 
		"SELECT count(m) from Media m where m.type = :type and m.borrowed = true";
	
	private static final String QUERY_COUNT_FORGOTTEN = 
		"SELECT count(m) from Media m where m.type = :type and m.borrowed = true and m.date < :date";
	
	public abstract EntityManager getEntityManager();
	
	public StatisticsDTO getStatistics() 
	{
		StatisticsDTO dto = new StatisticsDTO();
		EntityManager entityManager = null;
		try
		{
			entityManager = getEntityManager();

			countCDs(dto, entityManager);
			countDVDs(dto, entityManager);
			
			countBorrowedCDs(dto, entityManager);
			countBorrowedDVDs(dto, entityManager);
			
			countForgottenCDs(dto, entityManager);
			countForgottenDVDs(dto, entityManager);
			
		} catch (Exception exception)
		{
			LOGGER.log(Level.SEVERE, "Unexpected error.", exception);
			throw new RuntimeException(exception);
		} finally
		{
			PersistenceUtils.closeEntityManager(entityManager);
		}
		
		return dto;
	}
	
	/***********************************************
	 * utilities
	 ***********************************************/
	
	private static void countCDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setTotalCDs(countTotal(MediaType.CD, entityManager));
	}
	
	private static void countDVDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setTotalDVDs(countTotal(MediaType.DVD, entityManager));
	}
	
	private static void countBorrowedCDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setBorrowedCDs(countBorrowed(MediaType.CD, entityManager));
	}
	
	private static void countBorrowedDVDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setBorrowedDVDs(countBorrowed(MediaType.DVD, entityManager));
	}
	
	private static void countForgottenCDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setForgottenCDs(countForgotten(MediaType.CD, entityManager));
	}
	
	private static void countForgottenDVDs(StatisticsDTO dto, EntityManager entityManager)
	{
		dto.setForgottenDVDs(countForgotten(MediaType.DVD, entityManager));
	}
	
	/***********************************************
	 * utilities
	 ***********************************************/
	
	private static int countTotal(MediaType type, EntityManager entityManager)
	{
		Query query = entityManager.createQuery(QUERY_COUNT);
		query.setParameter("type", type);
		Long count = (Long)query.getSingleResult();
		return count.intValue();
	}
	
	private static int countBorrowed(MediaType type, EntityManager entityManager)
	{
		Query query = entityManager.createQuery(QUERY_COUNT_BORROWED);
		query.setParameter("type", type);
		Long count = (Long)query.getSingleResult();
		return count.intValue();
	}
	
	private static int countForgotten(MediaType type, EntityManager entityManager)
	{
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, -30);
		
		Query query = entityManager.createQuery(QUERY_COUNT_FORGOTTEN);
		query.setParameter("type", type);
		query.setParameter("date", date);
		Long count = (Long)query.getSingleResult();
		return count.intValue();
	}
}
