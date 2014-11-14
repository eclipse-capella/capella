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
package org.polarsys.capella.core.data.cs.validation.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * This rule ensures that a part is typed by an accessible Component.
 */
public class PartAccessibleType extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Part) {
        Part part = (Part) eObj;
        AbstractType abstractType = part.getAbstractType();
        if (abstractType != null) {
          List<CapellaElement> accessibleComponent = new ArrayList<CapellaElement>(1);
          // get all eligible Component that can be typed by 'part'
          if (abstractType instanceof Component) {
            accessibleComponent.addAll(getAvailableComponentToType(part, (Component) abstractType));
            
            // return failure warning : part is not typed any of the 'accessibleComponent'
            if (!accessibleComponent.contains(abstractType)) {
              return createFailureStatus(ctx_p, new Object[] { part.getName() });
            }
          }
          
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

  public List<CapellaElement> getAvailableComponentToType(Part part_p, Component component_p) {
    List<CapellaElement> componentList = new ArrayList<CapellaElement>();
    // get all the component that can by typed
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(part_p.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    if (query != null) {
      componentList.addAll(query.getAvailableElements(part_p));
    }

    return componentList;
  }
}
