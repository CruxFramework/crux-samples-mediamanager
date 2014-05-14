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
package org.cruxframework.mediamanager.core.client.dto;

import java.util.Date;

import org.cruxframework.crux.core.client.dto.DataObject;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

/**
 * Class description: 
 * @author alexandre.costa
 */
@DataObject("media")
public class MediaDTO extends AbstractDTO
{
	private String name;
	private MediaType type;
	private String person;
	private Boolean borrowed = false;
	private Date date;
	private ArtistDTO artist;
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public MediaType getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MediaType type)
	{
		this.type = type;
	}

	/**
	 * @return the person
	 */
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
	 * @return the borrowed
	 */
	public Boolean getBorrowed()
	{
		return borrowed;
	}

	/**
	 * @param borrowed the borrowed to set
	 */
	public void setBorrowed(Boolean borrowed)
	{
		this.borrowed = borrowed;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
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
	public ArtistDTO getArtist()
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(ArtistDTO artist)
	{
		this.artist = artist;
	}
}
