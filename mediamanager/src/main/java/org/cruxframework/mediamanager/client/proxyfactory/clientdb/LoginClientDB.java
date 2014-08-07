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
package org.cruxframework.mediamanager.client.proxyfactory.clientdb;

import java.util.Date;

import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCountCallback;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.SingleViewContainer;
import org.cruxframework.mediamanager.client.controller.LoginController;
import org.cruxframework.mediamanager.client.controller.MenuController;
import org.cruxframework.mediamanager.client.controller.RootController;
import org.cruxframework.mediamanager.client.proxy.LoginProxy;
import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.offline.client.dao.impl.ArtistDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.CountryDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.GenreDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.MediaDao;
import org.cruxframework.mediamanager.offline.client.dao.impl.UserDao;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;
import org.cruxframework.mediamanager.offline.client.entity.Media;
import org.cruxframework.mediamanager.offline.client.entity.User;

/**Class description: 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 */
public class LoginClientDB extends ServiceClientDB implements LoginProxy
{
	private static boolean sessionActive = false;
	
	
	@Override
	public void login(final String login, final String password, final LoginController controller)
	{
		if (!getDatabase().isOpen())
		{
			getDatabase().open(new DatabaseCallback()
			{
				
				@Override
				public void onSuccess()
				{
					doLogin(login, password, controller);
				}
			});
		}else
		{
			doLogin(login, password, controller);
		}
		
	}
	
