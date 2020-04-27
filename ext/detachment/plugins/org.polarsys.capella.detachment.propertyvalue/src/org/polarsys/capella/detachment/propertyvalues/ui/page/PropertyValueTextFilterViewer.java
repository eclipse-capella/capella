/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.common.model.label.LabelRetriever;

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
		String name = LabelRetriever.getLabel((EObject)element); //$NON-NLS-1$
		String path = LabelRetriever.getFullLabel((EObject)element); //$NON-NLS-1$
		Pattern regExpPattern = Pattern.compile(pattern);
		return regExpPattern.matcher(name).find() || regExpPattern.matcher(path).find();
	}
}
