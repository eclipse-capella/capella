/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.headless;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

/**
 * Internal class in order to read contribution to the extension point
 * org.polarsys.capella.core.refinement.framework.resolvingExtension
 */
public class ResolvingExtensionReader {

	// extension point useful data.
	public final static String RESOLVING_EXT_POINT = "org.polarsys.capella.core.refinement.framework.resolvingExtension"; //$NON-NLS-1$
	public final static String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
	public final static String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$	
  public final static String PRIORITY_ATTRIBUTE = "priority"; //$NON-NLS-1$ 

	// Internal use
	private List<IConfigurationElement> elements;
	private Iterator<IConfigurationElement> it;
	private IConfigurationElement currentElement = null;

	public ResolvingExtensionReader(IExtension extension) {
		if ((extension != null) && extension.getExtensionPointUniqueIdentifier().equals(RESOLVING_EXT_POINT)) {
			IConfigurationElement[] elts = extension.getConfigurationElements();
			if ( elts != null ) {
				elements = new ArrayList<IConfigurationElement>(elts.length);
				for (IConfigurationElement current: elts) {
					elements.add(current);
				}
			}
			it = elements.iterator();
		}
	}

	public boolean hasNext() { return it.hasNext();}
	public void next() { currentElement = it.next();}
	public String getIdAtt() { return currentElement.getAttribute(ID_ATTRIBUTE);}
	public String getClassAtt() { return currentElement.getAttribute(CLASS_ATTRIBUTE);}
  public String getPriorityAtt() { return currentElement.getAttribute(PRIORITY_ATTRIBUTE);}
	public void reset() { it = elements.iterator(); }
}
