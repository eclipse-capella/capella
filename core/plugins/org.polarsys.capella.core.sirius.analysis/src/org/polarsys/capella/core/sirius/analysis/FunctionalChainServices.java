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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription;
import org.eclipse.swt.graphics.RGB;

import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

/**
 * Services for functional chain.
 */
public class FunctionalChainServices {

  private static final Integer THICK_BORDER_SOURCE_FUNCTION = new Integer(4);
  private static final Integer THICK_BORDER_TARGET_FUNCTION = new Integer(4);
  private static final Integer THICK_EDGE_FUNCTIONAL_CHAIN = new Integer(4);
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
   * Returns the best function through the elements collection
   */
  public AbstractFunction getBestFunction(AbstractFunction element, Collection<? extends EObject> elements) {
    EObject current = element;
    while (current != null) {
      if (current instanceof AbstractFunction) {
        if (elements.contains(current)) {
          return (AbstractFunction) current;
        }
      }
      current = current.eContainer();
    }
    return null;
  }

  public List<FunctionalChain> getFunctionalChainsToInsert(EObject context, DDiagram currentDiagram, List<FunctionalChain> allFunctionalChains) {
    List<FunctionalChain> returnedList = new ArrayList<FunctionalChain>();
    for (DDiagramElement anObject : DiagramServices.getDiagramServices().getDiagramElements(currentDiagram)) {
      if (anObject.getTarget() instanceof AbstractFunction) {
        AbstractFunction aFunction = (AbstractFunction) anObject.getTarget();
        for (FunctionalChain aFunctionalChain : allFunctionalChains) {
          Collection<EObject> involvedFunctions = FunctionalChainExt.getFlatInvolvedElements(aFunctionalChain, FaPackage.Literals.ABSTRACT_FUNCTION);
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

  public void updateFunctionalChainStyles(DDiagram diagram_p) {
    HashMap<FunctionalChain, DNode> displayedFC = new HashMap<FunctionalChain, DNode>(); // displayed Functional Chains
    HashMap<FunctionalExchange, Set<DEdge>> displayedFE = new HashMap<FunctionalExchange, Set<DEdge>>(); // displayed Functional Exchanges
    HashMap<FunctionalChain, Set<DEdge>> displayedIL = new HashMap<FunctionalChain, Set<DEdge>>(); // displayed Internal Links
    HashMap<AbstractFunction, Set<DDiagramElement>> displayedFunctions = new HashMap<AbstractFunction, Set<DDiagramElement>>(); // displayed functions
    HashMap<DDiagramElement, Set<FunctionalChain>> coloredFunctionNodes = new HashMap<DDiagramElement, Set<FunctionalChain>>(); // colored functions
    Set<FunctionalChain> incompleteFC = new HashSet<FunctionalChain>(); // incomplete displayed functional chains
    HashMap<DEdge, Set<FunctionalChain>> coloredFE = new HashMap<DEdge, Set<FunctionalChain>>(); // colored functional Exchanges
    Set<DEdge> updatedInternalLinks = new HashSet<DEdge>();
    boolean hasResetNode = false;

    // find displayed Functional chains and functions
    for (DNode aNode : diagram_p.getNodes()) {
      if ((aNode.getTarget() != null) && (aNode.getTarget() instanceof FunctionalChain)) {
        displayedFC.put((FunctionalChain) aNode.getTarget(), aNode);
      }
    }
    // find displayed functions
    for (DNode aNode : diagram_p.getNodes()) {
      EObject target = aNode.getTarget();
      if ((target != null) && (aNode.getTarget() instanceof AbstractFunction)) {
        Set<DDiagramElement> set = displayedFunctions.get(target);
        if (set == null) {
          set = new HashSet<DDiagramElement>();
          displayedFunctions.put((AbstractFunction) target, set);
        }
        set.add(aNode);
      }
    }
    for (DDiagramElement aContainer : diagram_p.getContainers()) {
      EObject target = aContainer.getTarget();
      if ((target != null) && (aContainer.getTarget() instanceof AbstractFunction)) {
        Set<DDiagramElement> set = displayedFunctions.get(target);
        if (set == null) {
          set = new HashSet<DDiagramElement>();
          displayedFunctions.put((AbstractFunction) target, set);
        }
        set.add(aContainer);
      }
    }

    // find displayed Functional Exchanges and Internal Links
    for (DEdge anEdge : diagram_p.getEdges()) {
      EObject edgeTarget = anEdge.getTarget();
      if (edgeTarget != null) {
        if (edgeTarget instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) edgeTarget;
          Set<DEdge> edges = displayedFE.get(fe);
          if (edges == null) {
            edges = new HashSet<DEdge>();
            displayedFE.put(fe, edges);
          }
          edges.add(anEdge);
        }
      }
      if (edgeTarget instanceof FunctionalChain) {
        if (!displayedIL.containsKey(anEdge.getTarget())) {
          Set<DEdge> newSet = new HashSet<DEdge>();
          newSet.add(anEdge);
          displayedIL.put((FunctionalChain) anEdge.getTarget(), newSet);
        } else {
          displayedIL.get(anEdge.getTarget()).add(anEdge);
        }
      }
    }

    // find source and target functions that must be colored
    for (Entry<FunctionalChain, DNode> me : displayedFC.entrySet()) {
      Set<DDiagramElement> sourceFunctionNodes = null; // source Node of the functional chain
      Set<DDiagramElement> targetFunctionNodes = null; // target Node of the functional chain

      for (AbstractFunction aSourceFunction : FunctionalChainExt.getFlatFunctionalChainFirstFunctions(me.getKey())) {
        sourceFunctionNodes = getBestDisplayedFunctionNode(aSourceFunction, displayedFunctions);
        if (sourceFunctionNodes != null) {
          for (DDiagramElement sourceFunctionNode : sourceFunctionNodes) {
            if (!coloredFunctionNodes.containsKey(sourceFunctionNode)) {
              Set<FunctionalChain> newSet = new HashSet<FunctionalChain>();
              newSet.add(me.getKey());
              coloredFunctionNodes.put(sourceFunctionNode, newSet);
            } else {
              coloredFunctionNodes.get(sourceFunctionNode).add(me.getKey());
            }
          }
        }
      }
      for (AbstractFunction aTargetFunction : FunctionalChainExt.getFlatFunctionalChainLastFunctions(me.getKey())) {
        targetFunctionNodes = getBestDisplayedFunctionNode(aTargetFunction, displayedFunctions);
        if (targetFunctionNodes != null) {
          for (DDiagramElement targetFunctionNode : targetFunctionNodes) {
            if (!coloredFunctionNodes.containsKey(targetFunctionNode)) {
              Set<FunctionalChain> newSet = new HashSet<FunctionalChain>();
              newSet.add(me.getKey());
              coloredFunctionNodes.put(targetFunctionNode, newSet);
            } else {
              coloredFunctionNodes.get(targetFunctionNode).add(me.getKey());
            }
          }
        }
      }
      for (FunctionalExchange anExchange : FunctionalChainExt.getFlatFunctionalExchanges(me.getKey())) {
        if (displayedFE.containsKey(anExchange)) {
          Set<DEdge> exchangeEdges = displayedFE.get(anExchange);
          for (DEdge exchangeEdge : exchangeEdges) {
            if (!coloredFE.containsKey(exchangeEdge)) {
              Set<FunctionalChain> newSet = new HashSet<FunctionalChain>();
              newSet.add(me.getKey());
              coloredFE.put(exchangeEdge, newSet);
            } else {
              coloredFE.get(exchangeEdge).add(me.getKey());
            }
          }
        }
      }
    }

    // remove internal Links if the Functional chain is not displayed
    for (Entry<FunctionalChain, Set<DEdge>> me : displayedIL.entrySet()) {
      if (!displayedFC.containsKey(me.getKey())) {
        for (DEdge anEdge : me.getValue()) {
          DiagramServices.getDiagramServices().removeEdgeView(anEdge);
          hasResetNode = true;
        }
      }
    }

    // update functions style
    for (Entry<AbstractFunction, Set<DDiagramElement>> me : displayedFunctions.entrySet()) {
      Set<DDiagramElement> functionNodes = me.getValue();
      for (DDiagramElement functionNode : functionNodes) {
        if (!coloredFunctionNodes.containsKey(functionNode)) {
          if (resetFunctionStyle(functionNode)) {
            hasResetNode = true;
          }
        }
      }
    }

    // customize source and target function styles
    for (Entry<FunctionalChain, DNode> me : displayedFC.entrySet()) {

      updateFunctionalChainNodeColor(me.getValue(), displayedFC.values());
      RGBValues color = ShapeUtil.getNodeColorStyle(me.getValue());
      if (color == null) {
        continue;
      }
      Set<DDiagramElement> sourceFunctionNodes = null; // source Node of the functional chain
      Set<DDiagramElement> targetFunctionNodes = null; // target Node of the functional chain

      // customize source function of the chain
      for (AbstractFunction aSourceFunction : FunctionalChainExt.getFlatFunctionalChainFirstFunctions(me.getKey())) {
        sourceFunctionNodes = getBestDisplayedFunctionNode(aSourceFunction, displayedFunctions);
        if (sourceFunctionNodes != null) {
          for (DDiagramElement sourceFunctionNode : sourceFunctionNodes) {
            if ((color == null) || (coloredFunctionNodes.get(sourceFunctionNode).size() == 1)) {
              customizeSourceFunctionStyle(sourceFunctionNode, color);
              // color the border of the source function with the color of the functional chain
            } else {
              // color source function in red
              customizeSourceFunctionStyle(sourceFunctionNode, ShapeUtil.getBlackColor());
            }
          }
        }
      }

      // customize target function of the chain
      for (AbstractFunction aTargetFunction : FunctionalChainExt.getFlatFunctionalChainLastFunctions(me.getKey())) {
        targetFunctionNodes = getBestDisplayedFunctionNode(aTargetFunction, displayedFunctions);
        if (targetFunctionNodes != null) {
          for (DDiagramElement targetFunctionNode : targetFunctionNodes) {
            if (coloredFunctionNodes.get(targetFunctionNode).size() == 1) {
              // color the border of the target function with the color of the functional chain
              customizeTargetFunctionStyle(targetFunctionNode, color);
            } else {
              // color target function in red
              customizeTargetFunctionStyle(targetFunctionNode, ShapeUtil.getBlackColor());
            }
          }
        }
      }

      boolean isInOperationalAnalysis =
          BlockArchitectureExt.getRootBlockArchitecture(((DSemanticDiagram) diagram_p).getTarget()) instanceof OperationalAnalysis;
      // customize internal links (except in operational analysis architecture)
      Set<DEdge> internalLinks = new HashSet<DEdge>();
      if (!isInOperationalAnalysis) {
        internalLinks = updateInternalLinks(me.getKey(), displayedFE, displayedIL, color);
        updatedInternalLinks.addAll(internalLinks);
      }

      // customize functional exchanges
      for (FunctionalExchange anExchange : FunctionalChainExt.getFlatFunctionalExchanges(me.getKey())) {
        if (displayedFE.containsKey(anExchange)) {
          Set<DEdge> currentEdges = displayedFE.get(anExchange);
          for (DEdge currentEdge : currentEdges) {
            if ((coloredFE.get(currentEdge).size() == 1)) {
              customizeFunctionalExchangeEdgeStyle(currentEdge, color);
            } else {
              customizeFunctionalExchangeEdgeStyle(currentEdge, ShapeUtil.getBlackColor());
            }
          }
        } else {
          incompleteFC.add(me.getKey());
        }
      }
      me.getValue().refresh();
    }

    // destroy old internal links
    for (Set<DEdge> anInternalLinkSet : displayedIL.values()) {
      for (DEdge anInternalLink : anInternalLinkSet) {
        if (!updatedInternalLinks.contains(anInternalLink)) {
          DiagramServices.getDiagramServices().removeEdgeView(anInternalLink);
          hasResetNode = true;
        }
      }
    }

    // reset functional exchanges with no functional chain
    for (Set<DEdge> aFEs : displayedFE.values()) {
      for (DEdge aFE : aFEs) {
        if (!coloredFE.containsKey(aFE)) {
          resetFunctionalExchangeStyle(aFE);
        }
      }
    }
  }

  /**
   * @param currentSourceNode_p
   * @return
   */
  private boolean isValidNodeForInternalLink(EdgeTarget currentNode_p) {
    if (null == currentNode_p) {
      return false;
    }
    if (!(currentNode_p instanceof DNode)) {
      return false;
    }
    if (!DiagramServices.getDiagramServices().isABorderedNode((DNode) currentNode_p)) {
      return false;
    }
    return true;
  }

  protected Set<DEdge> updateInternalLinks(FunctionalChain fc_p, HashMap<FunctionalExchange, Set<DEdge>> displayedFunctionalExchanges_p,
      HashMap<FunctionalChain, Set<DEdge>> displayedIL_p, RGBValues color_p) {
    Set<DEdge> internalLinks = new HashSet<DEdge>();

    // iterate over involved functional exchange
    for (FunctionalChainInvolvement anInvolvement : FunctionalChainExt.getFlatInvolvementsOf(fc_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      FunctionalExchange currentExchange = (FunctionalExchange) anInvolvement.getInvolved();
      if (!displayedFunctionalExchanges_p.containsKey(currentExchange)) {
        continue;
      }

      Set<DEdge> currentEdges = displayedFunctionalExchanges_p.get(currentExchange);
      for (DEdge currentEdge : currentEdges) {
        EdgeTarget currentSourceNode = currentEdge.getSourceNode();
        EdgeTarget currentTargetNode = currentEdge.getTargetNode();
        if ((currentEdge != null) && isValidNodeForInternalLink(currentSourceNode)) {

          Collection<FunctionalExchange> previousExchanges = getFlatPreviousFunctionalExchanges(fc_p, anInvolvement);
          Collection<FunctionalExchange> nextExchanges = getFlatNextFunctionalExchanges(fc_p, anInvolvement);

          // Display an internal link from previousExchange.target to exchange.source
          for (FunctionalExchange elt : previousExchanges) {
            if (displayedFunctionalExchanges_p.containsKey(elt)) {
              Set<DEdge> edges = displayedFunctionalExchanges_p.get(elt);
              for (DEdge edge : edges) {
                if ((edge != null) && isValidNodeForInternalLink(edge.getTargetNode())
                    && isValidInternalLinkEdge(fc_p, edge.getTargetNode(), currentSourceNode)) {
                  internalLinks.add(retrieveInternalLink(edge.getTargetNode(), currentSourceNode, fc_p, color_p));
                }
              }
            }
          }

          // Display an internal link from exchange.target to nextExchange.source
          for (FunctionalExchange elt : nextExchanges) {
            if (displayedFunctionalExchanges_p.containsKey(elt)) {
              Set<DEdge> edges = displayedFunctionalExchanges_p.get(elt);
              for (DEdge edge : edges) {
                if ((edge != null) && isValidNodeForInternalLink(edge.getSourceNode())
                    && isValidInternalLinkEdge(fc_p, currentTargetNode, edge.getSourceNode())) {
                  internalLinks.add(retrieveInternalLink(currentTargetNode, edge.getSourceNode(), fc_p, color_p));
                }
              }
            }
          }
        }
      }
    }

    if (internalLinks.contains(null)) {
      internalLinks.remove(null);
    }
    return internalLinks;
  }

  /**
   * returns whether an internal link can be created between given both bordered nodes
   * @param currentSourceNode_p
   * @param currentTargetNode_p
   * @return
   */
  public boolean isValidInternalLinkEdge(FunctionalChain chain_p, EdgeTarget currentSourceNode_p, EdgeTarget currentTargetNode_p) {
    if (currentSourceNode_p == null) {
      return false;
    }
    if (currentTargetNode_p == null) {
      return false;
    }

    EObject sourceParent = currentSourceNode_p.eContainer();
    EObject targetParent = currentTargetNode_p.eContainer();
    // Allow internal links only on same parent (it is correct?)
    if ((sourceParent != null) && (targetParent != null)) {
      return sourceParent.equals(targetParent);
    }
    return false;
  }

  /**
   * Create or return an internal link between both nodes.
   */
  protected DEdge retrieveInternalLink(EdgeTarget sourceNode_p, EdgeTarget targetNode_p, FunctionalChain fc_p, RGBValues color_p) {
    HashMap<EdgeTarget, DEdge> outgoingEdges = new HashMap<EdgeTarget, DEdge>(); // outgoing Edges with targetNode as key

    // find displayed internal links
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceNode_p)) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalChain) && (anEdge.getTarget().equals(fc_p))) {
        outgoingEdges.put(anEdge.getTargetNode(), anEdge);
      }
    }

