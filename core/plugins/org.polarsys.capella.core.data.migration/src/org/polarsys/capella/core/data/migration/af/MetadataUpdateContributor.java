/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.af;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.af.integration.Messages;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.model.common.scrutiny.contrib.viewpoints.scrutinizes.UsedAFViewpoints;

/**
 * @author Thomas Guiu
 *
 */
public class MetadataUpdateContributor extends AbstractMigrationContributor {

	@Override
	public String getKind() {
		return MigrationConstants.MIGRATION_KIND__DIAGRAM;
	}

	@Override
	public boolean isValidResource(IResource member) {
		return CapellaResourceHelper.isAirdResource(member, true);
	}

	@Override
	public AbstractMigrationRunnable getRunnable(IFile file) {
		return new MigrationRunnable(file) {
			@Override
			public String getName() {
				return Messages.MetadataUpdateContributor_Name;
			}

			@Override
			protected void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
					MigrationContext context) throws IOException {
				Resource resource = resourceSet.getResource(EcoreUtil2.getURI(getFile()), true);

				Resource initIntegrationStorage = MetadataHelper.getViewpointMetadata(resourceSet)
						.initMetadataStorage();

				if (initIntegrationStorage != null) {
					boolean found = false;
					EObject eObject = resource.getContents().get(0);
					DAnalysis session = (DAnalysis) eObject;
					ResourceDescriptor descriptor = new ResourceDescriptor(initIntegrationStorage.getURI());
					URI descriptorURI = descriptor.getResourceURI();
					EList<ResourceDescriptor> semanticResources = session.getSemanticResources();
					for (ResourceDescriptor semanticResource : semanticResources) {
						URI semanticResourceURI = semanticResource.getResourceURI();
						String str = URI.encodeFragment(semanticResourceURI.toString(), true);
						if (str.equals(descriptorURI.toString())) {
							found = true;
						}
					}
					if (!found) {
						semanticResources.add(new ResourceDescriptor(initIntegrationStorage.getURI()));
					}

					// enable capella viewpoint
					org.polarsys.kitalpha.resourcereuse.model.Resource capellaVp = ViewpointManager
							.getViewpoint("org.polarsys.capella.core.viewpoint");
					Version readVersion = ViewpointManager.readVersion(capellaVp);
					MetadataHelper.getViewpointMetadata(resourceSet).setUsage(capellaVp, readVersion, true);
				}

				Set<org.polarsys.kitalpha.resourcereuse.model.Resource> lookForViewpoints = UsedAFViewpoints
						.lookUp(resource);
				for (org.polarsys.kitalpha.resourcereuse.model.Resource res : lookForViewpoints) {
					Version version = ViewpointManager.readVersion(res);
					MetadataHelper.getViewpointMetadata(resourceSet).updateVersion(res, version);
				}
			}

			@Override
			public XMLResource doCreateResource(URI uri, MigrationContext context) {
				return null;
			}

		};
	}

}
