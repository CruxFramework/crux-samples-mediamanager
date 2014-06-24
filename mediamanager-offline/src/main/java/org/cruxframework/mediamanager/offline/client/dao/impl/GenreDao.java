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
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

/**This class provides only one instance of GenreDAO, 
 * with the implementation of the methods the persistence in 
 * database for GenreDAO.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class GenreDao extends AbstractDao<GenreDTO, Genre>
{
	private static GenreDao INSTANCE = new GenreDao();
	private Database db;
	private GenreDao()
	{

	}

	public static GenreDao getInstance(Database db)
	{
		INSTANCE.db = db;
		return INSTANCE;
	}

	@Override
	protected Database getDatabase()
	{
		return db;
	}
	
	@Override
	protected String getStoreName()
	{
		return Genre.STORE_NAME;
	}
}
