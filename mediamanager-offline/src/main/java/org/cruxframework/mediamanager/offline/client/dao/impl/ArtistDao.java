package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Artist;

/**
 * @author bruno.rafael
 * 
 */
public class ArtistDao extends AbstractDao<ArtistDTO, Artist>
{
	
	private static ArtistDao INSTANCE = new ArtistDao();
	
	private ArtistDao()
	{
		
	}
	
	public static ArtistDao getInstance(){
		return INSTANCE;
	}
	
}
