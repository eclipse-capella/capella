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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that each parts of a component have the same level (i.e context, logical, physical,...) that the
 * component itself.
 */
public class MDCHK_Component_PartTypeLevel extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Component cpnt = (Component) eObj;
        for (Part part : cpnt.getContainedParts()) {

          AbstractType abstractType = part.getAbstractType();
          if (null != abstractType) {
            // Cannot test the ports levels...
            if ((cpnt instanceof Entity && !(abstractType instanceof Entity))
                || (cpnt instanceof SystemComponent && !(abstractType instanceof SystemComponent))
                || (cpnt instanceof LogicalComponent && !(abstractType instanceof LogicalComponent))
                || (cpnt instanceof PhysicalComponent && !(abstractType instanceof PhysicalComponent))
                || (cpnt instanceof ConfigurationItem && !(abstractType instanceof ConfigurationItem))) {
              return ctx.createFailureStatus(part.getName(), CapellaElementExt.getValidationRuleMessagePrefix(cpnt),
                  CapellaElementExt.getValidationRuleMessagePrefix(part.getAbstractType()));
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
