/**
 * 
 */
package org.cruxframework.mediamanager.client.proxyfactory.jpa;

import org.cruxframework.mediamanager.client.proxy.EditArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.WaitCallbackAdapter;
import org.cruxframework.mediamanager.client.service.EditArtistServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;

import com.google.gwt.core.client.GWT;

/**Class descrption: This class is responsible for running the service  REST communication (EditArtistServiceProxy)  with 
 * the server when the project is switched to use JPA database.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class EditArtist implements EditArtistProxy
{
	public EditArtistServiceProxy editArtistServiceProxy = GWT.create(EditArtistServiceProxy.class);

	@Override
	public void editArtist(ArtistControllerInterface controller, Integer identificator)
	{
		editArtistServiceProxy.get(identificator, new EditAristCallback(controller));
	}
	
	/********************
	 * Callback
	 *********************/

	private class EditAristCallback extends WaitCallbackAdapter<EditArtistDTO>
	{	
		public ArtistControllerInterface controller;
		
		public EditAristCallback(ArtistControllerInterface controller){
			this.controller = controller;
		}
		
		@Override
		protected void success(EditArtistDTO result)
		{
			controller.editableState(result);
		}
	}
	
}
