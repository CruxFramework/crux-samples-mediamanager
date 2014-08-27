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
import org.openqa.selenium.NoSuchElementException;

import br.ufmg.dcc.saotome.beholder.ui.Div;
import br.ufmg.dcc.saotome.beholder.ui.form.Button;

/**
 * Class description: Define os elementos basicos que um pop up tem, que são um texto e um botao de confirmação. Todas
 * as classes pop up devem estender essa classe
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar métodos
public class PopUp
{
	private Button btnOk;
	private Div divText;
	private String xpathBtnOk;
	private String xpathMsg;
	private String superXpath; 
	
	

	public PopUp(String superXpath, String xpathMsg,  String xpathBtnOk)
	{
		divText = SetUp.BUILDER.uiComponentBuilderInstance().divInterface();
		btnOk = SetUp.BUILDER.uiComponentBuilderInstance().buttonInstance();
		this.xpathBtnOk = xpathBtnOk;
		this.xpathMsg = xpathMsg;
		this.superXpath = superXpath;
	}
	
	
	

	public Div getDivText()
	{
		divText.loadByXPath(getXpathDivText());
		return divText;
	}

	public Button getBtnOk()
	{
		btnOk.loadByXPath(getXpathBtnOk());
		return btnOk;
	}

	public String getXpathBtnOk()
	{		
		return xpathBtnOk;
	}

	public void setXpathBtnOk(String xpathBtnOk)
	{		
		this.xpathBtnOk = xpathBtnOk;
	}

	public String getXpathDivText()
	{
		return xpathMsg;
	}

	public void setXpathDivText(String xpathDivText)
	{
		this.xpathMsg = xpathDivText;
	}

	public  boolean isDisplayedPopUp()
	{	
		try {
			Div popUp = SetUp.BUILDER.uiComponentBuilderInstance().divInterface();
			popUp.loadByXPath(getSuperXpath());
			return true; 
		} catch (NoSuchElementException e) 
		{	
			return false; 
		}
		 
	}

	public String getSuperXpath()
	{
		return superXpath;
	}

	public void setSuperXpath(String xpathPopUp)
	{
		this.superXpath = xpathPopUp;
	}
	
	public void confirmPopUp()
	{
		if(isDisplayedPopUp())
		{				
			getBtnOk().click();
		}
	}
	public String getMenssagePopUp()
	{
		return getDivText().getText();
	}
}

	
