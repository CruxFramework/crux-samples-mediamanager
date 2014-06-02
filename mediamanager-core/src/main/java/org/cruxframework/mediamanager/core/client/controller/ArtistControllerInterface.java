/**
 * 
 */
package org.cruxframework.mediamanager.core.client.controller;

import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;

/**This interface provides methods that controllers ArtistController
 * implement to handle the return of database services
 *
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public interface ArtistControllerInterface
{
	void editableState(EditArtistDTO result);
	
}
