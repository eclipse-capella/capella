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
package org.polarsys.capella.core.data.interaction.validation.stateFragment;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.interaction.services.InstanceRoleExt;
import org.polarsys.capella.core.data.helpers.interaction.services.StateFragmentExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check a StateFragment is related to a Mode/State for which the InstanceRole's AbstractFunction is available.
 */
public class MDCHK_StateFragment_FS_OAS_StateModeAvailability extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx.createSuccessStatus();
    }
    EObject eObj = ctx.getTarget();
    if (!(eObj instanceof StateFragment)) {
      return ctx.createSuccessStatus();
    }
    StateFragment stateFragment = (StateFragment) eObj;
    // It must be a StateFragment with a related AbstractState.
    if (null == stateFragment.getRelatedAbstractState()) {
      return ctx.createSuccessStatus();
    }
    // This rule is only valid for Function Scenarios or Operational Activity Scenarios (scenarios containing only instance roles of functions).
    if (!(stateFragment.eContainer() instanceof Scenario) || !ScenarioExt.isFunctionalScenario((Scenario) stateFragment.eContainer())) {
      return ctx.createSuccessStatus();
    }
    Scenario containingScenario = (Scenario) stateFragment.eContainer();
    // Get related AbstractState.
    AbstractState relatedState = stateFragment.getRelatedAbstractState();

    // Is the instance role's AbstractFunction available in the related Mode/State ?
    InstanceRole instanceRole = StateFragmentExt.getCoveredInstanceRole(stateFragment);
    AbstractFunction abstractFunction = InstanceRoleExt.getAbstractFunction(instanceRole);
    if ((null != abstractFunction) && !abstractFunction.getAvailableInStates().contains(relatedState)) {
      String relatedStateMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(relatedState, false);
      String containingScenarioMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(containingScenario, false);
      String abstractFunctionMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(abstractFunction, false);
      return ctx.createFailureStatus(relatedState.getName(), relatedStateMetaClassLabel, containingScenario.getName(), containingScenarioMetaClassLabel,
          abstractFunction.getName(), abstractFunctionMetaClassLabel);
    }
    return ctx.createSuccessStatus();
  }
}
