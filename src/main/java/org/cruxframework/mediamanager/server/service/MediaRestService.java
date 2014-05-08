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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.cruxframework.crux.core.server.rest.annotation.RestService;
import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.QueryParam;
import org.cruxframework.mediamanager.server.entity.Artist;
import org.cruxframework.mediamanager.server.entity.Media;
import org.cruxframework.mediamanager.server.entity.dao.ArtistDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.MediaDAOImpl;
import org.cruxframework.mediamanager.server.reuse.service.AbstractRestService;
import org.cruxframework.mediamanager.server.utils.Filter;
import org.cruxframework.mediamanager.server.utils.Operator;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.dto.MediaDTO;
import org.cruxframework.mediamanager.shared.enums.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Component
@Scope(value =  WebApplicationContext.SCOPE_REQUEST)
@RestService("mediaService")
@Path("media")
public class MediaRestService extends AbstractRestService<MediaDTO, Media>
{
	@Autowired
	private MediaDAOImpl mediaDAOImpl;
	
	@Autowired
	private ArtistDAOImpl artistDAOImpl;
	
	@GET
	@Path("search")
	public List<MediaDTO> search(
		@QueryParam("type") MediaType type, 
		@QueryParam("name") String name, 
		@QueryParam("person")String person) throws RestException
	{
		List<Filter> filters = getSearchFilters(type, name, person);
		return doSearch(filters);
	}
	
	/****************************************************
	 * Overwritten methods
	 ****************************************************/
	
	@Override
	protected void prepareEntity(Media media, MediaDTO mediaDTO)
	{
		media.setBorrowed(mediaDTO.getBorrowed());
		media.setPerson(mediaDTO.getPerson());
		media.setName(mediaDTO.getName());
		media.setType(mediaDTO.getType());
		
		Calendar calendar = null;
		
		if (mediaDTO.getDate() != null)
		{
			calendar = Calendar.getInstance();			
			calendar.setTime(mediaDTO.getDate());
		}
		
		media.setDate(calendar);
		
		if (mediaDTO.getArtist() != null)
		{
			Artist artist = artistDAOImpl.find(mediaDTO.getArtist().getId());
			media.setArtist(artist);
		}
	}
	
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
	
	/***************************************
	 * Getters and setters
	 ***************************************/

	/**
	 * @return the mediaDAOImpl
	 */
	@Override
	public MediaDAOImpl getDao()
	{
		return mediaDAOImpl;
	}

	/**
	 * @param mediaDAOImpl the mediaDAOImpl to set
	 */
	public void setMediaDAO(MediaDAOImpl mediaDAOImpl)
	{
		this.mediaDAOImpl = mediaDAOImpl;
	}

	/**
	 * @param artistDAOImpl the artistDAOImpl to set
	 */
	public void setArtistDAO(ArtistDAOImpl artistDAOImpl)
	{
		this.artistDAOImpl = artistDAOImpl;
	}
}
