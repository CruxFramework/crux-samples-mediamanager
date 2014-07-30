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
package org.cruxframework.mediamanager.offline.client.db;


import org.cruxframework.crux.core.client.db.Database;
import org.cruxframework.crux.core.client.db.annotation.DatabaseDef;
import org.cruxframework.crux.core.client.db.annotation.DatabaseDef.IndexDef;
import org.cruxframework.crux.core.client.db.annotation.DatabaseDef.ObjectStoreDef;
import org.cruxframework.mediamanager.offline.client.entity.Artist;
import org.cruxframework.mediamanager.offline.client.entity.Country;
import org.cruxframework.mediamanager.offline.client.entity.Genre;
import org.cruxframework.mediamanager.offline.client.entity.Media;
import org.cruxframework.mediamanager.offline.client.entity.User;
import org.cruxframework.mediamanager.offline.client.error.ErrorHandler;

/**
 * Class description:Defines a database and its settings, with ErrorHandler and objectStores
 *  
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */

@DatabaseDef(
		name = "MediaManager2", 
		version = 1,
		defaultErrorHandler = ErrorHandler.class,
		objectStores = {
			@ObjectStoreDef(targetClass = Artist.class),
			@ObjectStoreDef(targetClass = Media.class, indexes = {
				@IndexDef (name = "nameType", keyPath = {"name","type"}),
				@IndexDef (name = "borrowedType", keyPath = {"borrowed","type"}),
				@IndexDef (name = "dateType", keyPath = {"date","type"})}),
			@ObjectStoreDef(targetClass = Country.class),
			@ObjectStoreDef(targetClass = Genre.class),
			@ObjectStoreDef(targetClass = User.class)
		}
	)
public interface DbMediamanager extends Database
{
//	@IndexDef (name = "dataType", keyPath = {"data","type"})
}