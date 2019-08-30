/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.dataValue;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;

/**
 * EnumerationReference Value type check
 */
public class EnumerationReferenceTypeCheck extends AbstractDataValueTypeCheck {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isInstanceOf(EObject eObj) {
    if ((eObj instanceof EnumerationReference) && !(eObj instanceof AbstractExpressionValue)) {
      return true;
    }
    return false;
  }

}
