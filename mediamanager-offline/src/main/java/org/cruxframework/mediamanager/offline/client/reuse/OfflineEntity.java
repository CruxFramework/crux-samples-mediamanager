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
package org.cruxframework.mediamanager.offline.client.reuse;

import org.cruxframework.crux.core.client.db.annotation.Store.Indexed;
import org.cruxframework.crux.core.client.db.annotation.Store.Key;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

/**
 * Class description: This abstract class defines the attributes that all entities must have.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public abstract class OfflineEntity<T extends AbstractDTO> 
{
	
	private Integer id;
	private String name;
	
	public abstract String getStoreName();
	
	public abstract T getDTORepresentation();
		
	
	/************************
	 * GETTERS and SETTERS 
	 *********************/
	/**
	 * @key autoIncrement = true
	 * @return the id
	 */
	@Indexed
	@Key(autoIncrement = true)
	public Integer getId() 
	{
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	@Indexed()
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
	
}
