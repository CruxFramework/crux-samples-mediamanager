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
package org.cruxframework.mediamanager.test.procedure;

import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.screen.ScreenAddArtist;

/**
 * Class description:  This class implements the  procedures in 'Add Artist' screen
 * @author guilherme.alecrim
 */
public class PTAddArtist
{
	private static ScreenAddArtist screenAddArtist;

	/** 
	 * Add an artist 
	 * @param artist: Artist for add in data base
	 * @return: message of success to add artist 'Successfully saved!'
	 */
	public static String addArtist(Artist artist) 
	{		
		
		populateFields(artist);
		getScreenAddArtist().getBtnAddArtist().click();
		String msg = getScreenAddArtist().getPopUp().getMenssagePopUp();
		getScreenAddArtist().getPopUp().confirmPopUp();	
		Navegation.waitFiveSeconds();
		return msg;
	}


	/** 
	 * Populate the fields of 'Add Artist' screen
	 * @param artist: values for fill the fields
	 */
	public static void populateFields(Artist artist)
	{	
			getScreenAddArtist().getName().fill(artist.getName());
			getScreenAddArtist().getCountry().select(artist.getCountry());
			getScreenAddArtist().getGenre().select(artist.getGenre());
	}

	/** 
	 * Add a  artist and change yours data
	 * @param artist: artist for add 
	 * @param newValues: values for change data of artist 
	 * @return message of success 'Successfully saved!'
	 */
	public static String addAndChangerArtist(Artist artist, Artist newValues)
	{
		PTAddArtist.addArtist(artist);
		PTAddArtist.populateFields(newValues);
		getScreenAddArtist().getBtnSaveChanges().click();
		String sucessChage = getScreenAddArtist().getPopUp().getMenssagePopUp();
		getScreenAddArtist().getPopUp().confirmPopUp();
		return sucessChage;
	}


	/**
	 * Check if the pop   with message 'Successfully saved!' is displayed
	 * @return true:  pop up  is displayed 
	 * false:  pop up not is  displayed
	 */
	public static boolean isDisplayedPopUpSavedAddArtist()
	{
		return getScreenAddArtist().getPopUp().isDisplayedPopUp();
	}
	
	

	
	
	
	
	/** 
	 * instance the object ScreenAddArtist
	 * @return: ScreenAddArtist instance
	 */
	public static ScreenAddArtist getScreenAddArtist()
	{
		if(screenAddArtist == null)
		{
			screenAddArtist = new ScreenAddArtist();
		}
		return screenAddArtist;
	}
	
}
