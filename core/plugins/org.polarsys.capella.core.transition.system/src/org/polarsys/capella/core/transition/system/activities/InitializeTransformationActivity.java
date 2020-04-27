/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.activities;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.helpers.ContextHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public abstract class InitializeTransformationActivity
    extends org.polarsys.capella.core.transition.common.activities.InitializeTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.InitializeTransformationActivity"; //$NON-NLS-1$

  @Override
  protected abstract IHandler createDefaultTraceabilityTransformationHandler();

  @Override
  protected EObject createTargetTransformationContainer(Resource source, IContext context) {

    Project sourceProject = ContextHelper.getSourceProject(context);
    SystemEngineering sourceEngineering = ContextHelper.getSourceEngineering(context);
    Project targetProject = null;
    if(sourceProject instanceof Library) {
      targetProject = CapellamodellerFactory.eINSTANCE.createLibrary(sourceProject.getName());
    }else {
      targetProject = CapellamodellerFactory.eINSTANCE.createProject(sourceProject.getName());
    }
  
    
    SystemEngineering targetEngineering = CapellamodellerFactory.eINSTANCE.createSystemEngineering(sourceEngineering.getName());
    targetEngineering.setId(sourceEngineering.getId());
    targetProject.getOwnedModelRoots().add(targetEngineering);

    AttachmentHelper.getInstance(context).createdElement(null, targetProject, context);
    return targetProject;
  }

}
