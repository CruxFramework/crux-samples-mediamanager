package org.cruxframework.mediamanager.offline.client.entity;

import org.cruxframework.crux.core.client.db.annotation.Store;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

/**
 * Class description: 
 * @author Bruno.Rafael
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
	public String getStoreName() {
		return STORE_NAME;
	}
	
	@Override
	public CountryDTO getDTORepresentation() {
		CountryDTO dto = new CountryDTO();
		dto.setId(getId());
		dto.setName(getName());
		return dto;
	}
	
}
