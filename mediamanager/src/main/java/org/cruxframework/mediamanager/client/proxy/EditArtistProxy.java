package org.cruxframework.mediamanager.client.proxy;

import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;


/**
 * Class Description: This interface is used in the deferred binding process to 
 * define which form of data persistence.
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public interface EditArtistProxy
{
	void editArtist(ArtistControllerInterface controller, Integer identificator);
}
