/**
 * 
 */
package org.cruxframework.mediamanager.client.reuse.controller;

import org.cruxframework.crux.core.client.css.animation.StandardAnimation;
import org.cruxframework.crux.core.client.screen.views.View;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Claudio
 *
 */
public abstract class AbstractController {
	
	public void animateContent()
	{
		Widget content = View.of(this).getWidget("outerPanel");
		if (content != null)
		{
			StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.fadeInUp);
			animation.animate(content);
		}
	}
	
	public void animateButtonsNav()
	{
		Widget content = View.of(this).getWidget("commandButtonsNav");
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.fadeIn);
		animation.animate(content);
	}
	
	public void animateResults()
	{
		Widget content = View.of(this).getWidget("resultPanel");
		StandardAnimation animation = new StandardAnimation(StandardAnimation.Type.fadeIn);
		animation.animate(content);
	}

}
