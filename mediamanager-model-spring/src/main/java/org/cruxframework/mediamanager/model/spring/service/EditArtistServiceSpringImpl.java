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
package org.cruxframework.mediamanager.model.spring.service;

import org.cruxframework.mediamanager.core.dao.ArtistDao;
import org.cruxframework.mediamanager.core.dao.CountryDao;
import org.cruxframework.mediamanager.core.dao.GenreDao;
import org.cruxframework.mediamanager.core.service.impl.AbstractEditArtistService;
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
public class EditArtistServiceSpringImpl extends AbstractEditArtistService
{
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private GenreDao genreDao;
	
	@Autowired
	private ArtistDao artistDao;
	
	
	/******************************************
	 * Getters and setters
	 ******************************************/

	/**
	 * @return the countryDao
	 */
	@Override
	public CountryDao getCountryDao()
	{
		return countryDao;
	}
	
	/**
	 * @param countryDao the countryDao to set
	 */
	public void setCountryDao(CountryDao countryDao)
	{
		this.countryDao = countryDao;
	}
	
	/**
	 * @return the genreDao
	 */
	@Override
	public GenreDao getGenreDao()
	{
		return genreDao;
	}

	/**
	 * @param genreDao the genreDao to set
	 */
	public void setGenreDao(GenreDao genreDao)
	{
		this.genreDao = genreDao;
	}
	
	/**
	 * @return the artistDao
	 */
	@Override
	public ArtistDao getArtistDao()
	{
		return artistDao;
	}

	/**
	 * @param artistDao the artistDao to set
	 */
	public void setArtistDao(ArtistDao artistDao)
	{
		this.artistDao = artistDao;
	}
}
