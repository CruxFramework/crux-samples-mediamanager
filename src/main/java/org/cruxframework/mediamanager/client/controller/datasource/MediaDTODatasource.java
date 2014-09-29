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
package org.cruxframework.mediamanager.client.controller.datasource;

import org.cruxframework.crux.core.client.datasource.annotation.DataSource;
import org.cruxframework.crux.core.client.datasource.annotation.DataSourceRecordIdentifier;
import org.cruxframework.mediamanager.client.reuse.controller.AbstractDTODatasource;
import org.cruxframework.mediamanager.shared.dto.MediaDTO;

/**
 * Implements datasource pattern for {@link MediaDTO}.
 * @author alexandre.costa
 */
@DataSource("mediaDTODatasource")
@DataSourceRecordIdentifier("id")
public class MediaDTODatasource extends AbstractDTODatasource<MediaDTO>
{

}
