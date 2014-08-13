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

import org.cruxframework.mediamanager.test.dataprovider.PVSearchArtist;
import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTSearchArtist;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: Esta classe realiza os testes da tela Search Artist e assume que os artistas adicionados pelos
 * casos de testes CTAddArtist estão cadastrados na base.
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: completar comentário de métodos
@Test(groups = { "mediaManager", "searchArtist" }, priority = 5)
public class CTSearchArtist
{
	/**
	 * Pesquisa por um artista cadastrado e verifica se ele esta sendo retornado na tabela de resultado.
	 * @param ct
	 * @param nameArtist
	 * @param artist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001_SearchArtist", dataProviderClass = PVSearchArtist.class, groups = { "branch" })
	public void P001_SearchArtist(final String ct, String nameArtist, Artist artist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist resultSearch = PTSearchArtist.searchArtist(nameArtist);
		Assert.assertEquals(resultSearch, artist);
	}

	/**
	 * Pesquisa por um artista que não existe na base e valida se o pop up com a mensagem "No results found." é exibido.
	 * @param ct
	 * @param nameArtist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV002_ArtistNoExist", dataProviderClass = PVSearchArtist.class, groups = { "branch" })
	public void P002_ArtistNoExist(final String ct, String nameArtist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.searchArtist(nameArtist);
		String msgNotFound = PTSearchArtist.getMessageNoResultsNotFound();
		Assert.assertEquals(msgNotFound, "No results found.");
	}

	/**
	 * Pesquisa por um artista e o deleta, e verificando se ao pop up com a mensagem "Record successfully deleted!" foi
	 * exibido
	 * @param ct
	 * @param nameArtist
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV003_DeleteArtist", dataProviderClass = PVSearchArtist.class, groups = { "branch" })
	public void P003_DeleteArtist(final String ct, String nameArtist) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		String sucess = PTSearchArtist.deleteArtist(nameArtist);
		Assert.assertEquals(sucess, "Record successfully deleted!");
	}

	/**
	 * Pesquisa por um artista e edita seus dados, logo em seguida é feita uma nova pesquisa para validar se os dados
	 * foram devidamente modificados.
	 * @param ct
	 * @param artist
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV004_EditAndSearchArtist", dataProviderClass = PVSearchArtist.class, groups = { "branch" })
	public void P004_EditAndSearchArtist(final String ct, Artist artist, Artist newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.editArtist(artist, newValues);
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		PTSearchArtist.searchArtist(artist.getName());
		String msgNotFound = PTSearchArtist.getScreenSearchArtist().getPopUpNoResultsFound().getDivText().getText();
		PTSearchArtist.getScreenSearchArtist().getPopUpNoResultsFound().getBtnOk().click();
		Assert.assertEquals(msgNotFound, "No results found.");
		Artist resultSearch = PTSearchArtist.searchArtist(newValues.getName());
		Assert.assertEquals(newValues, resultSearch);
	}

	@Test(enabled = false, dataProvider = "PV005_ChangeValuesSearch", dataProviderClass = PVSearchArtist.class, groups = { "branch" })
	public void P005_ChangeValuesSearch(String ct, String firstQuery, String secondQuery, Artist resultFirstQuery,
			Artist resultSecondQuery) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_ARTIST);
		Artist fq = PTSearchArtist.searchArtist(firstQuery);
		Assert.assertEquals(fq, resultFirstQuery);
		Artist sq = PTSearchArtist.searchArtist(secondQuery);
		Assert.assertEquals(sq, resultSecondQuery);
	}
}
