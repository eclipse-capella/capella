/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.AbstractTransitionCommand;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownTransform;

/**
 */
public class IStoISCommand extends AbstractTransitionCommand {

  public IStoISCommand(Collection<EObject> rootElements) {
    super(rootElements);
  }

  public IStoISCommand(Collection<EObject> rootElements, IProgressMonitor progressMonitor) {
    super(rootElements, progressMonitor);
  }

  @Override
  public String getName() {
    if (rootElements != null && !rootElements.isEmpty()) {
      EObject element = rootElements.iterator().next();

      if (TransitionHelper.getService().isIS2ISSALATransitionAvailable(element)) {
        return Messages.transitionIS2IS_SAtoLA_label;
      } else if (TransitionHelper.getService().isIS2ISLAPATransitionAvailable(element)) {
        return Messages.transitionIS2IS_LAtoPA_label;
      } else if (TransitionHelper.getService().isIS2ISPAEPBSTransitionAvailable(element)) {
        return Messages.transitionIS2IS_PAtoEPBS_label;
      }
    }
    return Messages.transitionIS2IS_label;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element) {
    return new TopDownTransform();
  }

//  @Override
//  protected boolean isScenarioValid(Scenario scenario) {
//    return TransitionHelper.getService().isES2ESTransitionAvailable(scenario);
//  }
}
