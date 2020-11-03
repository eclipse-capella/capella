/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.Contribution;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * Add of remove the Capella Viewpoint reference accordingly to the usage of Capella resources in an aird
 * @author Thomas Guiu
 */
public class CapellaContribution extends Contribution {

  @Override
  public void update(ResourceSet context) {
    if (hasCapellaSemanticResource(context)) {
      referenceViewpoint(context, AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
    } else {
      unreferenceViewpoint(context, AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
    }
  }

  protected void unreferenceViewpoint(ResourceSet context, String viewpointId) {
    org.polarsys.kitalpha.resourcereuse.model.Resource capellaVp = ViewpointManager
        .getViewpoint(viewpointId);
    MetadataHelper.getViewpointMetadata(context).unReference(capellaVp);
  }

  protected void referenceViewpoint(ResourceSet context, String viewpointId) {
    org.polarsys.kitalpha.resourcereuse.model.Resource capellaVp = ViewpointManager
        .getViewpoint(viewpointId);
    Version readVersion = ViewpointManager.readVersion(capellaVp);
    MetadataHelper.getViewpointMetadata(context).reference(capellaVp, readVersion);
  }

  /**
   * Returns whether the aird located in the resourceSet has a reference towards a capella resource
   */
  protected boolean hasCapellaSemanticResource(ResourceSet context) {
    for (Resource resource : context.getResources()) {
      if (resource != null && CapellaResourceHelper.isAirdResource(resource.getURI())) {
        for (EObject root : resource.getContents()) {
          if (root instanceof DAnalysis && hasCapellaSemanticResource((DAnalysis)root)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the given aird has a reference towards a capella resource
   */
  protected boolean hasCapellaSemanticResource(DAnalysis analysis) {
    for (ResourceDescriptor descriptor : ((DAnalysis) analysis).getSemanticResources()) {
      if (CapellaResourceHelper.isCapellaResource(descriptor.getResourceURI())) {
        return true;
      }
    }
    return false;
  }
}
