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
package org.cruxframework.mediamanager.shared.dto;

import org.cruxframework.crux.core.client.dto.DataObject;
import org.cruxframework.mediamanager.server.entity.Artist;
import org.cruxframework.mediamanager.shared.reuse.dto.AbstractDTO;

/**
 * Class description: DTO class for {@link Artist} entity.
 * 
 * @author alexandre.costa
 */
@DataObject("artist")
public class ArtistDTO extends AbstractDTO
{
	private String name;
	private GenreDTO genre;
	private CountryDTO country;

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
	 * @return the genre
	 */
	public GenreDTO getGenre()
	{
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(GenreDTO genre)
	{
		this.genre = genre;
	}

	/**
	 * @return the country
	 */
	public CountryDTO getCountry()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(CountryDTO country)
	{
		this.country = country;
	}
}
