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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class MDCHK_Scenario_InvolvementComponents_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if ((eObj instanceof Scenario) 
          && !(eObj.eContainer() instanceof OperationalCapability)
          && !((Scenario) eObj).isMerged()
          ) {
        Scenario scenario = (Scenario) eObj;

        AbstractCapability usecase = ScenarioExt.getRelatedCapability(scenario);
        if (usecase != null) {
          for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
            AbstractInstance cpntInst = instRole.getRepresentedInstance();
            if (cpntInst != null) {
              AbstractType cpnt = cpntInst.getType();
              if (cpnt != null) {                
                List<Component> involvedElements = AbstractCapabilityExt.getInvolvedComponents(usecase);
                if (cpnt instanceof ExchangeItem)
                  break;
                else if (!involvedElements.contains(cpnt)) {
                  boolean isOk = false;
                  if (cpnt instanceof GeneralizableElement) {
                    List<GeneralizableElement> superElements = ((GeneralizableElement) cpnt).getSuper();
                    for (GeneralizableElement superElement : superElements) {
                      if (involvedElements.contains(superElement)) {
                        isOk = true;
                      }
                    }
                  }
                  if (!isOk) {
                    IStatus status =  createFailureStatus(ctx_p, new Object[] { cpnt.getName(), usecase.getName() });
                    statuses.add(status);
                  }
                }
              }
            }
          }
        }
      }
    }
    if(statuses.size()>0){
      return ConstraintStatus.createMultiStatus(ctx_p, statuses);
    }
    return ctx_p.createSuccessStatus();
  }
}
