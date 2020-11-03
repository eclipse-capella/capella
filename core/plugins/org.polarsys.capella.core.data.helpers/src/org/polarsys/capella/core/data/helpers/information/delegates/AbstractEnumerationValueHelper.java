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

import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractEnumerationValueHelper {

  private static AbstractEnumerationValueHelper instance;

  private AbstractEnumerationValueHelper() {
    // Do nothing
  }

  public static AbstractEnumerationValueHelper getInstance() {
    if (instance == null) {
    	instance = new AbstractEnumerationValueHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractEnumerationValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE)) {
      return getEnumerationType(element);
    }

    // no helper found... searching in super classes...
    return DataValueHelper.getInstance().doSwitch(element, feature);
  }

  protected Enumeration getEnumerationType(AbstractEnumerationValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof Enumeration) {
      return (Enumeration) absType;
    }
    return null;
  }
}
