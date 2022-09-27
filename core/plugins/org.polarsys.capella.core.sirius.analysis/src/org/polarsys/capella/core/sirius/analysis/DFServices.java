/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

/**
 * Shared helpers for Data Flow diagrams
 */
public class DFServices {

  private static DFServices service = null;

  public static DFServices getService() {
    if (service == null) {
      service = new DFServices();
    }
    return service;
  }

  /**
   * Returns whether creation of a Function[FunctionKind==FUNCTION] is allowed on the given view
   * 
   * @param containerView
   * @return
   */
  public boolean isValidCreationDFFunction(DSemanticDecorator containerView) {
    if (containerView == null) {
      return false;
    }
    EObject target = containerView.getTarget();
    if ((target == null) || target.eIsProxy()) {
      return false;
    }

    // if AbstractCapablitiy return true
    if (target instanceof AbstractCapability) {
      return true;
    }

    if (!(target instanceof AbstractFunction)) {
      return false;
    }

    return !FaServices.getFaServices().isControlNode(target);
  }

  /**
   * Returns whether creation of a Function[FunctionKind!=FUNCTION] is allowed on the given view
   * 
   * @param containerView
   * @return
   */
  public boolean isValidCreationDFControlNode(DSemanticDecorator containerView) {
    return isValidCreationDFFunction(containerView);
  }

  /**
   * Move the given function from oldContainer to the newContainer
   * 
   * @param function
   * @param oldContainer
   * @param newContainer
   * @return
   */
  public EObject dndDFBAbstractFunction(AbstractFunction node, NamedElement oldContainer, NamedElement newContainer) {
    return dndDFAbstractFunction(node, oldContainer, newContainer);
  }

  /**
   * Move the given function from oldContainer to the newContainer
   * 
   * @param function
   * @param oldContainer
   * @param newContainer
   * @return
   */
  public EObject dndDFAbstractFunction(AbstractFunction function, NamedElement oldContainer,
      NamedElement newContainer) {

    // move function in the new container
    if (newContainer instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer;
      // fix for bug when dnd function A in a diagram related to A
      if (newContainer == function) {
        if ((newFunction.eContainer() != null) && (newFunction.eContainer() != null)
            && (newFunction.eContainer().eContainer() != null)
            && (newFunction.eContainer().eContainer() instanceof AbstractFunction)) {
          ((AbstractFunction) (newFunction.eContainer().eContainer())).getOwnedFunctions().add(function);
        } else {
          return function;
        }
      } else {
        newFunction.getOwnedFunctions().add(function);
      }
    }

    for (FunctionalExchange exchange : FunctionExt.getIncomingExchange(function)) {
      FunctionalExchangeExt.attachToDefaultContainer(exchange);
    }
    for (FunctionalExchange exchange : FunctionExt.getOutGoingExchange(function)) {
      FunctionalExchangeExt.attachToDefaultContainer(exchange);
    }

    return function;

  }

  /**
   * Returns whether FunctionalExchange edge is valid between both sourceView/targetView
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public boolean isValidDFFunctionalExchangeEdge(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    if (context instanceof FunctionalExchange) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
      if (CapellaServices.getService().areInternalEdgePorts(sourceView, targetView)) {
        // FE is internal ? Do not display it.
        return false;
      }
      return !diagram.isSynchronized()
          || !FaServices.getFaServices().isACategoryDisplayed(context, sourceView, targetView);
    }
    // Context can be FunctionInputPort while tool creation of Functional Exchange (sirius weird behavior), so we return
    // false in this case.
    return false;
  }

  public boolean isValidDFFunctionalExchangeEdgeFromInternalTool(EObject context, DSemanticDecorator sourceView,
      DSemanticDecorator targetView) {
    if (context instanceof FunctionalExchange) {
      return !FaServices.getFaServices().isACategoryDisplayed(context, sourceView, targetView);
    }
    // Context can be FunctionInputPort while tool creation of Functional Exchange (sirius weird behavior), so we return
    // false in this case.
    return false;
  }

  /**
   * Returns whether the given part can be drop into the target element view
   */
  public boolean isValidDndDFBAbstractFunction(AbstractFunction semanticObjectToDrop, EObject targetContainerView) {
    EObject target = ((DSemanticDecorator) targetContainerView).getTarget();
    if (target instanceof AbstractFunction) {
      if (!FunctionExt.isActorFunction((AbstractFunction) target)) {
        if (EcoreUtil2.isContainedBy(target, semanticObjectToDrop)) {
          return false;
        }
        if (target.equals(semanticObjectToDrop)) {
          return false;
        }

        return !FaServices.getFaServices().isControlNode(target);
      }
    }
    return false;
  }

