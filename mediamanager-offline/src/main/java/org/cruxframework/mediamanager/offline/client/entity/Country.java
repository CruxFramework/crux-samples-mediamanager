package org.cruxframework.mediamanager.offline.client.entity;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

/**
 * Class description: Defines an entity Country with the properties that must be stored in the database.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */

@Store(Country.STORE_NAME)
public class Country extends OfflineEntity<CountryDTO> 
//implements AbstractEntity<CountryDTO>
{
	public static final String STORE_NAME = "country";
	
	/****************
	 * Methods Abstract
	 **************/
	@Override
	public String getStoreName()
	{
		return STORE_NAME;
	}
	
	@Override
	public CountryDTO getDTORepresentation()
	{
		CountryDTO dto = new CountryDTO();
		dto.setId(getId());
		dto.setName(getName());
		return dto;
	}
	
}
