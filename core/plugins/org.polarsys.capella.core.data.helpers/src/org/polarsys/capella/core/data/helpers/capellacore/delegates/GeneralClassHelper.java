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

  public Object doSwitch(GeneralClass element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.GENERAL_CLASS__CONTAINED_OPERATIONS)) {
      ret = getContainedOperations(element_p);
    }

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = ClassifierHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List <Operation> getContainedOperations(GeneralClass element_p) {
    List <Operation> ret = new ArrayList <Operation>();
    for (Feature feature : element_p.getOwnedFeatures()) {
      if(feature instanceof Operation) {
        ret.add((Operation)feature);
      }
    }
    return ret;
  }
}
