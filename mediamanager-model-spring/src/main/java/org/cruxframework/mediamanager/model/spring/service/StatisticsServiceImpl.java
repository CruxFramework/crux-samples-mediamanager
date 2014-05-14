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
package org.cruxframework.mediamanager.model.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.cruxframework.mediamanager.model.jpa.service.AbstractStatisticsService;
import org.cruxframework.mediamanager.model.spring.utils.SpringUtils;

/**
 * Class description: RPC implementation for {@link StatisticsServiceImpl} 
 * interface.
 * 
 * @author alexandre.costa
 */
public class StatisticsServiceImpl extends AbstractStatisticsService
{
	@Override
	public EntityManager getEntityManager()
	{
		EntityManagerFactory factory = SpringUtils.get().getBean(
			"entityManagerFactory", EntityManagerFactory.class);
		
		return factory.createEntityManager();
	}
}
