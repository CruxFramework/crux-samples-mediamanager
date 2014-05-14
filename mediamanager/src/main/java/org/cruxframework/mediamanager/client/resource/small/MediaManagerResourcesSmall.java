/*
 * Copyright 2013 cruxframework.org.
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
package org.cruxframework.mediamanager.client.resource.small;

import org.cruxframework.crux.core.client.resources.Resource;
import org.cruxframework.crux.core.client.screen.DeviceAdaptive.Device;
import org.cruxframework.mediamanager.client.resource.common.MediaManagerResourcesCommon;

import com.google.gwt.resources.client.DataResource;

/**
 * @author Claudio Junior
 *
 */
@Resource(value="mediaManagerResources", supportedDevices={Device.smallDisplayArrows, Device.smallDisplayTouch})
public interface MediaManagerResourcesSmall extends MediaManagerResourcesCommon
{
	@Source({"../common/cssMediaManagerCommon.css","cssMediaManagerSmall.css"})
	CssMediaManagerSmall css();
	
	@Source("logo.png")
	DataResource iconLogo();
	
	@Source("logo-text.png")
	DataResource iconLogoText();
	
	@Source("svg-icon-menu.svg")
	DataResource svgIconMenu();
	
	@Source("svg-icon-detail.svg")
	DataResource svgIconDetail();
}
