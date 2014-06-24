package org.cruxframework.mediamanager.client.controller.callback;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox;
import org.cruxframework.crux.smartfaces.client.dialog.MessageBox.MessageType;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.mediamanager.client.reuse.controller.SearchController;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

public class DeleteControllerCallback<T extends AbstractDTO> implements Callback<EditOperation>
{
	private final T dto;
	private final SearchController<T> controller;
	
	public DeleteControllerCallback(T dto, SearchController<T> controller)
	{
		this.dto = dto;
		this.controller = controller;
	}
	
	@Override
	public void onSuccess(EditOperation result)
	{
		if (result.getType() == null)
		{
			this.controller.showDeleteResult(dto);
		} else
		{
			WaitBox.hideAllDialogs();
			MessageBox.show(controller.getMessageForDeleteValidationError(), 
					MessageType.ERROR);
		}
	}

	@Override
	public void onError(Exception e)
	{
		WaitBox.hideAllDialogs();
		Crux.getErrorHandler().handleError(e.getLocalizedMessage(), e);
	}
}
