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

}
