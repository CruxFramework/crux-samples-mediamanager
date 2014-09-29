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
package org.cruxframework.mediamanager.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.server.Server;

/**
 * Class description: Initializes HSQLDB on server startup.
 * 
 * @author alexandre.costa
 */
// CHECKSTYLE:OFF
public class InitDBListener implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
			Statement stmt = connection.createStatement();
			stmt.execute("SHUTDOWN");
			stmt.close();
			connection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		String[] args = new String[4];
		args[0] = "--database.0";
		args[1] = "file:mydb";
		args[2] = "--dbname.0";
		args[3] = "xdb";
		Server.main(args);
	}
}
