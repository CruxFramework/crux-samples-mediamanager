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
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class LendMedia
{
	private Checkbox cbBorrowed;
	private TextField txtName;
	private TextField txtDate;
	private Button btnCancel;
	private Button btnSave;
	private Table tbHeaderCalendar;
	private Table tbDayCalendar;
	private PopUpDateInvalid popUpDateInvalid;

	public LendMedia()
	{
		cbBorrowed = SetUp.BUILDER.uiComponentBuilderInstance().checkboxInstance();
		txtName = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		txtDate = SetUp.BUILDER.uiComponentBuilderInstance().textFieldInstance();
		btnCancel = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		btnSave = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		tbHeaderCalendar = SetUp.BUILDER.uiComponentBuilderInstance().tableInstance();
		tbDayCalendar = SetUp.BUILDER.uiComponentBuilderInstance().tableInstance();
		popUpDateInvalid = new PopUpDateInvalid();
	}

	public Checkbox getCbBorrowed()
	{
		cbBorrowed.loadById("gwt-uid-1");
		return cbBorrowed;
	}

	public TextField getTxtName()
	{
		txtName.loadById("lend_nameTxt");
		return txtName;
	}

	public TextField getTxtDate()
	{
		txtDate.loadById("lend_date");
		return txtDate;
	}

	public Button getBtnCancel()
	{
		btnCancel.loadById("lend_cancelBtn");
		return btnCancel;
	}

	public Button getBtnSave()
	{
		btnSave.loadById("lend_saveBtn");
		return btnSave;
	}

	public Table getTbHeaderCalendar()
	{
		tbHeaderCalendar.loadByXPath("/html/body/div[8]/div/table/tbody/tr/td/table");
		return tbHeaderCalendar;
	}

	public Table getTbDayCalendar()
	{
		tbDayCalendar.loadByXPath("/html/body/div[8]/div/table/tbody/tr[2]/td/table");
		return tbDayCalendar;
	}

	public PopUpDateInvalid getPopUpDateInvalid()
	{
		return popUpDateInvalid;
	}
}
