/**
 * 
 */
package org.cruxframework.mediamanager.client.controller.callback;

import java.util.List;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.mediamanager.client.reuse.controller.SearchController;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class SearchCallback<T extends AbstractDTO>  implements Callback<List<T>>
{
	private final SearchController<T> controller;
	
	public SearchCallback(SearchController<T> controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void onSuccess(List<T> result)
	{
		controller.showSearchResult(result);
	};
	
	@Override
	public void onError(Exception e)
	{
		WaitBox.hideAllDialogs();
		Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
	}
}