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
package org.cruxframework.mediamanager.test.util;

import org.cruxframework.mediamanager.server.service.PersistenceJPAConfigXml;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Class description: Manages spring configurations.
 * 
 * @author alexandre.costa
 */
public final class SpringUtilsTest
{
	private static SpringUtilsTest instance;

	private final AutowireCapableBeanFactory autowireCapableBeanFactory;

	private final AnnotationConfigApplicationContext context;

	private SpringUtilsTest()
	{
		context = new AnnotationConfigApplicationContext();
		context.register(PersistenceJPAConfigXml.class);
		context.refresh();
		autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
	}

	/**
	 * Get unique instance.
	 *
	 * @return unique instance.
	 */
	public static SpringUtilsTest get()
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
			instance = new SpringUtilsTest();
		}
	}

	/**
	 * Delegate method.
	 * 
	 * @see {@link BeanFactory#containsBean(String)}.
	 * 
	 * @param beanName the name of the bean to query
	 * @return whether a bean with the given name is present
	 */
	public boolean containsBean(String beanName)
	{
		return autowireCapableBeanFactory.containsBean(beanName);
	}

	/**
	 * Delegate method.
	 *  
	 * @see {@link BeanFactory#getBean(Class)}.
	 * 
	 * @param clazz type the bean must match; can be an interface or superclass.
	 * @param <T> the type of the class modeled by {@link Class}.
	 * @return an instance of the single bean matching the required type
	 */
	public <T> T getBean(Class<T> clazz)
	{
		return autowireCapableBeanFactory.getBean(clazz);
	}

	/**
	 * Delegate method.
	 * 
	 * @see {@link BeanFactory#getBean(String, Class)}.
	 * 
	 * @param name he name of the bean to retrieve
	 * @param requiredType type the bean must match.
	 * @param <T> the type of the class modeled by {@link Class}.
	 * @return an instance of the bean
	 */
	public <T> T getBean(String name, Class<T> requiredType)
	{
		return autowireCapableBeanFactory.getBean(name, requiredType);
	}

	/**
	 * Delegate method.
	 * 
	 * @see {@link AutowireCapableBeanFactory#createBean(Class)}
	 * 
	 * @param clazz the class of the bean to create
	 * @param <T> the type of the class modeled by {@link Class}.
	 * @return the new bean instance
	 */
	public <T> T createBean(Class<T> clazz)
	{
		return autowireCapableBeanFactory.createBean(clazz);
	}

	/**
	 * Delegate method.
	 * 
	 * @see {@link AutowireCapableBeanFactory#autowireBean(Object)}
	 * 
	 * @param existingBean he existing bean instance
	 */
	public void autowireBean(Object existingBean)
	{
		autowireCapableBeanFactory.autowireBean(existingBean);
	}

	/**
	 * @return the context
	 */
	public AnnotationConfigApplicationContext getContext()
	{
		return context;
	}
}
