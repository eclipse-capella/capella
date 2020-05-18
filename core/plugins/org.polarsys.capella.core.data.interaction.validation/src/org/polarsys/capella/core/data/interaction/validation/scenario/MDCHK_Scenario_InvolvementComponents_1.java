/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.scenario;

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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Scenario_InvolvementComponents_1 extends AbstractValidationRule {
  private static final int ERROR_CODE = 1;

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if ((eObj instanceof Scenario) && !(eObj.eContainer() instanceof OperationalCapability)
          && !((Scenario) eObj).isMerged()) {
        Scenario scenario = (Scenario) eObj;

        AbstractCapability usecase = ScenarioExt.getRelatedCapability(scenario);
        if (usecase != null) {
          List<Component> involvedElements = AbstractCapabilityExt.getInvolvedComponents(usecase);
          Set<AbstractFunction> containedFunctions = new HashSet<AbstractFunction>();
          for (Component comp : involvedElements) {
            containedFunctions.addAll(comp.getAllocatedFunctions());
          }
          containedFunctions.addAll(usecase.getInvolvedAbstractFunctions());

          for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
            AbstractInstance cpntInst = instRole.getRepresentedInstance();
            if (cpntInst != null) {
              AbstractType cpnt = cpntInst.getType();
              if (cpnt != null) {
                if (cpnt instanceof ExchangeItem)
                  break;
                else if (!involvedElements.contains(cpnt)) {
                  boolean isOk = false;
                  if (cpnt instanceof GeneralizableElement) {
                    List<GeneralizableElement> superElements = ((GeneralizableElement) cpnt).getSuper();
                    for (GeneralizableElement superElement : superElements) {
                      if (involvedElements.contains(superElement)) {
                        isOk = true;
                        break;
                      }
                    }
                  }
                  if (!isOk) {
                    addCtxStatus(statuses, ctx_p, eObj, usecase, cpnt, cpnt.getName(), "Component");
                  }
                }
              } else {
                if (!containedFunctions.contains(cpntInst)) {
                  if (cpntInst instanceof AbstractFunction) {
                    boolean isOk = false;
                    AbstractFunction function = (AbstractFunction) cpntInst;
                    List<AbstractFunction> parentFunctions = FunctionExt.getParentFunctions(function);
                    for (AbstractFunction parentFunction : parentFunctions) {
                      if (containedFunctions.contains(parentFunction)) {
                        isOk = true;
                        break;
                      }
                    }
                    if (!isOk) {
                      addCtxStatus(statuses, ctx_p, eObj, usecase, cpntInst, cpntInst.getName(), "Function");
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    if (statuses.size() > 0) {
      return ConstraintStatus.createMultiStatus(ctx_p, statuses);
    }
    return ctx_p.createSuccessStatus();
  }

  private void addCtxStatus(Collection<IStatus> statuses, IValidationContext ctx_p, EObject eObj,
      AbstractCapability usecase, ModelElement element, String elementName, String contextName) {
    Object[] argMessage = new Object[] { elementName, contextName, usecase.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(usecase, false) };
    Collection<EObject> resultLocus = new ArrayList<EObject>();
    resultLocus.add(eObj);
    resultLocus.add(usecase);
    resultLocus.add(element);
    statuses.add(ConstraintStatus.createStatus(ctx_p, eObj, resultLocus, IStatus.WARNING, ERROR_CODE,
        Messages.DCOM_05_Validator_Message, argMessage));
  }

}
