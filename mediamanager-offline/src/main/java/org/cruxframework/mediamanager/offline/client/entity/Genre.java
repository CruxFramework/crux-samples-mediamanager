package org.cruxframework.mediamanager.offline.client.entity;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;
import org.cruxframework.mediamanager.core.reuse.AbstractEntity;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

/**
 * Class description: 
 * @author Bruno.Rafael
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
	public String getStoreName() {
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
