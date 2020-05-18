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
package org.polarsys.capella.core.projection.scenario.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.topdown.FS2FSTransform;

/**
 */
public class FStoFSCommand extends ESToISCommand {

  public FStoFSCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public FStoFSCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    if ((rootElements != null) && (rootElements.size() > 0)) {
      EObject element = rootElements.iterator().next();

      if (TransitionHelper.getService().isFS2FSForOASATransitionAvailable(element)) {
        return Messages.transitionFS2FS_OAtoSA_label;

      } else if (TransitionHelper.getService().isFS2FSForLAPATransitionAvailable(element)) {
        return Messages.transitionFS2FS_LAtoPA_label;

      } else if (TransitionHelper.getService().isFS2FSForSALATransitionAvailable(element)) {
        return Messages.transitionFS2FS_SAtoLA_label;
      }
    }

    return Messages.transitionFS2FS_label;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new FS2FSTransform();
  }

  @Override
  protected boolean isScenarioValid(Scenario scenario_p) {
    return TransitionHelper.getService().isFS2FSTransitionAvailable(scenario_p);
  }
}
