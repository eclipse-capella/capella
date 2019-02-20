/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.tools.api.command.view.RefreshSiriusElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.RGB;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.DiagramsPreferencePage;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * Services for functional chain.
 */
public class FunctionalChainServices {

  private static final Integer THICK_BORDER_SOURCE_FUNCTION = Integer.valueOf(4);
  private static final Integer THICK_BORDER_TARGET_FUNCTION = Integer.valueOf(4);
  private static final Integer THICK_EDGE_FUNCTIONAL_CHAIN = Integer.valueOf(4);
  private static final String INCOMPLETE_FUNCTIONAL_CHAIN_LABEL = "incomplete"; //$NON-NLS-1$
  private static final String INVALID_FUNCTIONAL_CHAIN_LABEL = "invalid"; //$NON-NLS-1$

  private static FunctionalChainServices singleton = null;

  public static FunctionalChainServices getFunctionalChainServices() {
    if (singleton == null) {
      singleton = new FunctionalChainServices();
    }
    return singleton;
  }

  /**
   * Returns all the functional chain involvement links for a function chain, including those on recursive levels. This
   * function is tail recursive.
   * 
   * @param chain
   *          the functional chain.
   * @return all the functional chain involvement links for a function chain, including those on recursive levels.
   */
  public Collection<FunctionalChainInvolvementLink> getAllFunctionalChainInvolvementLinks(FunctionalChain chain) {

    Set<FunctionalChainInvolvementLink> result = new HashSet<>();
    getAllFunctionalChainInvolvementLinks(chain, result);

    return result;
  }

  /**
   * This is a tail recursive version that returns all the functional chain involvement links for a function chain,
   * including those on recursive levels.
   * 
   * @param chain
   *          the functional chain.
   * @param linksAcumulator
   *          the functional chain involvment links acumulator.
   */
  private void getAllFunctionalChainInvolvementLinks(FunctionalChain chain,
      Collection<FunctionalChainInvolvementLink> linksAcumulator) {

    for (FunctionalChainInvolvement involvement : chain.getOwnedFunctionalChainInvolvements()) {
      if (involvement instanceof FunctionalChainInvolvementLink) {
        linksAcumulator.add((FunctionalChainInvolvementLink) involvement);
      } else if (involvement instanceof FunctionalChainReference) {
        FunctionalChain referencedChain = ((FunctionalChainReference) involvement).getReferencedFunctionalChain();
        getAllFunctionalChainInvolvementLinks(referencedChain, linksAcumulator);
      }
    }
  }

  /**
   * Returns all semantics of source views for the given chain
   */
  public Collection<EObject> getFunctionalChainSources(FunctionalChain chain) {
    Set<EObject> result = new HashSet<>();

    // Source of InternalLink is targets of Functional Exchanges
    for (FunctionalExchange exchange : FunctionalChainExt.getFlatFunctionalExchanges(chain)) {
      result.add(exchange.getTarget());
    }

    return result;
  }

  /**
   * Returns all semantics of target views for the given chain
   */
  public Collection<EObject> getFunctionalChainTargets(FunctionalChain chain) {
    Set<EObject> result = new HashSet<>();

    // Target of InternalLink is sources of Functional Exchanges
    for (FunctionalExchange exchange : FunctionalChainExt.getFlatFunctionalExchanges(chain)) {
      result.add(exchange.getSource());
    }

    return result;

  }

  /**
   * Returns the best function through the elements collection
   */
  public AbstractFunction getBestFunction(AbstractFunction element, Collection<? extends EObject> elements) {
    EObject current = element;
    while (current != null) {
      if (current instanceof AbstractFunction && elements.contains(current)) {
        return (AbstractFunction) current;
      }
      current = current.eContainer();
    }
    return null;
  }

