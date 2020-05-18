/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.instanceRole;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_InstanceRole_Type_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof InstanceRole) {
        InstanceRole instRole_p = (InstanceRole) eObj;
        AbstractInstance cpntInst = instRole_p.getRepresentedInstance();

        if (cpntInst != null) {

          ScenarioKind kind = ScenarioKind.UNSET;
          EObject container = instRole_p.eContainer();
          Scenario scenario = null;

          if (container instanceof Scenario) {
            scenario = (Scenario) container;
            kind = scenario.getKind();
          }

          if (ScenarioKind.FUNCTIONAL.equals(kind)) {
            if (!(cpntInst instanceof AbstractFunction)) {
              return createFailureStatus(ctx, new Object[] { instRole_p.getName() });
            }

          } else if (ScenarioKind.DATA_FLOW.equals(kind) || ScenarioKind.INTERFACE.equals(kind)) {
            if (cpntInst.getType() == null) {
              return createFailureStatus(ctx, new Object[] { instRole_p.getName() });
            }
          } else if (ScenarioKind.INTERACTION.equals(kind) && (scenario != null)) {
            // check if we are in an OES (i.e. scenario container is an Operational Capability)
            // and the representedInstance is a Part.
            if ((scenario.eContainer() instanceof OperationalCapability) && (cpntInst instanceof Part)) {
              if (cpntInst.getType() == null) {
                return createFailureStatus(ctx, new Object[] { instRole_p.getName() });
              }
            }
          }

        } else {
          return createFailureStatus(ctx, new Object[] { instRole_p.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
