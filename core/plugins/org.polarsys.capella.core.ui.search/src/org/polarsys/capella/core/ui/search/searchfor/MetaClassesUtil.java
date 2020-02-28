/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

public class MetaClassesUtil {

  private Map<String, Object> classifiersMap;
  private Map<String, Object> attributesMap;
  private Set<String> packages;

  private static MetaClassesUtil instance;

  private MetaClassesUtil() {
    classifiersMap = new HashMap<String, Object>();
    attributesMap = new HashMap<String, Object>();
    packages = new HashSet<String>();
    updateEClassifiers();
  }

  private void updateEClassifiers() {
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        if (!packages.contains(nsURI)) {
          packages.add(nsURI);
          EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
          for (EClassifier eClassifier : ePackage.getEClassifiers()) {
            if (eClassifier instanceof EClass) {
              EClass eClass = (EClass) eClassifier;
              classifiersMap.put(eClass.getName(), eClass);
              EList<EAttribute> attributes = eClass.getEAllAttributes();
              for (EAttribute attribute : attributes) {
                attributesMap.put(attribute.getName(), attribute);
              }
            }
          }
        }
      }
    }
  }

  public static MetaClassesUtil getInstance() {
    if (instance == null) {
      instance = new MetaClassesUtil();
    }
    return instance;
  }

  public Set<Object> getClassifiers() {
    Set<Object> list = Collections.unmodifiableSet(new HashSet<Object>(classifiersMap.values()));
    return list;
  }

  public Set<Object> getAttributes() {
    Set<Object> list = Collections.unmodifiableSet(new HashSet<Object>(attributesMap.values()));
    return list;
  }

  public Object getClassifier(String name) {
    return classifiersMap.get(name);
  }

  public Object getAttribute(String name) {
    return attributesMap.get(name);
  }
}
