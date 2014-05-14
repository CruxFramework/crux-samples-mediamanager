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
package org.cruxframework.mediamanager.model.jpa.service;

import java.util.Calendar;

import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.dao.ArtistDao;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.service.impl.AbstractMediaService;
import org.cruxframework.mediamanager.model.jpa.entity.Artist;
import org.cruxframework.mediamanager.model.jpa.entity.Media;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractMediaServiceJpa extends AbstractMediaService
{
	@Override
	protected void prepareEntity(AbstractEntity<MediaDTO> iMedia, MediaDTO mediaDTO)
	{
		Media media = (Media) iMedia;
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
			AbstractEntity<ArtistDTO> artist = getArtistDao().find(mediaDTO.getArtist().getId());
			media.setArtist((Artist)artist);
		}
	}
	
	/***************************************
	 * Getters and setters
	 ***************************************/

	/**
	 * @return the artistDAO
	 */
	public abstract ArtistDao getArtistDao();

}
