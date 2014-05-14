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

import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.core.dao.CountryDao;
import org.cruxframework.mediamanager.core.dao.GenreDao;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.service.impl.AbstractArtistService;
import org.cruxframework.mediamanager.model.jpa.entity.Artist;
import org.cruxframework.mediamanager.model.jpa.entity.Country;
import org.cruxframework.mediamanager.model.jpa.entity.Genre;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractArtistServiceJpa extends AbstractArtistService
{
	protected void prepareEntity(AbstractEntity<ArtistDTO> iArtist, ArtistDTO artistDTO)
	{
		Artist artist = (Artist) iArtist;
		artist.setName(artistDTO.getName());
		
		if (artistDTO.getCountry() != null)
		{
			AbstractEntity<CountryDTO> country = getCountryDao().find(artistDTO.getCountry().getId());
			artist.setCountry((Country) country);
		}
		
		if (artistDTO.getGenre() != null)
		{
			AbstractEntity<GenreDTO> genere = getGenreDao().find(artistDTO.getGenre().getId());
			artist.setGenre((Genre) genere);
		}
	}
	
	/**
	 * @return the countryDAO
	 */
	public abstract CountryDao getCountryDao();

	/**
	 * @return the genreDAO
	 */
	public abstract GenreDao getGenreDao();

}
