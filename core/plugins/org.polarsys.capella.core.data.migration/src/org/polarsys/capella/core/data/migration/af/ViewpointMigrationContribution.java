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

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.osgi.framework.Version;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.af.integration.Activator;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * This contribution ensures that all used viewpoints are available
 * 
 * @author Thomas Guiu
 *
 */
public class ViewpointMigrationContribution extends AbstractMigrationContribution {

	@Override
	public IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {
		if (MetadataHelper.isMetadataResource(fileToMigrate)) {
			// check all used VP are available (we suppose they are coming with migration tooling)
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getLoadOptions().put(GMFResource.OPTION_ABORT_ON_ERROR, Boolean.TRUE);
			resourceSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			 MultiStatus status = new MultiStatus(Activator.getSymbolicName(), IStatus.OK, "Some viewpoints are missing", null);
			try {
				// need to load model file into the resourceset
				Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(fileToMigrate.getFullPath().toString(), false), true);

				Map<String, Version> viewpointUsages = MetadataHelper.getViewpointMetadata(resourceSet).getViewpointUsages();
				for (String id : viewpointUsages.keySet())
				{
					if (ViewpointManager.getViewpoint(id) == null)
						status.add(new Status(IStatus.ERROR, Activator.getSymbolicName(), "The viewpoint '"+id+"' is missing"));
				}
			} catch (Exception e) {
				status.add( new Status(IStatus.ERROR, Activator.getSymbolicName(), e.getMessage()));
			} finally {
				for (Resource r : resourceSet.getResources()) {
					r.unload();
				}
				resourceSet.getResources().clear();
			}
			
			if (!status.isOK())
				return status;

		}
		return super.preMigrationExecute(fileToMigrate, context, checkVersion);
	}

	public void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
		// TODO check update ?
		return;
	}

}
