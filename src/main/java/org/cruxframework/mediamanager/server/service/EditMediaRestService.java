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
import org.cruxframework.mediamanager.server.utils.EntityUtils;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.dto.EditMediaDTO;
import org.cruxframework.mediamanager.shared.dto.MediaDTO;
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
@RestService("editMediaService")
@Path("editmedia")
public class EditMediaRestService
{
	@Autowired
	private MediaDAOImpl mediaDAOImpl;
	
	@Autowired
	private ArtistDAOImpl artistDAOImpl;
	
	@GET
	@Path("get")
	public EditMediaDTO get(@QueryParam("id")Integer id) throws RestException
	{
		MediaDTO mediaDTO = null;
		
		if (id != null)
		{
			Media media = id == null ? null : mediaDAOImpl.find(id);
			mediaDTO = media.getDTORepresentation();
		}
		
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		
		List<Artist> artists = artistDAOImpl.search(null, orderings);
		EditMediaDTO editMediaDTO = new EditMediaDTO();
		editMediaDTO.setMedia(mediaDTO);
		editMediaDTO.setArtists(EntityUtils.convert(artists));
		return editMediaDTO;
	}
	
	/******************************************
	 * Getters and setters
	 *****************************************/
	
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
