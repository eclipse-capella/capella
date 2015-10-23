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
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

/**
 *
 */
public class DiagramContext extends SessionContext {

  /**
   * Diagram to test.
   */
  protected DDiagram _diagram;

  protected SessionContext _sessionContext;

  protected String diagramIdentifier;

  private Map<String, DDiagramElement> _viewObjectMap;

  public Map<String, DDiagramElement> getViewObjectMap() {
    if (null == _viewObjectMap) {
      _viewObjectMap = new HashMap<String, DDiagramElement>();
    }
    return _viewObjectMap;
  }

  /**
   * @param context
   * @param diagram
   */
  public DiagramContext(SessionContext context, DDiagram diagram) {
    super(DiagramHelper.getService().getSession(diagram));
    diagramIdentifier = diagram.getName();
    _diagram = diagram;
    _sessionContext = context;
  }

  @Override
  protected Map<String, EObject> getSemanticObjectMap() {
    return _sessionContext.getSemanticObjectMap();
  }

  public DDiagram getDiagram() {
    return _diagram;
  }

  public String getDiagramId() {
    return diagramIdentifier;
  }

  public DSemanticDecorator getView(String semanticIdentifier) {
    if (getDiagramId().equals(semanticIdentifier)) {
      return (DSemanticDiagram) _diagram;
    }
    if (getViewObjectMap().containsKey(semanticIdentifier)) {
      DSemanticDecorator view = getViewObjectMap().get(semanticIdentifier);
      // view can be stored in the map but not present anymore on the diagram
      if (view.eContainer() == null) {
        getViewObjectMap().remove(semanticIdentifier);
        view = null;
      }
      return view;
    }
    return getView(getSemanticElement(semanticIdentifier));
  }

  public DDiagramElement getView(EObject semantic) {
    return org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.getOnDiagram(_diagram, semantic);
  }

  public void putView(String newIdentifier, DDiagramElement view) {
    getViewObjectMap().put(newIdentifier, view);
  }

  public void hasView(String identifier) {
    EObject eObject = getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);
    boolean result = view != null;
    Assert.assertTrue(
        NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
            EObjectLabelProviderHelper.getText(eObject)), result);
  }

  public void hasntView(String identifier) {
    EObject eObject = getSemanticElement(identifier);
    boolean result = getView(identifier) == null;
    Assert.assertTrue(
        NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
            EObjectLabelProviderHelper.getText(eObject)), result);
  }

  public void hasHiddenView(String identifier) {
    EObject eObject = getSemanticElement(identifier);
    if (getView(identifier) instanceof DDiagramElement) {
      boolean result = (getView(identifier) != null)
          && DiagramServices.getDiagramServices().isHidden((DDiagramElement) getView(identifier));
      Assert.assertTrue(
          NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
              EObjectLabelProviderHelper.getText(eObject)), result);
    } else {
      Assert.assertTrue("view is diagram", false);
    }
  }

  public DiagramContext open() {
    new OpenDiagramStep(this).run();
    return this;
  }

  public void mustBeInstanceOf(String objectId, EClass clazz) {
    Assert.assertTrue(clazz.isInstance(getSemanticElement(objectId)));
  }

  public void mustBeLinkedTo(String objectId, String valueId, EStructuralFeature feature) {
    EObject object = getSemanticElement(objectId);
    EObject value = getSemanticElement(valueId);

    if (feature.isMany()) {
      Assert.assertTrue(((EList) object.eGet(feature)).contains(value));

    } else {
      Assert.assertTrue(object.eGet(feature).equals(value));

    }
  }

  public void mustBeOwnedBy(String objectId, String containerId) {
    EObject object = getSemanticElement(objectId);
    EObject container = getSemanticElement(containerId);
    Assert.assertTrue(object.eContainer().equals(container));
  }

  public void mustGraphicalOwnedBy(String s1, String s2) {
    Assert.assertTrue(getView(s1).eContainer().equals(getView(s2)));
  }
}
