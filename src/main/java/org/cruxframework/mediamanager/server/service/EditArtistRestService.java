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
package org.cruxframework.mediamanager.server.service;

import java.util.ArrayList;
import java.util.List;

import org.cruxframework.crux.core.server.rest.annotation.RestService;
import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.crux.core.shared.rest.annotation.GET;
import org.cruxframework.crux.core.shared.rest.annotation.Path;
import org.cruxframework.crux.core.shared.rest.annotation.QueryParam;
import org.cruxframework.mediamanager.server.entity.Artist;
import org.cruxframework.mediamanager.server.entity.Country;
import org.cruxframework.mediamanager.server.entity.Genre;
import org.cruxframework.mediamanager.server.entity.dao.ArtistDAO;
import org.cruxframework.mediamanager.server.entity.dao.CountryDAO;
import org.cruxframework.mediamanager.server.entity.dao.GenreDAO;
import org.cruxframework.mediamanager.server.utils.EntityUtils;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;
import org.cruxframework.mediamanager.shared.dto.EditArtistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Component
@Scope(value =  WebApplicationContext.SCOPE_REQUEST)
@RestService("editArtistService")
@Path("editartist")
public class EditArtistRestService
{
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private GenreDAO genreDAO;
	
	@Autowired
	private ArtistDAO artistDAO;
	
	@GET
	@Path("get")
	public EditArtistDTO get(@QueryParam("id") Integer id) throws RestException
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
		
		List<Country> countries = countryDAO.search(null, orderings);
		List<Genre> genres = genreDAO.search(null, orderings);
		ArtistDTO artistDTO = null;
		
		if (id != null)
		{
			Artist artist = artistDAO.find(id);
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
	 * @param countryDAO the countryDAO to set
	 */
	public void setCountryDAO(CountryDAO countryDAO)
	{
		this.countryDAO = countryDAO;
	}

	/**
	 * @param genreDAO the genreDAO to set
	 */
	public void setGenreDAO(GenreDAO genreDAO)
	{
		this.genreDAO = genreDAO;
	}

	/**
	 * @param artistDAO the artistDAO to set
	 */
	public void setArtistDAO(ArtistDAO artistDAO)
	{
		this.artistDAO = artistDAO;
	}

}
