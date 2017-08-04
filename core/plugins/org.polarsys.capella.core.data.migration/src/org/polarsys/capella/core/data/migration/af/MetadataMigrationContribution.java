/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.af;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;

/**
 * This contribution ensures that all used viewpoints are available
 * 
 * @author Thomas Guiu
 */
public class MetadataMigrationContribution extends AbstractMigrationContribution {

  @Override
  public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {

    Collection<Resource> airds = new ArrayList<>();
    for (Resource resource : resourceSet.getResources()) {
      if (CapellaResourceHelper.isAirdResource(resource.getURI())) {
        airds.add(resource);
      }
    }

    for (Resource resource : airds) {
      Resource initMetadata = MetadataHelper.initMetadata(resource);
      if (initMetadata != null) {
        boolean found = false;
        EObject eObject = resource.getContents().get(0);
        if (eObject instanceof DAnalysis) {
          DAnalysis analysis = (DAnalysis) eObject;
          ResourceDescriptor descriptor = new ResourceDescriptor(initMetadata.getURI());
          URI descriptorURI = descriptor.getResourceURI();
          EList<ResourceDescriptor> semanticResources = analysis.getSemanticResources();
          for (ResourceDescriptor semanticResource : semanticResources) {
            URI semanticResourceURI = semanticResource.getResourceURI();
            String str = URI.encodeFragment(semanticResourceURI.toString(), true);
            if (str.equals(descriptorURI.toString())) {
              found = true;
            }
          }
          if (!found) {
            semanticResources.add(new ResourceDescriptor(initMetadata.getURI()));
          }
        }
      }
    }
    // TODO Auto-generated method stub
    super.postMigrationExecute(executionManager, resourceSet, context);
  }

}
