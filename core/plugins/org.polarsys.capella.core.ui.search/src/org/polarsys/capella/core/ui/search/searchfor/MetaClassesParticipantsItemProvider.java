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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;

public class MetaClassesParticipantsItemProvider extends AbstractMetaModelParticipantsItemProvider {
  private AbstractCapellaSearchForContainerArea area = null;
  private boolean isFilteringAbstract = false;
  private boolean showsOnlySemantics = false;
  private Set<Object> eClassifierList = null;
  private Set<Object> diagramElements = null;
  protected Map<String, Set<Object>> elements;

  public MetaClassesParticipantsItemProvider(AbstractCapellaSearchForContainerArea area) {
    this.area = area;
    eClassifierList = getEClassifiers();
    diagramElements = getDiagramElements();
    elements = new HashMap<String, Set<Object>>();
    elements.put(CapellaSearchConstants.ModelElements_Key, eClassifierList);
    elements.put(CapellaSearchConstants.DiagramElements_Key, diagramElements);
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement != null) {
      return elements.keySet().toArray();
    }
    return new Object[0];
  }

  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof EAttribute)
      return false;
    return getChildren(element).length > 0;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof String && elements.keySet().contains(parentElement)) {
      return elements.get((String) parentElement).toArray();
    }
    return super.getChildren(parentElement);
  }

  @Override
  public Object getParent(Object element) {
    if (element instanceof EClass) {
      if (elements.get(CapellaSearchConstants.ModelElements_Key).contains(element)) {
        return CapellaSearchConstants.ModelElements_Key;
      }
      if (elements.get(CapellaSearchConstants.DiagramElements_Key).contains(element)) {
        return CapellaSearchConstants.DiagramElements_Key;
      }
    }
    return null;
  }

  public boolean isFilteringAbstract() {
    return isFilteringAbstract;
  }

  public void filtersAbstract(boolean filter) {
    this.isFilteringAbstract = filter;
  }

  public boolean showOnlySemantics() {
    return showsOnlySemantics;
  }

  public void showOnlySemantics(boolean showOnly) {
    this.showsOnlySemantics = showOnly;
  }

  public Set<Object> getEClassifiers() {
    Set<Object> allDerivedReferences = new HashSet<>();
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        for (EClassifier eClassifier : ePackage.getEClassifiers())
          if (eClassifier instanceof EClass) {
            EClass eClass = (EClass) eClassifier;
            allDerivedReferences.add(eClass);
          }
      }
    }
    return allDerivedReferences;
  }
  
 public Set<Object> getDiagramElements() {
    Set<Object> result = new HashSet<Object>();
    result.add(DiagramPackage.eINSTANCE.getNote());
    result.add(ViewpointPackage.eINSTANCE.getDRepresentationDescriptor());
    return result;
  }
}
