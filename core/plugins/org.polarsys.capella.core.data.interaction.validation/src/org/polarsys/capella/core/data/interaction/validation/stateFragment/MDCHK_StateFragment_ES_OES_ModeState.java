/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.interaction.services.InstanceRoleExt;
import org.polarsys.capella.core.data.helpers.interaction.services.StateFragmentExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check a StateFragment is related to a Mode/State included in its InstanceRole Component's.
 */
public class MDCHK_StateFragment_ES_OES_ModeState extends AbstractValidationRule {
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
    // This rule is only valid for Exchange Scenarios (DATA_FLOW) or Operational Entity Scenarios (INTERACTION without instance role of functions).
    Scenario containingScenario = (Scenario) stateFragment.eContainer();
    if (!((containingScenario.getKind() == ScenarioKind.DATA_FLOW) || ((containingScenario.getKind() == ScenarioKind.INTERACTION) && !ScenarioExt
        .isFunctionalScenario(containingScenario)))) {
      return ctx.createSuccessStatus();
    }
    // Get related AbstractState.
    AbstractState relatedState = stateFragment.getRelatedAbstractState();

    // Is this Mode/State nested in the instance role's Component ?
    InstanceRole instanceRole = StateFragmentExt.getCoveredInstanceRole(stateFragment);
    Component component = InstanceRoleExt.getComponent(instanceRole);
    List<CapellaElement> modeAndStates = ComponentExt.getAllStatesAndModesFromComponent(component);
    if (component != null && !modeAndStates.contains(relatedState)) {
      String stateMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(relatedState, false);
      String scenarioMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(containingScenario, false);
      String componentMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(component, false);
      return ctx.createFailureStatus(relatedState.getName(), stateMetaClassLabel, containingScenario.getName(), scenarioMetaClassLabel, component.getName(),
          componentMetaClassLabel);
    }
    return ctx.createSuccessStatus();
  }
}
