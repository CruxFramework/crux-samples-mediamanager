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
public class EditMediaDTO
{
	private MediaDTO media;
	
	private List<ArtistDTO> artists;

	/**
	 * @return the media
	 */
	public MediaDTO getMedia()
	{
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(MediaDTO media)
	{
		this.media = media;
	}

	/**
	 * @return the artists
	 */
	public List<ArtistDTO> getArtists()
	{
		return artists;
	}

	/**
	 * @param artists the artists to set
	 */
	public void setArtists(List<ArtistDTO> artists)
	{
		this.artists = artists;
	}
}
