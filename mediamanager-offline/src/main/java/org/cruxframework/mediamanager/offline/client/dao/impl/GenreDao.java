package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Genre;

/**
 * @author bruno.rafael
 * 
 */
public class GenreDao extends AbstractDao<GenreDTO, Genre>
{
	private static GenreDao INSTANCE = new GenreDao();

	private GenreDao()
	{

	}

	public static GenreDao getInstance()
	{
		return INSTANCE;
	}
}
