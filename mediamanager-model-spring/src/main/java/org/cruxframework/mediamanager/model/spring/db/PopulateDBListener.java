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
package org.cruxframework.mediamanager.model.spring.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.cruxframework.mediamanager.core.client.enums.MediaType;
import org.cruxframework.mediamanager.model.jpa.entity.Artist;
import org.cruxframework.mediamanager.model.jpa.entity.Country;
import org.cruxframework.mediamanager.model.jpa.entity.Genre;
import org.cruxframework.mediamanager.model.jpa.entity.Media;
import org.cruxframework.mediamanager.model.jpa.entity.User;
import org.cruxframework.mediamanager.model.spring.utils.SpringUtils;

/**
 * Class description: 
 * @author alexandre.costa
 */
public class PopulateDBListener implements ServletContextListener
{
	private static final Logger LOGGER = 
		Logger.getLogger(PopulateDBListener.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		EntityManagerFactory emf = SpringUtils.get().getBean(
			EntityManagerFactory.class); 
		
			
		EntityManager em = null;
		EntityTransaction transaction = null;
		try
		{
			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			List<Genre> generes = new ArrayList<Genre>();
			List<Country> countries = new ArrayList<Country>();
			
			populateUsersTable(em);
			populateGeneresTable(em, generes);
			populateCountriesTable(em, countries);
			populateMediasTable(em, generes, countries);
			
			transaction.commit();
			em.close();
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "", e);
			if (transaction != null)
			{
				transaction.rollback();
			}
			
		} finally
		{
			if (transaction != null && transaction.isActive())
			{
				transaction.rollback();
			}
			
			if (em != null && em.isOpen())
			{
				em.close();
			}
		}
	}
	
	private static void populateUsersTable(EntityManager entityManager)
	{
		entityManager.persist(new User("Administrator", "admin", "admin"));
	}
	
	private static void populateMediasTable(EntityManager entityManager, 
		List<Genre> genres, List<Country> countries)
	{
		Artist artist = new Artist("Metallica", genres.get(0), countries.get(0));
		entityManager.persist(artist);
		
		
		entityManager.persist(new Media("St. Anger", MediaType.DVD, artist, false));
		entityManager.persist(new Media("Master of Puppets", MediaType.CD, artist, false));
		
		artist = new Artist("Eric Clapton", genres.get(3), countries.get(2));
		entityManager.persist(artist);
		entityManager.persist(new Media("Back Home", MediaType.CD, artist, false));
		
		artist = new Artist("The Beatles", genres.get(2), countries.get(2));
		entityManager.persist(artist);
		entityManager.persist(new Media("Yellow Submarine", MediaType.DVD, artist, false));
		entityManager.persist(new Media("Abbey Road", MediaType.CD, artist, false));
		entityManager.persist(new Media("Sgt. Pepper's Lonely Hearts Club Band", MediaType.CD, artist, false));
		entityManager.persist(new Media("Help!", MediaType.CD, artist, false));
	
		artist = new Artist("Bob Marley", genres.get(1), countries.get(4));
		entityManager.persist(artist);
		entityManager.persist(new Media("Soul Revolution", MediaType.CD, artist, false));
		
	}
	
	private static void populateGeneresTable(EntityManager entityManager, 
		List<Genre> generes)
	{
		generes.add(new Genre("Metal")); 
		generes.add(new Genre("Reggae"));
		generes.add(new Genre("Rock"));
		generes.add(new Genre("Blues"));  
		generes.add(new Genre("Country"));  
		generes.add(new Genre("Electronic"));
		generes.add(new Genre("Jazz"));  
		generes.add(new Genre("World"));

		for (Genre genere : generes)
		{
			entityManager.persist(genere);
		}
	}
	
	private static void populateCountriesTable(EntityManager entityManager, 
		List<Country> countries)
	{
		countries.add(new Country("United States"));
		countries.add(new Country("Brazil"));
		countries.add(new Country("England"));
		countries.add(new Country("Mexico"));		
		countries.add(new Country("Jamaica"));
		countries.add(new Country("Itali"));
		countries.add(new Country("France"));
		
		for (Country country : countries)
		{
			entityManager.persist(country);
		}
	}
}
