/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.interaction.services.InstanceRoleExt;
import org.polarsys.capella.core.data.helpers.interaction.services.StateFragmentExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowHelper;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check a StateFragment is related to an Abstract Function allocated by its InstanceRole's Component.
 */
public class MDCHK_StateFragment_ES_OES_AllocatedFunction extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx_p.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx_p.createSuccessStatus();
    }
    EObject eObj = ctx_p.getTarget();
    if (!(eObj instanceof StateFragment)) {
      return ctx_p.createSuccessStatus();
    }
    StateFragment stateFragment = (StateFragment) eObj;
    // It must be a StateFragment with a related AbstractFunction.
    if (null == stateFragment.getRelatedAbstractFunction()) {
      return ctx_p.createSuccessStatus();
    }
    // This rule is only valid for Exchange Scenarios (DATA_FLOW) or Operational Entity Scenarios (INTERACTION without instance role of functions).
    Scenario containingScenario = (Scenario) stateFragment.eContainer();
    if (!((containingScenario.getKind() == ScenarioKind.DATA_FLOW) || ((containingScenario.getKind() == ScenarioKind.INTERACTION) && !ScenarioExt
        .isFunctionalScenario(containingScenario)))) {
      return ctx_p.createSuccessStatus();
    }
    // Get related AbstractFunction.
    AbstractFunction relatedFunction = stateFragment.getRelatedAbstractFunction();

    // Is the StateFragment related AbstractFunction allocated by instance role's Component ?
    InstanceRole instanceRole = StateFragmentExt.getCoveredInstanceRole(stateFragment);
    List<AbstractFunction> allocatedFunctions = DataFlowHelper.findFunctionRealizedByInstanceRole(instanceRole);
    if (!allocatedFunctions.contains(relatedFunction)) {
      Component component = InstanceRoleExt.getComponent(instanceRole);
      String relatedFunctionMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(relatedFunction, false);
      String scenarioMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(containingScenario, false);
      String componentMetaClassLabel = EObjectLabelProviderHelper.getMetaclassLabel(component, false);
      String deployed = (BlockArchitectureExt.getRootBlockArchitecture(containingScenario) instanceof PhysicalArchitecture) ? "/deployed" : ""; //$NON-NLS-1$ //$NON-NLS-2$

      String componentName = null;
      if (null != component) {
        componentName = component.getName();
      }
      return ctx_p.createFailureStatus(relatedFunction.getName(), relatedFunctionMetaClassLabel, containingScenario.getName(), scenarioMetaClassLabel,
          componentName, componentMetaClassLabel, deployed);
    }

    return ctx_p.createSuccessStatus();
  }
}
