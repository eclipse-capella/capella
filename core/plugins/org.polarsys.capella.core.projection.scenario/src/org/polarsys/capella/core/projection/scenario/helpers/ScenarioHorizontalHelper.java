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
package org.polarsys.capella.core.projection.scenario.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.common.context.IContext;

public abstract class ScenarioHorizontalHelper implements IScenarioHelper {

  @Override
  public void init(IContext context_p) {
    // Nothing here
  }

  @Override
  public void dispose(IContext context_p) {
    // Nothing here
  }
  
  public List<EObject> getTargetRelatedElements(StateFragment state, IContext context_p) {

    if (state.getRelatedAbstractFunction() != null) {
      return Collections.singletonList((EObject) state.getRelatedAbstractFunction());
    }

    if (state.getRelatedAbstractState() != null) {
      return Collections.singletonList((EObject) state.getRelatedAbstractState());
    }
    return Collections.emptyList();
  }

  /**
   * This methods returns instanceRole that will be linked to transformed InteractionState. NOTE: at transformElement() step, we don't know yet
   * traced-InstanceRoles since transformation of InstanceRole is made at the end of the global transformation
   */
  public List<AbstractInstance> getTargetInstances(StateFragment state, IContext context_p) {
    List<AbstractInstance> roles = new ArrayList<AbstractInstance>();

    if (state.getRelatedAbstractFunction() != null) {
      for (EObject related : getTargetRelatedElements(state, context_p)) {
        for (InstanceRole role : state.getStart().getCoveredInstanceRoles()) {
          for (AbstractInstance tracedInstance : getTargetInstances(role, context_p)) {
            if (ScenarioExt.getAvailableFunctionsStateFragment(tracedInstance).contains(related)) {
              roles.add(tracedInstance);
            }
          }
        }
      }
    } else if (state.getRelatedAbstractState() != null) {
      for (InstanceRole role : state.getStart().getCoveredInstanceRoles()) {
        for (AbstractInstance tracedInstance : getTargetInstances(role, context_p)) {
          roles.add(tracedInstance);
        }
      }
    }

    return roles;
  }

}
