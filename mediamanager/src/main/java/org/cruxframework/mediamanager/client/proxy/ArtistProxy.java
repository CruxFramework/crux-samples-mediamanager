package org.cruxframework.mediamanager.client.proxy;

import org.cruxframework.mediamanager.client.controller.ArtistsController;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;


/**
 * Class description: This interface is used in the deferred binding process to 
 * define which form of data persistence.
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 * @param <T extends AbstractDTO>
 */
public interface ArtistProxy extends AbstractProxy<ArtistDTO>
{
	void search(String name, ArtistsController controller);
	void delete(ArtistDTO dto, ArtistsController controler);
}
