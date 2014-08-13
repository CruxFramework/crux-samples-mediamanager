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

import org.cruxframework.mediamanager.test.dataprovider.PVSearchMedia;
import org.cruxframework.mediamanager.test.model.LineTableSearchMedia;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.model.QueryMedia;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTSearchMedia;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class description: Esta classe realiza os testes da tela Search media e assume que as medias adicionadas pelos casos
 * de testes CTAddMedia constam na base de dados.
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: completar comentário de métodos
@Test(groups = { "mediaManager", "searchMedia" }, priority = 6)
public class CTSearchMedia
{
	/**
	 * Pesquisa por uma media que consta na base de dados, e verifica se esta media é exibida na tabela de resultados.
	 * @param ct
	 * @param media
	 * @param query
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV001_SearchMediaWhitExist", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P001_SearchMediaWhitExist(final String ct, Media media, QueryMedia query) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		Media resultSearch = PTSearchMedia.searchMedia(query).getMedia();
		Assert.assertEquals(resultSearch, media);
	}

	/**
	 * Pesquisa por uma media que não consta na base de dados e verifica se o pop up com a mensagem "No results found." é
	 * exibodo.
	 * @param ct
	 * @param query
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV002_SearchMediaWhitNotExist", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P002_SearchMediaWhitNotExist(final String ct, QueryMedia query) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.searchMedia(query);
		String noResultsFound = PTSearchMedia.getScreenSearchMedia().getPopUpNoResultsFound(0).getDivText().getText();
		Assert.assertEquals(noResultsFound, "No results found.");
	}

	/**
	 * Edita os dados de uma media e verifica se o pop up com a mensagem "Successfully saved!" é exibido.
	 * @param ct
	 * @param query
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV003_EditMedia", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P003_EditMedia(final String ct, QueryMedia query, Media newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String sucessEditMedia = PTSearchMedia.editMedia(query, newValues);
		Assert.assertEquals(sucessEditMedia, "Successfully saved!");
	}

	/**
	 * Deleta uma media e verifica se o pop up com a mensage "Record successfully deleted!" é exibido
	 * @param ct
	 * @param query
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV004_DeleteMedia", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P004_DeleteMedia(final String ct, QueryMedia query) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String sucessOnDelete = PTSearchMedia.deleteMedia(query);
		Assert.assertEquals(sucessOnDelete, "Record successfully deleted!");
	}

	/**
	 * Edita os dados de uma media e realiza uma busca para verificar se os dados foram devidamente alterados na base de
	 * dados .
	 * @param ct
	 * @param media
	 * @param newValues
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV005_EditAndSearchMedia", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P005_EditAndSearchMedia(final String ct, Media media, Media newValues) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		media = PTSearchMedia.editAndSearchMedia(media, newValues);
		Assert.assertEquals(media, newValues);
	}

	/**
	 * Deleta uma media e realiza uma busca para verificar se a media foi devidamente deletada da base de dados.
	 * @param ct
	 * @param query
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV006_DeleteAndSearchMedia", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P006_DeleteAndSearchMedia(final String ct, QueryMedia query) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		String noResultsFound = PTSearchMedia.deleteAndSearhcMedia(query);
		Assert.assertEquals(noResultsFound, "No results found.");
	}

	/**
	 * Realiza o procedimento de emprestar uma media
	 * @param ct
	 * @param query
	 * @param name
	 * @param date
	 * @throws InterruptedException
	 */
	@Test(enabled = false, dataProvider = "PV007_LendBorrwed", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P007_LendBorrwed(final String ct, QueryMedia query, String name, String date) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(query, name, date);
		LineTableSearchMedia lsm = PTSearchMedia.searchMedia(query);
		Assert.assertEquals(lsm.getPerson(), "Guilherme Alecrim");
	}

	/**
	 * Verifica se o não preenchimento do checkBox "Borrowed" da tela LendMedia bloqueia os demais campos para
	 * preenchimento.
	 * @param ct
	 * @param query
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV008_LendOnBorred", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P008_LendOnBorred(final String ct, QueryMedia query) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMediaUnborrowed(query);
		Assert.assertEquals(PTSearchMedia.getScreenSearchMedia().getLendMedia().getTxtName().getValue(), "");
		Assert.assertEquals(PTSearchMedia.getScreenSearchMedia().getLendMedia().getTxtDate().getValue(), "");
	}

	/**
	 * Verifica se na tela LendMedia ao preencher uma data maior que a data atual é exibido o pop up.
	 * @param ct
	 * @param query
	 * @param name
	 * @param date
	 * @throws InterruptedException
	 */
	@Test(enabled = true, dataProvider = "PV009_InvalidDate", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P009_InvalidDate(final String ct, QueryMedia query, String name, String date) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		PTSearchMedia.lendMedia(query, name, date);
		String msg = PTSearchMedia.getScreenSearchMedia().getLendMedia().getPopUpDateInvalid().getDivText().getText();
		Assert.assertEquals(msg, "The date you are trying to use is greater than the actual date.");
	}

	@Test(enabled = false, dataProvider = "PV010_ChangeValuesOfSearch", dataProviderClass = PVSearchMedia.class, groups = { "branch" })
	public void P010_ChangeValuesOfSearch(final String ct, QueryMedia firstQuery, QueryMedia secondQuery,
			LineTableSearchMedia resultFirstQuery, LineTableSearchMedia resultSecondQuery) throws InterruptedException
	{
		Navegation.acessMenu(EnumMenu.SEARCH_MEDIA);
		LineTableSearchMedia lt = PTSearchMedia.searchMedia(firstQuery);
		Assert.assertEquals(lt, resultFirstQuery);
		lt = PTSearchMedia.searchMedia(secondQuery);
		Assert.assertEquals(lt, resultSecondQuery);
	}
}
