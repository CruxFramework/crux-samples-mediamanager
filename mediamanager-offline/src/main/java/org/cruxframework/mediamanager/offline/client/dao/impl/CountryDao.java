package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.Country;

/**This class provides only one instance of CuntryDAO, 
 * with the implementation of the methods the persistence in 
 * database for CountryDAO.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class CountryDao extends AbstractDao<CountryDTO, Country>
{

	private static CountryDao INSTANCE = new CountryDao();
	private Database db;
	
	private CountryDao()
	{
		
	}
	
	public static CountryDao getInstance(Database db)
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
