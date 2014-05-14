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

import java.util.List;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class EditArtistDTO
{
	private ArtistDTO artist;
	
	private List<CountryDTO> countries;
	
	private List<GenreDTO> genres;

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

	/**
	 * @return the countries
	 */
	public List<CountryDTO> getCountries()
	{
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<CountryDTO> countries)
	{
		this.countries = countries;
	}

	/**
	 * @return the genres
	 */
	public List<GenreDTO> getGenres()
	{
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<GenreDTO> genres)
	{
		this.genres = genres;
	}
}
