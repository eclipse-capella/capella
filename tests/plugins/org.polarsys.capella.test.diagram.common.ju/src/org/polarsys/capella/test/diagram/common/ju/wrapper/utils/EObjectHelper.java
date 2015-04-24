/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Useful services on EObject
 */
public class EObjectHelper {

  public static Map<String, EObject> getMatchingEObjects(final EObject root_p, final EClass elementType_p, final EStructuralFeature featureType_p,
      String... values_p) {
    Assert.isNotNull(root_p);

    Map<String, EObject> results = new HashMap<String, EObject>();

    List<String> values = new ArrayList<String>();
    values.addAll(Arrays.asList(values_p));

    TreeIterator<EObject> allContents = root_p.eAllContents();
    while (allContents.hasNext()) {
      EObject current = allContents.next();
      EClass currentEClass = current.eClass();
      if (elementType_p.isSuperTypeOf(currentEClass) && current.eClass().getEAllStructuralFeatures().contains(featureType_p)) {
        String key = (String) current.eGet(featureType_p);
        if (values.contains(key)) {
          results.put(key, current);
          values.remove(key);
        }
      }
    }
    return results;
  }

  /**
   * Return all children object of a given meta class.
   * @param root_p the root {@link EObject} to check
   * @param eClass_p the type of object to find
   * @param strictMode_p must be of type/ or specialization of
   * @return a {@link List} of found objects, otherwise an empty one.
   */
  public static List<EObject> getAllEObjectOfType(EObject root_p, EClass eClass_p, boolean strictMode_p) {
    List<EObject> result = new ArrayList<EObject>();
    TreeIterator<EObject> allContents = root_p.eAllContents();
    EObject current = null;
    boolean isMatching = false;
    while (allContents.hasNext()) {
      current = allContents.next();
      isMatching = (true == strictMode_p) ? current.eClass().equals(eClass_p) : eClass_p.isSuperTypeOf(current.eClass());
      if (true == isMatching) {
        result.add(current);
      }
    }
    return result;
  }
}
