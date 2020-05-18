/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.impl.LogicalComponentImpl;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * TC_DC_11 - This rule ensures that Logical Component always realizes a System Component.
 */
public class LogicalComponent_RealizedSystemComponents extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent && !ComponentExt.isActor(eObj)) {
        LogicalComponent component = (LogicalComponent) eObj;
        if (component.eContainer() instanceof LogicalComponentImpl) {
          if (!component.getRealizedSystemComponents().isEmpty()) {
            return ctx.createSuccessStatus();
          }
          return ctx.createFailureStatus(
              CapellaElementExt.getValidationRuleMessagePrefix(component) + " does not realize any System Component");
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
