/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
