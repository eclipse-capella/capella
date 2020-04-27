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
package org.polarsys.capella.core.postgeneration.egf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
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
 */
@SuppressWarnings("restriction")
public class AddDependenciesTask implements ITaskProduction {

	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
		String manifestMFPathString = System.getProperty("manifestMFPath");
		String dependenciesToAddString = System.getProperty("dependenciesToAdd");
		final String[] dependenciesToAdd = dependenciesToAddString.split(";");
		String reexportDependenciesString = System.getProperty("reexportDependencies");
		final Boolean reexportDependencies = Boolean.valueOf(reexportDependenciesString);

		IPath manifestMFPath = new Path(manifestMFPathString);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(manifestMFPath.segment(0));
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

                	List<String> existingDependencies = new ArrayList<String>();
                	for (IPluginImport iPluginImport : imports) {
                		existingDependencies.add(iPluginImport.getId());
					}

                	for (String dependency : dependenciesToAdd) {
                		if (!existingDependencies.contains(dependency)) {
                			PluginImportNode pluginImport = new PluginImportNode(dependency);
                			pluginImport.setReexported(reexportDependencies);
							bundlePluginModelBase.getPluginBase().add(pluginImport);
                		}
                	}
                }
            }
        }, monitor);
	}

	public void postExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}

	public void preExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}
}
