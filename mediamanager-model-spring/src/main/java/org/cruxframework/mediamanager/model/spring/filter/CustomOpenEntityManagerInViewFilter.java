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
package org.cruxframework.mediamanager.model.spring.filter;

import javax.persistence.EntityManagerFactory;

import org.cruxframework.mediamanager.model.spring.utils.SpringUtils;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.util.StringUtils;

/**
 * Class description:
 * @author alexandre.costa
 */
public class CustomOpenEntityManagerInViewFilter extends
	OpenEntityManagerInViewFilter
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntityManagerFactory lookupEntityManagerFactory()
	{
		String emfBeanName = getEntityManagerFactoryBeanName();
		String puName = getPersistenceUnitName();
		if (StringUtils.hasLength(emfBeanName))
		{
			return SpringUtils.get().getBean(emfBeanName, EntityManagerFactory.class);
		} else if (!StringUtils.hasLength(puName)
			&& SpringUtils.get().containsBean(
				DEFAULT_ENTITY_MANAGER_FACTORY_BEAN_NAME))
		{
			return SpringUtils.get().getBean(
				DEFAULT_ENTITY_MANAGER_FACTORY_BEAN_NAME, EntityManagerFactory.class);
		} else
		{
			// Includes fallback search for single EntityManagerFactory bean by type.
			return EntityManagerFactoryUtils.findEntityManagerFactory(SpringUtils
				.get().getContext(), puName);
		}
	}
}
