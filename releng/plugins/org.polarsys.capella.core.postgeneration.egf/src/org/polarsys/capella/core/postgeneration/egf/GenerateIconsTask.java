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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.common.helper.FileHelper;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * 
 */
public class GenerateIconsTask implements ITaskProduction {
	/**
	 * 
	 */
	public void preExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}

	/**
	 * 
	 */
	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
		// Fill an array with all capella meta-models.
		IResource[] ecoreFiles = {
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/ContextArchitecture.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/EPBSArchitecture.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/FunctionalAnalysis.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/Information.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/Interaction.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/LogicalArchitecture.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/CapellaCommon.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/CapellaCore.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/OperationalAnalysis.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/PhysicalArchitecture.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/Requirement.ecore")),  //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.core.data.gen/model/SharedModel.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.common.data.core.gen/model/ModellingCore.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.common.data.behavior.gen/model/Behavior.ecore")), //$NON-NLS-1$
			FileHelper.getPlatformResource(new Path("/org.polarsys.capella.common.data.activity.gen/model/Activity.ecore")) //$NON-NLS-1$
		};

		UpdateIconsAction updaterAction = new UpdateIconsAction();
		// Set a faked selection based on the array of Ecore files.
		updaterAction.selectionChanged(null, new StructuredSelection(ecoreFiles));
		// Fake an UI call to generate icons in the UI thread due to
		// UpdateIconsAction implementation, thanks the rookie :)
//		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
//			public void run() {
		updaterAction.run(null);
//			}
//		});
	}

	/**
	 * 
	 */
	public void postExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}
}
