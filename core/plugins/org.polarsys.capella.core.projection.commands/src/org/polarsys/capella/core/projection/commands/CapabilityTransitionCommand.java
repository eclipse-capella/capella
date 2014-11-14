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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.transfo.operationalcapability.ExtendedTransformCapability;
import org.polarsys.capella.core.transfo.operationalcapability.ExtendedTransformOACapabilityToSACapability;

/**
 */
public class CapabilityTransitionCommand extends AbstractTransitionCommand {

  public CapabilityTransitionCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public CapabilityTransitionCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  /**
   * @see org.polarsys.capella.common.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return Messages.transitionCapability_label;
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {

    if ((element_p != null) && (element_p instanceof CapellaElement)) {
      CapellaElement element = (CapellaElement) element_p;

      if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(element)) {
        return new ExtendedTransformOACapabilityToSACapability();

      } else if (CapellaLayerCheckingExt.isAOrInContextLayer(element)) {
        return new ExtendedTransformCapability();

      } else if (CapellaLayerCheckingExt.isAOrInLogicalLayer(element)) {
        return new ExtendedTransformCapability();
      }
    }

    return null;
  }

}
