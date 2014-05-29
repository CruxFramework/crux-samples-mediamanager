package org.cruxframework.mediamanager.offline.client.reuse;

import org.cruxframework.crux.core.client.db.Cursor;
import org.cruxframework.crux.core.client.db.DatabaseCursorCallback;


public abstract class AbstractCallback<V, K> extends DatabaseCursorCallback<K, V>
{
	protected abstract void success(Cursor<K, V> result);
	
	@Override
	public void onSuccess(Cursor<K, V> result)
	{
		success(result);
	}
	
	@Override
	public void onError(String message)
	{
		super.onError(message);
	}
}
