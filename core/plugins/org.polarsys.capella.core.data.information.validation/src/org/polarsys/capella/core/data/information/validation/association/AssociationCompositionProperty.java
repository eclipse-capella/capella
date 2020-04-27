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
package org.polarsys.capella.core.data.information.validation.association;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.util.PropertyNamingHelper;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensure that the cardinality of the CompositionAssociation is 0..1 or 1..1
 */
public class AssociationCompositionProperty extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        Association ass = (Association) eObj;
        if (AssociationExt.isComposition(ass)) {
          for (Property property : AssociationExt.getProperties(ass)) {
            AggregationKind aggregationKind = property.getAggregationKind();
            if (aggregationKind != null && aggregationKind != AggregationKind.COMPOSITION) {
              NumericValue ownedMinCard = property.getOwnedMinCard();
              NumericValue ownedMaxCard = property.getOwnedMaxCard();
              String min = ICommonConstants.EMPTY_STRING;
              String max = ICommonConstants.EMPTY_STRING;

              min = PropertyNamingHelper.getCardValue(ownedMinCard);
              max = PropertyNamingHelper.getCardValue(ownedMaxCard);

              if (min != null && max != null) {
                if (!(min.equalsIgnoreCase("0") || min.equalsIgnoreCase("1")) || !max.equalsIgnoreCase("1")) { //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                  String actual = "{" + min + ICommonConstants.COMMA_CHARACTER + max + "}"; //$NON-NLS-1$//$NON-NLS-2$
                  String expected = "{0,1} or {1,1}"; //$NON-NLS-1$
                  return ctx.createFailureStatus(new Object[] {ass.getName(), property.getName(), actual, expected });
                }
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
