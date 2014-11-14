/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.lc2pc.breakdownstrategy.TransformLCtoPC_breakDownStrategy;
import org.polarsys.capella.core.projection.lc2pc.leafstrategy.TransformLCtoPC_leafStrategy;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;

/**
 */
public class LCtoPCTransitionCommand extends AbstractTransitionCommand {

  public LCtoPCTransitionCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public LCtoPCTransitionCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  /**
   * @see org.polarsys.capella.common.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return Messages.transitionLC2PC_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {
    EObject modelElement = modelElement_p;

    if (modelElement instanceof Part) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) modelElement))) {
        modelElement = ((Part) modelElement).getAbstractType();
      }
    }

    return Collections.singleton(modelElement);
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {

    if ((element_p != null) && (element_p instanceof CapellaElement)) {
      CapellaElement element = (CapellaElement) element_p;

      if (CapellaLayerCheckingExt.isAOrInLogicalLayer(element)) {
        AbstractTransform transfLCtoPC = null;
        if (ProjectionPreferencesPlugin.getDefault().isLC2PCLeafStrategy()) {
          transfLCtoPC = new TransformLCtoPC_leafStrategy();
        } else {
          transfLCtoPC = new TransformLCtoPC_breakDownStrategy();
        }
        return transfLCtoPC;
      }
    }

    return null;
  }

}
