/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.validation.statetransition;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.model.helpers.ComponentExt;

import com.google.common.base.Joiner;

public class MDCHK_StateMachine_Function extends AbstractModelConstraint {

  private static final String DO_ACTIVITY = "doActivity"; //$NON-NLS-1$
  private static final String ENTRY = "entry"; //$NON-NLS-1$
  private static final String EXIT = "exit"; //$NON-NLS-1$

  public MDCHK_StateMachine_Function() {
    // TODO Auto-generated constructor stub
  }

  /**/
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    State state = (State) ctx_p.getTarget();
    EObject eContainer = state.eContainer();
    String[] res = new String[3];
    res[0] = state.getDoActivity() == null ? null : DO_ACTIVITY;
    res[1] = state.getEntry() == null ? null : ENTRY;
    res[2] = state.getExit() == null ? null : EXIT;
    String[] elements = new String[3];
    elements[0] = state.getDoActivity() == null ? null : state.getDoActivity().getName();
    elements[1] = state.getEntry() == null ? null : state.getEntry().getName();
    elements[2] = state.getExit() == null ? null : state.getExit().getName();

    while (!(eContainer instanceof Component) && (!(eContainer instanceof Class))) {
      eContainer = eContainer.eContainer();
    }
    if (eContainer instanceof Component) {
      Component container = (Component) eContainer;
      Collection<Component> subComponents = ComponentExt.getAllSubUsedAndDeployedComponents(container);
      subComponents.add(container);
      for (Component component : subComponents) {
        if (((state.getDoActivity() instanceof AbstractFunction))
            && component.getAllocatedFunctions().contains(state.getDoActivity())) {
          res[0] = null;
          elements[0] = null;
        }
        if (((state.getEntry() instanceof AbstractFunction))
            && component.getAllocatedFunctions().contains(state.getEntry())) {
          res[1] = null;
          elements[1] = null;
        }
        if (((state.getExit() instanceof AbstractFunction))
            && component.getAllocatedFunctions().contains(state.getExit())) {
          res[2] = null;
          elements[2] = null;
        }
      }
      if ((res[0] == res[1]) && (res[1] == res[2]) && (res[2] == null)) {
        return ctx_p.createSuccessStatus();
      } else {
        return ctx_p
            .createFailureStatus(new Object[] {
                Joiner.on("/").skipNulls().join(res), Joiner.on("/").skipNulls().join(elements), state.getName(), eContainer }); //$NON-NLS-1$
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