    DEdge internalLink = outgoingEdges.get(targetNode_p);
    if (internalLink == null) {
      internalLink = createInternalLink(sourceNode_p, targetNode_p, fc_p, color_p);
    }
    if (internalLink != null) {
      customizeInternalLinksEdgeStyle(internalLink, color_p);
    }
    return internalLink;
  }

  /**
   * @param fc_p
   * @return
   */
  protected Collection<FunctionalExchange> getFlatPreviousFunctionalExchanges(FunctionalChain fc_p, FunctionalChainInvolvement fci_p) {
    Collection<FunctionalExchange> result = new HashSet<FunctionalExchange>();

    for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatPreviousExchangeInvolvements(fci_p)) {
      if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof FunctionalExchange)) {
        result.add((FunctionalExchange) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param fc_p
   * @return
   */
  protected Collection<FunctionalExchange> getFlatNextFunctionalExchanges(FunctionalChain fc_p, FunctionalChainInvolvement fci_p) {
    Collection<FunctionalExchange> result = new HashSet<FunctionalExchange>();

    for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatNextExchangeInvolvements(fci_p)) {
      if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof FunctionalExchange)) {
        result.add((FunctionalExchange) involvment.getInvolved());
      }
    }

    return result;
  }

  /**
   * @param sourceNode_p
   * @param targetNode_p
   * @param color_p
   * @return
   */
  public DEdge createInternalLink(EdgeTarget sourceNode_p, EdgeTarget targetNode_p, FunctionalChain fc_p, RGBValues color_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode_p);
    EdgeMapping mapping = getInternLinkEdgeMapping(diagram);
    DEdge newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode_p, targetNode_p, fc_p, mapping);
    if (newEdge == null) {
      DiagramServices.getDiagramServices().createEdge(mapping, sourceNode_p, targetNode_p, fc_p);
      newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode_p, targetNode_p, fc_p, mapping);
    }
    return newEdge;
  }

  public EdgeMapping getInternLinkEdgeMapping(DDiagram diagram_p) {
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.LDFB_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CLDF_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.LAB_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.PDFB_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CPDF_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.PAB_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.SDFB_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.CSDF_INTERNAL_LINK_MAPPING_NAME);
    }
    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, IMappingNameConstants.SAB_INTERNAL_LINK_MAPPING_NAME);
    }
    return null;
  }

  /**
   * @param aEdge_p
   */
  public void resetFunctionalExchangeStyle(DEdge aEdge_p) {
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(aEdge_p);
    if (mapping != null) {
      // get default style size of an edge
      EdgeStyleDescription desc =
          (EdgeStyleDescription) getMappingHelper(aEdge_p).getBestStyleDescription(mapping, aEdge_p.getTarget(), aEdge_p, aEdge_p.eContainer(),
              CapellaServices.getService().getDiagramContainer(aEdge_p));
      String defaultStyleSize = desc.getSizeComputationExpression();
      // get current style size of an edge
      EdgeStyle edgeStyle = aEdge_p.getOwnedStyle();
      Integer currentSize = edgeStyle.getSize();

      if ((null != currentSize) && (null != defaultStyleSize)) {
        // apply style & color : if currentSize is equal to default size + if current size is equal to default size of Functional Chain
        if (currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN) || currentSize.equals(defaultStyleSize)) {
          if (ShapeUtil.resetEdgeThickStyle(aEdge_p, new Integer(defaultStyleSize))) {
            ShapeUtil.resetEdgeColorStyle(aEdge_p, ShapeUtil.getDefaultColor(aEdge_p, desc, desc.getStrokeColor()));
          }
        }
      }
    }
  }

  /**
   * @param value_p
   * @param displayedFC_p
   */
  public boolean resetFunctionStyle(DDiagramElement functionNode_p) {
    boolean result = false;

    if (functionNode_p instanceof AbstractDNode) {
      AbstractDNode node = (AbstractDNode) functionNode_p;
      BorderedStyleDescription desc =
          (BorderedStyleDescription) getMappingHelper(functionNode_p).getBestStyleDescription(functionNode_p.getDiagramElementMapping(),
              functionNode_p.getTarget(), functionNode_p, functionNode_p.eContainer(), CapellaServices.getService().getDiagramContainer(functionNode_p));

      String defaultStyleSize = desc.getBorderSizeComputationExpression();
      BorderedStyle style = (BorderedStyle) ShapeUtil.getCurrentStyle(node);
      Integer currentSize = style.getBorderSize();

      if ((null != currentSize) && (null != defaultStyleSize)) {
        // apply style & color : if currentSize is equal to default size + if current size is equal to default size of Functional Chain
        if (currentSize.equals(THICK_BORDER_SOURCE_FUNCTION) || currentSize.equals(THICK_BORDER_TARGET_FUNCTION) || currentSize.equals(defaultStyleSize)) {
          if (ShapeUtil.resetBorderStyle(node, new Integer(desc.getBorderSizeComputationExpression()))) {
            if (ShapeUtil.resetBorderColorStyle(node, ShapeUtil.getDefaultColor(node, desc, desc.getBorderColor()))) {
              result = true;
            }
          }
        }
      }

    }

    return result;
  }

  public void customizeFunctionalExchangeEdgeStyle(DEdge edge_p, RGBValues color) {
    // get current style size of an edge
    EdgeStyle edgeStyle = edge_p.getOwnedStyle();
    Integer currentSize = edgeStyle.getSize();

    // get default style size of an edge
    DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(edge_p);
    if (mapping != null) {
      EdgeStyleDescription desc =
          (EdgeStyleDescription) getMappingHelper(edge_p).getBestStyleDescription(mapping, edge_p.getTarget(), edge_p, edge_p.eContainer(),
              CapellaServices.getService().getDiagramContainer(edge_p));
      if (null != desc) {
        // assuming it is an integer value
        String defaultSize = desc.getSizeComputationExpression();
        if ((null != defaultSize) && (null != currentSize)
            && (currentSize.equals(Integer.valueOf(defaultSize)) || (currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN)))) {
          // apply change
          customizeEdgeStyle(edge_p, color);
        }
      }
    }
  }

  public void customizeInternalLinksEdgeStyle(DEdge edge_p, RGBValues color) {
    customizeEdgeStyle(edge_p, color);
  }

  public void customizeEdgeStyle(DEdge edge_p, RGBValues color) {
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    ShapeUtil.setEdgeColorStyle(edge_p, rgbColor);
    ShapeUtil.setEdgeThickStyle(edge_p, THICK_EDGE_FUNCTIONAL_CHAIN);
  }

  public void customizeFunctionStyle(DDiagramElement functionNode_p, RGBValues color) {
    // Change color border style
    RGB rgbColor = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    if (functionNode_p instanceof AbstractDNode) {
      ShapeUtil.setBorderColorStyle(((AbstractDNode) functionNode_p), rgbColor);
    }
  }

  public void customizeSourceFunctionStyle(DDiagramElement functionNode_p, RGBValues color) {
    customizeFunctionStyle(functionNode_p, color);
    if (functionNode_p instanceof AbstractDNode) {
      ShapeUtil.setBorderStyle(((AbstractDNode) functionNode_p), THICK_BORDER_SOURCE_FUNCTION);
    }
  }

  public void customizeTargetFunctionStyle(DDiagramElement functionNode_p, RGBValues color) {
    customizeFunctionStyle(functionNode_p, color);
    if (functionNode_p instanceof AbstractDNode) {
      ShapeUtil.setBorderStyle(((AbstractDNode) functionNode_p), THICK_BORDER_TARGET_FUNCTION);
    }
  }

  /**
   * @param function_p
   * @param displayedFunctions_p
   * @return the function or one of its container contained in the map keys
   */
  public Set<DDiagramElement> getBestDisplayedFunctionNode(AbstractFunction function_p, HashMap<AbstractFunction, Set<DDiagramElement>> displayedFunctions_p) {
    if (displayedFunctions_p.containsKey(function_p)) {
      return displayedFunctions_p.get(function_p);
    }
    EObject ancestor = function_p.eContainer();
    while ((ancestor != null) && (ancestor instanceof AbstractFunction)) {
      if (displayedFunctions_p.containsKey(ancestor)) {
        return displayedFunctions_p.get(ancestor);
      }
      ancestor = ancestor.eContainer();
    }
    return null;
  }

  public boolean isCompleteFunctionalChain(FunctionalChain fc_p, DDiagram diagram_p) {
    Set<FunctionalExchange> visibleFE = new HashSet<FunctionalExchange>();
    for (DEdge anEdge : diagram_p.getEdges()) {
      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalExchange)) {
        visibleFE.add((FunctionalExchange) anEdge.getTarget());
      }
    }

    for (FunctionalExchange anElement : FunctionalChainExt.getFlatFunctionalExchanges(fc_p)) {
      if (!(visibleFE.contains(anElement))) {
        return false;
      }
    }
    return true;
  }

  public String getFunctionalChainLabel(FunctionalChain fc_p, DDiagram diagram_p) {
    String label = fc_p.getName();

    boolean isComplete = isCompleteFunctionalChain(fc_p, diagram_p);
    boolean isValid = FunctionalChainExt.isFunctionalChainValid(fc_p);
    if (!isComplete || !isValid) {
      label = label + " ("; //$NON-NLS-1$
    }
    if (!isComplete) {
      label = label + INCOMPLETE_FUNCTIONAL_CHAIN_LABEL;
    }
    if (!isComplete && !isValid) {
      label = label + ", "; //$NON-NLS-1$
    }
    if (!isValid) {
      label = label + INVALID_FUNCTIONAL_CHAIN_LABEL;
    }
    if (!isComplete || !isValid) {
      label = label + ")"; //$NON-NLS-1$
    }
    return label;
  }

  public void updateFunctionalChainNodeColor(DNode fcNode_p, Collection<DNode> visibleFunctionalChains_p) {
    RGBValues color = ShapeUtil.getNodeColorStyle(fcNode_p);
    LinkedList<RGB> colorList = new LinkedList<RGB>();

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
    for (DNode aFc : visibleFunctionalChains_p) {
      if (!aFc.equals(fcNode_p)) {
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
      ShapeUtil.setColorStyle(fcNode_p, colorList.get(0));
    }
  }

  public List<FunctionalExchange> getAvailableExchanges(EObject context_p, AbstractFunction source, AbstractFunction target) {
    List<FunctionalExchange> returnedFunctionalExchanges = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> incoming = FunctionExt.getIncomingExchange(target);
    List<FunctionalExchange> outgoing = FunctionExt.getOutGoingExchange(source);
    for (FunctionalExchange aFunctionalExchange : incoming) {
      if (outgoing.contains(aFunctionalExchange)) {
        returnedFunctionalExchanges.add(aFunctionalExchange);
      }
    }
    return returnedFunctionalExchanges;
  }

  public List<FunctionalChainInvolvement> getPreviousInvolvements(FunctionalChainInvolvement involvement_p) {
    return involvement_p.getPreviousFunctionalChainInvolvements();
  }

  public EObject createFunctionalChain(EObject context_p, List<EObject> views) {
    if (!views.isEmpty()) {
      List<EObject> newList = new ArrayList<EObject>();
      AbstractFunction aFunction = null;
      for (EObject aSelectedElement : views) {
        if ((aSelectedElement instanceof DDiagramElement) && (((DDiagramElement) aSelectedElement).getTarget() != null)) {
          newList.add(((DDiagramElement) aSelectedElement).getTarget());
          if ((aFunction == null) && (((DDiagramElement) aSelectedElement).getTarget() instanceof FunctionalExchange)) {
            AbstractFunction aSourceFunction = FunctionExt.getIncomingAbstractFunction(((FunctionalExchange) ((DDiagramElement) aSelectedElement).getTarget()));
            aFunction = FunctionExt.getRootFunction(aSourceFunction);
          }
        }
      }
      if (aFunction != null) {

        EObject diagramContainer = ((DSemanticDiagram) CapellaServices.getService().getDiagramContainer(views.get(0))).getTarget();
        FunctionalChain newFC;
        if (diagramContainer instanceof AbstractFunctionalChainContainer) {
          newFC = FunctionalChainExt.createFunctionalChain((AbstractFunctionalChainContainer) diagramContainer, newList);
        } else {
          newFC = FunctionalChainExt.createFunctionalChain(aFunction, newList);
        }
        return newFC;
      }
    }
    return context_p;
  }

  public void removeFunctionalChainAbstractCapabilityInvolvement(AbstractCapability capability, EObject target) {
    Set<FunctionalChainAbstractCapabilityInvolvement> toRemove = new HashSet<FunctionalChainAbstractCapabilityInvolvement>();
    for (FunctionalChainAbstractCapabilityInvolvement inv : capability.getOwnedFunctionalChainAbstractCapabilityInvolvements()) {
      if (inv.getInvolved().equals(target)) {
        toRemove.add(inv);
      }
    }
    for (FunctionalChainAbstractCapabilityInvolvement involvement : toRemove) {
      involvement.destroy();
    }
  }

  public FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement(AbstractCapability capability_p,
      FunctionalChain target_p) {
    return FunctionalChainExt.createFunctionalChainAbstractCapabilityInvolvement(capability_p, target_p);
  }

  public boolean isValidFunctionalChainSelection(EObject context_p, List<EObject> views_p) {
    SimpleOrientedGraph<AbstractFunction> graph = new SimpleOrientedGraph<AbstractFunction>();
    if (!views_p.isEmpty()) {
      for (EObject aSelectedElement : views_p) {
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

  public List<AbstractFunction> getFCDInvolveFunctionScope(EObject container_p) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(container_p);
    return FunctionExt.getAllAbstractFunctions(architecture);
  }

  protected List<FunctionalExchange> getOutgoingEdgeFunctionalExchanges(DNode node_p) {
    List<FunctionalExchange> existingInvolvedFE = new ArrayList<FunctionalExchange>();
    for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node_p)) {

      if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalChainInvolvement)) {
        FunctionalChainInvolvement currentInv = (FunctionalChainInvolvement) anEdge.getTarget();
        if ((currentInv.getInvolved() != null) && (currentInv.getInvolved() instanceof FunctionalExchange)) {
          existingInvolvedFE.add((FunctionalExchange) currentInv.getInvolved());
        }
      }
    }
    return existingInvolvedFE;
  }

  public List<FunctionalExchange> getFCDInvolveFunctionalExchangeAndFunctionScope(DNode node_p) {
    List<FunctionalExchange> returnedList = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> existingInvolvedFE = getOutgoingEdgeFunctionalExchanges(node_p);

    FunctionalChainInvolvement selectedInvolvement = (FunctionalChainInvolvement) node_p.getTarget();
    for (FunctionalExchange aFE : FunctionalChainExt.getFlatOutgoingExchanges(selectedInvolvement)) {
      if (!existingInvolvedFE.contains(aFE)) {
        returnedList.add(aFE);
      }
    }

    return returnedList;
  }

  public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainScope(DNode node_p) {
    HashMapSet<FunctionalExchange, FunctionalChain> set = new HashMapSet<FunctionalExchange, FunctionalChain>();
    if ((node_p == null) || (node_p.getTarget() == null) || node_p.getTarget().eIsProxy()) {
      return set;
    }
    EObject target = node_p.getTarget();
    if (!(target instanceof FunctionalChainInvolvement)) {
      return set;
    }

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node_p);
    if (!(diagram instanceof DSemanticDecorator)) {
      return set;
    }

    List<FunctionalExchange> existingInvolvedFE = getOutgoingEdgeFunctionalExchanges(node_p);
    FunctionalChainInvolvement involvement = (FunctionalChainInvolvement) target;
    Collection<FunctionalExchange> outgoing = FunctionalChainExt.getFlatOutgoingExchanges(involvement);
    Collection<FunctionalChain> chains = getFCDInvolveFunctionalChainScope((DSemanticDecorator) diagram);

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
   * @param context_p
   * @return
   */
  public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainInitialSelection(AbstractDNode context_p) {
    return new HashMapSet<FunctionalExchange, FunctionalChain>();
  }

  /**
   * @param context_p
   * @param hashMapSet_p
   * @param hashMapSet2_p
   * @param hashMapSet3_p
   */
  public void involvedFCDFunctionalExchangeFunctionalChain(AbstractDNode context_p, HashMapSet<FunctionalExchange, FunctionalChain> scope_p,
      HashMapSet<FunctionalExchange, FunctionalChain> initialSelection_p, HashMapSet<FunctionalExchange, FunctionalChain> selection_p) {

    if ((context_p == null)) {
      return;
    }
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(context_p);
    if ((diagram == null) || !(diagram instanceof DSemanticDecorator)) {
      return;
    }
    FunctionalChain sourceFC = (FunctionalChain) ((DSemanticDecorator) diagram).getTarget();
    EObject target = context_p.getTarget();
    if ((target == null) || target.eIsProxy() || !(target instanceof FunctionalChainInvolvement)) {
      return;
    }

    FunctionalChainInvolvement iSource = (FunctionalChainInvolvement) ((DSemanticDecorator) context_p).getTarget();

    for (FunctionalExchange exchange : selection_p.keySet()) {
      for (FunctionalChain chain : selection_p.get(exchange)) {
        FunctionalChainInvolvement iExchange = FaFactory.eINSTANCE.createFunctionalChainInvolvement();
        iExchange.setInvolved(exchange);
        iExchange.setInvolver(sourceFC);
        sourceFC.getOwnedFunctionalChainInvolvements().add(iExchange);
        iSource.getNextFunctionalChainInvolvements().add(iExchange);

        FunctionalChainReference iChain = FaFactory.eINSTANCE.createFunctionalChainReference();
        iChain.setInvolved(chain);
        iChain.setInvolver(sourceFC);
        sourceFC.getOwnedFunctionalChainInvolvements().add(iChain);
        iExchange.getNextFunctionalChainInvolvements().add(iChain);

        AbstractNodeMapping nodeMapping =
            DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, IMappingNameConstants.FCD_FUNCTIONAL_CHAIN__MAPPING_NAME);
        if (nodeMapping == null) {
          return;
        }
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(nodeMapping, iChain, diagram, diagram);
        if (node == null) {
          return;
        }
        EdgeMapping edgeMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram, IMappingNameConstants.FCD_FUNCTIONAL_EXCHANGE__MAPPING_NAME);
        if (edgeMapping == null) {
          return;
        }
        DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) context_p, (EdgeTarget) node, iExchange);
      }
    }
  }

  @Deprecated
  public List<FunctionalExchange> getAvailableFunctionalExchangeToInsertInFCD(DNode node_p) {
    return getFCDInvolveFunctionalExchangeAndFunctionScope(node_p);
  }

  public boolean isValidFCDInvolveFunctionalExchange(EObject context_p, FunctionalChainInvolvement source_p, FunctionalChainInvolvement target_p) {
    Collection<FunctionalExchange> commonExchanges = getFCDCommonFunctionalExchanges(source_p, target_p);
    boolean result = !commonExchanges.isEmpty();
    return (result && !findInvolvementInNext(target_p, source_p, new HashSet<FunctionalChainInvolvement>()));
  }

  public boolean isValidFCDInvolveFunctionalChain(DSemanticDecorator context_p) {
    return true;
  }

  public boolean isValidFCDInvolveFunction(DSemanticDecorator context_p) {
    return true;
  }

  public boolean isValidFCDInvolveFunctionalExchangeAndFunctionalChain(DSemanticDecorator context_p) {
    if ((context_p == null) || (context_p instanceof DDiagram)) {
      return false;
    }
    return true;
  }

  public boolean isValidFCDInvolveFunctionalExchangeAndFunction(DSemanticDecorator context_p) {
    if ((context_p == null) || (context_p instanceof DDiagram)) {
      return false;
    }
    return true;
  }

  @Deprecated
  public boolean isAnEdgeInvolvementAvailableInFCD(final EObject context_p, final FunctionalChainInvolvement source_p, final FunctionalChainInvolvement target_p) {
    return isValidFCDInvolveFunctionalExchange(context_p, source_p, target_p);
  }

  /**
   * Returns common functional exchanges between both source and target involvement
   * @param source_p
   * @param target_p
   */
  private Collection<FunctionalExchange> getFCDCommonFunctionalExchanges(FunctionalChainInvolvement source_p, FunctionalChainInvolvement target_p) {
    return FunctionalChainExt.getFlatCommonFunctionalExchanges(source_p, target_p);
  }

  public Collection<FunctionalExchange> getFCDInvolveFunctionalExchangeScope(EObject context_p, EObject source_p, EObject target_p) {
    Collection<FunctionalExchange> commonExchanges =
        getFCDCommonFunctionalExchanges((FunctionalChainInvolvement) source_p, (FunctionalChainInvolvement) target_p);
    return commonExchanges;
  }

  public Collection<FunctionalChain> getFCDInvolveFunctionalChainScope(DSemanticDecorator diagram_p) {
    EObject chain = diagram_p.getTarget();
    if (!(chain instanceof FunctionalChain)) {
      return Collections.emptyList();
    }

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(chain);

    // remove all chains owning the current chain
    List<FunctionalChain> chains = FunctionalChainExt.getAllFunctionalChains(architecture);
    List<FunctionalChain> result = new LinkedList<FunctionalChain>();

    for (FunctionalChain definedChain : chains) {
      boolean toAdd = true;
      if (definedChain.equals(chain)) {
        toAdd = false;
      }
      if (toAdd) {
        for (FunctionalChainInvolvement involvement : FunctionalChainExt.getFlatInvolvementsOf(definedChain, FaPackage.Literals.FUNCTIONAL_CHAIN)) {
          if (chain.equals(involvement.getInvolved())) {
            toAdd = false;
            break;
          }
        }
      }
      if (toAdd) {
        result.add(definedChain);
      }
    }
    return result;
  }

  public boolean findInvolvementInNext(final FunctionalChainInvolvement currentInvolvement_p, final FunctionalChainInvolvement involvementToFind_p,
      final Set<FunctionalChainInvolvement> visitedInvolvements_p) {
    if (visitedInvolvements_p.contains(currentInvolvement_p)) {
      // to avoid infinite loop (cycle)
      return false;
    }
    if (currentInvolvement_p.equals(involvementToFind_p)) {
      return true;
    }
    Set<FunctionalChainInvolvement> visitedInvolvements = new HashSet<FunctionalChainInvolvement>(visitedInvolvements_p);
    visitedInvolvements.add(currentInvolvement_p);
    for (FunctionalChainInvolvement aNext : currentInvolvement_p.getNextFunctionalChainInvolvements()) {
      if (findInvolvementInNext(aNext, involvementToFind_p, visitedInvolvements)) {
        return true;
      }
    }
    return false;
  }

  public MappingHelper getMappingHelper(DSemanticDecorator semanticDecorator_p) {
    return new MappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator_p.getTarget()));
  }

}
