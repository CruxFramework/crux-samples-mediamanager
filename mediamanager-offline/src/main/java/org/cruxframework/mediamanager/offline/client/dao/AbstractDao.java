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


/** Contains implementations of operations on the databases
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public abstract class AbstractDao<DTO extends AbstractDTO, E extends OfflineEntity>
{
	
	public void save(E entity, final Database database)
	{
		database.put(new Object[]{entity}, entity.getStoreName(), new DatabaseCallback() 
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
	
	public void save(E entity, final Database database, DatabaseCallback callback )
	{
		database.put(new Object[]{entity}, entity.getStoreName(), callback);
	}
	
	public DTO find(Integer id, String store_name, final Database database)
	{
		database.get(id, store_name, new DatabaseRetrieveCallback<E>() 
		{
			@Override
			public void onSuccess(E result) 
			{
				//Window.alert("Retorno do banco: "+result.getName());
				
			}
			
			@Override
			public void onError(String message) 
			{
				super.onError(message);
			}
		});
		return null;
	}
	
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
	
	public void search(String store_name, final Database database, DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = database.getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		objStore.openCursor(callback);
	}
	
	public void search(Integer id, String store_name, final Database database, DatabaseCursorCallback<Integer, E> callback)
	{
		Transaction transaction = database.getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		KeyRange<Integer> keyRange = objStore.getKeyRangeFactory().only(id);
		objStore.openCursor(callback);
	}
	
	public void search(String name, String store_name, final Database database, DatabaseRetrieveCallback<E> callback)
	{
		Transaction transaction = database.getTransaction(new String[]{store_name}, 
				Transaction.Mode.readWrite);
		ObjectStore<Integer, E> objStore = transaction.getObjectStore(store_name);
		Index<Integer, String, E> nameIndex = objStore.getIndex("name");
		nameIndex.get(name, callback); 
	}
}
