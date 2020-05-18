/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Abstract Cardinality parent rule.
 * 
 * Used by @see PropertyMaximumCardinalityIsNatural and @see PropertyMinimumCardinalityIsNaturalOrZero.
 * 
 */
public abstract class AbstractCardinalityRule extends AbstractValidationRule {
  /**
   * Check EObject class type. If EObject's class is Property type and its parent's class type is an Association or a
   * Class: return true. Else, return false.
   * 
   * @param eObj
   * @return
   */
  protected boolean isValidType(EObject eObj) {
    return eObj instanceof Property && (eObj.eContainer() instanceof Association || eObj.eContainer() instanceof Class)
        || eObj instanceof ExchangeItem;
  }
}
