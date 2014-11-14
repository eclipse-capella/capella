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
   * @param class1_p an EClass
   * @param class2_p an EClass
   * @return <code>true</code> if class1_p is equal to class_2 or is a super class of class_2, <code>false</code> otherwise. 
   */
  public static boolean isEqualOrSuperClass (EClass class1_p, EClass class2_p){
    return (null != class1_p) && (null != class2_p) && (class1_p.equals(class2_p) || class2_p.getEAllSuperTypes().contains(class1_p));
  }
  
  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a given <code>EClass</code> instance.
   * @param object_p the object
   * @param eClass_p the classifier
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object_p, EClass eClass_p) {
    return ((null != object_p) && isEqualOrSuperClass(eClass_p, object_p.eClass()));
  }

  /**
   * Allows to know if the given <code>EObject</code> can be instantiated as a one of the given <code>EClass</code>'s instance.
   * @param object_p the object
   * @param eClasses_p the classifiers list
   * @return <code>true</code> if the instantiation is possible, <code>false</code> otherwise
   */
  public static boolean canBeInstanciatedAs(EObject object_p, List<EClass> eClasses_p) {
    for (EClass eclass : eClasses_p) {
      if (canBeInstanciatedAs(object_p, eclass)) {
        return true;
      }
    }
    return false;
  }

}
