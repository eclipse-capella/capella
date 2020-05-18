/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;

/**
 * Specialization of the refresh action:
 * does not perform anything on IContainer on 
 * unavailable physical disk
 *
 */
public class RefreshAction extends org.eclipse.ui.actions.RefreshAction {

	public RefreshAction(Shell shell) {
		super(shell);
	}
	
	/**
	 * Returns a list containing the workspace root if the selection would
	 * otherwise be empty.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List getSelectedResources() {
		
		List resources = super.getSelectedResources();
		
		if (resources.isEmpty()) {
			resources = new ArrayList<Object>();
			resources.add(ResourcesPlugin.getWorkspace().getRoot());
		}
		
		List refreshableResources = new ArrayList();
		
		Iterator it = resources.iterator();
		
		while (it.hasNext()) {
			Object current = it.next();
			if ( current instanceof IResource ) {
				IResource iResource = (IResource) current;
				
				// Let's check if device is ok
				IPath path = iResource.getLocation();
				IPath device = path.removeLastSegments(path.segmentCount());
				File dir = device.toFile();
				
				if ( dir.canWrite() ) {
					refreshableResources.add(current);
				}
			} else {
				refreshableResources.add(current);
			}
		}
		
		
		return refreshableResources;
	}
	

}
