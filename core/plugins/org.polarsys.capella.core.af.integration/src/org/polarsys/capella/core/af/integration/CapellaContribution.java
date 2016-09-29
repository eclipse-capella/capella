/*******************************************************************************
 * Copyright (c) 2016 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.af.integration;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.framework.Version;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.Contribution;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * @author Thomas Guiu
 */
public class CapellaContribution extends Contribution {

	@Override
	public void update(ResourceSet context) {
		
		for (Resource resource : context.getResources()) {
			if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(resource.getURI().fileExtension())) {
				org.polarsys.kitalpha.resourcereuse.model.Resource capellaVp = ViewpointManager.getViewpoint(AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
				Version readVersion = ViewpointManager.readVersion(capellaVp);
				MetadataHelper.getViewpointMetadata(context).reference(capellaVp, readVersion);
				return ;
			}
		}
	}
}
