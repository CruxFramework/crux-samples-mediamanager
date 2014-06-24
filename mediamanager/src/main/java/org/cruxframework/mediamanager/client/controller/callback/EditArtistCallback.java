package org.cruxframework.mediamanager.client.controller.callback;

import org.cruxframework.mediamanager.client.controller.ArtistController;
import org.cruxframework.mediamanager.client.reuse.controller.WaitCallbackAdapter;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;

public class EditArtistCallback extends WaitCallbackAdapter<EditArtistDTO>
{	
	public ArtistController controller;
	
	public EditArtistCallback(ArtistController controller)
	{
		this.controller = controller;
	}

	@Override
	protected void success(EditArtistDTO result)
	{
		controller.editableState(result);
	}
}
