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
import org.cruxframework.crux.core.shared.rest.annotation.PathParam;
import org.cruxframework.mediamanager.server.entity.Artist;
import org.cruxframework.mediamanager.server.entity.Country;
import org.cruxframework.mediamanager.server.entity.Genre;
import org.cruxframework.mediamanager.server.entity.dao.impl.ArtistDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.impl.CountryDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.impl.GenreDAOImpl;
import org.cruxframework.mediamanager.server.utils.EntityUtils;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;
import org.cruxframework.mediamanager.shared.dto.EditArtistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class description: REST implementation for {@link EditArtistDTO}.
 * 
 * @author alexandre.costa
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RestService("editArtistService")
@Path("editartists")
public class EditArtistRestService
{
	@Autowired
	private CountryDAOImpl countryDAOImpl;

	@Autowired
	private GenreDAOImpl genreDAOImpl;

	@Autowired
	private ArtistDAOImpl artistDAOImpl;

	/**
	 * Get data for artist edition.
	 * @param id artist's id
	 * @return DTO object
	 * @throws RestException exception
	 */
	@GET
	@Path("{id}")
	public EditArtistDTO update(@PathParam("id") Integer id) throws RestException
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));

		List<Country> countries = countryDAOImpl.search(null, orderings);
		List<Genre> genres = genreDAOImpl.search(null, orderings);

		Artist artist = artistDAOImpl.find(id);
		ArtistDTO artistDTO = artist.getDTORepresentation();

		EditArtistDTO editArtistDTO = new EditArtistDTO();
		editArtistDTO.setArtist(artistDTO);
		editArtistDTO.setCountries(EntityUtils.convert(countries));
		editArtistDTO.setGenres(EntityUtils.convert(genres));
		return editArtistDTO;
	}
	
	/**
	 * Get data for artist edition.
	 * @return DTO object
	 * @throws RestException exception
	 */
	@GET
	public EditArtistDTO add() throws RestException
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));

		List<Country> countries = countryDAOImpl.search(null, orderings);
		List<Genre> genres = genreDAOImpl.search(null, orderings);
		ArtistDTO artistDTO = null;

		EditArtistDTO editArtistDTO = new EditArtistDTO();
		editArtistDTO.setArtist(new ArtistDTO());
		editArtistDTO.setCountries(EntityUtils.convert(countries));
		editArtistDTO.setGenres(EntityUtils.convert(genres));
		return editArtistDTO;
	}

	/******************************************
	 * Getters and setters
	 ******************************************/

	/**
	 * @param countryDAOImpl the countryDAOImpl to set
	 */
	public void setCountryDAO(CountryDAOImpl countryDAOImpl)
	{
		this.countryDAOImpl = countryDAOImpl;
	}

	/**
	 * @param genreDAOImpl the genreDAOImpl to set
	 */
	public void setGenreDAO(GenreDAOImpl genreDAOImpl)
	{
		this.genreDAOImpl = genreDAOImpl;
	}

	/**
	 * @param artistDAOImpl the artistDAOImpl to set
	 */
	public void setArtistDAO(ArtistDAOImpl artistDAOImpl)
	{
		this.artistDAOImpl = artistDAOImpl;
	}
}
