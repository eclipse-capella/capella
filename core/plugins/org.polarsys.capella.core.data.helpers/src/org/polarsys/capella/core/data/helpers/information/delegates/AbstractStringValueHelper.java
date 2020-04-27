/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractStringValueHelper {

  private static AbstractStringValueHelper instance;

  private AbstractStringValueHelper() {
    // do nothing
  }

  public static AbstractStringValueHelper getInstance() {
    if (instance == null) {
    	instance = new AbstractStringValueHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractStringValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE)) {
      return getStringType(element);
    }

    // no helper found... searching in super classes...
    return DataValueHelper.getInstance().doSwitch(element, feature);
  }

  protected StringType getStringType(AbstractStringValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof StringType) {
      return (StringType) absType;
    }
    return null;
  }
}
