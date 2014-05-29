package org.cruxframework.mediamanager.client.rebind.jpa;

import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.CallbackAdapter;
import org.cruxframework.mediamanager.client.service.ArtistServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

import com.google.gwt.core.client.GWT;

/**This class is responsible for running the service  REST communication (ArtistServiceProxy)  with 
 * the server when the project is switched to use JPA database.
 * @author bruno.rafael
 */
public class Artist<T extends ArtistDTO> implements ArtistProxy<ArtistDTO>
{
	
	private static final String DEFAULT_SUCCESS_MESSAGE = "Successfully saved!";
	
	public ArtistServiceProxy restServiceProxy = GWT.create(ArtistServiceProxy.class)  ;
	
	@Override
	public void insert(EditControllerInterface controller, ArtistDTO dto)
	{
		restServiceProxy.insert(dto, new InsertCallback(controller));
	}

	
	private class InsertCallback extends CallbackAdapter<EditOperation>
	{
		private final EditControllerInterface controller;
		
		public InsertCallback(EditControllerInterface controller)
		{
			this.controller = controller;
		}
		
		@Override
		public void onComplete(EditOperation result)
		{
			controller.completeInsert(result);
		}
	}
}
