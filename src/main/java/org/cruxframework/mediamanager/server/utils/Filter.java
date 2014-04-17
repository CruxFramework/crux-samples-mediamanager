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

import java.io.Serializable;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class Filter implements Serializable
{
	private static final long serialVersionUID = -8102908510693313633L;

	private String field;
	private Object value;

	public Filter()
	{
	}
	
	public Filter(String field, Object value)
	{
		this.field = field;
		this.value = value;
	}

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field)
	{
		this.field = field;
	}

	/**
	 * @return the value
	 */
	public Object getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value)
	{
		this.value = value;
	}
}
