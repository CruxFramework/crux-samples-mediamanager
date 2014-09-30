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
package org.cruxframework.mediamanager.client.service;

import java.util.List;

import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.rest.RestProxy.TargetRestService;
import org.cruxframework.mediamanager.client.reuse.service.RestServiceProxy;
import org.cruxframework.mediamanager.shared.dto.MediaDTO;
import org.cruxframework.mediamanager.shared.enums.MediaType;

/**
 * Class description: REST interface for medias.
 * 
 * @author alexandre.costa
 */
@TargetRestService("mediaService")
public interface MediaServiceProxy extends RestServiceProxy<MediaDTO>
{
	/**
	 * Search medias by type, name and author.
	 * @param type media type
	 * @param name media's name
	 * @param person media's author
	 * @param callback callback object.
	 */
	void search(MediaType type, String name, String person, Callback<List<MediaDTO>> callback);

}
