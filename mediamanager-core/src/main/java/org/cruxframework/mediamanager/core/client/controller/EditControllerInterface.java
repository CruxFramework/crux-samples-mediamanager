package org.cruxframework.mediamanager.core.client.controller;

import org.cruxframework.mediamanager.core.client.reuse.EditOperation;

/**This interface provides methods that controllers EditController
 * implement to handle the return of database services
 * 
 * @author Bruno Medeiros (bruno@triggolabs.com)
 */
public interface EditControllerInterface
{
	void completeInsert(EditOperation result);
	void completeUpdate();
}
