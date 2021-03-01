/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/*
 *  DWF_SM_16: Mode and State can not be mixed in the same State Machine
 */
public class MDCHK_State_Mode_Siblings extends AbstractModelConstraint {
  
  public boolean isMixedHierarchyAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed();
  }
  
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (isMixedHierarchyAllowed())
      return ctx.createSuccessStatus();
    
    StateMachine stateMachine = (StateMachine) ctx.getTarget();
    
    // check if state/mode has no mode/state as "brother"
    EList<Region> regions = stateMachine.getOwnedRegions();
    Collection<AbstractState> states = new ArrayList<>();
    Collection<AbstractState> modes = new ArrayList<>();
    for (Region region : regions) {
      for (AbstractState st : region.getOwnedStates()) {
        if (st instanceof Mode) {
          modes.add(st);
        } else if (st.eClass() == CapellacommonPackage.eINSTANCE.getState()) {
          states.add(st);
        }
      }
    }
    if (!modes.isEmpty() && !states.isEmpty()) {
      return ctx.createFailureStatus(stateMachine.getName());
    }
    return ctx.createSuccessStatus();
  }
}

