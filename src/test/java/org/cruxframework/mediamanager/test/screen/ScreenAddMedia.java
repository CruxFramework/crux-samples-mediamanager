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
package org.cruxframework.mediamanager.test.screen;

import org.cruxframework.mediamanager.test.procedure.SetUp;

import br.ufmg.dcc.saotome.beholder.ui.form.Button;
import br.ufmg.dcc.saotome.beholder.ui.form.Select;
import br.ufmg.dcc.saotome.beholder.ui.form.TextField;

/**
 * Class description: Esta classe representa a tela de adicionar media. Possui apenas metodos de acesso aos WebElements
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar métodos
public class ScreenAddMedia
{
	private Select mediaType;
	private TextField mediaName;
	private Select mediaArtist;
	private Button btnAddArtist;
	private Button btnSaveChanges;

	private PopUpSuccessfullySaved popUpSuccessfullySavedAdd;
	private PopUpSuccessfullySaved popUpSuccessfullySavedSaveChanges;

	public ScreenAddMedia()
	{
		mediaType = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		mediaName = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		mediaArtist = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		btnAddArtist = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		btnSaveChanges = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		popUpSuccessfullySavedAdd = new PopUpSuccessfullySaved(0);
		popUpSuccessfullySavedSaveChanges = new PopUpSuccessfullySaved(1);
	}

	public Select getMediaType()
	{
		mediaType.loadById("media_typeListBox");
		return mediaType;
	}

	public TextField getMediaName()
	{
		mediaName.loadById("media_nameTextBox");
		return mediaName;
	}

	public Select getMediaArtist()
	{
		mediaArtist.loadById("media_artistListBox");
		return mediaArtist;
	}

	public Button getBtnAddArtist()
	{
		btnAddArtist.loadById("media_insertButton");
		return btnAddArtist;
	}

	public Button getBtnSaveChanges()
	{
		btnSaveChanges.loadById("media_updateButton");
		return btnSaveChanges;
	}

	public PopUpSuccessfullySaved getPopUpSuccessfullySavedAddArtist()
	{
		return popUpSuccessfullySavedAdd;
	}

	public PopUpSuccessfullySaved getPopUpSuccessfullySavedSaveChanges()
	{
		return popUpSuccessfullySavedSaveChanges;
	}
}
