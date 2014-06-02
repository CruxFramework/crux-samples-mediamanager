package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Country;

/**
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class CountryDao extends AbstractDao<CountryDTO, Country>
{

	private static CountryDao INSTANCE = new CountryDao();
	
	private CountryDao()
	{
		
	}
	
	public static CountryDao getInstance()
	{
		return INSTANCE;
	}
	
}
