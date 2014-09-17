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

 * @author guilherme.alecrim
 */
public class Media
{
	
	/*
     * type: CD or DVD
     * name: nome of the media 
     * artist: artist with publish the media
     */
	
	private String type;
	private String name;
	private String artist;

	public Media()
	{
		type = new String();
		name = new String();
		artist = new String();
	}

	public Media(String type, String name, String artist)
	{
		this.type = type;
		this.name = name;
		this.artist = artist;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
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
}
