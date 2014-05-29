package org.cruxframework.mediamanager.offline.client.db;


import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.crux.core.client.db.annotation.DatabaseDef;
import org.cruxframework.crux.core.client.db.annotation.DatabaseDef.ObjectStoreDef;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;
import org.cruxframework.mediamanager.offline.client.entity.Media;
import org.cruxframework.mediamanager.offline.client.entity.User;
import org.cruxframework.mediamanager.offline.client.error.ErrorHandler;

/**
 * Class description: 
 * @author Bruno.Rafael
 */

@DatabaseDef(
		name = "MediaManager2", 
		version = 1,
		defaultErrorHandler = ErrorHandler.class,
		objectStores = {
			@ObjectStoreDef(targetClass = Media.class),
			@ObjectStoreDef(targetClass = Artist.class),
			@ObjectStoreDef(targetClass = Country.class),
			@ObjectStoreDef(targetClass = Genre.class),
			@ObjectStoreDef(targetClass = User.class)
		}
	)
public interface DbMediamanager extends Database{

}