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
	private String xpathBntCancel;
	
	public PopUpDelete(String superXpath, String xpathMsg,  String xpathBtnOk, String xpathBtnCancel)
	{
		super(superXpath,xpathMsg,xpathBtnOk);
		btnCancel = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		this.xpathBntCancel = xpathBtnCancel;
	}

	public Button getBtnCancel()
	{
		btnCancel.loadByXPath(getXpathBntCancel());
		return btnCancel;
	}
	
	public void CancelPopUp()
	{
		if(isDisplayedPopUp())
		{
			getBtnCancel().click();
		}
	
	}

	public String getXpathBntCancel() {
		return xpathBntCancel;
	}

	public void setXpathBntCancel(String xpathBntCancel) {
		this.xpathBntCancel = xpathBntCancel;
	}
}
