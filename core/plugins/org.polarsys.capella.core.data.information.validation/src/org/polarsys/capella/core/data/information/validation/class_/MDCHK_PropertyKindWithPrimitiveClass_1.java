/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.class_;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * @deprecated
 */
@Deprecated
public class MDCHK_PropertyKindWithPrimitiveClass_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Class) {
        Class currentClass = (Class) eObj;

        for (Property property : currentClass.getContainedProperties()) {
          Type targetType = property.getType();

          if (targetType instanceof Class) {
            Class targetClass = (Class) targetType;

            if (currentClass.isIsPrimitive() && !property.getAggregationKind().equals(AggregationKind.COMPOSITION)) {
              // Case : current class is primitive -> Relation kind should be 'Composition' only
              return createFailureStatus(ctx, new Object[] { currentClass.getName(), property.getName()});
            } else if (!currentClass.isIsPrimitive()) {
              // Case : current class is non primitive // -> Relation kind should be 'Composition' only in case of TargetClass type is primitive
              if (targetClass.isIsPrimitive() && !property.getAggregationKind().equals(AggregationKind.COMPOSITION)) {
                String str = "(because its property type is primitive)"; //$NON-NLS-1$
                return createFailureStatus(ctx, new Object[] { currentClass.getName(), property.getName(),"not",str }); //$NON-NLS-1$
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
