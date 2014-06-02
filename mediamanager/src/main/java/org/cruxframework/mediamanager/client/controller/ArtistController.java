/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cruxframework.mediamanager.client.controller;

import java.util.List;

import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.screen.views.BindableView;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.screen.views.ViewActivateEvent;
import org.cruxframework.crux.smartfaces.client.button.Button;
import org.cruxframework.crux.smartfaces.client.dialog.WaitBox;
import org.cruxframework.mediamanager.client.proxy.ArtistProxy;
import org.cruxframework.mediamanager.client.proxy.EditArtistProxy;
import org.cruxframework.mediamanager.client.reuse.controller.EditController;
import org.cruxframework.mediamanager.client.service.ArtistServiceProxy;
import org.cruxframework.mediamanager.core.client.controller.ArtistControllerInterface;
import org.cruxframework.mediamanager.core.client.dto.ArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.CountryDTO;
import org.cruxframework.mediamanager.core.client.dto.EditArtistDTO;
import org.cruxframework.mediamanager.core.client.dto.GenreDTO;

import com.google.gwt.user.client.ui.ListBox;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("artistController")
public class ArtistController extends EditController<ArtistDTO> implements ArtistControllerInterface
{
	//TODO remover esse artistserviceproxy
	@Inject
	public ArtistServiceProxy restServiceProxy;
	
	@Inject
	public ArtistProxy<ArtistDTO> artistService;

	@Inject
	public EditArtistProxy editArtist;
	
	@Expose
	public void onActivate(ViewActivateEvent event)
	{
		animateContent();
		//WaitBox.show("Wait", DialogAnimation.fadeDownUp);
		ArtistDTO artist = event.getParameterObject();
		Integer identificator = artist == null ? null : artist.getId();
		//editArtistServiceProxy.get(identificator, new EditAristCallback());
		editArtist.editArtist(this, identificator);	
	}
	
	/********************************************
	 * Callback classes
	 ********************************************/
	@Override
	public void editableState(EditArtistDTO result)
	{
		BindableView<ArtistDTO> view = View.of(ArtistController.this);
		ArtistDTO artist = result.getArtist();
		Integer identificator = artist == null ? null : artist.getId();
		WaitBox.hideAllDialogs();
		/* Adjust artistsViewAcessor state */
		if (identificator != null)
		{
			editState(view);
			view.setData(result.getArtist());
		} else
		{
			insertState(view);
			view.setData(getNewInstance());
		}
		
		fillGenereListBox((ListBox) view.getWidget("genereListBox"), 
			result.getGenres(), artist);
		
		fillCountryListBox((ListBox) view.getWidget("countryListBox"), 
			result.getCountries(), artist);
	}
	
	/********************************************
	 * View state settings
	 ********************************************/
	
	/**
	 * Adjust artistsViewAcessor for update mode.
	 * @param artistsViewAcessor artistsViewAcessor to be adjusted
	 */
	@Override
	protected void editState(View view)
	{
		Button insertButton = (Button)view.getWidget("insertButton");
		Button updateButton = (Button) view.getWidget("updateButton");
		insertButton.setEnabled(false);
		updateButton.setEnabled(true);
	}
	
	/**
	 * Adjust artistsViewAcessor for insert mode
	 * @param artistsViewAcessor artistsViewAcessor to be adjusted
	 */
	@Override
	protected void insertState(View view)
	{
		Button insertButton = (Button)view.getWidget("insertButton");
		Button updateButton = (Button) view.getWidget("updateButton");
		insertButton.setEnabled(true);
		updateButton.setEnabled(false);
	}
	
	/*****************************************
	 * Utilities
	 *****************************************/
	
	private static void fillGenereListBox(ListBox genreListBox, 
		List<GenreDTO> genreList, ArtistDTO artistDTO)
	{
		genreListBox.clear();
		genreListBox.addItem("", "");
		genreListBox.setSelectedIndex(0);
		for (int i = 0; i < genreList.size(); i++)
		{
			GenreDTO genre = genreList.get(i);
			genreListBox.addItem(genre.getName(), genre.getId().toString());
			
			if (artistDTO != null && artistDTO.getGenre().getId() == genre.getId())
			{
				genreListBox.setSelectedIndex(i + 1);
			}
		}
	}
	
	private static void fillCountryListBox(ListBox countryListBox, 
		List<CountryDTO> countryList, ArtistDTO artistDTO)
	{
		countryListBox.clear();
		countryListBox.addItem("", "");
		countryListBox.setSelectedIndex(0);
		
		for (int i = 0; i < countryList.size(); i++)
		{
			CountryDTO country = countryList.get(i);
			countryListBox.addItem(country.getName(), country.getId().toString());
			
			if (artistDTO != null && artistDTO.getCountry().getId() == country.getId())
			{
				countryListBox.setSelectedIndex(i + 1);
			}
		}
	}
	
	/*******************************************
	 * Overwritten methods
	 *******************************************/
	
	@Override
	protected void fillDTO(BindableView<ArtistDTO> view, ArtistDTO artist)
	{
		/* Get country id */
		ListBox countryListBox = (ListBox) view.getWidget("countryListBox");
		CountryDTO country = new CountryDTO();
		
		try
		{
			country.setId(Integer.parseInt(
				countryListBox.getValue(countryListBox.getSelectedIndex())));
			
		} catch (NumberFormatException e)
		{
			country.setId(null);
		}
		
		artist.setCountry(country);
		
		/* Get genre id */
		ListBox genereListBox = (ListBox) view.getWidget("genereListBox");
		GenreDTO genre = new GenreDTO();
		
		try
		{
			genre.setId(Integer.parseInt(
				genereListBox.getValue(genereListBox.getSelectedIndex())));
		} catch (NumberFormatException e)
		{
			genre.setId(null);
		}
		
		artist.setGenre(genre);
	}
	
	@Override
	protected boolean validate(ArtistDTO abstractDTO)
	{
		return abstractDTO.getName() != null 
			&& abstractDTO.getName().trim().length() > 0
			&& abstractDTO.getCountry().getId() != null
			&& abstractDTO.getGenre().getId() != null;
	}
	
	/**
	 * @return the restServiceProxy
	 */
	@Override
	public ArtistServiceProxy getRestServiceProxy()
	{
		return restServiceProxy;
	}
	
	@Override
	public ArtistProxy<ArtistDTO> getArtistProxy()
	{
		return artistService;
	}

	@Override
	protected ArtistDTO getNewInstance()
	{
		return new ArtistDTO();
	}
}
