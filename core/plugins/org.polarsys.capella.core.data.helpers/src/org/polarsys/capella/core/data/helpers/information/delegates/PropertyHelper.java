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

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;

public class PropertyHelper {
  private static PropertyHelper instance;

  private PropertyHelper() {
  }

  public static PropertyHelper getInstance() {
    if (instance == null) {
      instance = new PropertyHelper();
    }
    return instance;
  }

  public Object doSwitch(Property element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(InformationPackage.Literals.PROPERTY__ASSOCIATION)) {
      ret = getAssociation(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypedElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Association getAssociation(Property element) {
    Collection<Association> assocations = EObjectExt.getReferencers(element, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS);
    if (!assocations.isEmpty()) {
      return assocations.iterator().next();
    }
    return null;
  }
}
