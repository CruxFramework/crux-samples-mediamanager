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
package org.cruxframework.mediamanager.client.reuse.controller;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class WaitCallbackAdapter<T extends Object> 
	implements Callback<T>
{
	protected abstract void success(T result);

	@Override
	public final void onSuccess(T result)
	{
		try
		{
			success(result);
		} finally
		{
			WaitBox.hideAllDialogs();
		}
	}

	@Override
	public final void onError(Exception e)
	{
		WaitBox.hideAllDialogs();
		error(e);
	}
	
	protected void error(Exception e)
	{
		Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
	}
}
