/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;

public class PropertyValueGroupHelper {
  private static PropertyValueGroupHelper instance;

  private PropertyValueGroupHelper() {
  }

  public static PropertyValueGroupHelper getInstance() {
    if (instance == null) {
      instance = new PropertyValueGroupHelper();
    }
    return instance;
  }

  public Object doSwitch(PropertyValueGroup element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.PROPERTY_VALUE_GROUP__VALUED_ELEMENTS)) {
      ret = getValuedElements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamespaceHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<CapellaElement> getValuedElements(PropertyValueGroup element) {
    return EObjectExt.getReferencers(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
  }
}
