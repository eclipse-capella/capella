/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration;

import org.eclipse.emf.ecore.EClass;

/**
 * 
 * This class represents a feature that exists before the migration but not after the migration. Since the feature can
 * not be loaded, it's referenced via its name.
 */
public class UnknownEStructuralFeature {
  EClass containingEClass;
  String featureName;

  public UnknownEStructuralFeature(EClass containEClass, String featureName) {
    this.containingEClass = containEClass;
    this.featureName = featureName;
  }

  public EClass getContainingEClass() {
    return containingEClass;
  }

  public String getFeatureName() {
    return featureName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    UnknownEStructuralFeature unknownEStructuralFeature = (UnknownEStructuralFeature) obj;
    return featureName.equals(unknownEStructuralFeature.getFeatureName())
        && containingEClass.equals(unknownEStructuralFeature.getContainingEClass());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((featureName == null) ? 0 : featureName.hashCode());
    result = prime * result + ((containingEClass == null) ? 0 : containingEClass.hashCode());
    return result;
  }
}
