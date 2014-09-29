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

/**
 * Class description: Order by clause.
 * 
 * @author alexandre.costa
 */
public class OrderBy
{
	private final String field;
	private final boolean ascending;

	/**
	 * Constructor.
	 * @param field field name.
	 */
	public OrderBy(String field)
	{
		this.field = field;
		this.ascending = true;
	}

	/**
	 * Constructor.
	 * @param field field name
	 * @param ascending indicates if order by clause is ascending
	 */
	public OrderBy(String field, boolean ascending)
	{
		this.field = field;
		this.ascending = ascending;
	}

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * @return the ascending
	 */
	public boolean isAscending()
	{
		return ascending;
	}
}
