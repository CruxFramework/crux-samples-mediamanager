/**
 * 
 */
package org.cruxframework.mediamanager.core.client.controller;

import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

/**
 * @author bruno.rafael
 *
 */
public interface ArtistControllerInterface
{
	void editableState(EditArtistDTO result);
	
}