  public List<FunctionalChain> getFunctionalChainsToInsert(EObject context, DDiagram currentDiagram,
      List<FunctionalChain> allFunctionalChains) {
    List<FunctionalChain> returnedList = new ArrayList<FunctionalChain>();
    for (DDiagramElement anObject : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {
      if (anObject.getTarget() instanceof AbstractFunction) {
        AbstractFunction aFunction = (AbstractFunction) anObject.getTarget();
        for (FunctionalChain aFunctionalChain : allFunctionalChains) {
          Collection<EObject> involvedFunctions = FunctionalChainExt.getFlatInvolvedElements(aFunctionalChain,
              FaPackage.Literals.ABSTRACT_FUNCTION);
          if (!returnedList.contains(aFunctionalChain)) {
            if (involvedFunctions.contains(aFunction)) {
              returnedList.add(aFunctionalChain);
            } else {
              for (AbstractFunction aSubFunction : FunctionExt.getAllAbstractFunctions(aFunction)) {
                if (involvedFunctions.contains(aSubFunction)) {
                  returnedList.add(aFunctionalChain);
                }
              }
            }
          }
        }
      }
    }
    return returnedList;
  }

  /**
   * 1. Add internal edges for functional chains if not already existed 2. Remove INVALID internal edges for functional
   * chains.
   * 
   * @param diagram
   */
  public void updateInternalFunctionalChains(DDiagram diagram) {
    HashMap<FunctionalChain, DNode> functionalChainToNodeMap = computeFunctionalChainToNodeMap(diagram);

    HashMap<FunctionalExchange, Set<DEdge>> functionalExchangeToEdgesMap = computeFunctionalExchangeToEdgesMap(diagram);

    HashMap<FunctionalChain, Set<DEdge>> functionalChainToEdgesMap = computeFunctionalChainToEdgesMap(diagram);

    // remove internal links of functional chain if the Functional chain is not displayed
    for (Entry<FunctionalChain, Set<DEdge>> entry : functionalChainToEdgesMap.entrySet()) {
      FunctionalChain functionalChain = entry.getKey();
      if (!functionalChainToNodeMap.containsKey(functionalChain)) {
        Set<DEdge> edges = entry.getValue();
        for (DEdge edge : edges) {
          DiagramServices.getDiagramServices().removeEdgeView(edge);
        }
      }
    }

    boolean isInOperationalAnalysis = BlockArchitectureExt
        .getRootBlockArchitecture(((DSemanticDiagram) diagram).getTarget()) instanceof OperationalAnalysis;
    if (!isInOperationalAnalysis) {
      for (Entry<FunctionalChain, DNode> entry : functionalChainToNodeMap.entrySet()) {
        FunctionalChain functionalChain = entry.getKey();
        createNecessaryInternalLinks(functionalChain, functionalExchangeToEdgesMap);
      }
    }

    // Remove all internal links that are not valid.
    for (Entry<FunctionalChain, Set<DEdge>> entry : functionalChainToEdgesMap.entrySet()) {
      FunctionalChain chain = entry.getKey();
      Set<DEdge> edges = entry.getValue();
      for (DEdge edge : edges) {
        if (!isValidInternalLinkEdge(chain, edge.getSourceNode(), edge.getTargetNode())) {
          DiagramServices.getDiagramServices().removeEdgeView(edge);
        }
      }
    }
  }

  /**
   * Update the style for functional chains. Colored border, highlight edges.
   * 
   * @param diagram
   */
  public void updateFunctionalChainStyles(DDiagram diagram) {
    HashMap<FunctionalChain, DNode> functionalChainToNodeMap = computeFunctionalChainToNodeMap(diagram);

    // The internal edges representing functional chains on diagram
    HashMap<FunctionalChain, Set<DEdge>> functionalChainToEdgesMap = computeFunctionalChainToEdgesMap(diagram);

    HashMap<AbstractFunction, Set<DDiagramElement>> functionToDiagramElementsMap = computeFunctionToDiagramElementsMap(
        diagram);

    HashMap<FunctionalExchange, Set<DEdge>> functionalExchangeToEdgesMap = computeFunctionalExchangeToEdgesMap(diagram);

    HashMap<DDiagramElement, Set<FunctionalChain>> coloredNodeToFunctionalChainsMap = computeColoredNodeToFunctionalChainsMap(
        functionalChainToNodeMap, functionToDiagramElementsMap);

    HashMap<DEdge, Set<FunctionalChain>> coloredEdgeToFunctionalChainsMap = computeColoredEdgeToFunctionalChainsMap(
        functionalChainToNodeMap, functionalExchangeToEdgesMap);

    // update functions style
    for (Entry<AbstractFunction, Set<DDiagramElement>> entry : functionToDiagramElementsMap.entrySet()) {
      Set<DDiagramElement> functionNodes = entry.getValue();
      for (DDiagramElement functionNode : functionNodes) {
        if (!coloredNodeToFunctionalChainsMap.containsKey(functionNode)) {
          resetFunctionStyle(functionNode);
        }
      }
    }

    for (Entry<FunctionalChain, DNode> entry : functionalChainToNodeMap.entrySet()) {
      FunctionalChain functionalChain = entry.getKey();
      DNode functionalChainNode = entry.getValue();

      updateFunctionalChainNodeColor(functionalChainNode, functionalChainToNodeMap.values());
      RGBValues functionalChainColor = ShapeUtil.getNodeColorStyle(functionalChainNode);
      if (functionalChainColor == null) {
        continue;
      }

      // update style of source function of the chain
      for (AbstractFunction function : FunctionalChainExt.getFlatFunctionalChainFirstFunctions(functionalChain)) {
        // source Node of the functional chain
        Set<DDiagramElement> functionNodes = getBestDisplayedFunctionNode(function, functionToDiagramElementsMap);
        functionNodes = (functionNodes == null) ? new HashSet<>() : functionNodes;
        for (DDiagramElement functionNode : functionNodes) {
          // color the border of the target function with the color of the functional chain
          // black color if more than one functional chain
          RGBValues color = (coloredNodeToFunctionalChainsMap.get(functionNode).size() == 1) ? functionalChainColor
              : ShapeUtil.getBlackColor();
          customizeSourceFunctionStyle(functionNode, color);
        }
      }

      // update style of target function of the chain
      for (AbstractFunction function : FunctionalChainExt.getFlatFunctionalChainLastFunctions(functionalChain)) {
        // target Node of the functional chain
        Set<DDiagramElement> functionNodes = getBestDisplayedFunctionNode(function, functionToDiagramElementsMap);
        functionNodes = (functionNodes == null) ? new HashSet<>() : functionNodes;
        for (DDiagramElement functionNode : functionNodes) {
          // color the border of the target function with the color of the functional chain
          // black color if more than one functional chain
          RGBValues color = (coloredNodeToFunctionalChainsMap.get(functionNode).size() == 1) ? functionalChainColor
              : ShapeUtil.getBlackColor();
          customizeTargetFunctionStyle(functionNode, color);
        }
      }

      // update style of internal links of functional chain(except in operational analysis architecture)
      boolean isInOperationalAnalysis = BlockArchitectureExt
          .getRootBlockArchitecture(((DSemanticDiagram) diagram).getTarget()) instanceof OperationalAnalysis;
      if (!isInOperationalAnalysis) {
        // TODO: should we use getVisibleInternalLinks
        // Set<DEdge> internalLinks = getVisibleInternalLinks(functionalChain, functionalExchangeToEdgesMap);
        // For each internal edge representing the functional chain; decorate it with the color.
        Set<DEdge> internalLinks = functionalChainToEdgesMap.get(functionalChain);
        internalLinks = (internalLinks == null) ? new HashSet<>() : internalLinks;
        internalLinks.remove(null);
        for (DEdge internalLink : internalLinks) {
          customizeInternalLinksEdgeStyle(internalLink, functionalChainColor);
        }
      }

      // update style of functional exchange edges
      for (FunctionalExchange functionalExchange : FunctionalChainExt.getFlatFunctionalExchanges(functionalChain)) {
        if (functionalExchangeToEdgesMap.containsKey(functionalExchange)) {
          Set<DEdge> edges = functionalExchangeToEdgesMap.get(functionalExchange);
          for (DEdge edge : edges) {
            // color the border of the target function with the color of the functional chain
            // black color if more than one functional chain
            RGBValues color = (coloredEdgeToFunctionalChainsMap.get(edge).size() == 1) ? functionalChainColor
                : ShapeUtil.getBlackColor();
            customizeFunctionalExchangeEdgeStyle(edge, color);
          }
        }
      }

      RefreshSiriusElement.refresh(functionalChainNode);
    }

    // reset functional exchanges with no associated functional chain
    for (Set<DEdge> edges : functionalExchangeToEdgesMap.values()) {
      for (DEdge edge : edges) {
        if (!coloredEdgeToFunctionalChainsMap.containsKey(edge)) {
          resetFunctionalExchangeStyle(edge);
        }
      }
    }
  }

  private HashMap<DEdge, Set<FunctionalChain>> computeColoredEdgeToFunctionalChainsMap(
      HashMap<FunctionalChain, DNode> functionalChainToNodeMap,
      HashMap<FunctionalExchange, Set<DEdge>> functionalExchangeToEdgesMap) {
    // compute map from NEED-TO-BE-COLORED Edge to the functional chains
    HashMap<DEdge, Set<FunctionalChain>> coloredEdgeToFunctionalChainsMap = new HashMap<>(); // colored functional
                                                                                             // Exchanges
    for (Entry<FunctionalChain, DNode> entry : functionalChainToNodeMap.entrySet()) {
      FunctionalChain functionalChain = entry.getKey();
      for (FunctionalExchange anExchange : FunctionalChainExt.getFlatFunctionalExchanges(functionalChain)) {
        if (functionalExchangeToEdgesMap.containsKey(anExchange)) {
          Set<DEdge> exchangeEdges = functionalExchangeToEdgesMap.get(anExchange);
          for (DEdge exchangeEdge : exchangeEdges) {
            Set<FunctionalChain> functionalChains = coloredEdgeToFunctionalChainsMap.get(exchangeEdge);
            if (functionalChains == null) {
              functionalChains = new HashSet<>();
              coloredEdgeToFunctionalChainsMap.put(exchangeEdge, functionalChains);
            }
            functionalChains.add(functionalChain);
          }
        }
      }
    }
    return coloredEdgeToFunctionalChainsMap;
  }

  private HashMap<DDiagramElement, Set<FunctionalChain>> computeColoredNodeToFunctionalChainsMap(
      HashMap<FunctionalChain, DNode> functionalChainToNodeMap,
      HashMap<AbstractFunction, Set<DDiagramElement>> functionToDiagramElementsMap) {
    // compute map from NEED-TO-BE-COLORED Node to the functional chains
    HashMap<DDiagramElement, Set<FunctionalChain>> coloredElementToFunctionalChainsMap = new HashMap<>(); // colored
                                                                                                          // functions
    for (Entry<FunctionalChain, DNode> entry : functionalChainToNodeMap.entrySet()) {
      FunctionalChain functionalChain = entry.getKey();

      Set<AbstractFunction> fcFunctions = new HashSet<>();
      // source Node of the functional chain
      Set<AbstractFunction> fcSourceFunctions = FunctionalChainExt
          .getFlatFunctionalChainFirstFunctions(functionalChain);
      // target Node of the functional chain
      Set<AbstractFunction> fcTargetFunctions = FunctionalChainExt.getFlatFunctionalChainLastFunctions(functionalChain);

      fcFunctions.addAll(fcSourceFunctions);
      fcFunctions.addAll(fcTargetFunctions);

      for (AbstractFunction function : fcFunctions) {
        Set<DDiagramElement> functionNodes = getBestDisplayedFunctionNode(function, functionToDiagramElementsMap);
        if (functionNodes != null) {
          for (DDiagramElement functionNode : functionNodes) {
            Set<FunctionalChain> functionalChains = coloredElementToFunctionalChainsMap.get(functionNode);
            if (functionalChains == null) {
              functionalChains = new HashSet<>();
              coloredElementToFunctionalChainsMap.put(functionNode, functionalChains);
            }
            functionalChains.add(functionalChain);
          }
        }
      }
    }
    return coloredElementToFunctionalChainsMap;
  }

  private HashMap<FunctionalChain, Set<DEdge>> computeFunctionalChainToEdgesMap(DDiagram diagram) {
    // compute map from functional chain to its displaying edges on the diagram.
    // they are internal edges.
    HashMap<FunctionalChain, Set<DEdge>> functionalChainToEdgesMap = new HashMap<>(); // displayed Internal Links
    for (DEdge edge : diagram.getEdges()) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof FunctionalChain) {
        FunctionalChain fc = (FunctionalChain) edgeTarget;
        Set<DEdge> edges = functionalChainToEdgesMap.get(fc);
        if (edges == null) {
          edges = new HashSet<>();
          functionalChainToEdgesMap.put(fc, edges);
        }
        edges.add(edge);
      }
    }
    return functionalChainToEdgesMap;
  }

  private HashMap<FunctionalExchange, Set<DEdge>> computeFunctionalExchangeToEdgesMap(DDiagram diagram) {
    // compute map from functional exchange to its displaying edges on the diagram
    HashMap<FunctionalExchange, Set<DEdge>> functionalExchangeToEdgesMap = new HashMap<>();

    for (DEdge edge : diagram.getEdges()) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof FunctionalExchange) {
        FunctionalExchange fe = (FunctionalExchange) edgeTarget;
        Set<DEdge> edges = functionalExchangeToEdgesMap.get(fe);
        if (edges == null) {
          edges = new HashSet<>();
          functionalExchangeToEdgesMap.put(fe, edges);
        }
        edges.add(edge);
      }
    }

    return functionalExchangeToEdgesMap;
  }

  private HashMap<AbstractFunction, Set<DDiagramElement>> computeFunctionToDiagramElementsMap(DDiagram diagram) {
    // compute map from function to its displaying diagram elements on the diagram
    HashMap<AbstractFunction, Set<DDiagramElement>> functionToDiagramElementsMap = new HashMap<>();
    for (DNode aNode : diagram.getNodes()) {
      EObject target = aNode.getTarget();
      if (target instanceof AbstractFunction) {
        Set<DDiagramElement> set = functionToDiagramElementsMap.get(target);
        if (set == null) {
          set = new HashSet<>();
          functionToDiagramElementsMap.put((AbstractFunction) target, set);
        }
        set.add(aNode);
      }
    }
    for (DDiagramElement aContainer : diagram.getContainers()) {
      EObject target = aContainer.getTarget();
      if ((target instanceof AbstractFunction)) {
        Set<DDiagramElement> set = functionToDiagramElementsMap.get(target);
        if (set == null) {
          set = new HashSet<>();
          functionToDiagramElementsMap.put((AbstractFunction) target, set);
        }
        set.add(aContainer);
      }
    }

    return functionToDiagramElementsMap;
  }

  private HashMap<FunctionalChain, DNode> computeFunctionalChainToNodeMap(DDiagram diagram) {
    // compute map from functional chain to its displaying DNode on the diagram
    HashMap<FunctionalChain, DNode> functionalChainToNodeMap = new HashMap<>(); // displayed Functional Chains
    for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
      if ((aNode instanceof DNode) && (aNode.getTarget() instanceof FunctionalChain)) {
        functionalChainToNodeMap.put((FunctionalChain) aNode.getTarget(), (DNode) aNode);
      }
    }
    return functionalChainToNodeMap;
  }

  /**
   * @param currentSourceNode
   * @return
   */
  private boolean isValidNodeForInternalLink(EdgeTarget currentNode) {
    if (!(currentNode instanceof DNode)) {
      return false;
    }
    if (!DiagramServices.getDiagramServices().isABorderedNode((DNode) currentNode)) {
      return false;
    }
    return true;
  }

  /**
   * Create new internal links for the functional chain and related functional exchanges if not already existing.
   * 
   * @param functionalChain
   * @param displayedFunctionalExchanges
   * @return
   */
  private Set<DEdge> createNecessaryInternalLinks(FunctionalChain functionalChain,
      Map<FunctionalExchange, Set<DEdge>> displayedFunctionalExchanges) {
    Set<DEdge> newInternalLinks = new HashSet<>();

    // iterate over involved functional exchange
    for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvementsOf(functionalChain,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      FunctionalExchange functionalExchange = (FunctionalExchange) involvement.getInvolved();

      Collection<FunctionalExchange> previousExchanges = getFlatPreviousFunctionalExchanges(functionalChain,
          involvement);
      Collection<FunctionalExchange> nextExchanges = getFlatNextFunctionalExchanges(functionalChain, involvement);

      Set<DEdge> currentEdges = displayedFunctionalExchanges.get(functionalExchange);
      currentEdges = (currentEdges == null) ? new HashSet<>() : currentEdges;
      for (DEdge currentEdge : currentEdges) {
        if (currentEdge == null) {
          continue;
        }
        EdgeTarget currentSourceNode = currentEdge.getSourceNode();
        if (isValidNodeForInternalLink(currentSourceNode)) {
          // Display an internal link from previousExchange.target to exchange.source
          for (FunctionalExchange prevFE : previousExchanges) {
            Set<DEdge> prevEdges = displayedFunctionalExchanges.get(prevFE);
            prevEdges = (prevEdges == null) ? new HashSet<>() : prevEdges;
            prevEdges.remove(null); // Remove null, just in case.

            for (DEdge prevEdge : prevEdges) {
              EdgeTarget internalLinkSourceNode = prevEdge.getTargetNode();
              EdgeTarget internalLinkTargetNode = currentSourceNode;

              if (isValidNodeForInternalLink(internalLinkSourceNode)
                  && isValidInternalLinkEdge(functionalChain, internalLinkSourceNode, internalLinkTargetNode)) {

                DEdge internalLink = getExistingInternalLink(internalLinkSourceNode, internalLinkTargetNode,
                    functionalChain);
                if (internalLink == null) {
                  internalLink = createInternalLink(internalLinkSourceNode, internalLinkTargetNode, functionalChain);
                  newInternalLinks.add(internalLink);
                }
              }
            }
          }
        }

        EdgeTarget currentTargetNode = currentEdge.getTargetNode();
        if (isValidNodeForInternalLink(currentTargetNode)) {
          // Display an internal link from exchange.target to nextExchange.source
          for (FunctionalExchange nextFE : nextExchanges) {
            Set<DEdge> nextEdges = displayedFunctionalExchanges.get(nextFE);
            nextEdges = (nextEdges == null) ? new HashSet<>() : nextEdges;
            nextEdges.remove(null); // Remove null, just in case.

            for (DEdge nextEdge : nextEdges) {
              EdgeTarget internalLinkTargetNode = nextEdge.getSourceNode();
              EdgeTarget internalLinkSourceNode = currentTargetNode;

              if (isValidNodeForInternalLink(internalLinkTargetNode)
                  && isValidInternalLinkEdge(functionalChain, internalLinkSourceNode, internalLinkTargetNode)) {
                DEdge internalLink = getExistingInternalLink(internalLinkSourceNode, internalLinkTargetNode,
                    functionalChain);
                if (internalLink == null) {
                  internalLink = createInternalLink(internalLinkSourceNode, internalLinkTargetNode, functionalChain);
                  newInternalLinks.add(internalLink);
                }
              }
            }
          }
        }
      }
    }

    return newInternalLinks;
  }

  /**
   * returns whether an internal link can be created between given both bordered nodes
   * 
   * @param currentSourceNode
   * @param currentTargetNode
   * @return
   */
  public boolean isValidInternalLinkEdge(FunctionalChain chain, EdgeTarget currentSourceNode,
      EdgeTarget currentTargetNode) {
    if (currentSourceNode == null || currentSourceNode.getIncomingEdges().isEmpty()) {
      return false;
    }
    if (currentTargetNode == null || currentTargetNode.getOutgoingEdges().isEmpty()) {
      return false;
    }

    // At least one incoming edge should be visible
    if (!hasVisibleEdge(currentSourceNode.getIncomingEdges())) {
      return false;
    }

    // At least one outgoing edge should be visible
    if (!hasVisibleEdge(currentTargetNode.getOutgoingEdges())) {
      return false;
    }

    EObject sourceParent = currentSourceNode.eContainer();
    EObject targetParent = currentTargetNode.eContainer();
    // Allow internal links only on same parent (it is correct?)
    if ((sourceParent != null) && (targetParent != null)) {
      return sourceParent.equals(targetParent);
    }
    return false;
  }

  private boolean hasVisibleEdge(EList<DEdge> edges) {
    for (DEdge edge : edges) {
      if (!DiagramServices.getDiagramServices().isHidden(edge)
          && !DiagramServices.getDiagramServices().isFiltered(edge)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Create or return an internal link between both nodes.
   */
  protected DEdge getExistingInternalLink(EdgeTarget sourceNode, EdgeTarget targetNode, FunctionalChain fc) {
    DEdge internalLink = null;
    for (DEdge edge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceNode)) {
      if ((edge.getTarget() instanceof FunctionalChain) && edge.getTarget().equals(fc)
          && targetNode.equals(edge.getTargetNode())) {
        internalLink = edge;
        break;
      }
    }

    return internalLink;
  }

  /**
   * @param fc
   * @return
   */
  protected Collection<FunctionalExchange> getFlatPreviousFunctionalExchanges(FunctionalChain fc,
      FunctionalChainInvolvement fci) {
    Collection<FunctionalExchange> result = new HashSet<>();

    for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatPreviousExchangeInvolvements(fci)) {
      if (involvment.getInvolved() instanceof FunctionalExchange) {
        result.add((FunctionalExchange) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param fc
   * @return
   */
  protected Collection<FunctionalExchange> getFlatNextFunctionalExchanges(FunctionalChain fc,
      FunctionalChainInvolvement fci) {
    Collection<FunctionalExchange> result = new HashSet<>();

    for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatNextExchangeInvolvements(fci)) {
      if (involvment.getInvolved() instanceof FunctionalExchange) {
        result.add((FunctionalExchange) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param sourceNode
   * @param targetNode
   * @param color
   * @return
   */
  public DEdge createInternalLink(EdgeTarget sourceNode, EdgeTarget targetNode, FunctionalChain fc) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode);
    EdgeMapping mapping = getInternLinkEdgeMapping(diagram);
    DEdge newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode, targetNode, fc, mapping);
    if (newEdge == null) {
      DiagramServices.getDiagramServices().createEdge(mapping, sourceNode, targetNode, fc);
      newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode, targetNode, fc, mapping);
    }
    return newEdge;
  }

  public EdgeMapping getInternLinkEdgeMapping(DDiagram diagram) {
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram,
        MappingConstantsHelper.getInternLinkEdgeMapping(diagram));
  }

  /**
   * @param aEdge
   */
  public void resetFunctionalExchangeStyle(DEdge aEdge) {
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(aEdge);
    if (mapping != null) {
      // get default style size of an edge
      EdgeStyleDescription desc = (EdgeStyleDescription) getMappingHelper(aEdge).getBestStyleDescription(mapping,
          aEdge.getTarget(), aEdge, aEdge.eContainer(), CapellaServices.getService().getDiagramContainer(aEdge));
      String defaultStyleSize = desc.getSizeComputationExpression();
      // get current style size of an edge
      EdgeStyle edgeStyle = aEdge.getOwnedStyle();
      Integer currentSize = edgeStyle.getSize();

      if ((null != currentSize) && (null != defaultStyleSize)) {
        // apply style & color : if currentSize is equal to default size + if current size is equal to default size of
        // Functional Chain
        if (currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN) || currentSize.equals(defaultStyleSize)) {
          if (ShapeUtil.resetEdgeThickStyle(aEdge, Integer.valueOf(defaultStyleSize))) {
            ShapeUtil.resetEdgeColorStyle(aEdge, ShapeUtil.getDefaultColor(aEdge, desc, desc.getStrokeColor()));
          }
        }
      }
    }
  }

  /**
   * @param value
   * @param displayedFC
   */
  public boolean resetFunctionStyle(DDiagramElement functionNode) {
    boolean result = false;
    String defaultStyleSize = null;
    Integer currentSize = null;
    BorderedStyle style = null;

    if (functionNode instanceof AbstractDNode) {
      AbstractDNode node = (AbstractDNode) functionNode;
      BorderedStyleDescription desc = (BorderedStyleDescription) getMappingHelper(functionNode).getBestStyleDescription(
          functionNode.getDiagramElementMapping(), functionNode.getTarget(), functionNode, functionNode.eContainer(),
          CapellaServices.getService().getDiagramContainer(functionNode));

      if (desc != null) {
        defaultStyleSize = desc.getBorderSizeComputationExpression();
        style = (BorderedStyle) ShapeUtil.getCurrentStyle(node);
        if (style != null) {
          currentSize = style.getBorderSize();
        }

        if ((null != currentSize) && (null != defaultStyleSize)) {
          // apply style & color : if currentSize is equal to default size + if current size is equal to default size of
          // Functional Chain
          if (currentSize.equals(THICK_BORDER_SOURCE_FUNCTION) || currentSize.equals(THICK_BORDER_TARGET_FUNCTION)
              || currentSize.equals(defaultStyleSize)) {
            if (ShapeUtil.resetBorderStyle(node, Integer.valueOf(desc.getBorderSizeComputationExpression()))) {
              if (ShapeUtil.resetBorderColorStyle(node, ShapeUtil.getDefaultColor(node, desc, desc.getBorderColor()))) {
                result = true;
              }
            }
          }
        }
      }
    }

    return result;
  }

  public void customizeFunctionalExchangeEdgeStyle(DEdge edge, RGBValues color) {
    // get current style size of an edge
    EdgeStyle edgeStyle = edge.getOwnedStyle();
    Integer currentSize = edgeStyle.getSize();

    // get default style size of an edge
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(edge);
    if (mapping != null) {
      EdgeStyleDescription desc = (EdgeStyleDescription) getMappingHelper(edge).getBestStyleDescription(mapping,
          edge.getTarget(), edge, edge.eContainer(), CapellaServices.getService().getDiagramContainer(edge));
      if (null != desc) {
        // assuming it is an integer value
        String defaultSize = desc.getSizeComputationExpression();
        if ((null != defaultSize) && (null != currentSize) && (currentSize.equals(Integer.valueOf(defaultSize))
            || (currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN)))) {
          // apply change
          customizeEdgeStyle(edge, color);
        }
      }
    }
  }

  public void customizeInternalLinksEdgeStyle(DEdge edge, RGBValues color) {
    customizeEdgeStyle(edge, color);
  }

  public void customizeEdgeStyle(DEdge edge, RGBValues color) {
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    ShapeUtil.setEdgeColorStyle(edge, rgbColor);
    ShapeUtil.setEdgeThickStyle(edge, THICK_EDGE_FUNCTIONAL_CHAIN);
  }

  public void customizeFunctionStyle(DDiagramElement functionNode, RGBValues color) {
    // Change color border style
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    if (functionNode instanceof AbstractDNode) {
      ShapeUtil.setBorderColorStyle(((AbstractDNode) functionNode), rgbColor);
    }
  }

  public void customizeSourceFunctionStyle(DDiagramElement functionNode, RGBValues color) {
    customizeFunctionStyle(functionNode, color);
    if (functionNode instanceof AbstractDNode) {
      ShapeUtil.setBorderStyle(((AbstractDNode) functionNode), THICK_BORDER_SOURCE_FUNCTION);
    }
  }

  public void customizeTargetFunctionStyle(DDiagramElement functionNode, RGBValues color) {
    customizeFunctionStyle(functionNode, color);
    if (functionNode instanceof AbstractDNode) {
      ShapeUtil.setBorderStyle(((AbstractDNode) functionNode), THICK_BORDER_TARGET_FUNCTION);
    }
  }

  /**
   * @param function
   * @param displayedFunctions
   * @return the function or one of its container contained in the map keys
   */
  public Set<DDiagramElement> getBestDisplayedFunctionNode(AbstractFunction function,
      Map<AbstractFunction, Set<DDiagramElement>> displayedFunctions) {
    if (displayedFunctions.containsKey(function)) {
      return displayedFunctions.get(function);
    }
    EObject ancestor = function.eContainer();
    while (ancestor instanceof AbstractFunction) {
      if (displayedFunctions.containsKey(ancestor)) {
        return displayedFunctions.get(ancestor);
      }
      ancestor = ancestor.eContainer();
    }
    return null;
  }

  public boolean isCompleteFunctionalChain(FunctionalChain fc, DDiagram diagram) {
    Set<FunctionalExchange> functionalExchangesOnTheChain = FunctionalChainExt.getFlatFunctionalExchanges(fc);

    int numberOfVisibleRelatedFEEdges = 0;

    for (DEdge anEdge : diagram.getEdges()) {
      if (anEdge.isVisible()) {
        EObject edgeTarget = anEdge.getTarget();
        if (edgeTarget instanceof FunctionalExchange && functionalExchangesOnTheChain.contains(edgeTarget)) {
          numberOfVisibleRelatedFEEdges++;
        }
      }
    }

    return numberOfVisibleRelatedFEEdges == functionalExchangesOnTheChain.size();
  }

  // TODO Add filters for Functions
  public String getFCInvolvmentLinkLabel(FunctionalChainInvolvementLink involvementLink, DDiagram diagram) {

    String label = "";
    InvolvedElement involved = involvementLink.getInvolved();

    if (involved instanceof FunctionalExchange) {

      boolean showExchangeItems = false;
      boolean showExchangeItemsParameters = false;
      boolean showFEEI = false;

      FunctionalExchange fe = (FunctionalExchange) involvementLink.getInvolved();
      label = fe.getName();
      for (FilterDescription filter : diagram.getActivatedFilters()) {
        if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS)) {
          showExchangeItems = true;
        }
        if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_PARAMETERS)) {
          showExchangeItemsParameters = true;
        }
        if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS)) {
          showFEEI = true;
        }
      }

      if (showExchangeItems || showFEEI) {
        StringBuilder sb = new StringBuilder();
        if (showFEEI) {
          sb.append(label);
          sb.append("["); //$NON-NLS-1$
        }
        int indice = 0;
        EList<ExchangeItem> exchangedItems = involvementLink.getExchangedItems();
        if ((null == exchangedItems) || exchangedItems.isEmpty()) {
          for (AbstractExchangeItem ei : fe.getExchangedItems()) {
            sb.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
            indice++;
            if (indice < fe.getExchangedItems().size()) {
              sb.append(", "); //$NON-NLS-1$
            }
          }
        } else {
          for (AbstractExchangeItem ei : exchangedItems) {
            sb.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
            indice++;
            if (indice < exchangedItems.size()) {
              sb.append(", "); //$NON-NLS-1$
            }
          }
        }

        if (showFEEI) {
          sb.append("]"); //$NON-NLS-1$
        }
        return sb.toString();
      }
    } else if (involved instanceof AbstractFunction) {

      AbstractFunction function = (AbstractFunction) involved;
      label = function.getName();
    }

    return label;
  }

  public String getFunctionalChainLabel(FunctionalChain fc, DDiagram diagram) {
    String label = EObjectExt.getText(fc);

    boolean isComplete = isCompleteFunctionalChain(fc, diagram);
    boolean isValid = FunctionalChainExt.isFunctionalChainValid(fc);

    boolean displayIncompleteLabel = !isComplete;
    boolean displayInvalidLabel = !isValid;

    if (fc instanceof OperationalProcess) {
      displayIncompleteLabel &= ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(fc));
      displayInvalidLabel &= ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(fc));
    } else {
      displayIncompleteLabel &= ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL,
          PreferencesHelper.getProject(fc));
      displayInvalidLabel &= ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL, PreferencesHelper.getProject(fc));
    }

    if (displayIncompleteLabel || displayInvalidLabel) {
      label = label + " ("; //$NON-NLS-1$
    }
    if (displayIncompleteLabel) {
      label = label + INCOMPLETE_FUNCTIONAL_CHAIN_LABEL;
    }
    if (displayIncompleteLabel && displayInvalidLabel) {
      label = label + ", "; //$NON-NLS-1$
    }
    if (displayInvalidLabel) {
      label = label + INVALID_FUNCTIONAL_CHAIN_LABEL;
    }
    if (displayIncompleteLabel || displayInvalidLabel) {
      label = label + ")"; //$NON-NLS-1$
    }
    return label;
  }

  public void updateFunctionalChainNodeColor(DNode fcNode, Collection<DNode> visibleFunctionalChains) {
    RGBValues color = ShapeUtil.getNodeColorStyle(fcNode);
    LinkedList<RGB> colorList = new LinkedList<>();

    RGB blue = new RGB(24, 114, 248);
    RGB yellow = new RGB(249, 252, 103);
    RGB purple = new RGB(160, 32, 240);
    RGB gray = new RGB(136, 136, 136);
    RGB orange = new RGB(255, 165, 0);
    RGB green = new RGB(34, 139, 34);
    RGB brown = new RGB(165, 42, 42);

    colorList.addLast(blue);
    colorList.addLast(brown);
    colorList.addLast(orange);
    colorList.addLast(green);
    colorList.addLast(purple);
    colorList.addLast(yellow);
    colorList.addLast(gray);

    boolean changeColor = false;

    if (ShapeUtil.isSameColor(color, gray)) {
      changeColor = true;
    }
    for (DNode aFc : visibleFunctionalChains) {
      if (!aFc.equals(fcNode)) {
        RGBValues nodeColor = ShapeUtil.getNodeColorStyle(aFc);
        if (ShapeUtil.isSameColor(nodeColor, color)) {
          changeColor = true;
        }
        ShapeUtil.removeColorFromList(nodeColor, colorList);
      }
    }
    if (!changeColor) {
      return;
    }
    if (!colorList.isEmpty()) {
      ShapeUtil.setColorStyle(fcNode, colorList.get(0));
    }
  }

  public List<FunctionalExchange> getAvailableExchanges(EObject context, AbstractFunction source,
      AbstractFunction target) {
    List<FunctionalExchange> returnedFunctionalExchanges = new ArrayList<>();
    List<FunctionalExchange> incoming = FunctionExt.getIncomingExchange(target);
    List<FunctionalExchange> outgoing = FunctionExt.getOutGoingExchange(source);
    for (FunctionalExchange aFunctionalExchange : incoming) {
      if (outgoing.contains(aFunctionalExchange)) {
        returnedFunctionalExchanges.add(aFunctionalExchange);
      }
    }
    return returnedFunctionalExchanges;
  }

  public List<FunctionalChainInvolvement> getPreviousInvolvements(FunctionalChainInvolvement involvement) {
    return involvement.getPreviousFunctionalChainInvolvements();
  }

  public EObject createFunctionalChain(EObject context, List<EObject> views) {
    if (!views.isEmpty()) {
      List<EObject> newList = new ArrayList<>();
      AbstractFunction aFunction = null;
      for (EObject aSelectedElement : views) {
        if ((aSelectedElement instanceof DDiagramElement)
            && (((DDiagramElement) aSelectedElement).getTarget() != null)) {
          newList.add(((DDiagramElement) aSelectedElement).getTarget());
          if ((aFunction == null) && (((DDiagramElement) aSelectedElement).getTarget() instanceof FunctionalExchange)) {
            AbstractFunction aSourceFunction = FunctionExt
                .getIncomingAbstractFunction(((FunctionalExchange) ((DDiagramElement) aSelectedElement).getTarget()));
            aFunction = FunctionExt.getRootFunction(aSourceFunction);
          }
        }
      }
      if (aFunction != null) {

        EObject diagramContainer = ((DSemanticDiagram) CapellaServices.getService().getDiagramContainer(views.get(0)))
            .getTarget();
        FunctionalChain newFC;
        if (diagramContainer instanceof AbstractFunctionalChainContainer) {
          newFC = FunctionalChainExt.createFunctionalChain((AbstractFunctionalChainContainer) diagramContainer,
              newList);
        } else {
          newFC = FunctionalChainExt.createFunctionalChain(aFunction, newList);
        }
        return newFC;
      }
    }
    return context;
  }

  public void removeFunctionalChainAbstractCapabilityInvolvement(AbstractCapability capability, EObject target) {
    Set<FunctionalChainAbstractCapabilityInvolvement> toRemove = new HashSet<FunctionalChainAbstractCapabilityInvolvement>();
    for (FunctionalChainAbstractCapabilityInvolvement inv : capability
        .getOwnedFunctionalChainAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        toRemove.add(inv);
      }
    }
    for (FunctionalChainAbstractCapabilityInvolvement involvement : toRemove) {
      involvement.destroy();
    }
  }

  public FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement(
      AbstractCapability capability, FunctionalChain target) {
    return FunctionalChainExt.createFunctionalChainAbstractCapabilityInvolvement(capability, target);
  }

  public boolean isValidFunctionalChainSelection(EObject context, List<EObject> views) {
    SimpleOrientedGraph<AbstractFunction> graph = new SimpleOrientedGraph<AbstractFunction>();
    if (!views.isEmpty()) {
      for (EObject aSelectedElement : views) {
        if ((aSelectedElement instanceof DEdge) && (((DEdge) aSelectedElement).getTarget() != null)
            && (((DEdge) aSelectedElement).getTarget() instanceof FunctionalExchange)) {
          FunctionalExchange aSelectedExchange = (FunctionalExchange) ((DEdge) aSelectedElement).getTarget();
          AbstractFunction sourceFunction = FunctionExt.getIncomingAbstractFunction(aSelectedExchange);
          AbstractFunction targetFunction = FunctionExt.getOutGoingAbstractFunction(aSelectedExchange);
          graph.addNode(sourceFunction, targetFunction);
        }
      }
      if (!graph.isEmpty()) {
        return graph.isValid();
      }
      return false;
    }
    return false;
  }

  /**
   * Returns the scope used for the selection wizard in the Involve Function tool.
   * 
   * @param container
   *          the container
   * @return the scope used for the selection wizard in the Involve Function tool.
   */
  public List<AbstractFunction> computeFCIFunctionScope(EObject container) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(container);

    if (architecture != null) {
      return FunctionExt.getAllAbstractFunctions(architecture);
    }
    return Collections.emptyList();
  }

  /**
   * Returns the outgoing Function Exchanges for the current node.
   * 
   * @param node
   *          the node
   * @return the outgoing Function Exchanges.
   */
  protected Set<FunctionalExchange> getOutgoingEdgeFunctionalExchanges(DNode node) {

    return DiagramServices.getDiagramServices().getOutgoingEdges(node).stream().map(DEdge::getTarget)
        .filter(FunctionalChainInvolvementLink.class::isInstance)
        .map(link -> ((FunctionalChainInvolvementLink) link).getInvolved()).filter(FunctionalExchange.class::isInstance)
        .map(FunctionalExchange.class::cast).collect(Collectors.toSet());
  }

  /**
   * Returns the ordered scope used for the selection wizard in the Involve Functional Exchange and Function tool.
   * 
   * @param node
   *          the container node
   * @return the ordered scope used for the selection wizard in the Involve Functional Exchange and Function tool.
   */
  public List<FunctionalExchange> computeFCIFunctionalExchangeAndFunctionScope(DNode node) {
    List<FunctionalExchange> availableFunctionExchanges = new ArrayList<>();
    Set<FunctionalExchange> existingInvolvedFunctionalExchanges = getOutgoingEdgeFunctionalExchanges(node);

    FunctionalChainInvolvementFunction selectedFunction = (FunctionalChainInvolvementFunction) node.getTarget();

    for (FunctionalExchange functionalExchange : FunctionalChainExt.getFlatOutgoingExchanges(selectedFunction)) {
      if (!existingInvolvedFunctionalExchanges.contains(functionalExchange)) {
        availableFunctionExchanges.add(functionalExchange);
      }
    }

    return availableFunctionExchanges;
  }

  public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainScope(
      DNode node) {
    HashMapSet<FunctionalExchange, FunctionalChain> set = new HashMapSet<>();
    if ((node == null) || (node.getTarget() == null) || node.getTarget().eIsProxy()) {
      return set;
    }
    EObject target = node.getTarget();
    if (!(target instanceof FunctionalChainInvolvement)) {
      return set;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    if (!(diagram instanceof DSemanticDecorator)) {
      return set;
    }

    Set<FunctionalExchange> existingInvolvedFE = getOutgoingEdgeFunctionalExchanges(node);
    FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) target;
    Collection<FunctionalExchange> outgoing = FunctionalChainExt.getFlatOutgoingExchanges(involvement);
    Collection<FunctionalChain> chains = computeFCIFunctionalChainScope((DSemanticDecorator) diagram);

    for (FunctionalChain chain : chains) {
      Collection<FunctionalExchange> incoming = FunctionalChainExt.getFlatIncomingExchanges(chain);
      incoming.retainAll(outgoing);
      for (FunctionalExchange exchange : incoming) {
        if (!existingInvolvedFE.contains(exchange)) {
          set.put(exchange, chain);
        }
      }
    }

    return set;
  }

  /**
   * @param context
   * @return
   */
  public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainInitialSelection(
      AbstractDNode context) {
    return new HashMapSet<FunctionalExchange, FunctionalChain>();
  }

  /**
   * Precondition for the creation of a Functional Chain Involvement Link.
   * 
   * @param context
   *          the context
   * @param source
   *          the semantic source
   * @param sourceView
   *          the view source
   * @param target
   *          the semantic target
   * @param targetView
   *          the view target
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidFCILink(EObject context, FunctionalChainInvolvementFunction source, EdgeTarget sourceView,
      FunctionalChainInvolvementFunction target, EdgeTarget targetView) {

    // same source and target but not same container
    boolean sourceAndTargetAreSameObject = sourceView != targetView && source.getInvolved() == target.getInvolved();

    if (sourceAndTargetAreSameObject) {
      return true;
    }

    Collection<FunctionalExchange> commonExchanges = getFCDCommonFunctionalExchanges(source, target);

    // common exchanges exists and the new link does not create a cycle
    return !commonExchanges.isEmpty() && !doesConnectionExist(target, source, new HashSet<>());
  }

  /**
   * Computes the Functional Chain Reference hierarchy. The hierarchy is maintained in a bottom to top fashion, meaning
   * that the first element is the immediate Functional Chain Reference, and the last element is the root Functional
   * Chain Reference.
   * 
   * @param view
   *          the selected view.
   * @return the Functional Chain Reference hierarchy.
   */
  public List<FunctionalChainReference> computeFCReferenceHierarchy(EdgeTarget view) {

    List<FunctionalChainReference> referenceHierarchy = new ArrayList<>();
    DSemanticDecorator container = (DSemanticDecorator) view.eContainer();

    while (container != null) {
      EObject target = container.getTarget();

      if (target instanceof FunctionalChainReference) {
        FunctionalChainReference functionalChainReference = (FunctionalChainReference) target;
        referenceHierarchy.add(functionalChainReference);
      }

      container = (DSemanticDecorator) container.eContainer();
    }

    return referenceHierarchy;
  }

  /**
   * Computes the Functional Chain hierarchy. The hierarchy is maintained in a bottom to top fashion, meaning that the
   * first element is the immediate Functional Chain, and the last element is the root Functional Chain.
   * 
   * @param view
   *          the selected view.
   * @return the Functional Chain hierarchy.
   */
  public Set<FunctionalChain> computeFCHierarchy(EdgeTarget view) {
    LinkedHashSet<FunctionalChain> hierarchy = new LinkedHashSet<>();

    DSemanticDecorator container = (DSemanticDecorator) view.eContainer();

    while (container != null) {
      EObject target = container.getTarget();

      if (target instanceof FunctionalChain) {
        FunctionalChain functionalChain = (FunctionalChain) target;
        hierarchy.add(functionalChain);
      }

      container = (DSemanticDecorator) container.eContainer();
    }

    return hierarchy;
  }

  /**
   * Computes the first common Functional Chain, where first means the most immediate Functional Chain.
   * 
   * @param sourceView
   *          the source view
   * @param targetView
   *          the target view
   * @return the first common Functional Chain.
   */
  public FunctionalChain computeFirstCommonFC(EdgeTarget sourceView, EdgeTarget targetView) {

    Set<FunctionalChain> sourceFCHierarchy = computeFCHierarchy(sourceView);
    Set<FunctionalChain> targetFCHierarchy = computeFCHierarchy(targetView);

    // compute common hierarchy
    sourceFCHierarchy.retainAll(targetFCHierarchy);

    if (!sourceFCHierarchy.isEmpty()) {
      return sourceFCHierarchy.iterator().next();
    }

    return null;
  }

  public boolean isValidFCIFunctionalChain(DSemanticDecorator context) {
    return true;
  }

  public boolean isValidFCInvolveFunction(DSemanticDecorator context) {
    return true;
  }

  public boolean isValidFCIFunctionalExchangeAndFunction(FunctionalChainInvolvement involvement) {
    return involvement instanceof FunctionalChainInvolvementFunction;
  }

  /**
   * Returns common functional exchanges between both source and target involvement
   * 
   * @param source
   * @param target
   */
  private Collection<FunctionalExchange> getFCDCommonFunctionalExchanges(FunctionalChainInvolvement source,
      FunctionalChainInvolvement target) {
    return FunctionalChainExt.getFlatCommonFunctionalExchanges(source, target);
  }

  /**
   * Returns the scope used for for the selection wizard in Functional Chain Involvement tools. The scope can either
   * contain a function, or functional exchanges.
   * 
   * @param source
   *          the semantic source
   * @param target
   *          the semantic target.
   * @return the scope used for for the selection wizard in Functional Chain Involvement tools.
   */
  public Collection<EObject> computeFCILinkScope(FunctionalChainInvolvement sourceInvolvement,
      FunctionalChainInvolvement targetInvolvement) {

    Collection<EObject> scope = new ArrayList<>();
    boolean isSameFunctionInvolved = sourceInvolvement.getInvolved() == targetInvolvement.getInvolved();

    if (isSameFunctionInvolved) {
      scope.add(sourceInvolvement.getInvolved());
    } else {
      scope.addAll(getFCDCommonFunctionalExchanges(sourceInvolvement, targetInvolvement));
    }

    return scope;
  }

  /**
   * Returns the scope used for for the selection wizard in the Invoke Function Chain tool. The scope does not contain
   * chains that would form a cycle in regards to the source chain.
   * 
   * @param diagram
   *          the diagram
   * @return the scope used for for the selection wizard in the Invoke Function Chain tool.
   */
  public Collection<FunctionalChain> computeFCIFunctionalChainScope(DSemanticDecorator diagram) {

    EObject sourceChain = diagram.getTarget();
    if (!(sourceChain instanceof FunctionalChain)) {
      return Collections.emptyList();
    }

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(sourceChain);
    if (architecture == null) {
      return Collections.emptyList();
    }

    List<FunctionalChain> possibleChains = FunctionalChainExt.getAllFunctionalChains(architecture);
    List<FunctionalChain> scope = new ArrayList<>();

    // remove possible cycles in regards to the sourceChain
    for (FunctionalChain possibleChain : possibleChains) {
      if (possibleChain.equals(sourceChain)) {
        continue;
      }

      Set<InvolvedElement> childrenChains = FunctionalChainExt
          .getFlatInvolvementsOf(possibleChain, FaPackage.Literals.FUNCTIONAL_CHAIN).stream()
          .map(FunctionalChainInvolvement::getInvolved).collect(Collectors.toSet());

      if (!childrenChains.contains(sourceChain)) {
        scope.add(possibleChain);
      }
    }

    return scope;
  }

  /**
   * Tests if an direct/indirect connection exists between the current involvement and the goal involvement.
   * 
   * @param currentInvolvement
   *          the current involvement being analyzed.
   * @param goalInvolvement
   *          the goal involvement that servers at target goal.
   * @param visitedInvolvements
   *          the already visited involvements.
   * @return true if a direct/indirect connection exists between the current involvement and the goal involvement, false
   *         otherwise.
   */
  public boolean doesConnectionExist(FunctionalChainInvolvement currentInvolvement,
      FunctionalChainInvolvement goalInvolvement, Set<FunctionalChainInvolvement> visitedInvolvements) {

    // avoid infinite loops
    if (visitedInvolvements.contains(currentInvolvement)) {
      return false;
    }

    if (currentInvolvement.equals(goalInvolvement)) {
      return true;
    }

    // create a copy to insure that each recursive call does not contain involvements of other same level calls
    Set<FunctionalChainInvolvement> visitedInvolvementsCopy = new HashSet<>(visitedInvolvements);
    visitedInvolvementsCopy.add(currentInvolvement);

    // depth first recursive call
    for (FunctionalChainInvolvement nextInvolvement : currentInvolvement.getNextFunctionalChainInvolvements()) {
      if (doesConnectionExist(nextInvolvement, goalInvolvement, visitedInvolvementsCopy)) {
        return true;
      }
    }
    return false;
  }

  public MappingHelper getMappingHelper(DSemanticDecorator semanticDecorator) {
    return new MappingHelper(
        SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator.getTarget()));
  }
}
