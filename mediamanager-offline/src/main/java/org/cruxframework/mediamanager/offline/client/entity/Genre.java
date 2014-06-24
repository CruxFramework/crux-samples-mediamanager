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
package org.cruxframework.mediamanager.offline.client.entity;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

/**
 * Class description: Defines an entity Genre with the properties that must be stored in the database.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */

@Store(Genre.STORE_NAME)
public class Genre extends OfflineEntity<GenreDTO> 
//implements AbstractEntity<GenreDTO> 
{
	public static final String STORE_NAME = "ganre";
	
	/******************
	 * Methods Abstract
	 ******************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	@Override
	public GenreDTO getDTORepresentation()
	{
		GenreDTO dto = new GenreDTO();
		dto.setId(getId());
		dto.setName(getName());
		return dto;
	}
	
}
