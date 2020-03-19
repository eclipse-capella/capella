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
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForDiagramItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForNoteItem;
import org.polarsys.kitalpha.ad.common.utils.URIHelper;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Viewpoint;

/**
 * 
 * Cache of classes and attributes to search for
 */
public class SearchForItemCache {
  private Map<String, SearchForClassItem> classID2ClassItemMap;
  private Map<String, SearchForClassItem> classID2DiagItemMap;
  private Map<String, SearchForAttributeItem> attributeName2AttributeItemMap;
  private Set<Viewpoint> viewpoints;
  private Map<String, Viewpoint> classID2ViewpointMap;

  private static SearchForItemCache instance;

  private SearchForItemCache() {
    classID2ClassItemMap = new HashMap<>();
    classID2DiagItemMap = new HashMap<>();
    attributeName2AttributeItemMap = new HashMap<>();
    viewpoints = new HashSet<>();
    classID2ViewpointMap = new HashMap<>();
    initModelElements();
    initDiagramElements();
    initViewpointElements();
    
    initAttributes(classID2ClassItemMap);
    initAttributes(classID2DiagItemMap);
  }

  private void initViewpointElements() {
    ResourceSet set = new ResourceSetImpl();
    for (org.polarsys.kitalpha.resourcereuse.model.Resource res : ViewpointManager.getAvailableViewpoints()) {
      URI uri = URIHelper.createURI(res);
      Viewpoint vp = (Viewpoint) set.getEObject(uri, true);
      viewpoints.add(vp);
      if (vp.getMetamodel() != null) {
        for (EPackage pack : vp.getMetamodel().getModels()) {
          EPackage registeredPkg = EPackage.Registry.INSTANCE.getEPackage(pack.getNsURI());
          for (EClassifier eClassifier : registeredPkg.getEClassifiers()) {
            if (eClassifier instanceof EClass) {
              SearchForClassItem searchForClassItem = new SearchForClassItem(eClassifier);
              classID2ClassItemMap.put(searchForClassItem.getUniqueID(), searchForClassItem);
              classID2ViewpointMap.put(searchForClassItem.getUniqueID(), vp);
            }
          }
        }
      }
    }
  }
  
  private void initModelElements() {
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        for (EClassifier eClassifier : ePackage.getEClassifiers()) {
          if (eClassifier instanceof EClass) {
            SearchForClassItem searchForClassItem = new SearchForClassItem(eClassifier);
            classID2ClassItemMap.put(searchForClassItem.getUniqueID(), searchForClassItem);
          }
        }
      }
    }
  }

  private void initDiagramElements() {
    SearchForDiagramItem diagramItem = new SearchForDiagramItem(
        ViewpointPackage.eINSTANCE.getDRepresentationDescriptor());
    SearchForNoteItem noteItem = new SearchForNoteItem(NotationPackage.eINSTANCE.getShape());
    classID2DiagItemMap.put(diagramItem.getUniqueID(), diagramItem);
    classID2DiagItemMap.put(noteItem.getUniqueID(), noteItem);
  }

  private void initAttributes(Map<String, SearchForClassItem> classifiers) {
    classifiers.forEach((k, classItem) -> {
      List<Object> attributes = classItem.getAttributes();
      for (Object attribute : attributes) {
        if (attribute instanceof EAttribute) {
          EDataType type = ((EAttribute) attribute).getEAttributeType();
          if (type.getInstanceClass() == null || !type.getInstanceClass().equals(java.lang.String.class)) {
            continue;
          }
        }
        SearchForAttributeItem attributeItem = new SearchForAttributeItem();
        attributeItem.addAttribute(attribute);
        SearchForItem currentAttributeItem = attributeName2AttributeItemMap.putIfAbsent(attributeItem.getText(), attributeItem);
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

  public Set<SearchForItem> getDiagramItems() {
    return new HashSet<>(classID2DiagItemMap.values());
  }

  public Set<SearchForItem> getClassItems() {
    return new HashSet<>(classID2ClassItemMap.values());
  }

  public Set<SearchForItem> getCapellaClassItems() {
    return classID2ClassItemMap.values().stream().filter(item -> !classID2ViewpointMap.containsKey(item.getUniqueID()))
        .collect(Collectors.toSet());
  }

  public Set<SearchForItem> getAttributeItems() {
    return new HashSet<>(attributeName2AttributeItemMap.values());
  }

  public Set<SearchForItem> getAddonItems(Viewpoint vp) {
    Set<String> vpClasses = classID2ViewpointMap.keySet().stream().filter(cls -> classID2ViewpointMap.get(cls) == vp)
        .collect(Collectors.toSet());
    return classID2ClassItemMap.keySet().stream().filter(vpClasses::contains)
        .map(cls -> classID2ClassItemMap.get(cls)).collect(Collectors.toSet());
  }

  public Object getClassItem(String name) {
    Object eCls = classID2ClassItemMap.get(name);
    if (eCls == null) {
      eCls = classID2DiagItemMap.get(name);
    }
    return eCls;
  }

  public Object getAttribute(String attributeUniqueID) {
    return attributeName2AttributeItemMap.get(attributeUniqueID);
  }

  public Set<SearchForItem> getAttributes(Set<Object> checkedClassItems) {
    Set<SearchForItem> attributeItems = new HashSet<>();
    for (Object item : checkedClassItems) {
      if (item instanceof SearchForClassItem) {
        List<Object> attributes = ((SearchForClassItem) item).getAttributes();
        for (Object att : attributes) {
          Optional<SearchForAttributeItem> attOpt = attributeName2AttributeItemMap.values().stream()
              .filter(attributeItem -> attributeItem.represent(att)).findFirst();
          if (attOpt.isPresent()) {
            attributeItems.add(attOpt.get());
          }
        }
      }
    }
    return attributeItems;
  }
  
  public Set<Viewpoint> getViewpoints() {
    return viewpoints;
  }
}
