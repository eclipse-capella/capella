/*******************************************************************************
 * Copyright (c) 2016, 2022 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *    Obeo - 2303 Add computed transitions in M&S diagrams
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideMSMStateMode;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideMSMTransitions;

/**
 * Services for Mode State machine diagram.
 */
public class ModeStateMachineServices {

  /** A shared instance. */
  private static ModeStateMachineServices _service;

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static ModeStateMachineServices getService() {
    if (_service == null) {
      _service = new ModeStateMachineServices();
    }
    return _service;
  }

  public String getRegionLabel(Region region, DDiagram diagram) {
    return isDiagramFilterEnable(diagram, IMappingNameConstants.HIDE_REGION_NAMES) ? ""
        : " [" + EObjectExt.getText(region) + "]";
  }

  public String getActivityLabel(AbstractEvent abstractEvent) {

    if (abstractEvent instanceof FunctionalExchange) {
      return getOutgoingFunctionalExchangeLabel((FunctionalExchange) abstractEvent);
    }
    return EObjectExt.getText(abstractEvent);
  }

  public String getOutgoingFunctionalExchangeLabel(FunctionalExchange fe) {
    EObject target = fe.getTarget();
    if (target instanceof FunctionPort) {
      target = target.eContainer();
    }
    if (target instanceof AbstractFunction) {
      return EObjectExt.getText(fe) + " [-> " + EObjectExt.getText(target) + "]";
    }
    return "";
  }
  
  public String getIncomingFunctionalExchangeLabel(FunctionalExchange fe) {
    EObject source = fe.getSource();
    if (source instanceof FunctionPort) {
      source = source.eContainer();
    }
    if (source instanceof AbstractFunction) {
      return "[" + EObjectExt.getText(source) + " ->] " + EObjectExt.getText(fe);
    }
    return "";
  }

  public String getEntryExitPointLabel(Pseudostate pseudostate, DDiagram diagram) {

    if (isDiagramFilterEnable(diagram, IMappingNameConstants.DISPLAY_REGION_NAME_ON_ENTRY_EXIT_POINTS)) {
      EList<Region> regions = pseudostate.getInvolverRegions();
      if (!regions.isEmpty()) {
        Region region = regions.get(0);
        return EObjectExt.getText(pseudostate) + " (" + EObjectExt.getText(region)
            + ")";
      }
    }

    return EObjectExt.getText(pseudostate);
  }

  private boolean isDiagramFilterEnable(DDiagram diagram, String filterName) {
    if (diagram != null) {
      EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
      for (FilterDescription filterDescription : activatedFilters) {
        // if given filter is enable return true
        if ((null != filterDescription) && filterDescription.getName().equalsIgnoreCase(filterName)) {
          return true;
        }
      }
    }

    return false;
  }

