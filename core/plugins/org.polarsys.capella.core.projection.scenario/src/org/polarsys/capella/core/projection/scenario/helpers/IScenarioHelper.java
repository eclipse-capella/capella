/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.IHandler;

public interface IScenarioHelper extends IHandler {

  /**
   * Returns for a StateFragment in the source scenario related states or functions that will be linked to the transformed stateFragments
   */
  public List<EObject> getTargetRelatedElements(StateFragment state, IContext context_p);

  /**
   * Returns for a StateFragment in the source scenario the target instanceRoles covering the transformed fragment
   */
  public List<AbstractInstance> getTargetInstances(StateFragment state, IContext context_p);

  /**
   * Returns for an instanceRole in the source scenario the instances that will be represented by transformed instanceRoles
   */
  public List<AbstractInstance> getTargetInstances(InstanceRole role_p, IContext context_p);

  public static IScenarioHelper getInstance(IContext context_p) {
    if (context_p.containsKey(IScenarioHelper.class.getSimpleName())) {
      return (IScenarioHelper) context_p.get(IScenarioHelper.class.getSimpleName());
    }
    return null;
  }

  public static void setInstance(IContext context_p, IScenarioHelper handler) {
    context_p.put(IScenarioHelper.class.getSimpleName(), handler);
  }
  
}