  public boolean isValidDFFunctionalChainInternalLinkEdge(FunctionalChain chain, DSemanticDecorator source,
      DSemanticDecorator target) {
    return FunctionalChainServices.getFunctionalChainServices().isValidInternalLinkEdge(chain, (EdgeTarget) source,
        (EdgeTarget) target);
  }

  /**
   * Create a functional exchange between both views
   * 
   * @param context
   * @param sourceView
   * @param targetView
   * @return
   */
  public EObject createDFFunctionalExchange(EObject context, AbstractDNode sourceView, AbstractDNode targetView) {
    return ABServices.getService().createABFunctionalExchange(context, sourceView, targetView);
  }

  /**
   * Returns available states/modes to insert in given diagram
   * 
   * @param containerView
   * @return
   */
  public Collection<EObject> getDFInsertStateModesScope(DSemanticDecorator containerView) {
    HashSet<State> availableStates = new HashSet<State>();
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      // Retrieve all available states
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (AbstractFunction function : FunctionPkgExt
          .getAllAbstractFunctions(BlockArchitectureExt.getFunctionPkg(architecture))) {
        availableStates.addAll(function.getAvailableInStates());
      }

      // Retrains states to states with at least one function not displayed
      for (State state : availableStates) {
        boolean addElement = false;
        for (EObject function : getDFInsertStateModesRelatedElements(state, architecture)) {
          if (function instanceof AbstractFunction) {
            if (!content.containsView(function,
                FaServices.getFaServices().getMappingDFFunction((AbstractFunction) function, diagram))) {
              addElement = true;
              break;
            }
          }
        }
        if (addElement) {
          result.add(state);
        }
      }
    }
    return result;
  }

  /**
   * Display related elements of given states modes in the current diagram
   * 
   * @param containerView
   * @param elements
   */
  public void showDFStateModes(DSemanticDecorator containerView, Collection<EObject> elements) {

    HashSet<EObject> functionsToShow = new HashSet<EObject>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements) {
        if (object instanceof State) {
          State mode = (State) object;
          functionsToShow.addAll(getDFInsertStateModesRelatedElements(mode, sourceArchitecture));
        }
      }
      for (EObject toShow : functionsToShow) {
        if (toShow instanceof AbstractFunction) {
          FaServices.getFaServices().showDFAbstractFunction((AbstractFunction) toShow, content.getBestContainer(toShow),
              content);
        }
      }
    }
  }

  /**
   * Retrieve related elements for a mode and a given architecture (functions)
   * 
   * @param mode
   * @param sourceArchitecture
   * @return
   */
  public Collection<EObject> getDFInsertStateModesRelatedElements(State mode, BlockArchitecture sourceArchitecture) {
    return ABServices.getService().getABInsertStateModesRelatedElements(mode, sourceArchitecture);
  }

  public boolean isValidDndDFFunctionPort(EObject element, DSemanticDecorator newContainer) {
    if ((newContainer == null) || (newContainer.getTarget() == null) || newContainer.getTarget().eIsProxy()) {
      return false;
    }

    EObject targetContainer = newContainer.getTarget();
    if (targetContainer.equals(element.eContainer())) {
      return false;
    }

    if (!(targetContainer instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction targetFunction = FunctionExt.getRelatedFunction((ActivityNode) targetContainer);

    if (FunctionExt.isControlNodeOneOutput(targetFunction)) {
      if (element instanceof FunctionOutputPort) {
        if (!targetFunction.getOutputs().isEmpty()) {
          return false;
        }
        if (((FunctionOutputPort) element).getOutgoingFunctionalExchanges().size() > 1) {
          return false;
        }
        return true;
      }
    }

    if (FunctionExt.isControlNodeOneInput(targetFunction)) {
      if (element instanceof FunctionInputPort) {
        if (!targetFunction.getInputs().isEmpty()) {
          return false;
        }
        if (((FunctionInputPort) element).getIncomingFunctionalExchanges().size() > 1) {
          return false;
        }
        return true;
      }
    }

    // do not allow to move an output/input port to a target that has a FE defined with the same input/output port
    if (element instanceof FunctionPort && FunctionExt.isFlowPortInAnyFunctionalExchange((FunctionPort) element, targetFunction)) {
      return false;
    }

    return newContainer != null;
  }

  /**
   * Returns whether the tool insert states/modes is available in the given context
   * 
   * @param containerView
   * @return
   */
  public boolean isValidDFInsertStateModes(DSemanticDecorator containerView) {
    return containerView instanceof DDiagram;
  }

  public Collection<EObject> getDFInsertScenariosScope(DSemanticDecorator containerView) {
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      AbstractCapabilityPkg pkg = BlockArchitectureExt.getAbstractCapabilityPkg(sourceArchitecture);
      for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(pkg)) {

        boolean addElement = false;
        for (EObject element : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario,
            sourceArchitecture)) {
          if (element instanceof AbstractFunction) {
            if (!content.containsView(element,
                FaServices.getFaServices().getMappingDFFunction((AbstractFunction) element, diagram))) {
              addElement = true;
              break;
            }
          } else if (element instanceof FunctionalExchange) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingDFFunctionalExchange(diagram))) {
              addElement = true;
              break;
            }
          }
        }
        if (addElement) {
          if (ScenarioExt.isFunctionalScenario(scenario)) {
            result.add(scenario);
          } else if (ScenarioExt.isDataFlowFunctionalScenario(scenario)) {
            result.add(scenario);
          }
        }

      }
    }

    return result;
  }

  /**
   * Display related elements of given states modes in the current diagram
   * 
   * @param containerView
   * @param elements
   */
  public void showDFScenarios(DSemanticDecorator containerView, Collection<EObject> elements) {

    HashSet<AbstractFunction> functionsToShow = new HashSet<AbstractFunction>();
    HashSet<FunctionalExchange> exchangesToShow = new HashSet<FunctionalExchange>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements) {
        if (object instanceof Scenario) {
          Scenario scenario = (Scenario) object;

          for (EObject related : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario,
              sourceArchitecture)) {

            if (related instanceof AbstractFunction) {
              functionsToShow.add((AbstractFunction) related);

            } else if (related instanceof FunctionalExchange) {
              exchangesToShow.add((FunctionalExchange) related);
            }
          }
        }
      }

      for (AbstractFunction toShow : functionsToShow) {
        FaServices.getFaServices().showDFAbstractFunction(toShow, content.getBestContainer(toShow), content);
      }
      for (FunctionalExchange functionalExchange : exchangesToShow) {
        FaServices.getFaServices().showDFFunctionalExchange(null, functionalExchange, content, true);
      }
    }

  }

  /**
   * Returns whether the tool insert scenario is available in the given context
   * 
   * @param containerView
   * @return
   */
  public boolean isValidDFInsertScenarios(DSemanticDecorator containerView) {
    return containerView instanceof DDiagram;
  }

  /**
   * Gets the AB target.
   */
  public EObject getDFTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      EObject diagramTarget = decorator.getTarget();
      ContainerMapping aMapping = FaServices.getFaServices().getMappingDFFunction(null, (DDiagram) decorator);

      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if ((DiagramServices.getDiagramServices().isMapping(element, aMapping))) {
          // check if diagram target is in the diagram
          // return parent container
          if ((element.getTarget() == diagramTarget)) {
            return FaServices.getFaServices().getParentFunctionContainer(decorator.getTarget());
          }
        }
      }
      // if diagram target is AbstractCapabilty : return function root
      if (diagramTarget instanceof AbstractCapability) {
        return FaServices.getFaServices().getParentFunctionContainer(decorator.getTarget());
      }

      return decorator.getTarget();
    }
    return decorator.getTarget();
  }

  public Object getDFFunctionalExchangeSemanticCandidates(DDiagram diagram) {
    LinkedList<AbstractFunction> toVisit = new LinkedList<AbstractFunction>();
    Collection<FunctionalExchange> result = new ArrayList<FunctionalExchange>();

    // Retrieve all Functions from diagram
    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof AbstractFunction) {
        toVisit.add((AbstractFunction) target);
      }
    }

    // Retrieve all outgoing exchanges from displayed Functions (recursively since a parent function display
    // sub-exchanges if subfunction is not displayed)
    Collection<AbstractFunction> visited = new ArrayList<AbstractFunction>();
    while (!toVisit.isEmpty()) {
      AbstractFunction function = toVisit.removeFirst();
      if (!visited.contains(function)) {
        toVisit.addAll(function.getOwnedFunctions());
        result.addAll(FunctionExt.getOutGoingExchange(function));
        visited.add(function);
      }
    }
    return result;
  }

}
