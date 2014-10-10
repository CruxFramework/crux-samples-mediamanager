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

import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.rest.RestProxy;
import org.cruxframework.crux.core.client.rest.RestProxy.TargetRestService;
import org.cruxframework.mediamanager.shared.dto.EditArtistDTO;

/**
 * Class description: Client REST interface for editing artists.
 * 
 * @author alexandre.costa
 */
@TargetRestService("editArtistService")
public interface EditArtistServiceProxy extends RestProxy
{
	/**
	 * Get data for artist edition.
	 * @param id artist id.
	 * @param callback callback object
	 */
	void update(Integer id, Callback<EditArtistDTO> callback);
	
	/**
	 * Get data for artist edition.
	 * @param callback callback object
	 */
	void add(Callback<EditArtistDTO> callback);
}
