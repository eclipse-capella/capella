/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
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
public class DiagramContext {

  private SessionContext sessionContext;

  /**
   * Diagram to test.
   */
  private DDiagram diagram;

  private String diagramIdentifier;

  /**
   * Diagram's semantic level (OA, SA, LA, PA, EPBS)
   */
  private String diagramType;

  /**
   * Map containing graphical representations of objects present in the diagram
   * 
   * @key String: the object's identifier
   * @value DDiagramElement: graphical representation of the semantic object with the "key" identifier
   */
  private Map<String, DDiagramElement> objectViewMap;

  /**
   * @param context
   * @param diagram
   */
  public DiagramContext(SessionContext context, DDiagram diagram) {

    this.diagram = diagram;
    diagramIdentifier = EObjectExt.getText(diagram);
    diagramType = diagram.getDescription().getName();
    sessionContext = context;
  }

  public static DiagramContext getDiagram(SessionContext sessionContext, String diagramIdentifier) {

    Session session = sessionContext.getSession();
    ResourceSet resource = session.getTransactionalEditingDomain().getResourceSet();
    DRepresentationDescriptor desc = RepresentationHelper.getRepresentationDescriptor(resource, diagramIdentifier);

    return new DiagramContext(sessionContext, ((DDiagram) desc.getRepresentation()));
  }

  public Map<String, DDiagramElement> getViewObjectMap() {

    if (null == objectViewMap) {
      objectViewMap = new HashMap<String, DDiagramElement>();
    }
    return objectViewMap;
  }

  public String getType() {
    return diagramType;
  }

  public Map<String, EObject> getSemanticObjectMap() {
    return sessionContext.getSemanticObjectMap();
  }

  public DDiagram getDiagram() {
    return diagram;
  }

  public DRepresentationDescriptor getDiagramDescriptor() {
    return RepresentationHelper.getRepresentationDescriptor(diagram);
  }

  public String getDiagramId() {
    return diagramIdentifier;
  }

  public SessionContext getSessionContext() {
    return this.sessionContext;
  }

  protected boolean isA(String diagramDescription) {
    return DiagramHelper.getService().isA(diagram, diagramDescription);
  }

  public DSemanticDecorator getView(String semanticIdentifier) {
    if (getDiagramId().equals(semanticIdentifier)) {
      return (DSemanticDiagram) diagram;
    }

    DSemanticDecorator view = null;
    // Try to retrieve a view previously created by a tool
    if (getViewObjectMap().containsKey(semanticIdentifier)) {
      view = getViewObjectMap().get(semanticIdentifier);
      // view can be stored in the map but not present anymore on the diagram
      if (DiagramHelper.getService().getDiagramContainer(view) == null) {
        getViewObjectMap().remove(semanticIdentifier);
        view = null;
      }
    }
    if (view == null) {
      // Try to retrieve the view based on the semantic element id
      view = getView((EObject) sessionContext.getSemanticElement(semanticIdentifier));
      if (view instanceof DDiagramElement) {
        putView(semanticIdentifier, (DDiagramElement) view);
      }
    }
    if (view == null) {
      // Try to retrieve the view based on the uid of the view
      EObject o = diagram.eResource().getEObject(semanticIdentifier);
      if (o instanceof DDiagramElement) {
        putView(semanticIdentifier, (DDiagramElement) o);
        view = (DDiagramElement) o;
      }
    }
    return view;
  }

  public DDiagramElement getView(EObject semantic) {
    return org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.getOnDiagram(diagram, semantic);
  }

  public DDiagramElement getView(String semanticIdentifier, DSemanticDecorator view) {
    EObject semantic = sessionContext.getSemanticElement(semanticIdentifier);
    return org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.getOnDiagram(diagram, semantic,
        view);
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

  /**
   * If there is an edge whose semantic target is edgeId, between the source view whose semantic target is sourceId and
   * the target view whose semantic target is targetId
   * 
   * @param sourceId
   * @param targetId
   * @param edgeId
   * @return
   */
  public boolean hasEdge(String sourceId, String targetId, String edgeId) {

    EObject semanticSource = sessionContext.getSemanticElement(sourceId);
    DSemanticDecorator viewSource = getView(semanticSource);

    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
        EObjectLabelProviderHelper.getText(viewSource)), viewSource != null);

