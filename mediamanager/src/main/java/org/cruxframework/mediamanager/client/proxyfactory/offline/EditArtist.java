package org.cruxframework.mediamanager.client.proxyfactory.offline;

import org.cruxframework.mediamanager.client.proxy.EditArtistProxy;
import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.offline.client.builder.EditArtistBuilder;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;

/**Search and returns the information necessary to controller one editArtistDTO
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public class EditArtist implements EditArtistProxy
{
	@Override
	public void editArtist(final ArtistControllerInterface controller,
			final Integer identificator)
	{
		final EditArtistBuilder ea = new EditArtistBuilder(identificator);
		
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand()
		{
			@Override
			public boolean execute()
			{
					if ((ea.isDoneCountry()) && (ea.isDoneGenre()) )
					{
						if (identificator != null && ea.isDoneArtist())
						{
							ArtistDTO artistDTO = null;
							artistDTO = ea.getArtist();
							EditArtistDTO editArtistDTO = new EditArtistDTO();
							editArtistDTO.setArtist(artistDTO);
							editArtistDTO.setCountries(ea.getCountry());
							editArtistDTO.setGenres(ea.getGenre());

							//Manda o retorno para Controller
							controller.editableState(editArtistDTO);
							return false;
						}else if (identificator == null)
						{
							ArtistDTO artistDTO = null;
							EditArtistDTO editArtistDTO = new EditArtistDTO();
							editArtistDTO.setArtist(artistDTO);
							editArtistDTO.setCountries(ea.getCountry());
							editArtistDTO.setGenres(ea.getGenre());

							//Manda o retorno para Controller
							controller.editableState(editArtistDTO);
							return false;
						}
					}
					return true;
			}
		},1000);
	}
}
