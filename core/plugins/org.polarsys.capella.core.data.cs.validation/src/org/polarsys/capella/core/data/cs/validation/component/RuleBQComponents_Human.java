/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public abstract class RuleBQComponents_Human extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {

    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Component component = (Component) eObj;

        IBusinessQuery query = getQuery(component.eClass());
        if (query != null) {
          boolean res = query.getCurrentElements(component, false).stream()
              .anyMatch(x -> component.isHuman() != ((Component) x).isHuman());
          if (res) {
            String componentType = component.isHuman() ? "Human" : "non Human";
            String realizedComponentsType = component.isHuman() ? "non Human" : "Human";
            return ctx.createFailureStatus(
                new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(component), componentType,
                    realizedComponentsType });
          }
        }
      }
    }

    return ctx.createSuccessStatus();
  }

  protected abstract IBusinessQuery getQuery(EClass cls);
}
