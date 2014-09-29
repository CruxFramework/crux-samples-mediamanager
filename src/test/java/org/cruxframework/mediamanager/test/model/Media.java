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
package org.cruxframework.mediamanager.test.model;

/**
 * Class description: This class represent a media with  attributes, type, name and artist.
 * 
 * - type: CD or DVD
 * - name: nome of the media 
 * - artist: artist with publish the media
 * 
 * @author guilherme.alecrim
 */
public class Media
{
	private String type;
	private String name;
	private String artist;

	/**
	 * Default constructor.
	 */
	public Media()
	{
		type = new String();
		name = new String();
		artist = new String();
	}

	/**
	 * Constructor.
	 * @param type media type
	 * @param name media name
	 * @param artist artist of the media
	 */
	public Media(String type, String name, String artist)
	{
		this.type = type;
		this.name = name;
		this.artist = artist;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Media)
		{
			if (((Media) obj).getType().equals(this.type) && (((Media) obj).getName().equals(this.name))
					&& (((Media) obj).getArtist().equals(this.artist)))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		String media = "Type: " + type + "\n" + "Name: " + name + "\n" + "Artist: " + artist;
		return media;
	}
	
	/*******************************************
	 * getters and setters
	 ******************************************/

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the artist
	 */
	public String getArtist()
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
}
