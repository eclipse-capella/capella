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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.af.integration.Messages;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * @author Thomas Guiu
 */
public class MetadataCreationContributor extends AbstractMigrationContributor {

	@Override
	public String getKind() {
		return MigrationConstants.MIGRATION_KIND__METADATA;
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
				return Messages.MetadataCreationContributor_Name;
			}

			@Override
			protected void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) throws IOException {
				// create metadata resource if needed
//				Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(getFile().getFullPath().toString(), false), true);

//				Resource initIntegrationStorage = MetadataHelper.getViewpointMetadata(resourceSet).initMetadataStorage();
//				if (initIntegrationStorage != null) {
//					EObject eObject = resource.getContents().get(0);
//					DAnalysis session = (DAnalysis) eObject;
//					session.getSemanticResources().add(new ResourceDescriptor(initIntegrationStorage.getURI()));
//					
//					// enable capella viewpoint
//					org.polarsys.kitalpha.resourcereuse.model.Resource capellaVp = ViewpointManager.getViewpoint(AFIntegrationPlugin.CAPELLA_VIEWPOINT_ID);
//					Version readVersion = ViewpointManager.readVersion(capellaVp);
//					MetadataHelper.getViewpointMetadata(resourceSet).setUsage(capellaVp, readVersion, true);
//				}
			}

			@Override
			public XMLResource doCreateResource(URI uri, MigrationContext context) {
				return null;
			}

		};
	}

}
