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
 * Class description: This class represent an artist with attributes name, country and genre.
 * 
 * @author guilherme.alecrim
 */
public class Artist
{
	private String name;
	private String country;
	private String genre;
	
	/**
	 * Default constructor.
	 */
	public Artist()
	{
		name = new String();
		country = new String();
		genre = new String();
	}

	/**
	 * Constructor.
	 * @param name artist name
	 * @param country artist country
	 * @param genre artist genre
	 */
	public Artist(String name, String country, String genre)
	{
		this.name = name;
		this.country = country;
		this.genre = genre;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Artist)
		{
			if (((Artist) obj).getName().equals(this.name) && (((Artist) obj).getCountry().equals(this.country))
			    && (((Artist) obj).getGenre().equals(this.genre)))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		String artist = "Name: " + name + "\n" + "Country: " + country + "\n" + "Genre: " + genre;
		return artist;
	}
	
	/**************************************************
	 * Getters and setters
	 **************************************************/

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
	 * @return the country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * @return the genre
	 */
	public String getGenre()
	{
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
}
