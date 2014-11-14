/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;


/**
 * This class take a resulting project from a transformation, and save it
 * into a new/.updated XMI file.
 * The two files must be in the same resourceSet, the new file must 
 * see the source model to support correct traceability links. 
 *
 */
public class TigerProjectExporter {
	
	/**
   * 
   */
  private static final String CAPELLAMODELLER = ".melodymodeller"; //$NON-NLS-1$

	public void export (EObject sibbling_p, EObject root_p) {
		URI uri = sibbling_p.eResource().getURI();
		String[] segments = uri.segments();
		String name = ((NamedElement)root_p).getName();
		segments[segments.length - 1] = name + CAPELLAMODELLER;
		StringBuilder sb = new StringBuilder();
		for (int i=1; i < segments.length; i++) {
			sb.append ("/"); //$NON-NLS-1$
			sb.append(segments[i]);
		}
		
		XMIResource myNewResource = (XMIResource) MDEAdapterFactory.getEditingDomain().createResource(sb.toString());
		myNewResource.getResourceSet().getResources().add(sibbling_p.eResource());
		myNewResource.getContents().add(root_p);
		try {
			final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
      saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
      saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF , XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
			myNewResource.save(saveOptions);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
