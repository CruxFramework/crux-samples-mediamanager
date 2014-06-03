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
}
