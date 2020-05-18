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

import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractBooleanValueHelper {

  private static AbstractBooleanValueHelper instance;

  private AbstractBooleanValueHelper() {
    // Do nothing
  }

  public static AbstractBooleanValueHelper getInstance() {
    if (instance == null) {
    	instance = new AbstractBooleanValueHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractBooleanValue element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE)) {
      return getBooleanType(element);
    }

    // no helper found... searching in super classes...
    return DataValueHelper.getInstance().doSwitch(element, feature);
  }

  protected BooleanType getBooleanType(AbstractBooleanValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof BooleanType) {
      return (BooleanType) absType;
    }
    return null;
  }
}
