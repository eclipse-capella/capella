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
package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

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

  public Object doSwitch(AbstractConstraint element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONTEXT)) {
      ret = element_p.eContainer();
    } else {
      ret = ModelElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

}
