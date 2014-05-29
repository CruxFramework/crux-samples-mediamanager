/**
 * 
 */
package org.cruxframework.mediamanager.client.rebind.jpa;

import org.cruxframework.mediamanager.client.proxy.EditArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.WaitCallbackAdapter;
import org.cruxframework.mediamanager.client.service.EditArtistServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;

import com.google.gwt.core.client.GWT;

/**This class is responsible for running the service  REST communication (EditArtistServiceProxy)  with 
 * the server when the project is switched to use JPA database.
 * @author bruno.rafael
 */
public class EditArtist implements EditArtistProxy
{
//	@Inject
//	public EditArtistServiceProxy editArtistServiceProxy;
	
	public EditArtistServiceProxy editArtistServiceProxy = GWT.create(EditArtistServiceProxy.class);
	
	@Override
	public void editArtist(ArtistControllerInterface controller, Integer identificator)
	{
		editArtistServiceProxy.get(identificator, new EditAristCallback(controller));
	}

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
