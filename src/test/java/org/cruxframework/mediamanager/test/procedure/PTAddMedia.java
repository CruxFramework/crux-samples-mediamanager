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

import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.screen.ScreenAddMedia;

/**
 * Class description: This class implements the  procedures in 'Add Media' screen
 * @author guilherme.alecrim
 */
public class PTAddMedia
{
	private static ScreenAddMedia screenAddMedia;

	/** 
	 * Add a media
	 * @param media: media for add in database
	 * @return: menssage of sucess 'Successfully saved!'
	 */
	public static String addMedia(Media media)
	{
		PTAddMedia.populateFields(media);
		getScreenAddMedia().getBtnAddArtist().click();
		String msgSucess = getScreenAddMedia().getPopUp().getMenssagePopUp();
		getScreenAddMedia().getPopUp().confirmPopUp();
		return msgSucess;
	}

	/** 
	 * Add a media and after change your data 
	 * @param media: media for add 
	 * @param newValues: new values for media
	 * @return menssage of sucess 'Successfully saved!'
	 */
	public static String saveChanges(Media media, Media newValues)
	{
		PTAddMedia.addMedia(media);
		PTAddMedia.populateFields(newValues);
		getScreenAddMedia().getBtnSaveChanges().click();
		String msgSaveChange = getScreenAddMedia().getPopUp().getMenssagePopUp();
		getScreenAddMedia().getPopUp().confirmPopUp();
		return msgSaveChange;
	}

	/** 
	 * Filled of field 'Add Media' screen 
	 * @param media: values for fill the fields
	 */
	public static void populateFields(Media media)
	{
		getScreenAddMedia().getMediaType().select(media.getType());
		getScreenAddMedia().getMediaName().fill(media.getName());
		getScreenAddMedia().getMediaArtist().select(media.getArtist());
	}

	/**
	 * Check if the pop up is displayed
	 * @return true:  pop up  is displayed 
	 * false:  pop up not is  displayed
	 */
	public static boolean isDisplayedPopUp()
	{
		return getScreenAddMedia().getPopUp().isDisplayedPopUp();
	}
	
	
	/**
	 * instantiates the object ScreenAddMedia
	 * @return: ScreenAddMedia instatiates
	 */
	public static ScreenAddMedia getScreenAddMedia()
	{
		if (screenAddMedia == null )
		{
			screenAddMedia = new ScreenAddMedia();
		}
		return screenAddMedia;
	}
}
