package org.cruxframework.mediamanager.client.proxyfactory.offline;

import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.core.client.controller.EditControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.reuse.EditOperation;
import org.cruxframework.mediamanager.offline.client.builder.ArtistBuilder;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;

/**This class is responsible for calling the services necessary to
 * build and store the data in the database offline
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class Artist<T extends ArtistDTO> implements ArtistProxy<ArtistDTO>
{
	
	/********************
	 * Insert
	 *******************/
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
	
	/********************
	 * Update
	 *******************/
	
	@Override
	public void update(final EditControllerInterface controller, Integer id,
			ArtistDTO dto)
	{
		final ArtistBuilder artist = new ArtistBuilder(id, dto);
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
		{
			
			@Override
			public boolean execute()
			{
				if (artist.isDoneSave())
				{
					controller.completeUpdate();
					return false;
				}
				return true;
			}
		}, 1000);
	}
	
}
