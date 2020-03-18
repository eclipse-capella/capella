/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownTransform;

/**
 */
public class EStoESCommand extends ESToISCommand {

  public EStoESCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public EStoESCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    if ((rootElements != null) && (rootElements.size() > 0)) {
      EObject element = rootElements.iterator().next();

      if (TransitionHelper.getService().isES2ESForOASATransitionAvailable(element)) {
        return Messages.transitionES2ES_OAtoSA_label;

      } else if (TransitionHelper.getService().isES2ESForSALATransitionAvailable(element)) {
        return Messages.transitionES2ES_SAtoLA_label;

      } else if (TransitionHelper.getService().isES2ESForLAPATransitionAvailable(element)) {
        return Messages.transitionES2ES_LAtoPA_label;

      }
    }
    return Messages.transitionES2ES_label;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new TopDownTransform();
  }

  @Override
  protected boolean isScenarioValid(Scenario scenario_p) {
    return TransitionHelper.getService().isES2ESTransitionAvailable(scenario_p);
  }
}
