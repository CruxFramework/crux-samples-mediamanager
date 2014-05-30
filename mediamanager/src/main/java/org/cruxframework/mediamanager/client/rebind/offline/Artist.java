package org.cruxframework.mediamanager.client.rebind.offline;

import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.offline.client.builder.ArtistBuilder;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;

/**This class is responsible for running the service  REST communication (ArtistServiceProxy)  with 
 * the server when the project is switched to use JPA database.
 * @author bruno.rafael
 */
public class Artist<T extends ArtistDTO> implements ArtistProxy<ArtistDTO>
{

	@Override
	public void insert(final EditControllerInterface controller, ArtistDTO dto)
	{
		final ArtistBuilder artist = new ArtistBuilder(dto);
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
		{
			
			@Override
			public boolean execute()
			{
				if (artist.isDoneSave()){
					EditOperation edit = new EditOperation();
					edit.setId(artist.getId());
					controller.completeInsert(edit);
					return false;
				}
				return true;
			}
		}, 1000);
	}
	
	
	@Override
	public void update(EditControllerInterface controller, Integer id,
			ArtistDTO dto)
	{
		
		
	}
	
}
