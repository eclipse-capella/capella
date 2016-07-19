/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.regex.Pattern;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PropertyValueTextFilterViewer extends ViewerFilter {

	 private String pattern = null;
	 
	 public void setPattern(String pattern){
		 this.pattern = pattern;
	 }
	 
	 private String getPattern(){
		 return this.pattern;
	 }
	 
	 
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (getPattern() == null || getPattern().isEmpty())
			return true;
		String name = PropertyValueHelper.getStringAttribute(element, "getName"); //$NON-NLS-1$
		String path = PropertyValueHelper.getStringAttribute(element, "getFullLabel"); //$NON-NLS-1$
		Pattern regExpPattern = Pattern.compile(pattern);
		return regExpPattern.matcher(name).find() || regExpPattern.matcher(path).find();
	}
}