	private void doLogin(String login, String password, final LoginController controller)
	{
		Object[] obj = new Object[] {new String(login), new String(password)};
		UserDao.getInstance(getDatabase()).searchObjectOnly(obj, "loginPassword", 
			new DatabaseCountCallback()
			{
				
				@Override
				public void onSuccess(int result)
				{
					if (result > 0)
					{
						sessionActive = true;
						controller.LoginSuccessState(true);
					}
					else
					{
						controller.LoginSuccessState(false);
					}
				}
			});
	}
	
	
	/* (non-Javadoc)
	 * @see org.cruxframework.mediamanager.core.client.service.LoginService#isSessionActive()
	 */
	@Override
	public void isSessionActive(RootController controller)
	{
		controller.isSesionActiviteState(sessionActive);
		
		if (!getDatabase().isOpen()){
			getDatabase().open(new DatabaseCallback()
			{
				@Override
				public void onSuccess()
				{
					initDB();
				}
			});
		}
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void logout(MenuController controller)
	{
		sessionActive = false;
		((SingleViewContainer) Screen.get("views")).showView("login");
		controller.hideMenu();
	}

	
	
	private void initDB()
	{
		Country c = new Country();
		c.setName("Brazil");
		c.setId(1);
		CountryDao.getInstance(getDatabase()).save(c);
		
		Country c1 = new Country();
		c1.setName("England");
		c1.setId(2);
		CountryDao.getInstance(getDatabase()).save(c1);
		
		Country c2 = new Country();
		c2.setName("France");
		c2.setId(3);
		CountryDao.getInstance(getDatabase()).save(c2);
		
		Country c3 = new Country();
		c3.setName("Itali");
		c3.setId(4);
		CountryDao.getInstance(getDatabase()).save(c3);
		
		Country c4 = new Country();
		c4.setName("Jamaica");
		c4.setId(5);
		CountryDao.getInstance(getDatabase()).save(c4);
		
		Country c5 = new Country();
		c5.setName("Mexico");
		c5.setId(6);
		CountryDao.getInstance(getDatabase()).save(c5);
		
		Country c6 = new Country();
		c6.setName("United States");
		c6.setId(7);
		CountryDao.getInstance(getDatabase()).save(c6);
		
		Genre g1 = new Genre();
		g1.setName("Blues");
		g1.setId(1);
		GenreDao.getInstance(getDatabase()).save(g1);
		
		Genre g2 = new Genre();
		g2.setName("Country");
		g2.setId(2);
		GenreDao.getInstance(getDatabase()).save(g2);
		
		Genre g3 = new Genre();
		g3.setName("Electronic");
		g3.setId(3);
		GenreDao.getInstance(getDatabase()).save(g3);
		
		Genre g4 = new Genre();
		g4.setName("Jazz");
		g4.setId(4);
		GenreDao.getInstance(getDatabase()).save(g4);
		
		Genre g5 = new Genre();
		g5.setName("Reggae");
		g5.setId(5);
		GenreDao.getInstance(getDatabase()).save(g5);
		
		Genre g6 = new Genre();
		g6.setName("Rock");
		g6.setId(6);
		GenreDao.getInstance(getDatabase()).save(g6);
		
		Genre g7 = new Genre();
		g7.setName("World");
		g7.setId(7);
		GenreDao.getInstance(getDatabase()).save(g7);
		
		Genre g8 = new Genre();
		g8.setName("Metal");
		g8.setId(4);
		GenreDao.getInstance(getDatabase()).save(g8);
		
		Artist a1 =  new Artist();
		a1.setCountry(c4);
		a1.setGenre(g5);
		a1.setName("Bob Marley");
		a1.setId(1);
		ArtistDao.getInstance(getDatabase()).save(a1);
		
		Artist a2 =  new Artist();
		a2.setCountry(c1);
		a2.setGenre(g1);
		a2.setName("Eric Clapton");
		a2.setId(2);
		ArtistDao.getInstance(getDatabase()).save(a2);
		
		
		Artist a3 =  new Artist();
		a3.setCountry(c6);
		a3.setGenre(g8);
		a3.setName("Metallica");
		a3.setId(3);
		ArtistDao.getInstance(getDatabase()).save(a3);
		
		Artist a4 =  new Artist();
		a4.setCountry(c1);
		a4.setGenre(g6);
		a4.setName("The Beatles");
		a4.setId(4);
		ArtistDao.getInstance(getDatabase()).save(a4);
		
		Media m = new Media();
		m.setBorrowed(0);
		m.setDate(new Date());
		m.setId(1);
		m.setArtist(a4);
		m.setName("Abbey Road");
		m.setType(MediaType.CD);
		MediaDao.getInstance(getDatabase()).save(m);
		
		Media m1 = new Media();
		m1.setBorrowed(0);
		m1.setDate(new Date());
		m1.setId(2);
		m1.setArtist(a2);
		m1.setType(MediaType.CD);
		m1.setName("Back Home");
		MediaDao.getInstance(getDatabase()).save(m1);
		
		Media m2 = new Media();
		m2.setBorrowed(0);
		m2.setDate(new Date());
		m2.setId(3);
		m2.setArtist(a4);
		m2.setType(MediaType.CD);
		m2.setName("Help!");
		MediaDao.getInstance(getDatabase()).save(m2);
		
		
		Media m3 = new Media();
		m3.setBorrowed(0);
		m3.setDate(new Date());
		m3.setId(4);
		m3.setArtist(a3);
		m3.setType(MediaType.CD);
		m3.setName("Master of Puppets");
		MediaDao.getInstance(getDatabase()).save(m3);
		
		Media m4 = new Media();
		m4.setBorrowed(0);
		m4.setDate(new Date());
		m4.setId(5);
		m4.setArtist(a4);
		m4.setType(MediaType.CD);
		m4.setName("Sgt. Pepper's Lonely Hearts Club Band");
		MediaDao.getInstance(getDatabase()).save(m4);
		
		Media m5 = new Media();
		m5.setBorrowed(0);
		m5.setDate(new Date());
		m5.setId(6);
		m5.setArtist(a1);
		m5.setType(MediaType.CD);
		m5.setName("Soul Revolution");
		MediaDao.getInstance(getDatabase()).save(m5);
		
		Media m6 = new Media();
		m6.setBorrowed(0);
		m6.setDate(new Date());
		m6.setId(7);
		m6.setArtist(a3);
		m6.setType(MediaType.DVD);
		m6.setName("St. Anger");
		MediaDao.getInstance(getDatabase()).save(m6);
		
		Media m7 = new Media();
		m7.setBorrowed(0);
		m7.setDate(new Date());
		m7.setId(8);
		m7.setArtist(a4);
		m7.setType(MediaType.DVD);
		m7.setName("Yellow Submarine");
		MediaDao.getInstance(getDatabase()).save(m7);
		
		User u = new User();
		u.setId(1);
		u.setLogin("admin");
		u.setPassword("admin");
		UserDao.getInstance(getDatabase()).save(u);
		
	}
	

}
