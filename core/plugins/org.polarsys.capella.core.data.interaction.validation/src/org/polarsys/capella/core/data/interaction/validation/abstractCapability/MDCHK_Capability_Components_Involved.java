/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.interaction.validation.abstractCapability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Capability_Components_Involved extends AbstractValidationRule {
  private static final int ERROR_CODE = 1;

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    java.util.Collection<IStatus> statuses = new ArrayList<IStatus>();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractCapability) {

        AbstractCapability capability = (AbstractCapability) eObj;
        Set<Component> involvedComponents = new HashSet<Component>(
            AbstractCapabilityExt.getInvolvedComponents(capability));
        for (Component element : involvedComponents) {
          if (!(element instanceof SystemComponent && !ComponentExt.isActor(element))) {
            if (!isComponentInFunctionalChain(element, capability) && !isComponentInScenario(element, capability)) {
              addCtxStatus(statuses, ctx, eObj, capability, element);
            }
          }
        }
      }
    }

    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }

  private boolean isComponentInFunctionalChain(Component component, AbstractCapability abstractCapability) {
    List<Component> componentsInFunctionalChains = AbstractCapabilityExt
        .getComponentsFromAbstractCapabilityFunctionalChains(abstractCapability);
    return componentsInFunctionalChains.contains(component);
  }

  private boolean isComponentInScenario(Component component, AbstractCapability abstractCapability) {
    List<Component> componentsInScenarios = AbstractCapabilityExt
        .getComponentsFromAbstractCapabilityScenarios(abstractCapability);
    return componentsInScenarios.contains(component);
  }

  private void addCtxStatus(Collection<IStatus> statuses, IValidationContext ctx, EObject eObj,
      AbstractCapability capability, Component element) {
    Object[] msgArguments = new Object[] { capability.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(capability, false),
        CapellaElementExt.getValidationRuleMessagePrefix(element),
        getFunctionalChainType(capability) + " or Scenarios" };
    Collection<EObject> resultLocus = new ArrayList<EObject>();
    resultLocus.add(capability);
    resultLocus.add(element);
    statuses.add(ConstraintStatus.createStatus(ctx, eObj, resultLocus, IStatus.WARNING, ERROR_CODE,
        Messages.DWF_CA_07_Validator_Message, msgArguments));
  }

  private String getFunctionalChainType(AbstractCapability capability) {
    String type = "Functional Chains";
    if (capability instanceof OperationalCapability)
      type = "Operational Processes";

    return type;
  }
}
