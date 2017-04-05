/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.common.statemachine.validation.StateMachineUtils;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class StateModeFunctionalChain extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    State state = (State) ctx.getTarget();
    StateMachine stateMachine = StateMachineUtils.getStateMachine(state);
    Component component = StateMachineUtils.getComponent(stateMachine);
    EList<FunctionalChain> fcInvolvers = FunctionalAnalysisUtils.getFunctionalChainInvolvers(component);

    Collection<FunctionalChainAnalysisResult> results = new ArrayList<FunctionalChainAnalysisResult>();

    for (FunctionalChain fc : fcInvolvers) {
      EList<AbstractFunction> commonFunctions = FunctionalAnalysisUtils.getCommonFunctions(component, fc);
      results.addAll(FunctionalAnalysisUtils.analyzeFC_State(fc, state, component, commonFunctions));
    }

    if (results.size() > 0) {
      Collection<IStatus> children = new ArrayList<IStatus>();
      for (FunctionalChainAnalysisResult result : results) {
        children.add(ctx.createFailureStatus(result.toReadableString()));
      }
      return ConstraintStatus.createMultiStatus(ctx, children);
    }
    return ctx.createSuccessStatus();
  }
}