    EObject semanticTarget = sessionContext.getSemanticElement(targetId);
    DSemanticDecorator viewTarget = getView(semanticTarget);

    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
        EObjectLabelProviderHelper.getText(viewTarget)), viewTarget != null);

    Assert.assertTrue("The source should be of type EdgeTarget", viewSource instanceof EdgeTarget);
    Assert.assertTrue("The target should be of type EdgeTarget", viewTarget instanceof EdgeTarget);

    EdgeTarget portSource = (EdgeTarget) viewSource;
    EdgeTarget portTarget = (EdgeTarget) viewTarget;

    List<DEdge> relatedEdgesToSource = new ArrayList<>();
    relatedEdgesToSource.addAll(portSource.getIncomingEdges());
    relatedEdgesToSource.addAll(portSource.getOutgoingEdges());

    List<DEdge> relatedEdgesToTarget = new ArrayList<>();
    relatedEdgesToTarget.addAll(portTarget.getIncomingEdges());
    relatedEdgesToTarget.addAll(portTarget.getOutgoingEdges());

    boolean hasEdge = false;
    for (DEdge edgeToTarget : relatedEdgesToTarget) {
      for (DEdge edgeToSource : relatedEdgesToSource) {
        if (edgeToTarget.equals(edgeToSource)
            && edgeToSource.getTarget().equals(sessionContext.getSemanticElement(edgeId))) {

          hasEdge = true;
          break;
        }
      }
    }
    return hasEdge;
  }

  public void hasView(String identifier) {
    hasView(identifier, null);
  }

  public void hasViews(String... identifiers) {
    if (identifiers != null) {
      for (String identifier : identifiers)
        hasView(identifier, null);
    }
  }

  public void hasView(String identifier, String mappingName) {
    EObject eObject = sessionContext.getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);

    boolean result = view != null;
    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationNotAvailableOnDiagram,
        EObjectLabelProviderHelper.getText(eObject)), result);

    if (mappingName != null) {
      DiagramElementMapping mapping = DiagramServices.getDiagramServices()
          .getAbstractNodeMapping(getDiagram().getDescription(), mappingName);
      Assert.assertTrue(NLS.bind(CommonTestMessages.wrongMapping, mappingName),
          DiagramServices.getDiagramServices().isMapping((DDiagramElement) view, mapping));
    }
  }

  public void hasntView(String identifier) {
    EObject eObject = sessionContext.getSemanticElement(identifier);
    boolean result = getView(identifier) == null;
    Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
        EObjectLabelProviderHelper.getText(eObject)), result);
  }

  public void hasntViews(String... identifiers) {
    if (identifiers != null) {
      for (String identifier : identifiers)
        hasntView(identifier);
    }
  }

  public void hasHiddenViews(String... identifiers) {
    if (identifiers != null) {
      for (String identifier : identifiers)
        hasHiddenView(identifier);
    }
  }

  public void hasHiddenView(String identifier) {

    EObject eObject = sessionContext.getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);
    if (view == null) {
      Assert.assertTrue("view is null", false);

    } else if (view instanceof DDiagramElement) {
      boolean result = DiagramServices.getDiagramServices().isHidden((DDiagramElement) view);
      Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
          EObjectLabelProviderHelper.getText(eObject)), result);
    } else {
      Assert.assertTrue("view is diagram", false);
    }
  }

  public void hasFilteredView(String identifier) {

    EObject eObject = sessionContext.getSemanticElement(identifier);
    DSemanticDecorator view = getView(identifier);
    if (view == null) {
      Assert.assertTrue("view is null", false);

    } else if (view instanceof DDiagramElement) {
      boolean result = DiagramServices.getDiagramServices().isFiltered((DDiagramElement) view);
      Assert.assertTrue(NLS.bind(CommonTestMessages.objectRepresentationStillAvailableOnDiagram,
          EObjectLabelProviderHelper.getText(eObject)), result);
    } else {
      Assert.assertTrue("view is diagram", false);
    }
  }

  public DiagramContext open() {
    new OpenDiagramStep(this, false).run();
    return this;
  }

  public DiagramContext close() {
    org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper.closeEditor(sessionContext.getSession(),
        getDiagram());
    return this;
  }

  public void refreshDiagram() {
    new RefreshDiagramStep(this).run();
  }

  public void clearDiagram() {
    new ClearDiagramStep(this).run();
  }

  public void mustBeInstanceOf(String objectId, EClass clazz) {
    Assert.assertTrue(clazz.isInstance(sessionContext.getSemanticElement(objectId)));
  }

  public void mustBeLinkedTo(String objectId, String valueId, EStructuralFeature feature) {
    EObject object = sessionContext.getSemanticElement(objectId);
    EObject value = sessionContext.getSemanticElement(valueId);

    if (feature.isMany()) {
      Assert.assertTrue(((EList<?>) object.eGet(feature)).contains(value));

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

    EObject object = sessionContext.getSemanticElement(objectId);
    EObject container = sessionContext.getSemanticElement(containerId);
    Assert.assertTrue(object.eContainer().equals(container));
  }

  public void mustGraphicalOwnedBy(String objectId, String containerId) {
    Assert.assertTrue(getView(objectId).eContainer().equals(getView(containerId)));
  }

  public void setContextualElements(String... ids) {
    new SetContextualElementsStep(this, ids).run();
  }

  public Collection<EObject> adaptTool(AbstractToolStep<?> tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    return semanticElements;
  }

  protected String getSemanticIdFromView(DDiagramElement view) {

    EObject semanticElement = view.getTarget();

    return ((CapellaElement) semanticElement).getId();
  }
}
