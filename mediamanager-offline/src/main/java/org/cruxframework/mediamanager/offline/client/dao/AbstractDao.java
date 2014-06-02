package org.cruxframework.mediamanager.offline.client.dao;

import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.crux.core.client.db.DatabaseCallback;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;
import org.cruxframework.crux.core.client.db.DatabaseRetrieveCallback;
import org.cruxframework.crux.core.client.db.Index;
import org.cruxframework.crux.core.client.db.KeyRange;
import org.cruxframework.crux.core.client.db.ObjectStore;
import org.cruxframework.crux.core.client.db.Transaction;
import org.cruxframework.mediamanager.core.client.reuse.AbstractDTO;
import org.cruxframework.mediamanager.offline.client.reuse.OfflineEntity;

import com.google.gwt.user.client.Window;


/**Class descriptoin: Contains implementations of operations on the databases
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */

public abstract class AbstractDao<DTO extends AbstractDTO, E extends OfflineEntity<?>>
{
	

	/**Saves an entity (E) in a database (database).
	 * @param entity
	 * @param database
	 */
	public void save(E entity)
	{
		getDatabase().put(new Object[]{entity}, entity.getStoreName(), new DatabaseCallback() 
		{

			@Override
			public void onSuccess() 
			{
				//Window.alert("Entidaty enserida com sucesso !");
			}
			
			@Override
			public void onError(String message)
			{
				Window.alert(message);
				super.onError(message);
			}
		});
	}
	
	/** Save an entity (E) in a Database (database) with DatabaseCallback (callback).
	 * @param entity
	 * @param database
	 * @param callback
	 */
	
	public void save(E entity, DatabaseCallback callback )
	{
		getDatabase().put(new Object[]{entity}, entity.getStoreName(), callback);
	}
	
	/** Delete an Entity (E) in a Database (database) with DatabaseCallback (callback).
	 * @param id
	 * @param store_name
	 * @param database
	 */
	public void delete(Integer id, String store_name, final Database database) 
	{
		database.delete(id, store_name, new DatabaseCallback() 
		{
			
			@Override
			public void onSuccess() 
			{
//				Window.alert("Artist remove with sucessefull ");
			}
			
			@Override
			public void onError(String message) 
			{	
				super.onError(message);
			}
		});
	}
	
	
	/**Search all record of a ObjectStore and returns a Cursor.
	 * @param store_name
	 * @param database
	 * @param callback
	 */
	public void search(String store_name, DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		objStore.openCursor(callback);
	}
	
	/**Search Search a record (by id) in ObjectStore and returns a Cursor.
	 * @param id
	 * @param store_name
	 * @param database
	 * @param callback
	 */
	public void search(Integer id, String store_name, DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		KeyRange<Integer> keyRange = objStore.getKeyRangeFactory().only(id);
		objStore.openCursor(callback);
	}
	
	
	/**Search a record search (by name) in ObjectStore and returns a Cursor.
	 * @param name
	 * @param store_name
	 * @param database
	 * @param callback
	 */
	public void search(String name, String store_name, DatabaseRetrieveCallback<E> callback)
	{
		Transaction transaction = getDatabase().getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		Index<Integer, String, E> nameIndex = objStore.getIndex("name");
		nameIndex.get(name, callback); 
	}
	
	/*********************
	 *Abstract Methods
	 *********************/
	protected abstract Database getDatabase();
	
}
