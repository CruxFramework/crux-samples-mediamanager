package org.cruxframework.mediamanager.client.proxyfactory.jpa;

import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.CallbackAdapter;
import org.cruxframework.mediamanager.client.service.ArtistServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

import com.google.gwt.core.client.GWT;

/**This class is responsible for running the service  REST communication (ArtistServiceProxy)  with 
 * the server when the project is switched to use JPA database.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class Artist<T extends ArtistDTO> implements ArtistProxy<ArtistDTO>
{
	
	public ArtistServiceProxy restServiceProxy = GWT.create(ArtistServiceProxy.class)  ;
	
	/********************
	 * Insert
	 *******************/
	
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

	/********************
	 * Update
	 *******************/
	@Override
	public void update(EditControllerInterface controller, Integer id,
			ArtistDTO dto)
	{
		restServiceProxy.update(id, dto, new UpdateCallback(controller));
	}
	
	private class UpdateCallback extends CallbackAdapter<EditOperation>
	{
		EditControllerInterface controller;
		
		public UpdateCallback(EditControllerInterface controller)
		{
			this.controller = controller;
		}
		
		@Override
		public void onComplete(EditOperation result)
		{
			controller.completeUpdate();
		}
	}
	
	
}
