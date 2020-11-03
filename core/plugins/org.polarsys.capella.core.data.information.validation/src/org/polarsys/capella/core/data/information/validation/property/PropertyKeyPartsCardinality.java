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
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.util.PropertyNamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Property untyped
 */
public class PropertyKeyPartsCardinality extends AbstractValidationRule {
  private static final String MAX_CARD_KEYPART = "1";

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    // if eObj is a Property in a named container
    if ((eObj instanceof Property) && (eObj.eContainer() instanceof NamedElement)) {
      NamedElement container = (NamedElement) eObj.eContainer();
      Property property = (Property) eObj;
      // and if it is part of key
      if (property.isIsPartOfKey()) {
        // its max card
        NumericValue ownedMaxCard = property.getOwnedMaxCard();
        String max = PropertyNamingHelper.getCardValue(ownedMaxCard);

        // must be "1"
        if (!MAX_CARD_KEYPART.equals(max)) {
          return ctx.createFailureStatus(container.getName(), property.getName());
        }
      }
    }

    return ctx.createSuccessStatus();
  }
}
