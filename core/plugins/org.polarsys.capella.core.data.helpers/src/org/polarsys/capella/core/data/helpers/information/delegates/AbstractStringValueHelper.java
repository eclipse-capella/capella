/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    if (instance == null)
      instance = new AbstractStringValueHelper();
    return instance;
  }

  public Object doSwitch(AbstractStringValue element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE)) {
      return getStringType(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = DataValueHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected StringType getStringType(AbstractStringValue element_p) {
    AbstractType absType = element_p.getAbstractType();
    if (absType instanceof StringType) {
      return (StringType) absType;
    }
    return null;
  }
}
