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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class NumericValueHelper {

  private static NumericValueHelper instance;

  private NumericValueHelper() {
    // do nothing
  }

  public static NumericValueHelper getInstance() {
    if (instance == null) {
    	instance = new NumericValueHelper();
    }
    return instance;
  }

  public Object doSwitch(NumericValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE)) {
      return getNumericType(element);
    }

    // no helper found... searching in super classes...
      return DataValueHelper.getInstance().doSwitch(element, feature);
  }

  protected NumericType getNumericType(NumericValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof NumericType) {
      return (NumericType) absType;
    }
    return null;
  }
}
