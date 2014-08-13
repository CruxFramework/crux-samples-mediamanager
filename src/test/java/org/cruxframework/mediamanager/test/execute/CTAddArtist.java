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

import org.cruxframework.mediamanager.test.dataprovider.PVAddArtist;
import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddArtist;
import org.cruxframework.mediamanager.test.procedure.PTSearchArtist;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: Esta classe contêm os casos de testes para a tela Add artist do Media Manager, Os testes foram
 * definidos com prioridade 1 (primeiros testes a serem executados), pois todos os outros casos de testes dependem dos
 * artistas que são adicionados por estes casos de testes.
 * 
 * Ao final destes testes serão incluidos na base os seguintes artistas: - Test5 - Teste3 - Artista Editado -
 * Artista01P006 - Artista02P006 - Artista03P006 - Artista04P006
 * 
 * Estes artistas serão utilizados para os testes de Search Artist e Add Media
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: completar comentário dos métodos
@Test(groups = { "mediaManager", "addArtist" }, priority = 3)
public class CTAddArtist
{

	/**
	 * Adiciona um artista e verifica se o pop up com a mensagem "Successfully saved!" esta sendo exibido
	 * @param ct
	 * @param artist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001_Artists", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P001_AddArtist(final String ct, Artist artist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		Thread.sleep(6000);
		String msgSucess = PTAddArtist.addArtist(artist);
		Assert.assertEquals(msgSucess, "Successfully saved!");
	}

	/**
	 * Adiciona um artista e logo em seguida altera os dados e verifica se os pop up com a mensagem "Successfully saved!"
	 * foi exibido.
	 * @param ct
	 * @param artist
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV002_AddAndChangeArtists", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P002_AddAndSaveChanges(final String ct, Artist artist, Artist newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String sucessChange = PTAddArtist.addAndChangerArtist(artist, newValues);
		Assert.assertEquals(sucessChange, "Successfully saved!");
	}

	/**
	 * Adiciona um artista e logo em seguida realiza uma busca para verificar se o artista adicionado consta na base.
	 * @param ct
	 * @param artist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV003_AddAndSearchArtists", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P003_AddArtistAndSearchArtist(final String ct, Artist artist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(artist);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(artist.getName());
		Assert.assertEquals(artist, resultSearch);
	}

	/**
	 * Modifica os dados de um artista e logo em seguida realiza uma busca para verificar se os dados foram devidamente
	 * alterados na base.
	 * @param ct
	 * @param artist
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV004_ChangeAndSearchArtist", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P004_ChangeAndSearchArtist(final String ct, Artist artist, Artist newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addAndChangerArtist(artist, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(newValues.getName());
		Assert.assertEquals(newValues, resultSearch);
	}

	/**
	 * Adiciona um artista deixando alguns campos sem preencher, verificando se o pop up com a mensgem "Fill all fields."
	 * é exibido
	 * @param ct
	 * @param artist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV005_FieldCleanToAdd", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P005_FieldCleanToAdd(final String ct, Artist artist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String msgFillAllFields = PTAddArtist.addArtist(artist);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}

	/**
	 * Modifica os dados de um artista deixando alguns campos sem preencher, verificando se o pop up com a mensgem
	 * "Fill all fields." é exibido
	 * @param ct
	 * @param artist
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV006_FieldCleanToChange", dataProviderClass = PVAddArtist.class, groups = { "MM_1.0" })
	public void P006_FieldCleanToChange(final String ct, Artist artist, Artist newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		String msgFillAllFields = PTAddArtist.addAndChangerArtist(artist, newValues);
		Assert.assertEquals(msgFillAllFields, "Fill all fields.");
	}
}
