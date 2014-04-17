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
package org.cruxframework.mediamanager.server.service;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Class description: 
 * @author alexandre.costa
 */
public final class SpringUtils
{
	private static SpringUtils instance;

	private final AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	private final AnnotationConfigWebApplicationContext context;

	private SpringUtils()
	{
		context = new AnnotationConfigWebApplicationContext();
		context.register(PersistenceJPAConfigXml.class);
		context.refresh();
		autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
	}

	/**
	 *
	 * @return
	 */
	public static SpringUtils get()
	{
		if (instance == null)
		{
			initialize();
		}
		return instance;
	}

	private static synchronized void initialize()
	{
		if (instance == null)
		{
			instance = new SpringUtils();
		}
	}
	
	public boolean containsBean(String beanName)
	{
		return autowireCapableBeanFactory.containsBean(beanName);
	}

	public <T> T getBean(Class<T> clazz)
	{
		return autowireCapableBeanFactory.getBean(clazz);
	}
	
	public <T> T getBean(String name, Class<T> requiredType)
	{
		return autowireCapableBeanFactory.getBean(name, requiredType);
	}

	public <T> T createBean(Class<T> clazz)
	{
		return autowireCapableBeanFactory.createBean(clazz);
	}

	public void autowireBean(Object t)
	{
		autowireCapableBeanFactory.autowireBean(t);
	}

	/**
	 * @return the context
	 */
	public AnnotationConfigWebApplicationContext getContext()
	{
		return context;
	}
}
