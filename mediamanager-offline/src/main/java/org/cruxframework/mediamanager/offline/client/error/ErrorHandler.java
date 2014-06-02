package org.cruxframework.mediamanager.offline.client.error;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.db.DatabaseErrorHandler;

/**
 * Class description: 
 * @author Bruno.Rafael
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
