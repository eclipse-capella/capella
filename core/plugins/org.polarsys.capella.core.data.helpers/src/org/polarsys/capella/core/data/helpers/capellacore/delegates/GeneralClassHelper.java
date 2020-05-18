/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public class GeneralClassHelper {

  private static GeneralClassHelper instance;

  private GeneralClassHelper() {
    // do nothing
  }

  public static GeneralClassHelper getInstance(){
    if(instance == null)
      instance = new GeneralClassHelper();
    return instance;
  }

  public Object doSwitch(GeneralClass element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.GENERAL_CLASS__CONTAINED_OPERATIONS)) {
      ret = getContainedOperations(element);
    }

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = ClassifierHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List <Operation> getContainedOperations(GeneralClass element) {
    List <Operation> ret = new ArrayList <>();
    for (Feature feature : element.getOwnedFeatures()) {
      if(feature instanceof Operation) {
        ret.add((Operation)feature);
      }
    }
    return ret;
  }
}
