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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

public class MetaClassesUtil {

  private Map<String, Object> classifiersMap;
  private Map<String, Object> classifiersDiagMap;
  private Map<String, Object> attributesMap;
  private Set<String> packages;

  private static MetaClassesUtil instance;

  private MetaClassesUtil() {
    classifiersMap = new HashMap<String, Object>();
    classifiersDiagMap = new HashMap<String, Object>();
    attributesMap = new HashMap<String, Object>();
    packages = new HashSet<String>();
    updateEClassifiers();
    updateDiagramElements();
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
            }
          }
        }
      }
    }
    
    updateEAttributes(classifiersMap);
  }
  
  private void updateDiagramElements() {
    EClass note = DiagramPackage.eINSTANCE.getNote();
    EClass diag = ViewpointPackage.eINSTANCE.getDRepresentationDescriptor();
    classifiersDiagMap.put(note.getName(), note);
    classifiersDiagMap.put(diag.getName(), diag);
    updateEAttributes(classifiersDiagMap);
  }
  
  private void updateEAttributes(Map<String, Object> classifiers) {
    classifiers.forEach((k, obj)->{
      EClass eClass = (EClass) obj;
      EList<EAttribute> attributes = eClass.getEAllAttributes();
      for (EAttribute attribute : attributes) {
        attributesMap.put(attribute.getName(), attribute);
      }
    });
  }
  
  public static MetaClassesUtil getInstance() {
    if (instance == null) {
      instance = new MetaClassesUtil();
    }
    return instance;
  }

  public Set<Object> getClassifiersDiagramElements() {
    Set<Object> list = Collections.unmodifiableSet(new HashSet<Object>(classifiersDiagMap.values()));
    return list;
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
    Object eCls = classifiersMap.get(name);
    if(eCls == null) {
      eCls = classifiersDiagMap.get(name);
    }
    return eCls;
  }

  public Object getAttribute(String name) {
    return attributesMap.get(name);
  }
  
  public Set<Object> getAttributes(Set<Object> checkedMetaClasses) {
    Set<Object> eAttributesList = new HashSet<Object>();
    for (Object obj : checkedMetaClasses) {
      if (obj instanceof EClass) {
        if (!obj.equals(DiagramPackage.eINSTANCE.getNote())) {
          EList<EAttribute> attributes = ((EClass) obj).getEAllAttributes();
          for (EAttribute newAttr : attributes) {
            EDataType type = newAttr.getEAttributeType();
            if (type.getInstanceClass().equals(java.lang.String.class)) {
              Object attributeMap = attributesMap.get(newAttr.getName());
              if(attributeMap != null)
                eAttributesList.add(attributeMap);
            }
          }
        }
      }
    }
    return eAttributesList;
  }
}
