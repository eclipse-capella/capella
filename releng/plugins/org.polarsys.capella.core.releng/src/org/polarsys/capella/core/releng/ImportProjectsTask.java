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
package org.polarsys.capella.core.releng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.ui.wizards.imports.PluginImportOperation;

/**
 * 
 */
public class ImportProjectsTask implements ITaskProduction {

	private static final String THALES_PREFIX = "org.polarsys.capella.";
	private static final String GEN_SUFFIX = "gen";
	private static final String EMDE_SUFFIX = "emde";

	public void preExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

	public void doExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
		final Collection<IPluginModelBase> modelsToImport = new ArrayList<IPluginModelBase>();

		IPluginModelBase[] allModels = PluginRegistry.getAllModels();
		for (IPluginModelBase pluginModelBase : allModels) {
			String id = pluginModelBase.getPluginBase().getId();

			//is it a wanted bundle
			if (id.startsWith(THALES_PREFIX) && (id.endsWith(GEN_SUFFIX) || id.endsWith(EMDE_SUFFIX))) {
				for (IPluginModelBase sourcePluginModelBase : allModels) {
					boolean willImport = true;
					willImport &= sourcePluginModelBase.getPluginBase().getId().equals(id + ".source");
					willImport &= sourcePluginModelBase.getPluginBase().getVersion().equals(pluginModelBase.getPluginBase().getVersion());
					
					//is it a corresponding source bundle
					if (willImport) {

						//is it bundle already selected for import
						for (IPluginModelBase modelToImport : modelsToImport) {
							if (modelToImport.getPluginBase().getId().equals(id)) {
								throw new InvocationException("This bundle was already selected for import : " + id);
							}
						}
						
						modelsToImport.add(pluginModelBase);
					}
				}
			}
		}
		
		PluginImportOperation job = new PluginImportOperation(modelsToImport.toArray(new IPluginModelBase[0]), PluginImportOperation.IMPORT_WITH_SOURCE, false) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				IStatus result = super.runInWorkspace(monitor);
				
				for (IPluginModelBase iPluginModelBase : modelsToImport) {
					//in some emf bundle, the code was in the "generated" folder
					//so we copy "src" to "generated" in case of
					//by now "generated" is needed by EGF EMF CDO factory 

					String id = iPluginModelBase.getPluginBase().getId();
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(id);
					IFolder folder = project.getFolder("src"); 
					// the "src" folder has to be there, or it will fail
					// but it should because the source bundle should provide it
					// this was checked before
					folder.copy(folder.getFullPath().removeLastSegments(1).append("generated"), true, new NullProgressMonitor());

					System.out.println("imported from target platform --> " + id);
				}

				return result;
			}
		};
		job.setAlternateSource(null);
		job.setPluginsInUse(false);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.setUser(true);
		job.schedule();
	}

	public void postExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

}
