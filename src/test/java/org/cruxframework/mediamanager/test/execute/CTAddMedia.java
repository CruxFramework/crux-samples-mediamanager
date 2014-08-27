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
package org.cruxframework.mediamanager.test.execute;

import org.cruxframework.mediamanager.test.dataprovider.PVAddMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddMedia;
import org.cruxframework.mediamanager.test.procedure.PTSearchMedia;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: Esta classe realiza os casos de testes para tela Add media do Media Manager. Seus casos de testes
 * dependem, dos artista adicinados pelos CTAddArtist
 * 
 * Ao final destes testes serão incluindos na base as seguintes medias: - Black Rain - MEDIA EDITADA
 * 
 * Estas medias serão utilizados para os testes de Search Media
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: completar comentário dos métodos
@Test(groups = { "mediaManager", "addMedia" }, priority = 4)
public class CTAddMedia
{
	/** 
	 * Adiciona uma media e verifica se o pop up com a mensagem de "Successfully saved!" esta sendo exibido
	 * @param ct
	 * @param media
	 */
	@Test(enabled = true, dataProvider = "PV001_Medias", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P001_AddMedia(final String ct, Media media) 
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgSucess = PTAddMedia.addMedia(media);
		Assert.assertEquals(msgSucess, "Successfully saved!");
		Assert.assertEquals(PTAddMedia.isDisplayedPopUp(), false);
	}

	/** 
	 * Realiza o processo de adicionar uma media e logo em seguida alterar os dados que foram adicionados
	 * @param ct
	 * @param media
	 * @param newValues
	 */
	@Test(enabled = true, dataProvider = "PV002_SaveChanges", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P002_SaveChanges(final String ct, Media media, Media newValues) 
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgSaveChanges = PTAddMedia.saveChanges(media, newValues);
		Assert.assertEquals(msgSaveChanges, "Successfully saved!");
	}

	/** 
	 * Adiciona uma media e verifica se ela consta na base de dados.
	 * @param ct
	 * @param media
	 */
	@Test(enabled = true, dataProvider = "PV003_AddAndSearchMedia", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P003_AddAndSearchMedia(final String ct, Media media)  
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(media);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		QueryMedia queryMedia = new QueryMedia(media.getType(), media.getName(), "");
		Media resultSearch = PTSearchMedia.searchMedia(queryMedia).getMedia();
		Assert.assertEquals(media, resultSearch);
	}

	/** 
	 * Modifica uma media e verifica se as alterações feitas, constam na base de dados
	 * @param ct
	 * @param media
	 * @param newValues
	 */
	@Test(enabled = true, dataProvider = "PV004_ChangeAndSearchMedia", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P004_ChangeAndSearchMedia(final String ct, Media media, Media newValues)  
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.saveChanges(media, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		QueryMedia queryMedia = new QueryMedia(newValues.getType(), newValues.getName(), "");
		Media resultSearch = PTSearchMedia.searchMedia(queryMedia).getMedia();
		Assert.assertEquals(newValues, resultSearch);
	}

	/** 
	 * Adiciona uma media, deixando algum campo da tela sem preencher.
	 * @param ct
	 * @param media
	 */
	@Test(enabled = true, dataProvider = "PV005_FieldCleanToAdd", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P005_FieldCleanToAdd(final String ct, Media media)  
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgFillAllFields = PTAddMedia.addMedia(media);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}

	/** 
	 * Modifica os dados de uma media, e tenta salvar as alterações deixando algum campo sem preencher.
	 * @param ct
	 * @param media
	 * @param newValues
	 */
	@Test(enabled = true, dataProvider = "PV006_FieldCleanToChange", dataProviderClass = PVAddMedia.class, groups = { "branch" })
	public void P006_FieldCleanToChange(final String ct, Media media, Media newValues)  
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		String msgFillAllFields = PTAddMedia.saveChanges(media, newValues);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}
}
