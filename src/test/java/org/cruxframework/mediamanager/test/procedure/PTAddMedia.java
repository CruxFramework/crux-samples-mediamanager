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
		screenAddMedia = new ScreenAddMedia();
		PTAddMedia.populateFields(media);
		screenAddMedia.getBtnAddArtist().click();
		String msgSucess = screenAddMedia.getPopUpSuccessfullySavedAddArtist().getDivText().getText();
		screenAddMedia.getPopUpSuccessfullySavedAddArtist().getBtnOk().click();
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
		screenAddMedia.getBtnSaveChanges().click();
		String msgSaveChange = screenAddMedia.getPopUpSuccessfullySavedSaveChanges().getDivText().getText();
		screenAddMedia.getPopUpSuccessfullySavedSaveChanges().getBtnOk().click();
		return msgSaveChange;
	}

	/** 
	 * preenche os campos da tela de Add Media
	 * @param media
	 */
	public static void populateFields(Media media)
	{
		screenAddMedia = new ScreenAddMedia();
		screenAddMedia.getMediaType().select(media.getType());
		screenAddMedia.getMediaName().fill(media.getName());
		screenAddMedia.getMediaArtist().select(media.getArtist());
	}

	public static ScreenAddMedia getScreenAddMedia()
	{
		return screenAddMedia;
	}
}
