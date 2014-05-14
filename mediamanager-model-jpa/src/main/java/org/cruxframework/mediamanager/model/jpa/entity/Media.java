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
package org.cruxframework.mediamanager.model.jpa.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.model.jpa.reuse.JpaEntity;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_MEDIA")
public class Media extends JpaEntity<MediaDTO> implements AbstractEntity<MediaDTO>
{
	private String name;
	private MediaType type;
	private Boolean borrowed = false;
	private String person;
	private Calendar date;
	private Artist artist;
	
	public Media()
	{
		
	}
	
	public Media(String name, MediaType type, Artist artist, Boolean borrowed)
	{
		this.name = name;
		this.type = type;
		this.artist = artist;
		this.borrowed = borrowed;
	}
	
	/**
	 * @return the name
	 */
	@Column(unique = true, nullable = false)
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
	@NotNull
	@Column(nullable = false)
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
	 * @return the borrowed
	 */
	@NotNull
	@Column(nullable = false)
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
	 * @return the date
	 */
	@Temporal(TemporalType.DATE)
	public Calendar getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date)
	{
		this.date = date;
	}
	
	/**
	 * @return the artist
	 */
	@ManyToOne(fetch = FetchType.LAZY)
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
	
	@Override
	@Transient
	public MediaDTO getDTORepresentation()
	{
		MediaDTO dto = new MediaDTO();
		dto.setId(getId());
		dto.setName(getName());
		dto.setType(getType());
		dto.setPerson(getPerson());
		dto.setBorrowed(getBorrowed());
		dto.setDate(getDate() == null ? null : getDate().getTime());
		dto.setArtist(getArtist().getDTORepresentation());
		return dto;
	}
}
