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

/**
 * Class description: 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: documentar classe e métodos
public class PopUpNoResultsFound extends PopUp
{
	public PopUpNoResultsFound(int option)
	{
		super();
		String xpathPadrao = new String();

		switch (option)
		{
			case 0:
				xpathPadrao = "/html/body/div[6]/div/div/div[2]/div";
				break;
			case 1:
				xpathPadrao = "/html/body/div[7]/div/div/div[2]/div";
				break;
			default:
				xpathPadrao = "";
				break;
		}

		setXpathBtnOk(xpathPadrao + "/button");
		setXpathDivText(xpathPadrao + "/div");
	}
}
