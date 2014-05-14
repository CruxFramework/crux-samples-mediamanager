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
import org.cruxframework.mediamanager.core.client.dto.EditMediaDTO;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.dao.ArtistDao;
import org.cruxframework.mediamanager.core.dao.MediaDao;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.service.EditMediaRestService;
import org.cruxframework.mediamanager.core.utils.EntityUtils;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractEditMediaService implements EditMediaRestService
{
	public EditMediaDTO get(Integer id) throws RestException
	{
		MediaDTO mediaDTO = null;
		
		if (id != null)
		{
			AbstractEntity<MediaDTO> media = id == null ? null : getMediaDao().find(id);
			mediaDTO = media.getDTORepresentation();
		}
		
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		
		List<AbstractEntity<ArtistDTO>> artists = getArtistDao().search(null, orderings);
		EditMediaDTO editMediaDTO = new EditMediaDTO();
		editMediaDTO.setMedia(mediaDTO);
		editMediaDTO.setArtists(EntityUtils.convert(artists));
		return editMediaDTO;
	}
	
	/******************************************
	 * Getters and setters
	 *****************************************/
	
	
	/**
	 * @return the mediaDAO
	 */
	public abstract MediaDao getMediaDao();

	/**
	 * @return the artistDAO
	 */
	public abstract ArtistDao getArtistDao();

}
