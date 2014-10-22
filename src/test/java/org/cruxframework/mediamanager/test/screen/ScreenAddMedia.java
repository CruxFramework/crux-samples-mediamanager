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
 * Class description: This class represent the screen Add Media.
 * 
 * @author guilherme.alecrim
 */
public class ScreenAddMedia
{
	/*
	 * This parameters equivalent the elements in screen This element are providers by Beholder Framework
	 */
	private Select mediaType;
	private TextField mediaName;
	private Select mediaArtist;
	private Button btnAddArtist;
	private Button btnSaveChanges;

	private PopUp popUp;

	/**
	 * constructor initialize all elements.
	 */
	public ScreenAddMedia()
	{
		mediaType = SetUp.getBuilder().uiComponentBuilderInstance().selectFieldInstance();
		mediaName = SetUp.getBuilder().uiComponentBuilderInstance().textFieldInstance();
		mediaArtist = SetUp.getBuilder().uiComponentBuilderInstance().selectFieldInstance();
		btnAddArtist = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		btnSaveChanges = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		popUp = new PopUp("/html/body/div[5]/div", "/html/body/div[5]/div/div/div[2]/div/div",
		    "/html/body/div[5]/div/div/div[2]/div/button");
	}

	/*
	 * accessors methods, this methods performed scan in the html before return the element this scan is necessary because the element can
	 * not is displayed in screen to the access
	 */
	public Select getMediaType()
	{
		mediaType = SetUp.getBuilder().uiComponentBuilderInstance().selectFieldInstance();
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
		mediaArtist = SetUp.getBuilder().uiComponentBuilderInstance().selectFieldInstance();
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

	/**
	 * @return the popUp
	 */
	public PopUp getPopUp()
	{
		return popUp;
	}
}
