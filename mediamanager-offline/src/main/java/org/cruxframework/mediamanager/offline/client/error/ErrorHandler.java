package org.cruxframework.mediamanager.offline.client.error;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.db.DatabaseErrorHandler;

/**
 * Class description: Defines the error handling for database operations
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */

public class ErrorHandler implements DatabaseErrorHandler 
{

	@Override
	public void onError(String message) 
	{
		 Crux.getErrorHandler().handleError(message);
	}

	@Override
	public void onError(String message, Throwable t) 
	{
		 Crux.getErrorHandler().handleError(message, t);
	}

}
