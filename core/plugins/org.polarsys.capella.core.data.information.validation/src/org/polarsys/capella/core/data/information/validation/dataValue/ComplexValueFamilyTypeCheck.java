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
package org.polarsys.capella.core.data.information.validation.dataValue;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;

/**
 * AbstractComplexValue Value type check
 */
public class ComplexValueFamilyTypeCheck extends AbstractDataValueTypeCheck {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isInstanceOf(EObject eObj) {
    if ((eObj instanceof AbstractComplexValue) && !(eObj instanceof AbstractExpressionValue)) {
      return true;
    }
    return false;
  }

}
