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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.common.data.activity.ActivityNode;

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
   * @param containerView_p
   * @return
   */
  public boolean isValidCreationDFFunction(DSemanticDecorator containerView_p) {
    if (containerView_p == null) {
      return false;
    }
    EObject target = containerView_p.getTarget();
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

    // if ActorFunction return false
    if ((target instanceof AbstractFunction) && FunctionExt.isActorFunction((AbstractFunction) target)) {
      return false;
    }
    return !FaServices.getFaServices().isControlNode(target);
  }

  /**
   * Returns whether creation of a Function[FunctionKind!=FUNCTION] is allowed on the given view
   * @param containerView_p
   * @return
   */
  public boolean isValidCreationDFControlNode(DSemanticDecorator containerView_p) {
    return isValidCreationDFFunction(containerView_p);
  }

  /**
   * Move the given function from oldContainer to the newContainer
   * @param function_p
   * @param oldContainer
   * @param newContainer
   * @return
   */
  public EObject dndDFBAbstractFunction(AbstractFunction node_p, NamedElement oldContainer, NamedElement newContainer) {
    return dndDFAbstractFunction(node_p, oldContainer, newContainer);
  }

  /**
   * Move the given function from oldContainer to the newContainer
   * @param function_p
   * @param oldContainer
   * @param newContainer
   * @return
   */
  public EObject dndDFAbstractFunction(AbstractFunction function_p, NamedElement oldContainer, NamedElement newContainer) {

    // move function in the new container
    if (newContainer instanceof AbstractFunction) {
      AbstractFunction newFunction = (AbstractFunction) newContainer;
      // fix for bug when dnd function A in a diagram related to A
      if (newContainer == function_p) {
        if ((newFunction.eContainer() != null) && (newFunction.eContainer() != null) && (newFunction.eContainer().eContainer() != null)
            && (newFunction.eContainer().eContainer() instanceof AbstractFunction)) {
          ((AbstractFunction) (newFunction.eContainer().eContainer())).getOwnedFunctions().add(function_p);
        } else {
          return function_p;
        }
      } else {
        newFunction.getOwnedFunctions().add(function_p);
      }
    }

    for (FunctionalExchange exchange : FunctionExt.getIncomingExchange(function_p)) {
      FunctionalExchangeExt.attachToDefaultContainer(exchange);
    }
    for (FunctionalExchange exchange : FunctionExt.getOutGoingExchange(function_p)) {
      FunctionalExchangeExt.attachToDefaultContainer(exchange);
    }

    return function_p;

  }

  /**
   * Returns whether FunctionalExchange edge is valid between both sourceView/targetView
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public boolean isValidDFFunctionalExchangeEdge(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    if (context_p instanceof FunctionalExchange) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
      return !diagram.isSynchronized() || !FaServices.getFaServices().isACategoryDisplayed(context_p, sourceView_p, targetView_p);
    }
    // Context can be FunctionInputPort while tool creation of Functional Exchange (sirius weird behavior), so we return false in this case.
    return false;
  }

  public boolean isValidDFFunctionalExchangeEdgeFromInternalTool(EObject context_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    if (context_p instanceof FunctionalExchange) {
      return !FaServices.getFaServices().isACategoryDisplayed(context_p, sourceView_p, targetView_p);
    }
    // Context can be FunctionInputPort while tool creation of Functional Exchange (sirius weird behavior), so we return false in this case.
    return false;
  }

  /**
   * Returns whether the given part can be drop into the target element view
   */
  public boolean isValidDndDFBAbstractFunction(AbstractFunction semanticObjectToDrop_p, EObject targetContainerView_p) {
    EObject target = ((DSemanticDecorator) targetContainerView_p).getTarget();
    if (target instanceof AbstractFunction) {
      if (!FunctionExt.isActorFunction((AbstractFunction) target)) {
        if (EcoreUtil2.isContainedBy(target, semanticObjectToDrop_p)) {
          return false;
        }
        if (target.equals(semanticObjectToDrop_p)) {
          return false;
        }

        return !FaServices.getFaServices().isControlNode(target);
      }
    }
    return false;
  }

  public boolean isValidDFFunctionalChainInternalLinkEdge(FunctionalChain chain_p, DSemanticDecorator source_p, DSemanticDecorator target_p) {
    return FunctionalChainServices.getFunctionalChainServices().isValidInternalLinkEdge(chain_p, (EdgeTarget) source_p, (EdgeTarget) target_p);
  }

  /**
   * Create a functional exchange between both views
   * @param context_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  public EObject createDFFunctionalExchange(EObject context_p, AbstractDNode sourceView_p, AbstractDNode targetView_p) {
    return ABServices.getService().createABFunctionalExchange(context_p, sourceView_p, targetView_p);
  }

  /**
   * Returns available states/modes to insert in given diagram
   * @param containerView_p
   * @return
   */
  public Collection<EObject> getDFInsertStateModesScope(DSemanticDecorator containerView_p) {
    HashSet<State> availableStates = new HashSet<State>();
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      // Retrieve all available states
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (AbstractFunction function : FunctionPkgExt.getAllAbstractFunctions(BlockArchitectureExt.getFunctionPkg(architecture))) {
        availableStates.addAll(function.getAvailableInStates());
      }

      // Retrains states to states with at least one function not displayed
      for (State state : availableStates) {
        boolean addElement = false;
        for (EObject function : getDFInsertStateModesRelatedElements(state, architecture)) {
          if (function instanceof AbstractFunction) {
            if (!content.containsView(function, FaServices.getFaServices().getMappingDFFunction((AbstractFunction) function, diagram))) {
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
   * @param containerView_p
   * @param elements_p
   */
  public void showDFStateModes(DSemanticDecorator containerView_p, Collection<EObject> elements_p) {

    HashSet<EObject> functionsToShow = new HashSet<EObject>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements_p) {
        if (object instanceof State) {
          State mode = (State) object;
          functionsToShow.addAll(getDFInsertStateModesRelatedElements(mode, sourceArchitecture));
        }
      }
      for (EObject toShow : functionsToShow) {
        if (toShow instanceof AbstractFunction) {
          FaServices.getFaServices().showDFAbstractFunction((AbstractFunction) toShow, content.getBestContainer(toShow), content);
        }
      }
    }
  }

  /**
   * Retrieve related elements for a mode and a given architecture (functions)
   * @param mode_p
   * @param sourceArchitecture_p
   * @return
   */
  public Collection<EObject> getDFInsertStateModesRelatedElements(State mode_p, BlockArchitecture sourceArchitecture_p) {
    return ABServices.getService().getABInsertStateModesRelatedElements(mode_p, sourceArchitecture_p);
  }

  public boolean isValidDndDFFunctionPort(EObject element_p, DSemanticDecorator newContainer_p) {
    if ((newContainer_p == null) || (newContainer_p.getTarget() == null) || newContainer_p.getTarget().eIsProxy()) {
      return false;
    }

    EObject targetContainer = newContainer_p.getTarget();
    if (targetContainer.equals(element_p.eContainer())) {
      return false;
    }

    if (!(targetContainer instanceof ActivityNode)) {
      return false;
    }

    AbstractFunction targetFunction = FunctionExt.getRelatedFunction((ActivityNode) targetContainer);

    if (FunctionExt.isControlNodeOneOutput(targetFunction)) {
      if (element_p instanceof FunctionOutputPort) {
        if (!targetFunction.getOutputs().isEmpty()) {
          return false;
        }
        if (((FunctionOutputPort) element_p).getOutgoingFunctionalExchanges().size() > 1) {
          return false;
        }
        return true;
      }
    }

    if (FunctionExt.isControlNodeOneInput(targetFunction)) {
      if (element_p instanceof FunctionInputPort) {
        if (!targetFunction.getInputs().isEmpty()) {
          return false;
        }
        if (((FunctionInputPort) element_p).getIncomingFunctionalExchanges().size() > 1) {
          return false;
        }
        return true;
      }
    }

    return newContainer_p != null;
  }

  /**
   * Returns whether the tool insert states/modes is available in the given context
   * @param containerView_p
   * @return
   */
  public boolean isValidDFInsertStateModes(DSemanticDecorator containerView_p) {
    return containerView_p instanceof DDiagram;
  }

  public Collection<EObject> getDFInsertScenariosScope(DSemanticDecorator containerView_p) {
    HashSet<EObject> result = new HashSet<EObject>();

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      AbstractCapabilityPkg pkg = BlockArchitectureExt.getAbstractCapabilityPkg(sourceArchitecture);
      for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(pkg)) {

        boolean addElement = false;
        for (EObject element : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario, sourceArchitecture)) {
          if (element instanceof AbstractFunction) {
            if (!content.containsView(element, FaServices.getFaServices().getMappingDFFunction((AbstractFunction) element, diagram))) {
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
   * @param containerView_p
   * @param elements_p
   */
  public void showDFScenarios(DSemanticDecorator containerView_p, Collection<EObject> elements_p) {

    HashSet<AbstractFunction> functionsToShow = new HashSet<AbstractFunction>();
    HashSet<FunctionalExchange> exchangesToShow = new HashSet<FunctionalExchange>();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView_p);
    DDiagramContents content = new DDiagramContents(diagram);

    EObject target = ((DSemanticDecorator) diagram).getTarget();
    if (target != null) {

      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      for (EObject object : elements_p) {
        if (object instanceof Scenario) {
          Scenario scenario = (Scenario) object;

          for (EObject related : ContextualDiagramHelper.getService().getInsertScenariosRelatedElements(scenario, sourceArchitecture)) {

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
   * @param containerView_p
   * @return
   */
  public boolean isValidDFInsertScenarios(DSemanticDecorator containerView_p) {
    return containerView_p instanceof DDiagram;
  }

  /**
   * Gets the AB target.
   */
  public EObject getDFTarget(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagram) {
      EObject diagramTarget = decorator_p.getTarget();
      ContainerMapping aMapping = FaServices.getFaServices().getMappingDFFunction(null, (DDiagram) decorator_p);

      for (DDiagramElement element : ((DDiagram) decorator_p).getOwnedDiagramElements()) {
        if ((DiagramServices.getDiagramServices().isMapping(element, aMapping))) {
          // check if diagram target is in the diagram
          // return parent container
          if ((element.getTarget() == diagramTarget)) {
            return FaServices.getFaServices().getParentFunctionContainer(decorator_p.getTarget());
          }
        }
      }
      // if diagram target is AbstractCapabilty : return function root
      if (diagramTarget instanceof AbstractCapability) {
        return FaServices.getFaServices().getParentFunctionContainer(decorator_p.getTarget());
      }

      return decorator_p.getTarget();
    }
    return decorator_p.getTarget();
  }

}
