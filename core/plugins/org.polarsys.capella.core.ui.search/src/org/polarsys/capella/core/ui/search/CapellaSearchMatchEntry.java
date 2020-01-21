/*******************************************************************************
 * Copyright (c) 2007, 2008 Anyware Technologies and others. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * EcoreModelSearchResultEntry.java
 * 
 * Contributors: 
 * 		Lucas Bigeardel (Anyware Technologies) - initial API and implementation
 * 		Lucas Bigeardel - javadoc
 ******************************************************************************/

package org.polarsys.capella.core.ui.search;

/*-
 * #%L
 * com.thalesgroup.mde.capella.search.parent
 * %%
 * Copyright (C) 2018 - 2019 Thales DMS France SAS
 * %%
 * All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *    
 *  Contributors:
 *    Thales DMS France SAS - initial API and implementation
 * #L%
 */

import java.io.File;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;

/**
 * Handles Ecore model search results matches in terms of qualified names building
 */
public final class CapellaSearchMatchEntry extends AbstractCapellaSearchResultEntry {

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param object
	 * @param source
	 * @param matched
	 */
	public CapellaSearchMatchEntry(AbstractCapellaSearchResultEntry parent, Object object, Object source, boolean matched) {
		super(parent, object, source, matched);
	}
	
	/**
	 * @return fully qualified name for current search query match, void String otherwise
	 */
	public String getModelResultFullyQualifiedName2() {
		String txt = processFullyQualifiedName(""); //$NON-NLS-1$
		return txt.endsWith(".")?txt.substring(0, txt.lastIndexOf(".")):txt; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private String processFullyQualifiedName(String txt) {
        for (AbstractCapellaSearchResultEntry e : getHierarchyFromRootToLeaf()) {
            if (e != null ) {
                if (e.getSource() instanceof ENamedElement) {
                    txt = ((ENamedElement)e.getSource()).getName() + "." + txt; //$NON-NLS-1$
                } else if (e.getSource() instanceof EModelElement){
                    if (e.getSource() instanceof EAnnotation) {
                        txt = "[Annotation: \"" + 
                                    ((EAnnotation)e.getSource()).getSource() +
                                    "\"]" + processEAnnotationDetails(((EAnnotation)e.getSource())) +
                                        "." + txt; //$NON-NLS-1$
                    } else {
                        txt = ((EModelElement)e.getSource()).toString() + "." + txt; //$NON-NLS-1$
                    }
                }
            }
        }
	    return txt;
	}

	private String processEAnnotationDetails(EAnnotation eAnnotation) {
	    String details = "(";
	    for (String key : eAnnotation.getDetails().keySet()) {
	        String value = eAnnotation.getDetails().get(key);
	        details += "(\"" + key + "\" => \"" + value+ "\")";
	    }
	    return details + ")";
	}
	
	/**
	 * fully qualified name for current search query match, void String otherwise
	 */
	public String getModelResultFullyQualifiedName() {
		String txt = ""; //$NON-NLS-1$
		if (getParent() instanceof AbstractCapellaSearchResultEntry) {
	        txt = processFullyQualifiedName(""); //$NON-NLS-1$
		}
		return txt.endsWith(".")?txt.substring(0, txt.lastIndexOf(".")):txt; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Convenience getter which adapts EMF Resource target coming from getTartget()
	 * into java.io.File whenever it is possible.
	 * 
	 * @return an adapted java.io.File whenever it is possible, null otherwise. 
	 */
	public Object getFile() {
		if (target instanceof IAdaptable) {
		    return ((IAdaptable)target).getAdapter(File.class);
		}
		return null;
	}

	public boolean isExcluded() {
		return false;
	}
}
