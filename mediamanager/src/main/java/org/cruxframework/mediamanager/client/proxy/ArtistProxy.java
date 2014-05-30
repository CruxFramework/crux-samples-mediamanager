package org.cruxframework.mediamanager.client.proxy;

import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;

public interface ArtistProxy<T extends AbstractDTO>
{
	void insert(EditControllerInterface controller, T dto);
	void update(EditControllerInterface controller, Integer id , T dto);

}
