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

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class PopUpDelete extends PopUp
{
	private Button btnCancel;

	public PopUpDelete()
	{
		super("/html/body/div[7]/div/div/div[2]/div/div", "/html/body/div[7]/div/div/div[2]/div/nav/button");
		btnCancel = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
	}

	public Button getBtnCancel()
	{
		btnCancel.loadByXPath("/html/body/div[7]/div/div/div[2]/div/nav/button[2]");
		return btnCancel;
	}
}
