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
package org.cruxframework.mediamanager.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cruxframework.mediamanager.server.reuse.entity.AbstractEntity;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;

/**
 * Class description: Implements artist entity.
 * 
 * @author alexandre.costa
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_ARTIST")
public class Artist extends AbstractEntity<ArtistDTO>
{
	private String name;
	private Genre genre;
	private Country country;

	/**
	 * Default constructor.
	 */
	public Artist()
	{

	}

	/**
	 * Constructor.
	 * @param name artist name
	 * @param genre artist genre
	 * @param country artist country
	 */
	public Artist(String name, Genre genre, Country country)
	{
		this.name = name;
		this.genre = genre;
		this.country = country;
	}

	/**
	 * @return the genre
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
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

	/**
	 * @return the country
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
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
	 * @return the name
	 */
	@Column(nullable = false)
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

	@Override
	@Transient
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
}
