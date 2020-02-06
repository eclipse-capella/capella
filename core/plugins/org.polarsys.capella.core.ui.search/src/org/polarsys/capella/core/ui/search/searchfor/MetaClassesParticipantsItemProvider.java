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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.search.Messages;

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
    elements.put(Messages.ModelElements_Key, eClassifierList);
    elements.put(Messages.DiagramElements_Key, diagramElements);
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
      if (elements.get(Messages.ModelElements_Key).contains(element)) {
        return Messages.ModelElements_Key;
      }
      if (elements.get(Messages.DiagramElements_Key).contains(element)) {
        return Messages.DiagramElements_Key;
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

  public Set<Object> getDRepresentations() {
    IProject[] prjs = getProjectsFromWorkspace();
    Set<Object> result = new HashSet();

    for (IProject prj : prjs) {
      Collection<Session> sessions = SessionHelper.getExistingSessions(prj);
      Session session = sessions.iterator().next();
      Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
          .getAllRepresentationDescriptors(session);
      result.addAll(representations);
    }
    return result;
  }

  public Set<Object> getDRepresentationElements() {
    IProject[] prjs = getProjectsFromWorkspace();
    Set<Object> result = new HashSet();

    for (IProject prj : prjs) {
      Collection<Session> sessions = SessionHelper.getExistingSessions(prj);
      Session session = sessions.iterator().next();
      Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
          .getAllRepresentationDescriptors(session);
      for (DRepresentationDescriptor representation : representations) {
        EList<DRepresentationElement> els = representation.getRepresentation().getRepresentationElements();
        result.addAll(els);
      }
    }
    return result;
  }

  public Set<Object> getDiagramElements() {
    Set<Object> result = new HashSet();

    EClass noteCls = DiagramPackage.eINSTANCE.getNote();
    EClass diagCls = DiagramPackage.eINSTANCE.getDDiagram();
    result.add(noteCls);
    result.add(diagCls);

    return result;
  }

  private IProject[] getProjectsFromWorkspace() {
    IProject[] projectsToCheck = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    return projectsToCheck;
  }

}
