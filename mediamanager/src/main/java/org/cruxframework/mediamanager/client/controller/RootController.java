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
package org.cruxframework.mediamanager.client.controller;

import java.util.Date;

import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.rpc.AsyncCallbackAdapter;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractController;
import org.cruxframework.mediamanager.core.client.service.LoginServiceAsync;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.CountryDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.GenreDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.MediaDao;
import org.cruxframework.mediamanager.offline.client.db.DbMediamanager;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;
import org.cruxframework.mediamanager.offline.client.entity.Media;

/**
 * Class description: 
 * @author alexandre.costa
 */
@Controller("rootController")
public class RootController extends AbstractController
{
	@Inject
	public LoginServiceAsync loginServiceAsync;
	
	@Inject
	public DbMediamanager database;
	
	@Expose
	public void onActivate()
	{
		database.open(new DatabaseCallback()
		{
			
			@Override
			public void onSuccess()
			{
				initDB();
			}
		});
		
		loginServiceAsync.isSessionActive(new AsyncCallbackAdapter<Boolean>()
		{
			@Override
			public void onComplete(Boolean result)
			{
				if (result != null && result)
				{
					Screen.get("menuView").setVisible(true);
					((SimpleViewContainer) Screen.get("views")).showView("statistics");
				} else
				{
					((SimpleViewContainer) Screen.get("views")).showView("login");
				}
			}
		});
	}
	
	private void initDB(){
		Country c = new Country();
		c.setName("Brasil");
		c.setId(1);
		CountryDao.getInstance().save(c, database);
		
		Country c1 = new Country();
		c1.setName("Argentina");
		c1.setId(2);
		CountryDao.getInstance().save(c1, database);
		
		Country c2 = new Country();
		c2.setName("Uruguai");
		c2.setId(3);
		CountryDao.getInstance().save(c2, database);
		
		Genre g1 = new Genre();
		g1.setName("Pop");
		g1.setId(1);
		GenreDao.getInstance().save(g1, database);
		
		Genre g2 = new Genre();
		g2.setName("Sertanejo");
		g2.setId(2);
		GenreDao.getInstance().save(g2, database);
		
		Artist a1 =  new Artist();
		a1.setCountry(c);
		a1.setGenre(g1);
		a1.setName("Roberto Carlos");
		a1.setId(1);
		ArtistDao.getInstance().save(a1, database);
		
		Artist a2 =  new Artist();
		a2.setCountry(c1);
		a2.setGenre(g2);
		a2.setName("Luan Santana");
		a2.setId(2);
		ArtistDao.getInstance().save(a2, database);
		
		Media m = new Media();
		m.setBorrowed(false);
		m.setDate(new Date());
		m.setId(1);
		m.setArtist(a1);
		m.setName("Esse Cara Sou Eu");
		MediaDao.getInstance().save(m, database);
		
		Media m1 = new Media();
		m1.setBorrowed(false);
		m1.setDate(new Date());
		m1.setId(2);
		m1.setArtist(a1);
		m1.setName("Pra Sempre: Década de 90");
		MediaDao.getInstance().save(m1, database);
		
	}
	
}
