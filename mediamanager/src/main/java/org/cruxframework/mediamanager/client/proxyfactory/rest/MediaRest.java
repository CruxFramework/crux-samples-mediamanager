/**
 * 
 */
package org.cruxframework.mediamanager.client.proxyfactory.rest;

import org.cruxframework.mediamanager.client.controller.MediaController;
import org.cruxframework.mediamanager.client.controller.MediasController;
import org.cruxframework.mediamanager.client.controller.callback.DeleteControllerCallback;
import org.cruxframework.mediamanager.client.controller.callback.EditMediaCallback;
import org.cruxframework.mediamanager.client.controller.callback.GetLendCallback;
import org.cruxframework.mediamanager.client.controller.callback.InsertMediaCallback;
import org.cruxframework.mediamanager.client.controller.callback.LendMediaCallback;
import org.cruxframework.mediamanager.client.controller.callback.SearchControllerCallback;
import org.cruxframework.mediamanager.client.controller.callback.UpdateMediaCallback;
import org.cruxframework.mediamanager.client.proxy.MediaProxy;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;
import org.cruxframework.mediamanager.client.service.EditMediaServiceProxy;
import org.cruxframework.mediamanager.client.service.MediaServiceProxy;
import org.cruxframework.mediamanager.core.client.dto.MediaDTO;
import org.cruxframework.mediamanager.core.client.enums.MediaType;

import com.google.gwt.core.client.GWT;

/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class MediaRest implements MediaProxy
{
	
	private final MediaServiceProxy mediaServiceProxy = GWT.create(MediaServiceProxy.class);

	private final EditMediaServiceProxy editMediaServiceProxy = GWT.create(EditMediaServiceProxy.class);

	
	/********************************
	 *Search
	 *******************************/
	
	@Override
	public void search(MediaType type, String name, String person, MediasController controller)
	{
		mediaServiceProxy.search(type, name, person, new SearchControllerCallback<MediaDTO>(controller));
	}
	
	
	/********************************
	 * Get
	 *******************************/
	
	@Override
	public void get(Integer identificator, AbstractController abstractController)
	{
		MediaController controller = (MediaController) abstractController;
		getEditMediaServiceProxy().get(identificator, new EditMediaCallback(controller));
	}

	
	
	/********************************
	 * Insert
	 *******************************/
	
	@Override
	public void insert(MediaDTO dto, AbstractController abstractController)
	{
		MediaController controller = (MediaController) abstractController;
		getMediaServiceProxy().insert(dto, new InsertMediaCallback(controller));		
	}

	
	/********************************
	 * Update
	 *******************************/

	@Override
	public void update(Integer id, MediaDTO dto, AbstractController abstractController)
	{
		MediaController controller = (MediaController) abstractController;
		getMediaServiceProxy().update(id, dto, new UpdateMediaCallback(controller));
	}
	
	
	
	/********************************
	 * Delete
	 *******************************/

	@Override
	public void delete(MediaDTO dto, MediasController controller)
	{
		getMediaServiceProxy().delete(dto, new DeleteControllerCallback<MediaDTO>(dto, controller));
	}

	
	/********************************
	 * Lend
	 *******************************/

	@Override
	public void lend(Integer id, MediaDTO dto, MediasController controller)
	{
		getMediaServiceProxy().update(id, dto, new LendMediaCallback(dto ,controller));
	}
	
	
	
	/********************************
	 * Get
	 *******************************/
	
	@Override
	public void getLend(Integer identificator, MediasController controller)
	{
		getMediaServiceProxy().get(identificator, new GetLendCallback(controller));
	}

	
	/********************************
	 * Utils
	 *******************************/


	/**
	 * @return the mediaServiceProxy
	 */
	public MediaServiceProxy getMediaServiceProxy()
	{
		return mediaServiceProxy;
	}

	/**
	 * @return the editMediaServiceProxy
	 */
	public EditMediaServiceProxy getEditMediaServiceProxy()
	{
		return editMediaServiceProxy;
	}
}