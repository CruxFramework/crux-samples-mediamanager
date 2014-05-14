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
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.core.dao.ArtistDao;
import org.cruxframework.mediamanager.core.dao.CountryDao;
import org.cruxframework.mediamanager.core.dao.GenreDao;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.core.service.EditArtistRestService;
import org.cruxframework.mediamanager.core.utils.EntityUtils;
import org.cruxframework.mediamanager.core.utils.OrderBy;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class AbstractEditArtistService implements EditArtistRestService
{
	public EditArtistDTO get(Integer id) throws RestException
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		
		List<AbstractEntity<CountryDTO>> countries = getCountryDao().search(null, orderings);
		List<AbstractEntity<GenreDTO>> genres = getGenreDao().search(null, orderings);
		ArtistDTO artistDTO = null;
		
		if (id != null)
		{
			AbstractEntity<ArtistDTO> artist = getArtistDao().find(id);
			artistDTO = artist.getDTORepresentation();
		}
		
		EditArtistDTO editArtistDTO = new EditArtistDTO();
		editArtistDTO.setArtist(artistDTO);
		editArtistDTO.setCountries(EntityUtils.convert(countries));
		editArtistDTO.setGenres(EntityUtils.convert(genres));
		return editArtistDTO;
	}
	
	/******************************************
	 * Getters and setters
	 ******************************************/

	/**
	 * @return the countryDAO
	 */
	public abstract CountryDao getCountryDao();

	/**
	 * @return the genreDAO
	 */
	public abstract GenreDao getGenreDao();

	/**
	 * @return the artistDAO
	 */
	public abstract ArtistDao getArtistDao();

}
