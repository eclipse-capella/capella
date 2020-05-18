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

package org.polarsys.capella.core.data.cs.validation.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that a part is typed by an accessible Component.
 */
public class Part_AccessibleType extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Part) {
        Part part = (Part) eObj;
        AbstractType abstractType = part.getAbstractType();
        if (abstractType != null) {
          List<EObject> accessibleComponent = new ArrayList<EObject>(1);
          // get all eligible Component that can be typed by 'part'
          if (abstractType instanceof Component) {
            accessibleComponent.addAll(getAvailableComponentToType(part, (Component) abstractType));
            
            // return failure warning : part is not typed any of the 'accessibleComponent'
            if (!accessibleComponent.contains(abstractType)) {
              return ctx.createFailureStatus(
                  new Object[] { part.getName(), CapellaElementExt.getValidationRuleMessagePrefix(abstractType) });
            }
          }
          
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  public List<EObject> getAvailableComponentToType(Part part, Component component) {
    List<EObject> componentList = new ArrayList<EObject>();
    // get all the component that can by typed
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(part.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      componentList.addAll(query.getAvailableElements(part));
    }

    return componentList;
  }
}
