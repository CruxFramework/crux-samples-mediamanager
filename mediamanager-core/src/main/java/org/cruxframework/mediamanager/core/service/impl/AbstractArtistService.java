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
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.dao.MediaDao;
import org.cruxframework.mediamanager.core.exception.ValidationException;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.reuse.AbstractService;
import org.cruxframework.mediamanager.core.service.ArtistRestService;
import org.cruxframework.mediamanager.core.utils.Filter;
import org.cruxframework.mediamanager.core.utils.Operator;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractArtistService 
	extends AbstractService<ArtistDTO> implements ArtistRestService
{
	public List<ArtistDTO> search(String name) 
		throws RestException
	{
		List<Filter> filters = getSearchFilters(name);
		return doSearch(filters);
	}
	
	/****************************************
	 * Overwritten methods
	 ****************************************/
	@Override
	protected void validateDelete(AbstractEntity<ArtistDTO> object)
	{
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("artist", object));
		List<AbstractEntity<MediaDTO>> medias = getMediaDao().search(filters, null);
		if (medias.size() != 0)
		{
			throw new ValidationException();
		}
	}
	
	@Override
	protected List<OrderBy> orderBy()
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		return orderings;
	}
	
	/****************************************
	 * Utilities
	 ****************************************/
	
	private static List<Filter> getSearchFilters(String name)
	{
		List<Filter> filters = new ArrayList<Filter>(0);
		if (name != null && name.trim().length() > 0)
		{
			Filter filter = new Filter("name", name);
			filter.setOperator(Operator.LIKE_FULL);
			filters.add(filter);
		}
		
		return filters;
	}
	
	/****************************************
	 * Getters and setters
	 ****************************************/

	/**
	 * @return the mediaDAO
	 */
	public abstract MediaDao getMediaDao();
}
