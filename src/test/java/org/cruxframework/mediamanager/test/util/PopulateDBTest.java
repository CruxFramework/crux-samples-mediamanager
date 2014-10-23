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
package org.cruxframework.mediamanager.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.cruxframework.mediamanager.server.db.PopulateDBListener;
import org.cruxframework.mediamanager.server.entity.Artist;
import org.cruxframework.mediamanager.server.entity.Country;
import org.cruxframework.mediamanager.server.entity.Genre;
import org.cruxframework.mediamanager.server.entity.Media;
import org.cruxframework.mediamanager.server.entity.User;
import org.cruxframework.mediamanager.shared.enums.MediaType;

/**
 * Class description: Populates database on server startup.
 * 
 * @author guilherme.alecrim
 */
public class PopulateDBTest 
{
	private static final String ADMIN = "admin";
	private static final Logger LOGGER = Logger.getLogger(PopulateDBListener.class.getName());
	
	public void populateDbTest() 
	{
		List<Genre> generes = new ArrayList<Genre>();
		List<Country> countries = new ArrayList<Country>();
		List<Artist> artists = new ArrayList<Artist>();
		
		EntityManagerFactory emf = SpringUtilsTest.get().getBean(EntityManagerFactory.class); 
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
			populateUsers(em);
			populateGenre(em, generes);
			populateCountry(em, countries);
			populateArtists(em, artists, generes, countries);
			populateMedias(em,artists);
		transaction.commit();
		em.close();
		
	}
	

	public void populateUsers(EntityManager entityManager)
	{
		entityManager.persist(new User("Administrator", ADMIN, ADMIN));
	}
	
	public void populateCountry(EntityManager entityManager, List<Country> countries)
	{
		countries.add(new Country("United States"));
		countries.add(new Country("Brazil"));
		countries.add(new Country("England"));
		countries.add(new Country("Mexico"));
		countries.add(new Country("Jamaica"));
		countries.add(new Country("Italia"));
		countries.add(new Country("France"));

		for (Country country : countries)
		{
			entityManager.persist(country);
		}
	}
	
	public void populateGenre(EntityManager entityManager, List<Genre> generes)
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
	
	public void populateArtists(EntityManager entityManager, List<Artist> artists, List<Genre> genre, List<Country> contry)
	{
		artists.add(new Artist("Artista01P006", genre.get(0), contry.get(3)));
		artists.add(new Artist ("Artista02P006", genre.get(6),contry.get(2)));
		artists.add(new Artist ("ArtistForDelete", genre.get(6),contry.get(2)));
		artists.add(new Artist ("ArtistForEdit", genre.get(0), contry.get(3)));
		
		for (Artist a: artists)
		{
			entityManager.persist(a);
		}
		
	}
	
	public void populateMedias(EntityManager entityManager, List<Artist> artist)
	{
		List<Media> medias = new ArrayList<Media>();
		medias.add(new Media("MediaForDelete1", MediaType.CD, artist.get(0), false));
		medias.add(new Media("MediaForDelete2", MediaType.CD, artist.get(0), false));
		medias.add(new Media("MediaForBorrowed", MediaType.DVD, artist.get(0), false));
		medias.add(new Media("MediaForSearch", MediaType.CD, artist.get(0), false));
		medias.add(new Media("MediaForSearch2", MediaType.DVD, artist.get(1), false));
		medias.add(new Media("MediaForEdit", MediaType.DVD, artist.get(0), false));
		medias.add(new Media("MediaForEdit2", MediaType.DVD, artist.get(0), false));
		medias.add(new Media("MediaForEdit3", MediaType.CD, artist.get(0), false));
		
		medias.add(new Media("CD1Borrowed", MediaType.CD, artist.get(1), true));
		medias.add(new Media("CD2Borrowed", MediaType.CD, artist.get(1), true));
		medias.add(new Media("CD3NotBorrowed", MediaType.CD, artist.get(1), false));
		
		medias.add(new Media("DVD1Borrowed", MediaType.DVD, artist.get(1), true));
		medias.add(new Media("DVD2Borrowed", MediaType.DVD, artist.get(1), true));
		medias.add(new Media("DVD3Borrowed", MediaType.DVD, artist.get(1), false));
		
		
		
		for (Media m : medias)
		{
			entityManager.persist(m);
		}
	}

	public void removeArtist()
	{
		
		EntityManagerFactory emf = SpringUtilsTest.get().getBean(EntityManagerFactory.class); 
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		Query query;
		transaction.begin();
		
		query = em.createQuery("DELETE FROM Media");
		query.executeUpdate();
		query = em.createQuery("DELETE FROM Artist");
		query.executeUpdate();
		
		transaction.commit();
		em.close();
	}
	
				
			
	
	
}
