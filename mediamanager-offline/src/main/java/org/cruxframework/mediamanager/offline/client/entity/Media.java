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


import java.util.Date;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.crux.core.client.db.annotation.Store.Indexed;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;


/**
 * Class description: Defines an entity Media with the properties that must be stored in the database.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
@Store(Media.STORE_NAME)
public class Media extends OfflineEntity<MediaDTO> 
{
	public static final String STORE_NAME = "media";
	private String type;
	private int borrowed = 0;
	private String person;
	private Date date;
	private Artist artist;
	private String nameMedia;
	
	/**********************************
	 * Methods Abstract
	 **********************************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	
	@Override
	public MediaDTO getDTORepresentation()
	{	
		MediaDTO dto = new MediaDTO();
		dto.setId(getId());
		dto.setName(getNameMedia());
		try
		{
			MediaType mt = MediaType.valueOf(getType());
			dto.setType(mt);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		dto.setPerson(getPerson());
		dto.setBorrowed(getBorrowed() == 1 ? true : false);
		dto.setDate(getDate() == null ? null : getDate());
		dto.setArtist(getArtist().getDTORepresentation());
		return dto;
	}
	
	/************************
	 * GETTERS and SETTERS
	 ***********************/	
	/**
	 * @return the borrowed
	 */
	@Indexed
	public int getBorrowed()
	{
		return borrowed;
	}

	/**
	 * @param borrowed the borrowed to set
	 */
	public void setBorrowed(int borrowed)
	{
		this.borrowed = borrowed;
	}

	/**
	 * @return the person
	 */
	@Indexed
	public String getPerson()
	{
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person)
	{
		this.person = person;
	}

	/**
	 * @return the date
	 */
	@Indexed
	public Date getDate()
	{
		return this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist()
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist)
	{
		this.artist = artist;
	}

	/**
	 * @return the type
	 */
	@Indexed
	public String getType()
	{
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MediaType type)
	{
		String t = type.name();
		this.type = t;
	}
	
	/**
	 * @return the name
	 */
	@Indexed()
	public String getNameMedia() 
	{
		return nameMedia;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setNameMedia(String name) 
	{
		this.nameMedia = name;
	}

}
