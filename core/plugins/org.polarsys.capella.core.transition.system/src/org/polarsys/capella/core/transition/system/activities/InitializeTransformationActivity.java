/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.activities;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.TransformationConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class InitializeTransformationActivity extends org.polarsys.capella.core.transition.common.activities.InitializeTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.InitializeTransformationActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultTraceabilityTransformationHandler() {
    ITraceabilityConfiguration configuration = new TransformationConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * @param source
   * @param context
   * @return
   */
  @Override
  protected EObject createTargetTransformationContainer(Resource source, IContext context) {
    // Should create a temporary model
    // For debug purpose, we create another systemEngineering into the root project of the element
    // We create another engineering if already exist

    Project project = (Project) org.polarsys.capella.core.model.helpers.CapellaElementExt.getRoot((CapellaElement) source.getContents().get(0));

    SystemEngineering engineering = getEngineering(project, "TRANSFORMED", context);
    if (engineering != null) {

      // Delete content of the engineering
      ArrayList<EObject> toDelete = new ArrayList<EObject>();
      toDelete.add(engineering);
      ExecutionManager em = TransactionHelper.getExecutionManager(toDelete);
      DeleteStructureCommand command = new DeleteStructureCommand(em.getEditingDomain(), toDelete);

      if (context.get(ITransitionConstants.DIFFMERGE_DISABLE) == null) {
        if (command.canExecute()) {
          command.execute();
        }
        engineering = null;
      }
    }
    if (engineering == null) {
      engineering = CapellamodellerFactory.eINSTANCE.createSystemEngineering("TRANSFORMED");
      AttachmentHelper.getInstance(context).createdElement(null, engineering, context);

      // for debug purposes only
      if (context.get(ITransitionConstants.DIFFMERGE_DISABLE) != null) {
        project.getOwnedModelRoots().add(1, engineering);
      }
    }

    return engineering;
  }

  private SystemEngineering getEngineering(Project project, String name, IContext context) {
    for (ModelRoot root : project.getOwnedModelRoots()) {
      if (root instanceof SystemEngineering) {
        if (name.equals(((SystemEngineering) root).getName())) {
          return (SystemEngineering) root;
        }
      }
    }
    return null;
  }
}
