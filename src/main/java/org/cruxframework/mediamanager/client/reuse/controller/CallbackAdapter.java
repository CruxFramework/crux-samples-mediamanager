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

/**
 * Class description: 
 * @author alexandre.costa
 */
public abstract class CallbackAdapter<T> implements Callback<T>
{
	@Override
	public final void onSuccess(T result)
	{
		onComplete(result);
	}
	
	@Override
	public final void onError(Exception e) 
	{
		onFailure(e);
	}
	
	/**
	 * Override this method to handle result received for a remote call
	 * @param result
	 */
	public abstract void onComplete(T result);
	
	/**
	 * Override this method to add specific error handling for a remote call
	 * @param e
	 */
	public void onFailure(Exception e)
	{
		Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
	}
}
