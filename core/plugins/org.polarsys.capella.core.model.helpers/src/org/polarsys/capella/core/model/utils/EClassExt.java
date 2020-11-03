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

package org.polarsys.capella.core.model.utils;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 *
 */
public class EClassExt {
  
  /**
   * Allows to know if an <code>EClass</code> is equal or a super class of another <code>EClass</code>
   * @param class1 an EClass
   * @param class2 an EClass
   * @return <code>true</code> if class1 is equal to class_2 or is a super class of class_2, <code>false</code> otherwise. 
   */
  public static boolean isEqualOrSuperClass (EClass class1, EClass class2){
    return (null != class1) && (null != class2) && (class1.equals(class2) || class2.getEAllSuperTypes().contains(class1));
  }
  
  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a given <code>EClass</code> instance.
   * @param object the object
   * @param eClass the classifier
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object, EClass eClass) {
    return ((null != object) && isEqualOrSuperClass(eClass, object.eClass()));
  }

  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a one of the given <code>EClass</code>'s instance.
   * @param object the object
   * @param eClasses the classifiers list
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object, List<EClass> eClasses) {
    for (EClass eclass : eClasses) {
      if (canBeInstanciatedAs(object, eclass)) {
        return true;
      }
    }
    return false;
  }

}
