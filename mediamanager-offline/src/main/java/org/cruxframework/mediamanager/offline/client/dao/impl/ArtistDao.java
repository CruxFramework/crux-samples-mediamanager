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
package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Artist;

/**This class provides only one instance of ArtistDAO, 
 * with the implementation of the methods in the persistence in 
 * database for ArtistDAO.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class ArtistDao extends AbstractDao<ArtistDTO, Artist>
{
	
	private static ArtistDao INSTANCE = new ArtistDao();
	private Database db;
	
	private ArtistDao()
	{
		
	}
	
	public static ArtistDao getInstance(Database db)
	{ 
		INSTANCE.db = db;
		return INSTANCE;
	}

	@Override
	protected Database getDatabase()
	{
		return this.db;
	}

	@Override
	protected String getStoreName()
	{
		return Artist.STORE_NAME;
	}
}
