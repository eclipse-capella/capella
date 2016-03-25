/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractComplexValueHelper {

  private static AbstractComplexValueHelper instance;

  private AbstractComplexValueHelper() {
    // do nothing
  }

  public static AbstractComplexValueHelper getInstance() {
    if (instance == null)
      instance = new AbstractComplexValueHelper();
    return instance;
  }

  public Object doSwitch(AbstractComplexValue element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE)) {
      return getComplexType(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = DataValueHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Classifier getComplexType(AbstractComplexValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof Classifier) {
      return (Classifier) absType;
    }
    return null;
  }
}
