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

import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractComplexValueHelper {

  private static AbstractComplexValueHelper instance;

  private AbstractComplexValueHelper() {
    // Do nothing
  }

  public static AbstractComplexValueHelper getInstance() {
    if (instance == null) {
    	instance = new AbstractComplexValueHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractComplexValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE)) {
      return getComplexType(element);
    }

    // no helper found... searching in super classes...
    return DataValueHelper.getInstance().doSwitch(element, feature);
  }

  protected Classifier getComplexType(AbstractComplexValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof Classifier) {
      return (Classifier) absType;
    }
    return null;
  }
}
