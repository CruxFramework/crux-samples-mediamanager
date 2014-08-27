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
 * Class description: Esta classe representa a tela de adicionar artista. Possui apenas metodos de acesso aos
 * WebElements
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar métodos
public class ScreenAddArtist
{
	private TextField txtName;
	private Select selectFieldCountry;
	private Select selectFieldGenre;
	private Button btnAddArtist;
	private Button btnSaveChanges;
	private PopUp popUp;
	
	
	public ScreenAddArtist()
	{
		txtName = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		selectFieldCountry = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		selectFieldGenre = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		btnAddArtist = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		btnSaveChanges = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		popUp = new PopUp("/html/body/div[5]/div","/html/body/div[5]/div/div/div[2]/div/div","/html/body/div[5]/div/div/div[2]/div/button");
	}

	public PopUp getPopUp()
	{
		
		return popUp;
	}


	public TextField getName()
	{
		txtName.loadById("artist_nameTextBox");
		return txtName;
	}

	public Select getCountry()
	{	
		selectFieldCountry = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		selectFieldCountry.loadById("artist_countryListBox");
		return selectFieldCountry;
	}

	public Select getGenre()
	{
		selectFieldGenre = SetUp.BUILDER.uiComponentBuilderInstance().selectFieldInstance();
		selectFieldGenre.loadById("artist_genereListBox");
		return selectFieldGenre;
	}

	public Button getBtnAddArtist()
	{
		btnAddArtist.loadById("artist_insertButton");
		return btnAddArtist;
	}

	public Button getBtnSaveChanges()
	{
		btnSaveChanges.loadById("artist_updateButton");
		return btnSaveChanges;
	}

	
	
}
