/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.egf;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.pde.core.IBaseModel;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.text.plugin.PluginImportNode;
import org.eclipse.pde.internal.ui.util.ModelModification;
import org.eclipse.pde.internal.ui.util.PDEModelUtility;

/**
 * This class add:
 * - import to "org.polarsys.kitalpha.emde.xmi" on manifests if not yet set
 */
public class ManifestModificationTask implements ITaskProduction {

	@SuppressWarnings("restriction")
	public void doExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
		
		for (String manifestMFPathString : ICommonConstants.MANIFESTS ) {
			
	        IPath manifestMFPath = new Path(manifestMFPathString);
	        IWorkspace workspace = ResourcesPlugin.getWorkspace();
	        final IProject project = workspace.getRoot().getProject(manifestMFPath.segment(0));
	        IFile manifestMFFile = project.getFile(ICoreConstants.BUNDLE_FILENAME_DESCRIPTOR);
	        if (!manifestMFFile.exists()) {
	        	System.out.println("file " + manifestMFPathString + " doesn't exist (skipping).");
	        	return;
	        }
			PDEModelUtility.modifyModel(new ModelModification(manifestMFFile) {

	            @Override
	            protected void modifyModel(IBaseModel model, IProgressMonitor innerMonitor) throws CoreException {
	                if (model instanceof IBundlePluginModelBase) {
	                	IBundlePluginModelBase bundlePluginModelBase = (IBundlePluginModelBase) model;
	                	
	                	IPluginImport[] imports = bundlePluginModelBase.getPluginBase().getImports();
	                	boolean extension_xmi_found = false;
	                	for (IPluginImport iPluginImport : imports) {
							if ("org.polarsys.kitalpha.emde.xmi".equals(iPluginImport.getId())) 
								extension_xmi_found = true;
						}
	                	if (!extension_xmi_found)
	                		bundlePluginModelBase.getPluginBase().add(new PluginImportNode("org.polarsys.kitalpha.emde.xmi"));
	                }
	            }

	        }, monitor);
		}
		
	}

	public void postExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

	public void preExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

}
