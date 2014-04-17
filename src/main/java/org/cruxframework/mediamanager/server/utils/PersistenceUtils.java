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
package org.cruxframework.mediamanager.server.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class PersistenceUtils
{
	
	private static final Logger LOGGER = Logger.getLogger(PersistenceUtils.class.getName());
	
	public static void closeEntityManager(EntityManager entityManager)
	{
		try
		{
			if (entityManager != null && entityManager.isOpen())
			{
				entityManager.close();
			}
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "", e);
		}
	}
	
	public static void rollbackTransaction(EntityTransaction transaction)
	{
		try
		{
			if (transaction != null && transaction.isActive())
			{
				transaction.rollback();
			}
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "", e);
		}
	}
}
