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
 * Class description: This class represent a field of 'Search Media' screen.
 * 
 * @author guilherme.alecrim
 */
public class QueryMedia
{
	/*
	 * type: Type of media, e.g CD or DVD name: Name of media, e.g Yellow Submarine borrowed: Client with media been borrowed, e.g Guilherme
	 */
	private String type;
	private String name;
	private String borrowed;

	/**
	 * Constructor.
	 */
	public QueryMedia()
	{
		this.type = new String();
		this.name = new String();
		this.borrowed = new String();
	}

	/**
	 * Constructor.
	 * @param type type
	 * @param name name
	 * @param borrowed borrowed
	 */
	public QueryMedia(String type, String name, String borrowed)
	{
		this.type = type;
		this.name = name;
		this.borrowed = borrowed;
	}
	
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
	 * @return the borrowed
	 */
	public String getBorrowed()
	{
		return borrowed;
	}

	/**
	 * @param borrowed the borrowed to set
	 */
	public void setBorrowed(String borrowed)
	{
		this.borrowed = borrowed;
	}

	@Override
	public String toString()
	{
		return type + "-" + name + "-" + borrowed;
	}
}
