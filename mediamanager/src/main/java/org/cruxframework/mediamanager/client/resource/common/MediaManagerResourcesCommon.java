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
package org.cruxframework.mediamanager.client.resource.common;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

/**
 * @author Claudio Junior
 *
 */
public interface MediaManagerResourcesCommon extends ClientBundle
{
	@Source("svg-icon-locker.svg")
	DataResource svgIconLocker();
	
	@Source("svg-icon-logout.svg")
	DataResource svgIconLogout();
	
	@Source("svg-icon-arrowdown.svg")
	DataResource svgIconArrowDown();
	
	@Source("svg-icon-arrowback.svg")
	DataResource svgIconArrowBack();
	
	@Source("svg-icon-success.svg")
	DataResource svgIconSuccess();
	
	@Source("svg-icon-error.svg")
	DataResource svgIconError();
	
	@Source("svg-icon-info.svg")
	DataResource svgIconInfo();
	
	@Source("svg-icon-calendar.svg")
	DataResource svgIconCalendar();
	
	@Source("svg-icon-close.svg")
	DataResource svgIconClose();
	
	@Source("svg-icon-paginator-first.svg")
	DataResource svgIconPaginatorFirst();
	
	@Source("svg-icon-paginator-last.svg")
	DataResource svgIconPaginatorLast();
	
	@Source("svg-icon-paginator-next.svg")
	DataResource svgIconPaginatorNext();
	
	@Source("svg-icon-paginator-prev.svg")
	DataResource svgIconPaginatorPrev();
	
}
