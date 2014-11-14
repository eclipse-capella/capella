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
package org.polarsys.capella.core.sirius.analysis.task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.experimental.sync.AbstractDNodeCandidate;
import org.eclipse.sirius.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.ArrangeConstraint;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.NodeCreationDescription;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;

/**
 * Using the default CreateView tool in odesign remove all extensibility provided by Sirius. We must define a mapping to create instead of
 *         using mapping extensibility. This task create the best view which can be created into the containerView_p view.
 * @see Sirius-2531
 */
public class CreateViewTask extends AbstractCommandTask {

  protected AbstractToolDescription tool;

  protected EObject context;

  protected EObject toCreate;

  protected DSemanticDiagram diagram;

  protected DSemanticDecorator containerView;

  /**
   * Create a new {@link CreateViewTask}.
   * @param extPackage the extended package.
   * @param context the current context.
   * @param mapping_p the mapping.
   * @param containerViewExpression_p the container
   * @param createViewOp the create view operation
   * @param iRegistry the interpreter registry
   */
  public CreateViewTask(EObject context_p, DSemanticDecorator containerView_p, EObject toCreate_p, String toolId_p) {
    context = context_p;
    toCreate = toCreate_p;
    containerView = containerView_p;
    diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(containerView_p);

    tool = getTool(diagram, toolId_p);
  }

