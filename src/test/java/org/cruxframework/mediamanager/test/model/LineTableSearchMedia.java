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
 * Class description: Represent a line of the result table of 'Search Media' screen
 *  One line of the result table is  compost  by attributes of a 
 *  media and a client witch borrowed the media (column person) 
 * @author guilherme.alecrim
 */
public class LineTableSearchMedia
{
	/*
	 * Media, are the columns, Type, Name and  Artist
	 *  Person is client witch borrowed the media, column Person    
	 */
	private Media media;
	private String person;

	public LineTableSearchMedia()
	{
		media = new Media();
		person = new String();
	}

	public LineTableSearchMedia(Media media, String person)
	{
		this.media = media;
		this.person = person;
	}

	public Media getMedia()
	{
		return media;
	}

	public void setMedia(Media media)
	{
		this.media = media;
	}

	public String getPerson()
	{
		return person;
	}

	public void setPerson(String person)
	{
		this.person = person;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof LineTableSearchMedia)
		{
			if (((LineTableSearchMedia) obj).getMedia().equals(this.media)
					&& (((LineTableSearchMedia) obj).getPerson().equals(this.person)))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		return this.media.toString() + "\n" + "Person: " + this.person;
	}
}
