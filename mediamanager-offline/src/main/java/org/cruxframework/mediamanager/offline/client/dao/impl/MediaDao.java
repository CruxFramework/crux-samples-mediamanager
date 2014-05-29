package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Media;

/**
 * @author bruno.rafael
 * 
 */
public class MediaDao extends AbstractDao<MediaDTO, Media> 
{
	private static MediaDao INSTANCE = new MediaDao();

	private MediaDao()
	{
		
	}

	public static MediaDao getInstance()
	{
		return INSTANCE;
	}
}
