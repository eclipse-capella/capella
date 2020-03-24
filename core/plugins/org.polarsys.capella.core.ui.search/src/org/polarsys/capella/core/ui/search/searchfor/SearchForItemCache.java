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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForDiagramItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForNoteItem;

/**
 * 
 * Cache of classes and attributes to search for
 */
public class SearchForItemCache {

  private Map<String, SearchForItem> searchForClassItemMap;
  private Map<String, SearchForItem> searchForDiagItemMap;
  private Map<String, SearchForAttributeItem> searchForAttributeItemMap;
  private Set<String> packages;

  private static SearchForItemCache instance;

  private SearchForItemCache() {
    searchForClassItemMap = new HashMap<>();
    searchForDiagItemMap = new HashMap<>();
    searchForAttributeItemMap = new HashMap<>();
    packages = new HashSet<>();
    initModelElements();
    initDiagramElements();
  }

  private void initModelElements() {
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        if (!packages.contains(nsURI)) {
          packages.add(nsURI);
          EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
          for (EClassifier eClassifier : ePackage.getEClassifiers()) {
            if (eClassifier instanceof EClass) {
              SearchForClassItem searchForClassItem = new SearchForClassItem(eClassifier);
              searchForClassItemMap.put(searchForClassItem.getUniqueID(), searchForClassItem);
            }
          }
        }
      }
    }

    initAttributes(searchForClassItemMap);
  }

  private void initDiagramElements() {
    SearchForDiagramItem diagramItem = new SearchForDiagramItem(
        ViewpointPackage.eINSTANCE.getDRepresentationDescriptor());
    SearchForNoteItem noteItem = new SearchForNoteItem(NotationPackage.eINSTANCE.getShape());
    searchForDiagItemMap.put(diagramItem.getUniqueID(), diagramItem);
    searchForDiagItemMap.put(noteItem.getUniqueID(), noteItem);
    
    initAttributes(searchForDiagItemMap);
  }

  private void initAttributes(Map<String, SearchForItem> classifiers) {
    classifiers.forEach((k, obj) -> {
      SearchForClassItem classItem = (SearchForClassItem) obj;
      List<Object> attributes = classItem.getAttributes();
      for (Object attribute : attributes) {
        if (attribute instanceof EAttribute) {
          EDataType type = ((EAttribute) attribute).getEAttributeType();
          if (!type.getInstanceClass().equals(java.lang.String.class)) {
            continue;
          }
        }
        SearchForAttributeItem attributeItem = new SearchForAttributeItem();
        attributeItem.addAttribute(attribute);
        SearchForItem currentAttributeItem = searchForAttributeItemMap.putIfAbsent(attributeItem.getText(), attributeItem);
        if (currentAttributeItem instanceof SearchForAttributeItem) {
          ((SearchForAttributeItem) currentAttributeItem).addAttribute(attribute);
        }
      }
    });
  }

  public static SearchForItemCache getInstance() {
    if (instance == null) {
      instance = new SearchForItemCache();
    }
    return instance;
  }

  public Set<SearchForItem> getSearchForDiagramItems() {
    return new HashSet<>(searchForDiagItemMap.values());
  }

  public Set<SearchForItem> getSearchForClassItems() {
    return new HashSet<>(searchForClassItemMap.values());
  }

  public Set<SearchForItem> getSearchForAttributeItems() {
    return new HashSet<>(searchForAttributeItemMap.values());
  }

  public Object getClassItem(String name) {
    Object eCls = searchForClassItemMap.get(name);
    if (eCls == null) {
      eCls = searchForDiagItemMap.get(name);
    }
    return eCls;
  }

  public Object getAttribute(String attributeUniqueID) {
    return searchForAttributeItemMap.get(attributeUniqueID);
  }

  public Set<SearchForItem> getAttributes(Set<Object> checkedClassItems) {
    Set<SearchForItem> attributeItems = new HashSet<>();
    for (Object item : checkedClassItems) {
      if (item instanceof SearchForClassItem) {
        List<Object> attributes = ((SearchForClassItem) item).getAttributes();
        for (Object att : attributes) {
          Optional<SearchForAttributeItem> attOpt = searchForAttributeItemMap.values().stream()
              .filter(attributeItem -> attributeItem.cover(att)).findFirst();
          if (attOpt.isPresent()) {
            attributeItems.add(attOpt.get());
          }
        }
      }
    }
    return attributeItems;
  }
}
