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
package org.cruxframework.mediamanager.offline.client.dao;

import java.util.Date;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCountCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.crux.core.client.db.Index;
import org.cruxframework.crux.core.client.db.KeyRange;
import org.cruxframework.crux.core.client.db.ObjectStore;
import org.cruxframework.crux.core.client.db.Transaction;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;
import org.cruxframework.mediamanager.offline.client.reuse.Utils;

/**
 * Class descriptoin: Contains implementations of operations on the databases
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 *
 * @param <DTO>
 * @param <E>
 */
public abstract class AbstractDao<DTO extends AbstractDTO, E extends OfflineEntity<?>>
{

	/**
	 * Saves an entity (E) in a database.
	 * 
	 * @param entity
	 */
	public void save(final E entity)
	{
		getDatabase().put(new Object[]
		{ entity }, entity.getStoreName(), new DatabaseCallback()
		{

			@Override
			public void onSuccess()
			{

			}

			@Override
			public void onError(String message)
			{
				// TODO
				super.onError(message);
			}
		});
	}

	/**
	 * Save an entity (E) in a Database with DatabaseCallback
	 * (callback).
	 * 
	 * @param entity
	 * @param callback
	 */

	public void save(E entity, DatabaseCallback callback)
	{
		getDatabase().put(new Object[]
		{ entity }, entity.getStoreName(), callback);
	}

	/**
	 * Delete an Entity (E) in a Database with DatabaseCallback
	 * (callback).
	 * 
	 * @param id
	 */
	public void delete(Integer id, DatabaseCallback callback)
	{
		getDatabase().delete(id, getStoreName(), callback);
	}

	/**
	 * Search all record of a Store and returns a DatabaseCursor.
	 * 
	 * @param callback
	 */
	public void search(DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		objStore.openCursor(callback);
	}

	/**
	 * Search a record (by id) in ObjectStore and returns a DatabaseCursor.
	 * 
	 * @param id
	 * @param callback
	 */
	public void search(Integer id, DatabaseRetrieveCallback<E> callback)
	{
		getDatabase().get(id, getStoreName(), callback);
	}

	/**
	 * Search a record (by index) with an value (value) in Store and returns
	 * just one record.
	 * 
	 * @param value
	 * @param index
	 * @param callback
	 */
	public void search(String value, String index,
		DatabaseRetrieveCallback<E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, String, E> nameIndex = objStore.getIndex(index);
		nameIndex.get(value, callback);
	}
	
	
	/**
	 * Search a record (by index) with an value (value) in Store and returns
	 * a DatabaseCursorCallback.
	 * @param value
	 * @param index
	 * @param callback
	 */
	public void search(String value, String index,
		DatabaseCursorCallback<String, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readOnly);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, String, E> nameIndex = objStore.getIndex(index);
		KeyRange<String> keyRange = nameIndex.getKeyRangeFactory().only(value);
		nameIndex.openCursor(keyRange, callback);
	}
	

	/**
	 * Search all record search (by index) with a date is less then (value) in
	 * ObjectStore and returns a Cursor.
	 * 
	 * @param value
	 * @param index
	 * @param callback
	 */
	public void searchUpperBound(Date value, String index,
		DatabaseCursorCallback<Date, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readOnly);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, Date, E> nameIndex = objStore.getIndex(index);
		KeyRange<Date> keyrange = nameIndex.getKeyRangeFactory().upperBound(
			value);
		nameIndex.openCursor(keyrange, callback);
	}

	/**
	 * Search all record with (value)% in ObjectStore and returns a
	 * DatabaseCursor.
	 * 
	 * @param value
	 * @param callback
	 */
	public void searchLike(String value, String index,
		DatabaseCursorCallback<String, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readOnly);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, String, E> nameIndex = objStore.getIndex(index);
		/* Generate StringBound */
		String bound = Utils.StringBound(value);
		KeyRange<String> keyrange = nameIndex.getKeyRangeFactory().bound(value,
			bound);
		nameIndex.openCursor(keyrange, callback);
	}

	/**
	 * Search a set of records in the database through a Object. This Object
	 * contains a set of values ​​that records must apresentrar. (ObjBound)
	 * determines the limit of intevarlo in which the record should be selected.
	 * 
	 * indexCom determines the index compound set in database statement.
	 * 
	 * @param value
	 * @param objBound
	 * @param indexComp 
	 * @param callback
	 */
	public void searchObjectLike(Object[] obj, Object[] objBound,
		String indexComp, DatabaseCursorCallback<Object[], E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, Object[], E> indexComps = objStore.getIndex(indexComp);
		KeyRange<Object[]> keyRange = indexComps.getKeyRangeFactory().bound(
			obj, objBound);
		indexComps.openCursor(keyRange, callback);
	}

	
	/**
	 * Search all record with only (id) in ObjectStore and returns a
	 * DatabaseCursor.
	 * @param id
	 * @param callback
	 */
	public void searchLike(Integer id,
		DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readOnly);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, Integer, E> index = objStore.getIndex("artist.id");
		KeyRange<Integer> keyRange = index.getKeyRangeFactory().only(id);
		index.openCursor(keyRange, callback);
	}

	/**
	 * Count a record by value and index and returns total.
	 * 
	 * @param value
	 * @param index
	 * @param callback
	 */
	public void count(String value, String index, DatabaseCountCallback callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readOnly);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(getStoreName());
		Index<Integer, String, E> nameIndex = objStore.getIndex(index);
		KeyRange<String> keyrange = nameIndex.getKeyRangeFactory().only(value);
		nameIndex.count(keyrange, callback);
	}

	/**
	 * Count record by Object[] value and a indexComp, than returns count total.
	 * 
	 * @param value
	 * @param indexComp
	 * @param callback
	 */
	public void count(Object[] value, String indexComp,
		DatabaseCountCallback callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction
			.getObjectStore(getStoreName());
		Index<Integer, Object[], E> indexComps = objStore.getIndex(indexComp);
		KeyRange<Object[]> keyRange = indexComps.getKeyRangeFactory().only(
			value);
		indexComps.count(keyRange, callback);
	}

	/**
	 * Count record by Date value and a index, than returns count total.
	 * 
	 * @param value
	 * @param index
	 * @param callback
	 */
	public void count(Date value, String index,
		DatabaseCountCallback callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]
		{ getStoreName() }, Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction
			.getObjectStore(getStoreName());
		Index<Integer, Date, E> indexComps = objStore.getIndex(index);
		KeyRange<Date> keyRange = indexComps.getKeyRangeFactory().upperBound(
			value);
		indexComps.count(keyRange, callback);
	}

	
	/*********************
	 * Abstract Methods
	 *********************/
	protected abstract Database getDatabase();

	protected abstract String getStoreName();
}