  /**
   * @param diagram_p
   * @param toolId_p
   * @return
   */
  private AbstractToolDescription getTool(DSemanticDiagram diagram_p, String toolId_p) {
    AbstractToolDescription result = null;
    final Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());
    final List<AbstractToolDescription> tools = new DiagramComponentizationManager().getAllTools(session.getSelectedViewpoints(false), diagram.getDescription());

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolId_p)) {
        result = current;
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
   */
  public String getLabel() {
    return "create a view"; //$NON-NLS-1$
  }

  protected Collection<EObject> getScope() {
    return Collections.singletonList(toCreate);
  }

  // Maybe we are going to have a problem when the 2class are in different
  // hierarchical branch...
  protected int computeInheritanceDistance(EObject object, String className) {
    EClass currentEclass = object.eClass();
    if (currentEclass.getName().equals(className)) {
      return 0;
    }
    return checkInheritanceDistance(currentEclass, className, -1);
  }

  protected int checkInheritanceDistance(EClass classType, String classSearched, int distanceInheritance) {
    int currentInheritanceDistance = -1;
    Iterator<EClass> itsuperType = classType.getESuperTypes().iterator();
    while (itsuperType.hasNext()) {
      EClass currentSuperType = itsuperType.next();
      if (currentSuperType.getName().equals(classSearched)) {
        return distanceInheritance + 1;
      }
      currentInheritanceDistance = checkInheritanceDistance(currentSuperType, classSearched, distanceInheritance + 1);
      if (currentInheritanceDistance != -1) {
        return currentInheritanceDistance;
      }

    }
    return -1;
  }

  protected boolean checkBestInheritance(ContainerMapping best, ContainerMapping currentMapping, EObject createdObj, int previousInheritanceDistance) {
    EClass createdObjEClass = createdObj.eClass();

    /*
     * if the current domainClass mapping is exactly the metaClass of the createdObject, then we have to use it. But what if there two mapping the same
     * DomainClass?
     */
    if (createdObjEClass.getName().equals(currentMapping.getDomainClass())) {
      return true;
    }
    int currentInheritanceDistance = computeInheritanceDistance(createdObj, currentMapping.getDomainClass());

    if (currentInheritanceDistance != -1 && previousInheritanceDistance > currentInheritanceDistance) {
      return true;
    }

    return false;
  }

  protected DiagramElementMapping getBestMapping(EObject element_p, DiagramElementMapping containerMapping_p) {
    // retrieve all valid mappings
    Collection<DiagramElementMapping> validMappings = getAllValidMappings(element_p, containerMapping_p);

    DiagramElementMapping bestMapping = null;
    int bestIndex = -1;

    for (DiagramElementMapping mapping : validMappings) {
      String domainClass = null;
      if (mapping instanceof EdgeMapping) {
        domainClass = ((EdgeMapping) mapping).getDomainClass();
      } else if (mapping instanceof AbstractNodeMapping) {
        domainClass = ((AbstractNodeMapping) mapping).getDomainClass();
      }

      int current = computeInheritanceDistance(element_p, domainClass);
      if (current != -1 && (bestIndex == -1 || bestIndex > current)) {
        bestIndex = current;
        bestMapping = mapping;
      }
    }

    return bestMapping;
  }

  /**
   * @param element_p
   * @param containerMapping_p
   * @return
   */
  protected Collection<DiagramElementMapping> getAllValidMappings(EObject element_p, DiagramElementMapping containerMapping_p) {
    Collection<DiagramElementMapping> validMappings = new HashSet<DiagramElementMapping>();

    final DiagramDescription desc = diagram.getDescription();
    final Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());

    if (containerMapping_p != null) {
      for (DiagramElementMapping mapping : containerMapping_p.getAllMappings()) {
        if (isValidMapping(element_p, mapping)) {
          validMappings.add(mapping);
        }
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager().getAllContainerMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element_p, mapping)) {
        validMappings.add(mapping);
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager().getAllNodeMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element_p, mapping)) {
        validMappings.add(mapping);
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager().getAllEdgeMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element_p, mapping)) {
        validMappings.add(mapping);
      }
    }
    return validMappings;
  }

  /**
   * @param element_p
   * @param mapping_p
   * @return
   */
  protected boolean isValidMapping(EObject element_p, DiagramElementMapping mapping_p) {

    if (isDomainValid(element_p, mapping_p) && mapping_p.checkPrecondition(element_p, getDestinationContainer().getTarget(), getDestinationContainer())) {
      // Remove mappings from an inactive layer
      Layer layer = (Layer) EcoreUtil2.getFirstContainer(mapping_p, DescriptionPackage.Literals.LAYER);
      if (layer != null) {
        if (!diagram.getActivatedLayers().contains(layer)) {
          return false;
        }
      }

      if (tool != null) {
        if (tool instanceof ContainerCreationDescription) {
          ContainerCreationDescription cTool = (ContainerCreationDescription) tool;
          return cTool.getContainerMappings().contains(mapping_p);

        } else if (tool instanceof NodeCreationDescription) {
          NodeCreationDescription cTool = (NodeCreationDescription) tool;
          return cTool.getNodeMappings().contains(mapping_p);

        } else if (tool instanceof EdgeCreationDescription) {
          EdgeCreationDescription cTool = (EdgeCreationDescription) tool;
          return cTool.getEdgeMappings().contains(mapping_p);
        }

        // Restrict to mappings
      }
      return true;
    }
    return false;
  }

  /**
   * @param element_p
   * @param mapping_p
   * @return
   */
  private boolean isDomainValid(EObject element_p, DiagramElementMapping mapping_p) {
    String domainClass = null;

    if (mapping_p instanceof EdgeMapping) {
      domainClass = ((EdgeMapping) mapping_p).getDomainClass();
    } else if (mapping_p instanceof AbstractNodeMapping) {
      domainClass = ((AbstractNodeMapping) mapping_p).getDomainClass();
    }

    if (domainClass != null) {
      if (domainClass.equals(element_p.eClass().getName())) {
        return true;
      }

      for (EClass parent : element_p.eClass().getEAllSuperTypes()) {
        if (domainClass.equals(parent.getName())) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
   */
  public void execute() {
    DSemanticDecorator destinationContainer = getDestinationContainer();
    DiagramElementMapping containerMapping = getViewMapping(destinationContainer);

    for (EObject element : getScope()) {
      DiagramElementMapping mapping = getBestMapping(element, containerMapping);
      if (mapping != null) {
        createNode(element, mapping, (DragAndDropTarget) destinationContainer);
      }
    }
  }

  /**
   * @param element_p
   * @param mapping_p
   */
  private void createNode(EObject element_p, DiagramElementMapping mapping_p, DragAndDropTarget containerView) {
    DDiagramElement newView = null;
    AbstractDNodeCandidate candidate = new AbstractDNodeCandidate((AbstractNodeMapping) mapping_p, element_p, containerView);
    final DDiagramElementSynchronizer sync = new DDiagramElementSynchronizer(diagram, InterpreterUtil.getInterpreter(element_p), null) {
      @Override
      public void refresh(final DDiagramElement newNode) {
        if (newNode instanceof DNode) {
          refresh((DNode) newNode);
        } else if (newNode instanceof DNodeListElement) {
          refresh((DNodeListElement) newNode);
        } else if (newNode instanceof DDiagramElementContainer) {
          refresh((DDiagramElementContainer) newNode);
        } else if (newNode instanceof DEdge) {
          refresh((DEdge) newNode);
        }
      }
    };

    Session session = SessionManager.INSTANCE.getSession(element_p);
    DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
    newView = sync.createNewNode(mappingManager, candidate, false);
    initView(newView);
  }

  protected boolean autoPinOnCreateEnabled() {
    return Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), true, new IScopeContext[0]);
  }

  /**
   * @param newView_p
   */
  protected void initView(DDiagramElement newView_p) {
    if (newView_p instanceof DDiagramElementContainer) {
      DDiagramElementContainer container = (DDiagramElementContainer) newView_p;
      if (autoPinOnCreateEnabled()) {
        container.getArrangeConstraints().add(ArrangeConstraint.KEEP_LOCATION);
        container.getArrangeConstraints().add(ArrangeConstraint.KEEP_RATIO);
        container.getArrangeConstraints().add(ArrangeConstraint.KEEP_SIZE);
      }
    }
  }

  protected DSemanticDecorator getDestinationContainer() {
    return containerView;
  }

  /**
   * Return the mapping of the container.
   * @return the mapping of the container.
   */
  private DiagramElementMapping getViewMapping(DSemanticDecorator element) {
    DiagramElementMapping result = null;
    if (element != null) {

      if (element instanceof DDiagramElementContainer) {
        result = ((DDiagramElementContainer) element).getDiagramElementMapping();
      } else if (element instanceof DNode) {
        result = ((DNode) element).getActualMapping();
      } else if (element instanceof DEdge) {
        result = ((DEdge) element).getDiagramElementMapping();
      }
    }
    return result;
  }

}
