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
package org.cruxframework.mediamanager.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.reuse.AbstractService;
import org.cruxframework.mediamanager.core.service.MediaRestService;
import org.cruxframework.mediamanager.core.utils.Filter;
import org.cruxframework.mediamanager.core.utils.Operator;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractMediaService extends AbstractService<MediaDTO> 
	implements MediaRestService
{
	public List<MediaDTO> search(MediaType type, String name, String person) 
		throws RestException
	{
		List<Filter> filters = getSearchFilters(type, name, person);
		return doSearch(filters);
	}
	
	/****************************************************
	 * Overwritten methods
	 ****************************************************/
	
	@Override
	protected List<OrderBy> orderBy()
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		return orderings;
	}
	
	/******************************************
	 * Utilities
	 ******************************************/
	
	private static List<Filter> getSearchFilters(MediaType type, String name, 
		String person)
	{
		List<Filter> filters = new ArrayList<Filter>(0);
		if (name != null && name.trim().length() > 0)
		{
			Filter filter = new Filter("name", name);
			filter.setOperator(Operator.LIKE_FULL);
			filters.add(filter);
		}
		
		if (person != null && person.trim().length() > 0)
		{
			Filter filter = new Filter("person", person);
			filter.setOperator(Operator.LIKE_FULL);
			filters.add(new Filter("person", person));
		}
		
		if (type != null)
		{
			filters.add(new Filter("type", type));
		}
		
		return filters;
	}
}
