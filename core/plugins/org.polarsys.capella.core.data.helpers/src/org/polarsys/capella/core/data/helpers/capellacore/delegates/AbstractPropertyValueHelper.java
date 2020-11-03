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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public class AbstractPropertyValueHelper {
  private static AbstractPropertyValueHelper instance;

  private AbstractPropertyValueHelper() {
  }

  public static AbstractPropertyValueHelper getInstance() {
    if (instance == null) {
      instance = new AbstractPropertyValueHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractPropertyValue element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS)) {
      ret = getValuedElements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<CapellaElement> getValuedElements(AbstractPropertyValue element) {
    return EObjectExt.getReferencers(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES);
  }
}