  public EObject showHideStatesInStateAndModeDiag(DSemanticDecorator view, List<State> selectedStates,
      List<State> visibleStates, List<AbstractDNode> visibleStateViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<EObject> toBeRemoved = new HashSet<EObject>();

    ShowHideMSMStateMode shHide = new ShowHideMSMStateMode(content);

    DiagramContext diagramContext = new ShowHideMSMStateMode(content).new DiagramContext();

    // store context
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER, view.getTarget()/* .eContainer() */);
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER_VIEW, view);

    for (IState state : selectedStates) {
      shHide.show(state, diagramContext);
    }

    for (AbstractDNode node : visibleStateViews) {
      if (!selectedStates.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }

    // remove views
    for (EObject aView : toBeRemoved) {
      shHide.hide(aView, diagramContext);
    }

    return view;
  }

  public EObject moveRegionMSM(EObject context, Region newRegion, Region selectedRegion) {

    EObject container = newRegion.eContainer();
    State state = (State) container;
    state.getOwnedRegions().remove(newRegion);
    int index = 0;
    if (selectedRegion != null) {
      index = state.getOwnedRegions().indexOf(selectedRegion) + 1;
    }
    state.getOwnedRegions().add(index, newRegion);

    return context;
  }

  public Region getRegionForTransitionMSM(EObject context, DDiagramElement sourceView) {

    EObject containerView = sourceView.eContainer();
    if (containerView instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) containerView).getTarget();
      if (target instanceof Region) {
        return (Region) target;
      } else if (target instanceof State && context instanceof Pseudostate && context.eContainer() instanceof Region) {
        return (Region) context.eContainer();
      }
    }
    return null;
  }

  public boolean canCreateTransitionMSM(EObject context, EObject sourceElement, EObject targetElement) {

    IState source = null;
    IState target = null;

    if (sourceElement instanceof IState) {
      source = (IState) sourceElement;
    } else if (sourceElement instanceof Region) {
      source = (IState) sourceElement.eContainer();
    } else {
      return false;
    }

    if (targetElement instanceof IState) {
      target = (IState) targetElement;
    } else if (targetElement instanceof Region) {
      target = (IState) targetElement.eContainer();
    } else {
      return false;
    }

    if ((target instanceof InitialPseudoState) || (source instanceof TerminatePseudoState)
        || (source instanceof FinalState)) {
      return false;
    }

    if ((source instanceof InitialPseudoState)
        && ((target instanceof TerminatePseudoState) || (target instanceof FinalState))) {
      return false;
    }

    if (((target instanceof ExitPointPseudoState)
        && !(StateMachineServices.getService().isInSameOrSubRegion(target, source)))
        || ((source instanceof EntryPointPseudoState)
            && !StateMachineServices.getService().isInSameOrSubRegion(source, target))
        || ((source instanceof JoinPseudoState)
            && (StateMachineServices.getService().getSourcingTransition(source).size() != 0))
        || ((target instanceof ForkPseudoState)
            && (StateMachineServices.getService().getTargettingTransition(target).size() != 0))) {
      return false;
    }

    // self connecting transition
    if (source.equals(target) && !(source instanceof Pseudostate)) {
      return true;
    }
    if (EcoreUtil.isAncestor(source, target) || EcoreUtil.isAncestor(target, source)) {
      return false;
    }

    // cannot create a transition between states located on different regions of the same parent state
    if (EcoreUtil2.getCommonAncestor(source, target) instanceof IState) {
      return false;
    }

    return true;
  }

  public Region getRegionForInsertionMSM(EObject context, EObject delement) {
    Region region = null;

    if (delement instanceof DDiagram) {
      region = (Region) ((DSemanticDiagram) delement).getTarget();
    } else if (delement instanceof DDiagramElementContainer) {
      EObject target = ((DNodeContainer) delement).getTarget();
      if (target instanceof Region) {
        return (Region) target;
      }
    }
    return region;
  }

  public EObject showHideStatesInMSMDiag(DSemanticDecorator view, List<State> selectedStates, List<State> visibleStates,
      List<AbstractDNode> visibleStateViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<EObject> toBeRemoved = new HashSet<EObject>();

    ShowHideMSMStateMode shHide = new ShowHideMSMStateMode(content);

    DiagramContext diagramContext = new ShowHideMSMStateMode(content).new DiagramContext();

    // store context
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER, view.getTarget());
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER_VIEW, view);

    for (IState state : selectedStates) {
      shHide.show(state, diagramContext);
    }

    for (AbstractDNode node : visibleStateViews) {
      if (!selectedStates.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }

    // remove views
    for (EObject aView : toBeRemoved) {
      shHide.hide(aView, diagramContext);
    }

    return view;
  }

  public EObject showHideMSMTransitions(DSemanticDecorator view, List<StateTransition> selectedTransitions,
      List<StateTransition> visibleTransitions, List<DEdge> visibleTransitionViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<EObject> toBeRemoved = new HashSet<EObject>();

    ShowHideMSMStateMode shHide = new ShowHideMSMTransitions(content);
    DiagramContext diagramContext = shHide.new DiagramContext();

    // store context
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER, view.getTarget());
    diagramContext.setVariable(ShowHideMSMStateMode.CONTEXTUAL_CONTAINER_VIEW, view);

    for (StateTransition trans : selectedTransitions) {
      shHide.show(trans, diagramContext);
    }

    for (DEdge edge : visibleTransitionViews) {
      if (!selectedTransitions.contains(edge.getTarget())) {
        toBeRemoved.add(edge.getTarget());
      }
    }
    // remove views
    for (EObject aView : toBeRemoved) {
      shHide.hide(aView, diagramContext);
    }

    return view;
  }

  public ContainerMapping getMappingMSMStateMode(State state, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MSM_MODE_STATE_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, mappingName);
  }

  public NodeMapping getMappingMSMPseudostate(Pseudostate pseudoState, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MSM_PSEUDOSTATE_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getNodeMapping(diagram, mappingName);
  }

  public EdgeMapping getMappingMSMTransition(StateTransition function, DDiagram diagram) {
    String mappingName = null;
    if (diagram.getDescription().getName().equalsIgnoreCase(IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MSM_TRANSITION_MAPPING_NAME;
    }
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
  }

  /**
   * Get all transitions displayed in the diagram
   * 
   * @param region
   *          Region
   * @param diagram
   *          DDiagram
   * @return all transitions displayed in the diagram
   */
  public List<StateTransition> getComputedTransitions(Region region, DDiagram diagram) {
    List<StateTransition> transitions = new ArrayList<>();
    region.eAllContents().forEachRemaining(el -> {
      if (el instanceof StateTransition) {
        StateTransition transition = (StateTransition) el;
        Collection<DSemanticDecorator> sourceDiagramElements = DiagramServices.getDiagramServices()
            .getDiagramElements(diagram, transition.getSource());
        Collection<DSemanticDecorator> targetDiagramElements = DiagramServices.getDiagramServices()
            .getDiagramElements(diagram, transition.getTarget());
        Stream.concat(sourceDiagramElements.stream(), targetDiagramElements.stream()).forEach(diagElt -> {
          if (diagElt instanceof DDiagramElement && !((DDiagramElement) diagElt).isVisible()) {
            transitions.add(transition);
          }
        });
      }
    });
    return transitions;
  }

  /**
   * @param related
   *          EObject
   * @param diagram
   *          DDiagram
   * @return computed transition source.
   */
  public EObject getComputedTransitionSource(EObject related, DDiagram diagram) {
    if (related instanceof StateTransition) {
      StateTransition transition = (StateTransition) related;
      AbstractState transitionSource = transition.getSource();
      DDiagramElement sourceDiagramElement = DiagramServices.getDiagramServices().getDiagramElement(diagram,
          transitionSource);
      Optional<DDiagramElement> firstVisibleEdgeEnd = getFirstVisibleEdgeEnd(diagram, sourceDiagramElement);
      if (firstVisibleEdgeEnd.isPresent()) {
        return firstVisibleEdgeEnd.get().getTarget();
      }
    }
    return null;
  }

  /**
   * @param related
   *          EObject
   * @param diagram
   *          DDiagram
   * @return computed transition target.
   */
  public EObject getComputedTransitionTarget(EObject related, DDiagram diagram) {
    if (related instanceof StateTransition) {
      StateTransition transition = (StateTransition) related;
      AbstractState transitionTarget = transition.getTarget();
      DDiagramElement targetDiagramElement = DiagramServices.getDiagramServices().getDiagramElement(diagram,
          transitionTarget);
      Optional<DDiagramElement> firstVisibleEdgeEnd = getFirstVisibleEdgeEnd(diagram, targetDiagramElement);
      if (firstVisibleEdgeEnd.isPresent()) {
        return firstVisibleEdgeEnd.get().getTarget();
      }
    }
    return null;
  }

  /**
   * @param diagram
   *          DDiagram
   * @param diagramElement
   *          DDiagramElement
   * @return first visible transition end in diagram.
   */
  private Optional<DDiagramElement> getFirstVisibleEdgeEnd(DDiagram diagram, DDiagramElement diagramElement) {
    if (diagramElement != null && diagramElement.getTarget() instanceof AbstractState && diagramElement.isVisible()) {
      return Optional.of(diagramElement);
    }
    if (diagramElement != null && diagramElement.eContainer() instanceof DDiagramElement) {
      return getFirstVisibleEdgeEnd(diagram, (DDiagramElement) diagramElement.eContainer());
    }
    return Optional.empty();
  }
}
