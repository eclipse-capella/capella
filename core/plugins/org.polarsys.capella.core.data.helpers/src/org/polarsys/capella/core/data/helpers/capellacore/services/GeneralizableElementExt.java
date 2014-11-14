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
package org.polarsys.capella.core.data.helpers.capellacore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;

/**
 * The GeneralizableElement helpers
 *
 */
public class GeneralizableElementExt {

  /**
   * This method removes an inherited classifier.
   * @param classifier_p The classifier.
   * @param superClassifier_p The parent classifier.
   */
  public static void removeSuperGeneralizableElement(GeneralizableElement generalizableElement_p, GeneralizableElement superGeneralizableElement_p) {
    if ((generalizableElement_p != null) && (superGeneralizableElement_p != null)) {
      Generalization generalization = null;
      ListIterator<Generalization> it = generalizableElement_p.getSuperGeneralizations().listIterator();
      while (it.hasNext()) {
        Generalization gen = it.next();
        if (gen.getSuper().equals(superGeneralizableElement_p)) {
          generalization = gen;
        }
      }
      if (generalization != null) {
        generalization.destroy();
      }
    }
  }

  /**
   * This helper allows to know if one of the given <code>GeneralizableElement</code> can be  affected to the other as one of its super types.
   * @param element_a the first element
   * @param element_b the second element
   * @return <code>true</code> if the affectation is possible, <code>false</code> otherwise
   */
  public static boolean isInheritancyCycleCompatible(GeneralizableElement element_a, GeneralizableElement element_b) {
    if (!GeneralizableElementExt.getAllSuperGeneralizableElements(element_a).contains(element_b)
     && !GeneralizableElementExt.getAllSuperGeneralizableElements(element_b).contains(element_a))
    {
      return true;
    }
    return false;
  }


  /**
   * Gets the list of super interfaces of the current interface
   * @param generalizableElement_p the current classifier
   * @return list of classifiers
   */
  static public List<GeneralizableElement> getAllSuperGeneralizableElements(GeneralizableElement generalizableElement_p) {
    List<GeneralizableElement> superGeneralizableElements = new ArrayList<GeneralizableElement>();
    if (null != generalizableElement_p) {
      getAllSuperGeneralizableElements2(generalizableElement_p, superGeneralizableElements);
    }
    return superGeneralizableElements;
  }

  /**
   * Gets the list of super interfaces of the current interface
   * @param generalizableElement_p the current classifier
   * @return list of classifiers
   */
  static private void getAllSuperGeneralizableElements2(GeneralizableElement generalizableElement_p, List<GeneralizableElement> list_p) {
    for (GeneralizableElement generalizableElement : generalizableElement_p.getSuper()) {
      if (
          !list_p.contains(generalizableElement)
      ) {
        list_p.add(generalizableElement);
        getAllSuperGeneralizableElements2(generalizableElement, list_p);
      }
    }
    
    return;
  }  
  
  /**
   * This method retrieves recursively all the inheriting generalizable elements.
   * @param generalizableElement_p The generalizable element.
   * @return the inheriting generalizable elements list.
   */
  public static List<GeneralizableElement> getAllSubGeneralizableElements(GeneralizableElement generalizableElement_p) {
    List<GeneralizableElement> subGeneralizableElements = new ArrayList<GeneralizableElement>();
    
    if (null != generalizableElement_p) {
      getAllSubGeneralizableElements2(generalizableElement_p, subGeneralizableElements);
    }
    
    return subGeneralizableElements;
  }

  private static void getAllSubGeneralizableElements2(GeneralizableElement generalizableElement_p, List<GeneralizableElement> list_p) {
      for (GeneralizableElement generalizableElement : generalizableElement_p.getSub()) {
        if (
            !list_p.contains(generalizableElement) 
        ) {
          list_p.add(generalizableElement);
          getAllSubGeneralizableElements2(generalizableElement, list_p);
        }
      }
      return;
  }

  /**
   * This method adds an inherited generalizable element.
   * @param generalizableElement_p The generalizable element.
   * @param superGeneralizableElement_p The parent generalizable element.
   */
  public static void addSuperGeneralizableElement(GeneralizableElement generalizableElement_p, GeneralizableElement superGeneralizableElement_p) {
    if ((generalizableElement_p != null) && (superGeneralizableElement_p != null)) {
      Generalization generalization = CapellacoreFactory.eINSTANCE.createGeneralization();
      generalizableElement_p.getOwnedGeneralizations().add(generalization);
      generalization.setSub(generalizableElement_p);
      generalization.setSuper(superGeneralizableElement_p);
    }
  }

  /**
   * This method returns the root super classifiers of the given classifier
   * @param classifier_p the classifier
   * @return the root super types
   */
  static public List<GeneralizableElement> getRootSupertypes(GeneralizableElement classifier_p) {
    List<GeneralizableElement> list = new ArrayList<GeneralizableElement>();
    if (null != classifier_p) {
      // Gets the super classifiers of the current element
      for (Generalization generalization : classifier_p.getSuperGeneralizations()) {
        // handles the current super classifier
        GeneralizableElement superCls = generalization.getSuper();
        if (null != superCls) {
          // if it is not null
          // Gets its own super types
          List<GeneralizableElement> supertypes = superCls.getSuper();
          if (null == supertypes || supertypes.size() == 0) {
            // If there is no super type for this one, then it is a root super type of <code>classifier_p</code>
            list.add(superCls);
          } else {
            // Else, processes recursively its super types to find the root super types
            list.addAll(getRootSupertypes(superCls));
          }
        }
      }
    }
    if (list.size() == 0) {
      // The current type is the root type because it generalizes "nobody"
      list.add(classifier_p);
    }
    return list;
  }
}
