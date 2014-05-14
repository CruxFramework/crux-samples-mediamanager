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

import javax.transaction.Transactional;

import org.cruxframework.crux.core.shared.rest.RestException;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.core.dao.ArtistDao;
import org.cruxframework.mediamanager.core.dao.CountryDao;
import org.cruxframework.mediamanager.core.dao.GenreDao;
import org.cruxframework.mediamanager.core.dao.MediaDao;
import org.cruxframework.mediamanager.model.jpa.service.AbstractArtistServiceJpa;
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
public class ArtistServiceSpringImpl extends AbstractArtistServiceJpa
{
	@Autowired
	private ArtistDao artistDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private GenreDao genreDao;
	
	@Autowired
	private MediaDao mediaDao;
	
	@Override
	@Transactional
	public EditOperation update(Integer id, ArtistDTO dto) throws RestException
	{
		return super.update(id, dto);
	}
	
	@Override
	@Transactional
	public EditOperation insert(ArtistDTO dto) throws RestException
	{
		return super.insert(dto);
	}
	
	@Override
	@Transactional
	public EditOperation delete(ArtistDTO dto) throws RestException
	{
		return super.delete(dto);
	}
	
	/***********************************************
	 * Getters and setters
	 ***********************************************/
	
	/**
	 * @return the artistDao
	 */
	@Override
	public ArtistDao getDao()
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
	 * @return the mediaDao
	 */
	@Override
	public MediaDao getMediaDao()
	{
		return mediaDao;
	}

	/**
	 * @param mediaDao the mediaDao to set
	 */
	public void setMediaDao(MediaDao mediaDao)
	{
		this.mediaDao = mediaDao;
	}
}
