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
package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;

public class ModelElementHelper {
  private static ModelElementHelper instance;

  private ModelElementHelper() {
  }

  public static ModelElementHelper getInstance() {
    if (instance == null) {
      instance = new ModelElementHelper();
    }
    return instance;
  }

  public Object doSwitch(ModelElement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ModellingcorePackage.Literals.MODEL_ELEMENT__CONSTRAINTS)) {
      ret = getConstraints(element);
    }

    return ret;
  }

  protected List<AbstractConstraint> getConstraints(ModelElement element) {
    return EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS);
  }
}
