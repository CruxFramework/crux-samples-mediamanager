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
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class PTAddMedia
{
	private static ScreenAddMedia screenAddMedia;

	/** 
	 * adicionar uma media
	 * @param media
	 * @return
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
	 * adiciona uma media e logo em seguida altera os dados adicionados
	 * @param media
	 * @param newValues
	 * @return
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
	 * preenche os campos da tela de Add Media
	 * @param media
	 */
	public static void populateFields(Media media)
	{
		getScreenAddMedia().getMediaType().select(media.getType());
		getScreenAddMedia().getMediaName().fill(media.getName());
		getScreenAddMedia().getMediaArtist().select(media.getArtist());
	}

	/**
	 * Verifica a presença de um popUp na tela
	 * @return true caso o popup esta sendo exibido, ou false caso contrario
	 * 
	 */
	public static boolean isDisplayedPopUp()
	{
		return getScreenAddMedia().getPopUp().isDisplayedPopUp();
	}
	
	public static ScreenAddMedia getScreenAddMedia()
	{
		if (screenAddMedia == null )
		{
			screenAddMedia = new ScreenAddMedia();
		}
		return screenAddMedia;
	}
}
