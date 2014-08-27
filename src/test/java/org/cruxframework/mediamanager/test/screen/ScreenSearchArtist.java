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

import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.procedure.SetUp;

import br.ufmg.dcc.saotome.beholder.ui.Div;
import br.ufmg.dcc.saotome.beholder.ui.form.Button;
import br.ufmg.dcc.saotome.beholder.ui.form.TextField;

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class ScreenSearchArtist
{
	private TextField txtName;
	private Button btnSearch;
	private Artist lineTable;
	private Button btnEditArtist;
	private Button btnDeleteArtist;
	private PopUpDelete popUpDelete;
	private PopUp popUpSucessDelete;
	private PopUp popUpNoResultsFound;

	public ScreenSearchArtist()
	{
		txtName = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		btnSearch = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();

		lineTable = new Artist();
		btnEditArtist = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		btnDeleteArtist = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();

		popUpDelete = new PopUpDelete("/html/body/div[5]/div",
				"/html/body/div[5]/div/div/div[2]/div/div", 
				"/html/body/div[5]/div/div/div[2]/div/nav/button ",
				"/html/body/div[5]/div/div/div[2]/div/nav/button[2]"
				);
	
		popUpNoResultsFound = new PopUp(
				"/html/body/div[4]/div",
				"/html/body/div[4]/div/div/div[2]/div/div",
				"/html/body/div[4]/div/div/div[2]/div/button"
				);
		
		popUpSucessDelete = new PopUp(
				"/html/body/div[4]/div",
				"/html/body/div[4]/div/div/div[2]/div/div",
				"/html/body/div[4]/div/div/div[2]/div/button"
				);
	
	}

	public TextField getTxtName()
	{
		txtName.loadById("artists_nameTextBox");
		return txtName;
	}

	public Button getBtnSearch()
	{
		btnSearch.loadById("artists_pesquisarButton");
		return btnSearch;
	}

	public Artist getFirstItemSearchTable()
	{
		Div line = SetUp.BUILDER.uiComponentBuilderInstance().divInterface();
		line.loadByXPath("/html/body/div[3]/div[2]/div/div[2]/div/div/div/table/tbody/tr[2]/td/div/div");
		lineTable.setName(line.getText());
		line.loadByXPath("/html/body/div[3]/div[2]/div/div[2]/div/div/div/table/tbody/tr[2]/td[2]/div/div");
		lineTable.setCountry(line.getText());
		line.loadByXPath("/html/body/div[3]/div[2]/div/div[2]/div/div/div/table/tbody/tr[2]/td[3]/div/div");
		lineTable.setGenre(line.getText());
		return lineTable;
	}

	public Button getBtnEditArtist()
	{
		btnEditArtist.loadById("artists_edit");
		return btnEditArtist;
	}

	public Button getBtnDeleteArtist()
	{
		btnDeleteArtist.loadById("artists_delete");
		return btnDeleteArtist;
	}

	public PopUpDelete getPopUpDelete()
	{		
		return popUpDelete;
	}

	public PopUp getPopUpSucessDelete()
	{
		return popUpSucessDelete;
	}

	public PopUp getPopUpNoResultsFound()
	{
//		popUpNoResultsFound.setSuperXpath("/html/body/div[4]/div");
//		popUpNoResultsFound.setXpathBtnOk("/html/body/div[4]/div/div/div[2]/div/button");
//		popUpNoResultsFound.setXpathDivText("/html/body/div[4]/div/div/div[2]/div/div");
		return popUpNoResultsFound;
	}

}
