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
package org.polarsys.capella.core.data.oa.validation.entity;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that an Entity is realized by at least one system Actor
 */
public class Entity_RealizedSystemComponents extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Entity) {
        Entity actor = (Entity) eObj;
        if (actor.getRealizingSystemComponents().isEmpty()) {
          return ctx_p.createFailureStatus(
              CapellaElementExt.getValidationRuleMessagePrefix(actor) // $NON-NLS-1$
                  + " is not realized by any System Component."); //$NON-NLS-1$
        }
      }
    }

    return ctx_p.createSuccessStatus();
  }
}
