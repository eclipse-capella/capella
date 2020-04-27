/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.extension.migration.egf;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.egf.domain.DomainException;
import org.eclipse.egf.domain.file.WorkspaceDomainHelper;
import org.eclipse.egf.model.domain.LoadableDomain;
import org.eclipse.egf.model.domain.WorkspaceDomain;

/**
 *
 */
public class SkippingWorkspaceDomainHelper extends WorkspaceDomainHelper {
	
	@Override
	protected boolean doLoadDomain(LoadableDomain domain) throws DomainException {
        if (domain instanceof WorkspaceDomain) {
            WorkspaceDomain myDomain = (WorkspaceDomain) domain;
            final String path = myDomain.getPath();
            myDomain.setLoaded(true);
            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(path);
            if (!project.exists()) {
            	System.out.println("project " + path + " doesn't exist (skipping).");
            	return true;
            }
        }
		return super.doLoadDomain(domain);
	}

}
