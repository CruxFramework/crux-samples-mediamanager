package org.cruxframework.mediamanager.client.proxy;

import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;


/**
 * Class description: This interface is used in the deferred binding process to 
 * define which form of data persistence.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 * @param <T extends AbstractDTO>
 */
public interface ArtistProxy<T extends AbstractDTO>
{
	
	void insert(EditControllerInterface controller, T dto);
	void update(EditControllerInterface controller, Integer id , T dto);

}
