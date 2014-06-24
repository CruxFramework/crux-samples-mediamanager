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
package org.cruxframework.mediamanager.offline.client.entity;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;


/**
 * Class description: Defines an entity Artist with the properties that must be stored in the database.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
@Store(Artist.STORE_NAME)
public class Artist extends OfflineEntity<ArtistDTO> 
{
	public static final String STORE_NAME = "artist";
	private Country country;
	private Genre genre;
		
	/**********************************
	 * Methods Abstract
	 **********************************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	
	@Override
	public ArtistDTO getDTORepresentation()
	{
		ArtistDTO dto = new ArtistDTO();
		dto.setId(getId());
		dto.setName(getName());
		
		if (genre != null)
		{
			dto.setGenre(genre.getDTORepresentation());
		}
		
		if (country != null)
		{
			dto.setCountry(country.getDTORepresentation());
		}
		
		return dto;
	}
	
	/************************
	 * GETTERS and SETTERS
	 ***********************/	
	/**
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}
	
	/**
	 * @return the genre
	 */
	public Genre getGenre()
	{
		return genre;
	}
	
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}
}
