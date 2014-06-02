package org.cruxframework.mediamanager.offline.client.dao.impl;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.mediamanager.core.client.dto.UserDTO;
import org.cruxframework.mediamanager.offline.client.dao.AbstractDao;
import org.cruxframework.mediamanager.offline.client.entity.User;

/**This class provides only one instance of UserDAO, 
 * with the implementation of the methods the persistence in 
 * database for UserDAO.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 * 
 */
public class UserDao extends AbstractDao<UserDTO, User>
{

	@Override
	protected Database getDatabase()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
