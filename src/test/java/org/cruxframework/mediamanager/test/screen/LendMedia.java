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
import br.ufmg.dcc.saotome.beholder.ui.form.Checkbox;
import br.ufmg.dcc.saotome.beholder.ui.form.TextField;
import br.ufmg.dcc.saotome.beholder.ui.table.Table;

/**
 * Class description: This class represent the screen Lend Media.
 * 
 * This parameters equivalent the elements in screen This element are providers by Beholder Framework
 * 
 * @author guilherme.alecrim
 */
public class LendMedia
{
	private Checkbox cbBorrowed;
	private TextField txtName;
	private TextField txtDate;
	private Button btnCancel;
	private Button btnSave;
	private Table tbHeaderCalendar;
	private Table tbDayCalendar;
	private PopUp popUpDateInvalid;

	/**
	 * Default constructor. Initializes all elements.
	 */
	public LendMedia()
	{
		cbBorrowed = SetUp.getBuilder().uiComponentBuilderInstance().checkboxInstance();
		txtName = SetUp.getBuilder().uiComponentBuilderInstance().textFieldInstance();
		txtDate = SetUp.getBuilder().uiComponentBuilderInstance().textFieldInstance();
		btnCancel = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		btnSave = SetUp.getBuilder().uiComponentBuilderInstance().buttonInstance();
		tbHeaderCalendar = SetUp.getBuilder().uiComponentBuilderInstance().tableInstance();
		tbDayCalendar = SetUp.getBuilder().uiComponentBuilderInstance().tableInstance();

		// TODO: Implementar quando pop estiver sendo exibido.
		popUpDateInvalid = new PopUp("", "", "");
	}

	/*
	 * Accessors methods, this methods performed scan in the html before return the element this scan is necessary because the element can
	 * not is displayed in screen to the access.
	 */

	/**
	 * @return borrowed checkbox
	 */
	public Checkbox getCbBorrowed()
	{
		cbBorrowed.loadById("gwt-uid-1");
		return cbBorrowed;
	}

	/**
	 * @return name textfield
	 */
	public TextField getTxtName()
	{
		txtName.loadById("lend_nameTxt");
		return txtName;
	}

	/**
	 * @return date textfield
	 */
	public TextField getTxtDate()
	{
		txtDate.loadById("_mask_1");
		return txtDate;
	}

	/**
	 * @return cancel button
	 */
	public Button getBtnCancel()
	{
		btnCancel.loadById("lend_cancelBtn");
		return btnCancel;
	}

	/**
	 * @return save button
	 */
	public Button getBtnSave()
	{
		btnSave.loadById("lend_saveBtn");
		return btnSave;
	}

	/**
	 * @return header calendar table
	 */
	public Table getTbHeaderCalendar()
	{
		tbHeaderCalendar.loadByXPath("/html/body/div[8]/div/table/tbody/tr/td/table");
		return tbHeaderCalendar;
	}

	/**
	 * @return calendar table
	 */
	public Table getTbDayCalendar()
	{
		tbDayCalendar.loadByXPath("/html/body/div[8]/div/table/tbody/tr[2]/td/table");
		return tbDayCalendar;
	}

	/**
	 * @return date popup
	 */
	public PopUp getPopUpDateInvalid()
	{
		return popUpDateInvalid;
	}
}
