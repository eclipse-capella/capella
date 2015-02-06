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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Erwan Brottier
 */
public class GuiHelper {

  /** Get all selected resources in the package explorer view */
  public static List<IResource> getCurrentSelectionInNavigator(String navigatorId) {
  	List<IResource> resources = new ArrayList<IResource>();
  	IWorkbenchWindow window =
  	    PlatformUI.getWorkbench().getActiveWorkbenchWindow();
  	IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection(navigatorId);
		if (selection != null) {
			for (Object elt : selection.toList()) {
				IResource resource = (IResource)Platform.getAdapterManager().getAdapter(elt, IResource.class);
				if (resource != null) {
					resources.add(resource);				
				}
			}			
		}
    return resources;
  }

}
