/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.step.crud.ClearDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.RefreshDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.SetContextualElementsStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.framework.api.CommonTestMessages;
import org.polarsys.capella.test.framework.context.SessionContext;

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

  private String _type;
  
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
    _type = _diagram.getDescription().getName();
    _sessionContext = context;
  }
  
  public String getType() {
    return _type;
  }

  @Override
  public Map<String, EObject> getSemanticObjectMap() {
    return _sessionContext.getSemanticObjectMap();
  }

  public DDiagram getDiagram() {
    return _diagram;
  }

  public String getDiagramId() {
    return diagramIdentifier;
  }

  protected boolean isA(String diagramDescription) {
    return DiagramHelper.getService().isA(_diagram, diagramDescription);
  }

  public DSemanticDecorator getView(String semanticIdentifier) {
    if (getDiagramId().equals(semanticIdentifier)) {
      return (DSemanticDiagram) _diagram;
    }
    DSemanticDecorator view = null;
    if (getViewObjectMap().containsKey(semanticIdentifier)) {
      view = getViewObjectMap().get(semanticIdentifier);
      // view can be stored in the map but not present anymore on the diagram
      if (DiagramHelper.getService().getDiagramContainer(view) == null) {
        getViewObjectMap().remove(semanticIdentifier);
        view = null;
      }
    }
    if (view == null) {
      view = getView(getSemanticElement(semanticIdentifier));
      if (view instanceof DDiagramElement) {
        putView(semanticIdentifier, (DDiagramElement) view);
      }
    }
    return view;
  }

  public DDiagramElement getView(EObject semantic) {
    return org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.getOnDiagram(_diagram, semantic);
  }

  /**
   * @param semantic
   * @return
   */
  protected EObject adaptToView(EObject semantic) {
    return semantic;
  }

  public void putView(String newIdentifier, DDiagramElement view) {
    getViewObjectMap().put(newIdentifier, view);
  }

  public void hasView(String identifier) {
    hasView(identifier, null);
  }

  public void hasView(String identifier, String mappingName) {
    EObject eObject = getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);

    boolean result = view != null;
    Assert.assertTrue(
        NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
            EObjectLabelProviderHelper.getText(eObject)), result);

    if (mappingName != null) {
      DiagramElementMapping mapping = DiagramServices.getDiagramServices().getAbstractNodeMapping(
          getDiagram().getDescription(), mappingName);
      Assert.assertTrue(NLS.bind(CommonTestMessages.wrongMapping, mappingName), DiagramServices.getDiagramServices()
          .isMapping((DDiagramElement) view, mapping));
    }
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
    DSemanticDecorator view = getView(identifier);
    if (view == null) {
      Assert.assertTrue("view is null", false);

    } else if (view instanceof DDiagramElement) {
      boolean result = DiagramServices.getDiagramServices().isHidden((DDiagramElement) view);
      Assert.assertTrue(
          NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
              EObjectLabelProviderHelper.getText(eObject)), result);
    } else {
      Assert.assertTrue("view is diagram", false);
    }
  }

  public void hasFilteredView(String identifier) {
    EObject eObject = getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);
    if (view == null) {
      Assert.assertTrue("view is null", false);

    } else if (view instanceof DDiagramElement) {
      boolean result = DiagramServices.getDiagramServices().isFiltered((DDiagramElement) view);
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

  public void refreshDiagram() {
    new RefreshDiagramStep(this).run();
  }

  public void clearDiagram() {
    new ClearDiagramStep(this).run();
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

  /**
   * @param i
   */
  public void hasCountViews(int i) {
    Assert.assertTrue(getDiagram().getDiagramElements().size() == i);

  }

  public void mustBeOwnedBy(String objectId, String containerId) {
    EObject object = getSemanticElement(objectId);
    EObject container = getSemanticElement(containerId);
    Assert.assertTrue(object.eContainer().equals(container));
  }

  public void mustGraphicalOwnedBy(String objectId, String containerId) {
    Assert.assertTrue(getView(objectId).eContainer().equals(getView(containerId)));
  }

  public void setContextualElements(String... ids) {
    new SetContextualElementsStep(this, ids).run();
  }

  public Collection<EObject> adaptTool(AbstractToolStep tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    return semanticElements;
  }
}
