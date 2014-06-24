package org.cruxframework.mediamanager.client.controller.callback;

import org.cruxframework.mediamanager.client.controller.ArtistController;
import org.cruxframework.mediamanager.client.reuse.controller.CallbackAdapter;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

public class InsertArtistCallback extends CallbackAdapter<EditOperation>
{
	private final ArtistController controller;
	
	public InsertArtistCallback(ArtistController controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void onComplete(EditOperation result)
	{
		controller.completeInsert(result);
	}
}