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
import org.cruxframework.mediamanager.server.entity.Media;
import org.cruxframework.mediamanager.server.entity.dao.impl.ArtistDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.impl.CountryDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.impl.GenreDAOImpl;
import org.cruxframework.mediamanager.server.entity.dao.impl.MediaDAOImpl;
import org.cruxframework.mediamanager.server.reuse.service.AbstractRestService;
import org.cruxframework.mediamanager.server.utils.Filter;
import org.cruxframework.mediamanager.server.utils.Operator;
import org.cruxframework.mediamanager.server.utils.OrderBy;
import org.cruxframework.mediamanager.shared.dto.ArtistDTO;
import org.cruxframework.mediamanager.shared.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class description: REST implementation for {@link Artist} resource.
 * 
 * @author alexandre.costa
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RestService("artistService")
@Path("artists")
public class ArtistRestService extends AbstractRestService<ArtistDTO, Artist>
{
	private static final String NAME = "name";

	@Autowired
	private ArtistDAOImpl artistDAOImpl;

	@Autowired
	private CountryDAOImpl countryDAOImpl;

	@Autowired
	private GenreDAOImpl genreDAOImpl;

	@Autowired
	private MediaDAOImpl mediaDAOImpl;

	/**
	 * Search method.
	 * @param name artist name
	 * @return artists list.
	 * @throws RestException exception
	 */
	@GET
	public List<ArtistDTO> search(@QueryParam(NAME) String name) throws RestException
	{
		List<Filter> filters = getSearchFilters(name);
		return doSearch(filters);
	}

	/****************************************
	 * Overwritten methods
	 ****************************************/

	@Override
	protected void validateDelete(Artist object)
	{
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("artist", object));
		List<Media> medias = mediaDAOImpl.search(filters, null);
		if (medias.size() != 0)
		{
			throw new ValidationException();
		}
	}

	@Override
	protected void prepareEntity(Artist artist, ArtistDTO artistDTO)
	{
		artist.setName(artistDTO.getName());

		if (artistDTO.getCountry() != null)
		{
			Country country = countryDAOImpl.find(artistDTO.getCountry().getId());
			artist.setCountry(country);
		}

		if (artistDTO.getGenre() != null)
		{
			Genre genere = genreDAOImpl.find(artistDTO.getGenre().getId());
			artist.setGenre(genere);
		}
	}

	@Override
	protected List<OrderBy> orderBy()
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy(NAME));
		return orderings;
	}

	/****************************************
	 * Utilities
	 ****************************************/

	private static List<Filter> getSearchFilters(String name)
	{
		List<Filter> filters = new ArrayList<Filter>(0);
		if (name != null && name.trim().length() > 0)
		{
			Filter filter = new Filter(NAME, name);
			filter.setOperator(Operator.LIKE_FULL);
			filters.add(filter);
		}

		return filters;
	}

	/****************************************
	 * Getters and setters
	 ****************************************/

	/**
	 * @return the artistDAOImpl
	 */
	@Override
	public ArtistDAOImpl getDao()
	{
		return artistDAOImpl;
	}

	/**
	 * @param artistDAOImpl the artistDAOImpl to set
	 */
	public void setArtistDAO(ArtistDAOImpl artistDAOImpl)
	{
		this.artistDAOImpl = artistDAOImpl;
	}

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
	 * @param mediaDAOImpl the mediaDAOImpl to set
	 */
	public void setMediaDAO(MediaDAOImpl mediaDAOImpl)
	{
		this.mediaDAOImpl = mediaDAOImpl;
	}
}
