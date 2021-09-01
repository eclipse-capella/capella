/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;

/**
 * Using the default CreateView tool in odesign remove all extensibility provided by Sirius. We must define a mapping to
 * create instead of using mapping extensibility. This task create the best view which can be created into the
 * containerView view.
 *
 * @see Sirius-2531
 */
public class CreateViewTask extends AbstractCommandTask {

  protected AbstractToolDescription tool;

  protected EObject context;

  protected EObject toCreate;

  protected DSemanticDiagram diagram;

  protected DSemanticDecorator containerView;

  protected String outVariable;

  /**
   * Create a new {@link CreateViewTask}.
   *
   * @param context
   * @param containerView
   * @param toCreate
   * @param toolId
   * @param outVariable
   */
  public CreateViewTask(EObject context, DSemanticDecorator containerView, EObject toCreate, String toolId,
      String outVariable) {
    this.context = context;
    this.toCreate = toCreate;
    this.containerView = containerView;
    this.diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(containerView);
    this.outVariable = outVariable;
    this.tool = getTool(toolId);
  }

  /**
   * @param semanticDiagram
   * @param toolId
   * @return
   */
  private AbstractToolDescription getTool(String toolId) {
    AbstractToolDescription result = null;
    final Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());
    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(session.getSelectedViewpoints(false), diagram.getDescription());

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolId)) {
        result = current;
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   *
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

  protected boolean checkBestInheritance(ContainerMapping best, ContainerMapping currentMapping, EObject createdObj,
      int previousInheritanceDistance) {
    EClass createdObjEClass = createdObj.eClass();

    /*
     * if the current domainClass mapping is exactly the metaClass of the createdObject, then we have to use it. But
     * what if there two mapping the same DomainClass?
     */
    if (createdObjEClass.getName().equals(getDomainClass(currentMapping))) {
      return true;
    }
    int currentInheritanceDistance = computeInheritanceDistance(createdObj, getDomainClass(currentMapping));
    return (currentInheritanceDistance != -1) && (previousInheritanceDistance > currentInheritanceDistance);
  }

  protected DiagramElementMapping getBestMapping(EObject element, DiagramElementMapping containerMapping) {
    // retrieve all valid mappings
    Collection<DiagramElementMapping> validMappings = getAllValidMappings(element, containerMapping);

    DiagramElementMapping bestMapping = null;
    int bestIndex = -1;

    for (DiagramElementMapping mapping : validMappings) {
      String domainClass = getDomainClass(mapping);

      int current = computeInheritanceDistance(element, domainClass);
      if ((current != -1) && ((bestIndex == -1) || (bestIndex > current))) {
        bestIndex = current;
        bestMapping = mapping;
      }
    }

    return bestMapping;
  }

  /**
   * @param element
   * @param containerMapping
   * @return
   */
  protected Collection<DiagramElementMapping> getAllValidMappings(EObject element,
      DiagramElementMapping containerMapping) {
    Collection<DiagramElementMapping> validMappings = new HashSet<>();

    final DiagramDescription desc = diagram.getDescription();
    final Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());

    if (containerMapping != null) {
      for (DiagramElementMapping mapping : MappingHelper.getAllMappings(containerMapping)) {
        if (isValidMapping(element, mapping)) {
          validMappings.add(mapping);
        }
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager()
        .getAllContainerMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element, mapping)) {
        validMappings.add(mapping);
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager()
        .getAllNodeMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element, mapping)) {
        validMappings.add(mapping);
      }
    }

    for (DiagramElementMapping mapping : new DiagramComponentizationManager()
        .getAllEdgeMappings(session.getSelectedViewpoints(false), desc)) {
      if (isValidMapping(element, mapping)) {
        validMappings.add(mapping);
      }
    }
    return validMappings;
  }

  /**
   * @param element
   * @param mapping
   * @return
   */
  protected boolean isValidMapping(EObject element, DiagramElementMapping mapping) {

    if (isDomainValid(element, mapping) && SiriusElementMappingSpecOperations.checkPrecondition(mapping, element,
        getDestinationContainer().getTarget(), getDestinationContainer())) {
      // Remove mappings from an inactive layer
      Layer layer = (Layer) EcoreUtil2.getFirstContainer(mapping,
          org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER);
      if (layer != null && !diagram.getActivatedLayers().contains(layer)) {
        return false;
      }

      if (tool != null) {
        if (tool instanceof ContainerCreationDescription) {
          ContainerCreationDescription cTool = (ContainerCreationDescription) tool;
          return cTool.getContainerMappings().contains(mapping);

        } else if (tool instanceof NodeCreationDescription) {
          NodeCreationDescription cTool = (NodeCreationDescription) tool;
          return cTool.getNodeMappings().contains(mapping);

        } else if (tool instanceof EdgeCreationDescription) {
          EdgeCreationDescription cTool = (EdgeCreationDescription) tool;
          return cTool.getEdgeMappings().contains(mapping);
        }

        // Restrict to mappings
      }
      return true;
    }
    return false;
  }

  /**
   * @param element
   * @param mapping
   * @return
   */
  private boolean isDomainValid(EObject element, DiagramElementMapping mapping) {

    String domainClass = getDomainClass(mapping);

    if (domainClass != null) {
      if (domainClass.equals(element.eClass().getName())) {
        return true;
      }

      for (EClass parent : element.eClass().getEAllSuperTypes()) {
        if (domainClass.equals(parent.getName())) {
          return true;
        }
      }
    }

    return false;
  }

  protected String getDomainClass(DiagramElementMapping mapping) {
    String domainClass = null;

    if (mapping instanceof EdgeMapping) {
      domainClass = ((EdgeMapping) mapping).getDomainClass();
    } else if (mapping instanceof AbstractNodeMapping) {
      domainClass = ((AbstractNodeMapping) mapping).getDomainClass();
    }
    return StringUtil.lastSegment(domainClass, Arrays.asList(".", "::"));
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
   */
  public void execute() {
    DSemanticDecorator destinationContainer = getDestinationContainer();
    DiagramElementMapping containerMapping = getViewMapping(destinationContainer);

    for (EObject element : getScope()) {
      DiagramElementMapping mapping = getBestMapping(element, containerMapping);
      if (mapping != null) {
        DDiagramElement view = createNode(element, mapping, (DragAndDropTarget) destinationContainer);
        InterpreterUtil.getInterpreter(element).setVariable(outVariable, view);
      }
    }
  }

  /**
   * @param element
   * @param mapping
   */
  private DDiagramElement createNode(EObject element, DiagramElementMapping mapping, DragAndDropTarget containerView) {
    DDiagramElement newView = null;
    RefreshIdsHolder refreshId = RefreshIdsHolder.getOrCreateHolder(diagram);
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(diagram);
    DNodeCandidate candidate = new DNodeCandidate((AbstractNodeMapping) mapping, element, containerView, refreshId);
    final DDiagramElementSynchronizer sync = new DDiagramElementSynchronizer(diagram,
        InterpreterUtil.getInterpreter(element), accessor) {
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

    Session session = SessionManager.INSTANCE.getSession(element);
    DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session,
        diagram);
    newView = sync.createNewNode(mappingManager, candidate, false);
    initView(newView);
    return newView;
  }

  protected boolean autoPinOnCreateEnabled() {
    return Platform.getPreferencesService().getBoolean(SiriusPlugin.ID,
        SiriusDiagramPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), true, new IScopeContext[0]);
  }

  /**
   * @param newView
   */
  protected void initView(DDiagramElement newView) {
    if (newView instanceof DDiagramElementContainer) {
      DDiagramElementContainer container = (DDiagramElementContainer) newView;
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
   *
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
