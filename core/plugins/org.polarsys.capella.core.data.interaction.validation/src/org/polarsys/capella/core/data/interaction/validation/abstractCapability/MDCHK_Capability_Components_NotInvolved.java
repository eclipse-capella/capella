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
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Capability_Components_NotInvolved extends AbstractValidationRule {
  private static final int ERROR_CODE = 1;

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    java.util.Collection<IStatus> statuses = new ArrayList<IStatus>();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractCapability) {

        AbstractCapability capability = (AbstractCapability) eObj;
        List<Component> components = AbstractCapabilityExt.getInvolvedComponents(capability);
        List<Component> componentsInCapabilityScenarios = AbstractCapabilityExt
            .getComponentsFromAbstractCapabilityScenarios(capability);
        List<Component> componentsInCapabilityFunctionalChains = AbstractCapabilityExt
            .getComponentsFromAbstractCapabilityFunctionalChains(capability);
        Set<Component> allReferencedComponents = new HashSet<Component>();

        if (!componentsInCapabilityScenarios.isEmpty())
          allReferencedComponents.addAll(componentsInCapabilityScenarios);

        if (!componentsInCapabilityFunctionalChains.isEmpty())
          allReferencedComponents.addAll(componentsInCapabilityFunctionalChains);

        allReferencedComponents.removeAll(components);
        for (Component element : allReferencedComponents) {
          addCtxStatus(statuses, ctx, eObj, capability, element);
        }
      }
    }

    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
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
        Messages.DWF_CA_08_Validator_Message, msgArguments));
  }

  private String getFunctionalChainType(AbstractCapability capability) {
    String type = "Functional Chains";
    if (capability instanceof OperationalCapability)
      type = "Operational Processes";

    return type;
  }
}
