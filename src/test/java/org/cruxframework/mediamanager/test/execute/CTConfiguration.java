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

import org.cruxframework.mediamanager.test.model.Artist;
import org.cruxframework.mediamanager.test.model.Media;
import org.cruxframework.mediamanager.test.procedure.Navegation;
import org.cruxframework.mediamanager.test.procedure.PTAddArtist;
import org.cruxframework.mediamanager.test.procedure.PTAddMedia;
import org.cruxframework.mediamanager.test.procedure.SetUp;
import org.cruxframework.mediamanager.test.util.EnumMenu;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Class description: Classe que irá executar os parametros de configuração para
 * os testes
 * 
 * @author guilherme.alecrim
 */
// TODO guilherme.alecrim: comentar classe e métodos

public class CTConfiguration
{
	/**
	 * Instancia o BROWSER  e o BUILDER
	 * @param url: URL da aplicação 
	 * @throws Exception
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "url" })
	public void setUp(String url) throws Exception
	{
		SetUp.setUP(url);
		cadastrarMassaDeDados();
		
	}
	


	public void cadastrarMassaDeDados() 
	{
		Navegation.loginSucess("admin", "admin");
		System.out.println(">>>>>> adicinando artistas ...");
		artists();
		System.out.println(">>>>>> adicinando medias ...");
		medias();
		Navegation.signOut();		
		System.out.println(">>>>>> Dados cadastrados com sucesso. Executando testes...");
	}
	
	public void artists()
	{
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("Artista01P006","Mexico","Metal"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("Artista02P006","England","Jazz"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("ArtistForDelete","England","Jazz"));
		Navegation.acessMenu(EnumMenu.ADD_ARTIST);
		PTAddArtist.addArtist(new Artist("ArtistForEdit","United States","Metal"));
	}
	
	public void medias()
	{
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD","MediaForDelete1","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD","MediaForDelete2","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD","MediaForBorrowed","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD","MediaForSearch","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD","MediaForSearch2","Artista02P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD","MediaForEdit","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("DVD","MediaForEdit2","Artista01P006"));
		Navegation.acessMenu(EnumMenu.ADD_MEDIA);
		PTAddMedia.addMedia(new Media("CD", "MediaForEdit3", "Artista01P006"));
		
	}
}
