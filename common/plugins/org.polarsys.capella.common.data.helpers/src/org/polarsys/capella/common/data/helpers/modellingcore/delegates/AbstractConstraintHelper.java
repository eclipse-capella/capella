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

package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

public class AbstractConstraintHelper {
  private static AbstractConstraintHelper instance;
  
  public static AbstractConstraintHelper getInstance() {
    if (instance == null)
      instance = new AbstractConstraintHelper();
    return instance;
  }

  public Object doSwitch(AbstractConstraint element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONTEXT)) {
      ret = getContext(element);
    } else {
      ret = ModelElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }
  
  protected EObject getContext(AbstractConstraint element) {
    return element.eContainer();
  }

}
