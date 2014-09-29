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
package org.cruxframework.mediamanager.client.reuse.controller;

import org.cruxframework.crux.core.client.css.animation.StandardAnimation;
import org.cruxframework.crux.core.client.screen.views.View;

import com.google.gwt.user.client.ui.Widget;

/**
 * Base controller. Contains animation methods.
 * 
 * @author Claudio
 *
 */
public abstract class AbstractController
{
	protected void animateContent()
	{
		Widget content = View.of(this).getWidget("outerPanel");
		if (content != null)
		{
			StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.fadeInUp);
			animation.animate(content);
		}
	}

	protected void animateResults()
	{
		Widget content = View.of(this).getWidget("resultPanel");
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.fadeIn);
		animation.animate(content);
	}
}
