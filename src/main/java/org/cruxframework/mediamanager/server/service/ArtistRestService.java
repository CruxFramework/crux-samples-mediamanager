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
import org.cruxframework.mediamanager.server.entity.dao.ArtistDAO;
import org.cruxframework.mediamanager.server.entity.dao.CountryDAO;
import org.cruxframework.mediamanager.server.entity.dao.GenreDAO;
import org.cruxframework.mediamanager.server.entity.dao.MediaDAO;
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
 * Class description: 
 * @author alexandre.costa
 */
@Component
@Scope(value =  WebApplicationContext.SCOPE_REQUEST)
@RestService("artistService")
@Path("artist")
public class ArtistRestService extends AbstractRestService<ArtistDTO, Artist>
{
	@Autowired
	private ArtistDAO artistDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private GenreDAO genreDAO;
	
	@Autowired
	private MediaDAO mediaDAO;
	
	@GET
	@Path("search")
	public List<ArtistDTO> search(@QueryParam("name") String name) 
		throws RestException
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
		List<Media> medias = mediaDAO.search(filters, null);
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
			Country country = countryDAO.find(artistDTO.getCountry().getId());
			artist.setCountry(country);
		}
		
		if (artistDTO.getGenre() != null)
		{
			Genre genere = genreDAO.find(artistDTO.getGenre().getId());
			artist.setGenre(genere);
		}
	}
	
	@Override
	protected List<OrderBy> orderBy()
	{
		List<OrderBy> orderings = new ArrayList<OrderBy>(1);
		orderings.add(new OrderBy("name"));
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
			Filter filter = new Filter("name", name);
			filter.setOperator(Operator.LIKE_FULL);
			filters.add(filter);
		}
		
		return filters;
	}
	
	/****************************************
	 * Getters and setters
	 ****************************************/

	/**
	 * @return the artistDAO
	 */
	@Override
	public ArtistDAO getDao()
	{
		return artistDAO;
	}

	/**
	 * @param artistDAO the artistDAO to set
	 */
	public void setArtistDAO(ArtistDAO artistDAO)
	{
		this.artistDAO = artistDAO;
	}

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
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO)
	{
		this.mediaDAO = mediaDAO;
	}
}
