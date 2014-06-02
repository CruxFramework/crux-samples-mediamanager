package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Media;

/**This class provides only one instance of MediaDAO, 
 * with the implementation of the methods the persistence in 
 * database for MediaDAO.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class MediaDao extends AbstractDao<MediaDTO, Media> 
{
	private static MediaDao INSTANCE = new MediaDao();
	private Database db;
	private MediaDao()
	{
		
	}

	public static MediaDao getInstance(Database db)
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
