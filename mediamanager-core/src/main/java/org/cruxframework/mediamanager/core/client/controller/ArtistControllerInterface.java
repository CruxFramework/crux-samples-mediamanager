/**
 * 
 */
package org.cruxframework.mediamanager.core.client.controller;

import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;

/**This interface provides methods that controllers
 * implement to handle the return of database services
 * @author bruno.rafael
 */
public interface ArtistControllerInterface
{
	void editableState(EditArtistDTO result);
	
}
