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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.ControlNodeKind;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.SequenceLinkEndExt;
import org.polarsys.capella.core.model.helpers.SequenceLinkExt;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementNode;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog.NewFEData;
import org.polarsys.capella.core.sirius.analysis.cache.DEdgeIconCache;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.helpers.FunctionalChainReferenceHierarchyHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.DiagramProcessChainPathPreferencePage;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * Services for functional chain.
 */
public class FunctionalChainServices {

  public static final Integer THICK_BORDER_SOURCE_FUNCTION = Integer.valueOf(4);
  public static final Integer THICK_BORDER_TARGET_FUNCTION = Integer.valueOf(4);
  public static final Integer THICK_EDGE_FUNCTIONAL_CHAIN = Integer.valueOf(4);
  public static final String INCOMPLETE_FUNCTIONAL_CHAIN_LABEL = "incomplete"; //$NON-NLS-1$
  public static final String INVALID_FUNCTIONAL_CHAIN_LABEL = "invalid"; //$NON-NLS-1$
  public static final String FCR_CONTAINER_COLLAPSED_INDICATOR = " [+]"; //$NON-NLS-1$
  public static final String IT = "IT"; //$NON-NLS-1$

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
        if (referencedChain != null) {
          getAllFunctionalChainInvolvementLinks(referencedChain, linksAcumulator);
        }
      }
    }
  }

  /**
   * Returns all semantics of source views for the given chain
   */
  public Collection<EObject> getFunctionalChainSources(FunctionalChain chain) {
    InvolvementGraph g = FunctionalChainCache.getInstance().getInvolvementGraph(chain);
    InternalLinksGraph linksGraph = FunctionalChainCache.getInstance().getInternalLinksGraph(g);
    return linksGraph.getEdges().values().stream().map(e -> e.getSource().getSemantic()).collect(Collectors.toSet());
  }

  /**
   * Returns all semantics of target views for the given chain
   */
  public Collection<EObject> getFunctionalChainTargets(FunctionalChain chain) {
    InvolvementGraph g = FunctionalChainCache.getInstance().getInvolvementGraph(chain);
    InternalLinksGraph linksGraph = FunctionalChainCache.getInstance().getInternalLinksGraph(g);
    return linksGraph.getEdges().values().stream().map(e -> e.getTarget().getSemantic()).collect(Collectors.toSet());
  }

  /**
   * Returns all semantics of source views for the given chain
   */
  public Collection<EObject> getFunctionalChainsToDisplays(EObject chain, DSemanticDiagram diagram) {
    HashMap<FunctionalChain, DNode> functionalChains = getDisplayedFunctionalChains(diagram);
    return (Collection) functionalChains.keySet();
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
   * Update the style for functional chains. Colored border, highlight edges.
   * 
   * @param diagram
   */
  public void updateFunctionalChainStyles(DDiagram diagram) {

    HashMap<FunctionalChain, DNode> functionalChains = getDisplayedFunctionalChains(diagram);
    HashMapSet<DSemanticDecorator, RGBValues> colors = new HashMapSet<>();
    Map<DEdge, Set<FunctionalChain>> coloredLinks = new HashMap<>();

    Collection<DEdge> internalLinks = new ArrayList<DEdge>();

    for (Entry<FunctionalChain, DNode> entry : functionalChains.entrySet()) {
      FunctionalChain chain = entry.getKey();
      DNode chainView = entry.getValue();
      updateFunctionalChainNodeColor(chainView, functionalChains.values());

      InvolvementGraph graph = FunctionalChainCache.getInstance().getInvolvementGraph(chain);

      RGBValues color = ShapeUtil.getNodeColorStyle(chainView);
      if (color == null) {
        continue;
      }

      // Add color for starting and ending functions and control nodes
      for (InvolvementNode node : graph.getNodes().values()) {
        if (graph.isInvolvingFunction(node) && (graph.isStartingFunction(node) || graph.isEndingFunction(node))) {
          AbstractFunction function = graph.getInvolvedFunction(node);
          Collection<DSemanticDecorator> views = getBestDisplayedFunctionNode(function, diagram);
          for (DSemanticDecorator view : views) {
            colors.put(view, color);
          }
        }
      }

      // Add color for related functional exchanges and sequence links
      for (InvolvementEdge edge : graph.getEdges().values()) {
        EObject semantic = null;

        if (graph.isInvolvingFunctionalExchange(edge)) {
          semantic = graph.getInvolvedFunctionalExchange(edge);
        } else if (graph.isSequenceLink(edge)) {
          semantic = graph.getSequenceLink(edge);
        }

        if (semantic != null) {
          for (DSemanticDecorator view : DiagramServices.getDiagramServices().getDiagramElements(diagram, semantic)) {
            colors.put(view, color);
            if (semantic instanceof FunctionalExchange && view instanceof DEdge) {
              Set<FunctionalChain> chains = coloredLinks.computeIfAbsent((DEdge) view, k -> new HashSet<>());
              chains.add(chain);
            }
          }
        }
      }

      // Create and highlight internal links
      if (!(chain instanceof OperationalProcess)) {
        InternalLinksGraph linksGraph = FunctionalChainCache.getInstance().getInternalLinksGraph(graph);
        for (InternalLinkEdge edge : linksGraph.getEdges().values()) {
          FunctionPort source = edge.getSource().getSemantic();
          FunctionPort target = edge.getTarget().getSemantic();
          DDiagramElement sourceNode = DiagramServices.getDiagramServices().getDiagramElement(diagram, source);
          DDiagramElement targetNode = DiagramServices.getDiagramServices().getDiagramElement(diagram, target);
          if (sourceNode != null && targetNode != null) {
            DEdge link = createInternalLink((EdgeTarget) sourceNode, (EdgeTarget) targetNode, chain);
            if (link != null) {
              internalLinks.add(link);
            }
          }
        }
      }
    }

    for (DEdge link : internalLinks) {
      ArrayList<RGBValues> chainColors = new ArrayList<RGBValues>();
      for (EObject fc : link.getSemanticElements()) {
        if (functionalChains.containsKey(fc)) {
          RGBValues color = ShapeUtil.getNodeColorStyle(functionalChains.get(fc));
          chainColors.add(color);
        }
      }
      customizeFunctionalChainEdgeStyle(link, chainColors.size() == 1 ? chainColors.get(0) : ShapeUtil.getBlackColor());
    }

    // Browse all nodes/containers looking for functions
    for (DDiagramElement view : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = view.getTarget();
      if (target instanceof AbstractFunction) {
        if (colors.containsKey(view)) {
          customizeSourceFunctionStyle(view, ShapeUtil.getColor(colors.get(view)));
        } else {
          resetFunctionStyle(view);
        }
      }
    }

    customizeFunctionalExchangeEdgeLabels(coloredLinks, functionalChains);

    // Update all functional exchanges and sequence links
    for (DEdge view : diagram.getEdges()) {
      EObject target = view.getTarget();
      if (target instanceof FunctionalExchange || target instanceof SequenceLink) {
        if (colors.containsKey(view)) {
          customizeFunctionalChainEdgeStyle(view, ShapeUtil.getColor(colors.get(view)));
        } else {
          resetFunctionalExchangeStyle(view);
          resetFunctionalExchangeEdgeLabels(view);
        }
      }
    }

  }

  /**
   * Add pie icons on the begin and the end labels of a Functional Exchange
   * 
   * @param coloredLinks
   * @param displayedFCs
   */
  public void customizeFunctionalExchangeEdgeLabels(Map<DEdge, Set<FunctionalChain>> coloredLinks,
      Map<FunctionalChain, DNode> displayedFCs) {
    for (Map.Entry<DEdge, Set<FunctionalChain>> entry : coloredLinks.entrySet()) {
      DEdge edge = entry.getKey();
      Set<FunctionalChain> chains = entry.getValue();
      List<RGBValues> fcColors = chains.stream().map(displayedFCs::get).map(ShapeUtil::getNodeColorStyle)
          .collect(Collectors.toList());
      if (fcColors.size() > 1) {
        DEdgeIconCache.getInstance().setIcon(edge, fcColors.stream().collect(Collectors.toList()));
        DEdgeIconCache.getInstance().setLabel(edge, DiagramServices.getDiagramServices()
            .getOverlappedLabels(chains.stream().map(AbstractNamedElement::getName).collect(Collectors.toList())));
      } else {
        DEdgeIconCache.getInstance().removeIcon(edge);
        DEdgeIconCache.getInstance().setLabel(edge, ICommonConstants.EMPTY_STRING);
      }
      DiagramServices.getDiagramServices().refreshBeginEndLabels(edge);
      CapellaServices.getService().refreshElement(edge);
    }
  }

  public void resetFunctionalExchangeEdgeLabels(DEdge edge) {
    DEdgeIconCache.getInstance().removeIcon(edge);
    DEdgeIconCache.getInstance().setLabel(edge, ICommonConstants.EMPTY_STRING);
    DiagramServices.getDiagramServices().refreshBeginEndLabels(edge);
    CapellaServices.getService().refreshElement(edge);
  }

  /**
   * Retrieve map of displayed functional chains and associated DNode
   */
  public HashMap<FunctionalChain, DNode> getDisplayedFunctionalChains(DDiagram diagram) {
    HashMap<FunctionalChain, DNode> functionalChainToNodeMap = new HashMap<>();
    for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
      if ((aNode instanceof DNode) && (aNode.getTarget() instanceof FunctionalChain)) {
        functionalChainToNodeMap.put((FunctionalChain) aNode.getTarget(), (DNode) aNode);
      }
    }
    return functionalChainToNodeMap;
  }

  /**
   * Retrieve set of displayed functional chains
   */
  public Set<FunctionalChain> getDisplayedFunctionalChainsOnDiagram(DDiagram diagram) {
    Set<FunctionalChain> functionalChains = new HashSet<>();
    for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
      if ((aNode instanceof DNode) && (aNode.getTarget() instanceof FunctionalChain)) {
        functionalChains.add((FunctionalChain) aNode.getTarget());
      }
    }
    return functionalChains;
  }

  public Collection<EObject> getFCInternalLinkSemanticElements(DEdge view) {
    Collection<FunctionalChain> displayedChains = getDisplayedFunctionalChainsOnDiagram(view.getParentDiagram());
    return view.getSemanticElements().stream().filter(x -> displayedChains.contains(x)).collect(Collectors.toList());
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

    // Allow internal links only on same parent
    EObject sourceParent = currentSourceNode.eContainer();
    EObject targetParent = currentTargetNode.eContainer();

    if (sourceParent == null || targetParent == null || !sourceParent.equals(targetParent)) {
      return false;
    }

    // If there is a link between these two ports
    InvolvementGraph g = FunctionalChainCache.getInstance().getInvolvementGraph(chain);
    InternalLinksGraph g2 = FunctionalChainCache.getInstance().getInternalLinksGraph(g);

    if (!g2.hasInternalLink(((DDiagramElement) currentSourceNode).getTarget(),
        ((DDiagramElement) currentTargetNode).getTarget())) {
      return false;
    }

    DDiagram diagram = ((DDiagramElement) currentSourceNode).getParentDiagram();
    DEdge anotherEdge = DiagramServices.getDiagramServices().findInternalLinkEdge(diagram, currentSourceNode,
        currentTargetNode, getInternLinkEdgeMapping(diagram));
    boolean hasAnotherEdge = anotherEdge != null && !(chain.equals(anotherEdge.getTarget()));
    return !hasAnotherEdge;
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
    // Before creating, check if the internal link is possible to display, depending on conditions of their 2 ports.
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode);
    EdgeMapping mapping = getInternLinkEdgeMapping(diagram);
    DEdge newEdge = DiagramServices.getDiagramServices().findInternalLinkEdge(diagram, sourceNode, targetNode, mapping);
    if (newEdge == null && isValidInternalLinkEdge(fc, sourceNode, targetNode)) {
      DiagramServices.getDiagramServices().createEdge(mapping, sourceNode, targetNode, fc);
      newEdge = DiagramServices.getDiagramServices().findInternalLinkEdge(diagram, sourceNode, targetNode, mapping);
    }
    if (newEdge != null && !newEdge.getSemanticElements().contains(fc)) {
      newEdge.getSemanticElements().add(fc);
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
      EdgeStyle edgeStyle = aEdge.getOwnedStyle();
      Integer currentSize = edgeStyle.getSize();

      // reset style & color : if current size is the default size of Functional Chain applied by FunctionalChains (e.g.
      // as custom feature)
      if ((null != currentSize) && currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN)
          && ShapeUtil.isCustomisation(edgeStyle, DiagramPackage.Literals.EDGE_STYLE__SIZE)) {

        // get default style size of an edge
        EdgeStyleDescription desc = (EdgeStyleDescription) getMappingHelper(aEdge).getBestStyleDescription(mapping,
            aEdge.getTarget(), aEdge, aEdge.eContainer(), CapellaServices.getService().getDiagramContainer(aEdge));
        String defaultStyleSize = desc.getSizeComputationExpression();
        if (defaultStyleSize != null) {

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

  public void customizeFunctionalChainEdgeStyle(DEdge edge, RGBValues color) {
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
  public Collection<DSemanticDecorator> getBestDisplayedFunctionNode(AbstractFunction function, DDiagram diagram) {
    EObject semantic = function;
    Collection<DSemanticDecorator> elements = DiagramServices.getDiagramServices().getDiagramElements(diagram,
        function);
    while (elements.isEmpty() && semantic != null) {
      elements = DiagramServices.getDiagramServices().getDiagramElements(diagram, semantic);
      semantic = semantic.eContainer();
    }
    return elements;
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
  
  public boolean isFCRegion(EObject view) {
    return view instanceof AbstractDNode && IMappingNameConstants.FCD_CHAIN_REFERENCE_CHAIN__MAPPING_NAME.equals(((AbstractDNode) view).getDiagramElementMapping().getName());
  }

  public String getFCInvolvmentLinkLabel(FunctionalChainInvolvementLink involvementLink, DDiagram diagram) {
    String label = "";
    InvolvedElement involved = involvementLink.getInvolved();

    if (involved instanceof FunctionalExchange) {
      FunctionalExchange functionalExchange = (FunctionalExchange) involvementLink.getInvolved();
      label = functionalExchange.getName();

      Set<String> filterNames = diagram.getActivatedFilters().stream().map(FilterDescription::getName)
          .collect(Collectors.toSet());

      boolean showExchangeItems = filterNames.contains(IMappingNameConstants.SHOW_EXCHANGE_ITEMS);
      boolean showExchangeItemsParameters = filterNames.contains(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_PARAMETERS);
      boolean showFEExchangeItems = filterNames.contains(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS);

      if (showExchangeItems || showFEExchangeItems) {
        StringBuilder sb = new StringBuilder();

        if (showFEExchangeItems) {
          sb.append(label);
          sb.append("["); //$NON-NLS-1$
        }

        EList<ExchangeItem> exchangedItemsForLabel = involvementLink.getExchangedItems().isEmpty()
            ? functionalExchange.getExchangedItems()
            : involvementLink.getExchangedItems();

        String exchangedItemsLabel = exchangedItemsForLabel.stream()
            .map(ei -> ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters).toString())
            .collect(Collectors.joining(", "));

        sb.append(exchangedItemsLabel);

        if (showFEExchangeItems) {
          sb.append("]"); //$NON-NLS-1$
        }

        label = sb.toString();
      }
    } else if (involved instanceof AbstractFunction) {
      AbstractFunction function = (AbstractFunction) involved;
      label = function.getName();
    }

    return label;
  }

  public String getFunctionalChainLabel(FunctionalChain chain, DDiagram diagram) {

    boolean displayIncompleteLabel = false;
    boolean displayInvalidLabel = false;

    if (chain instanceof OperationalProcess) {
      displayIncompleteLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(chain));
      displayInvalidLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(chain));
    } else {
      displayIncompleteLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL,
          PreferencesHelper.getProject(chain));
      displayInvalidLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramProcessChainPathPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL,
          PreferencesHelper.getProject(chain));
    }

    List<String> chainStatusLabels = new ArrayList<>();
    if (displayIncompleteLabel && !isCompleteFunctionalChain(chain, diagram)) {
      chainStatusLabels.add(INCOMPLETE_FUNCTIONAL_CHAIN_LABEL);
    }
    if (displayInvalidLabel && !FunctionalChainExt.isFunctionalChainValid(chain)) {
      chainStatusLabels.add(INVALID_FUNCTIONAL_CHAIN_LABEL);
    }

    String chainLabel = EObjectExt.getText(chain);
    String chainStatusLabel = chainStatusLabels.isEmpty() ? ""
        : chainStatusLabels.stream().collect(Collectors.joining(", ", " (", ")"));

    return chainLabel + chainStatusLabel;
  }

  public void updateFunctionalChainNodeColor(DNode fcNode, Collection<DNode> visibleFunctionalChains) {
    RGBValues color = ShapeUtil.getNodeColorStyle(fcNode);
    ColorManager colorManager = ColorManager.getInstance();

    List<RGB> colorList = colorManager.getColorList();
    boolean changeColor = false;

    if (ShapeUtil.isSameColor(color, colorManager.getGrayColor())) {
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
   * Returns the incoming and outgoing Function Exchanges for the current node.
   * 
   * @param node
   *          the node
   * @return the outgoing Function Exchanges.
   */
  protected Set<FunctionalExchange> getAllFunctionalExchanges(DNode node) {
    return DiagramServices.getDiagramServices().getAllEdges(node).stream().map(DEdge::getTarget)
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
  public Collection<FunctionalExchange> computeFCIFunctionalExchangeAndFunctionScope(DNode node) {
    Set<FunctionalExchange> existingInvolvedFunctionalExchanges = getAllFunctionalExchanges(node);

    FunctionalChainInvolvementFunction selectedFunction = (FunctionalChainInvolvementFunction) node.getTarget();

    Collection<FunctionalExchange> possibleFunctionalExchanges = new LinkedHashSet<>();
    possibleFunctionalExchanges.addAll(FunctionalChainExt.getFlatIncomingExchanges(selectedFunction));
    possibleFunctionalExchanges.addAll(FunctionalChainExt.getFlatOutgoingExchanges(selectedFunction));

    possibleFunctionalExchanges.removeAll(existingInvolvedFunctionalExchanges);

    return possibleFunctionalExchanges;
  }

  /**
   * Precondition for the creation of a Functional Chain Involvement Link having a Functional Exchange as Involved.
   * 
   * @param sourceView
   *          the semantic source
   * 
   * @param targetView
   *          the semantic target
   * 
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidFCILinkExchange(DNode sourceView, DNode targetView) {

    EObject source = sourceView.getTarget();
    EObject target = targetView.getTarget();

    if (source instanceof FunctionalChainInvolvementFunction && target instanceof FunctionalChainInvolvementFunction) {
      FunctionalChainInvolvementFunction sourceFunction = (FunctionalChainInvolvementFunction) source;
      FunctionalChainInvolvementFunction targetFunction = (FunctionalChainInvolvementFunction) target;

      Collection<FunctionalExchange> commonExchanges = FunctionalChainExt
          .getFlatCommonFunctionalExchanges(sourceFunction, targetFunction);

      return !commonExchanges.isEmpty() && !doesConnectionExist(targetView, sourceView, new HashSet<>());
    }

    return false;
  }

  /**
   * Precondition for the creation of a Functional Chain Involvement Link having a Function as Involved.
   * 
   * @param source
   *          the semantic source
   * @param sourceView
   *          the source view
   * @param target
   *          the semantic target
   * @param targetView
   *          the target view
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidFCILinkFunction(FunctionalChainInvolvementFunction source, EdgeTarget sourceView,
      FunctionalChainInvolvementFunction target, EdgeTarget targetView) {
    return sourceView != targetView && isSameFunctionInvolved(source, target) && !isSameFunctionalChain(source, target);
  }

  /**
   * Returns true if the same function is involved by both parameters, false otherwise.
   * 
   * @param sourceInvolvement
   *          the source involvement parameter.
   * @param targetInvolvement
   *          the target involvement parameter.
   * @return true if the same function is involved by both parameters, false otherwise
   */
  public boolean isSameFunctionInvolved(FunctionalChainInvolvement sourceInvolvement,
      FunctionalChainInvolvement targetInvolvement) {
    return sourceInvolvement.getInvolved() == targetInvolvement.getInvolved();
  }

  /**
   * Returns true if the source and target functions are owned by the same FunctionalChain, false otherwise.
   * 
   * @param sourceInvolvement
   *          the source involvement parameter.
   * @param targetInvolvement
   *          the target involvement parameter.
   * @return true if the source and target functions are owned by the same FunctionalChain, false otherwise
   */
  public boolean isSameFunctionalChain(FunctionalChainInvolvement sourceInvolvement,
      FunctionalChainInvolvement targetInvolvement) {
    return sourceInvolvement.eContainer() == targetInvolvement.eContainer();
  }

  /**
   * Computes the Functional Chain Reference hierarchy until reaching the endFunctionalChain. The hierarchy is
   * maintained in a bottom to top fashion, meaning that the first element is the immediate Functional Chain Reference,
   * and the last element is the Functional Chain Reference contained by the endFunctionalChain.
   * 
   * @param view
   *          the selected view.
   * 
   * @param endFunctionalChain
   *          the end point of the hierarchy
   * @return the Functional Chain Reference hierarchy.
   */
  public List<FunctionalChainReference> computeFCReferenceHierarchy(EdgeTarget view,
      FunctionalChain endFunctionalChain) {
    return FunctionalChainReferenceHierarchyHelper.computeHierarchy(view, endFunctionalChain);
  }

  /**
   * Computes the Functional Chain that will serve as container for the new edge.
   * 
   * @param sourceView
   *          the source view
   * @param targetView
   *          the target view
   * @return the Functional Chain that will serve as container for the new edge.
   */
  public FunctionalChain computeContainerFunctionalChain(EdgeTarget sourceView, EdgeTarget targetView) {
    return FunctionalChainReferenceHierarchyHelper.computeContainerFunctionalChain(sourceView, targetView);
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
   * Returns the scope used for for the selection wizard in Functional Chain Involvement tools. Since the wizard is
   * displayed only for Function Involvements binded by a Functional exchange, the scope only contains functional
   * exchanges. For Functional Involvements binded by an Abstract function no wizard is displayed.
   * 
   * @param source
   *          the semantic source
   * @param target
   *          the semantic target.
   * @return the scope used for for the selection wizard in Functional Chain Involvement tools.
   */
  public Collection<EObject> computeFCILinkScope(FunctionalChainInvolvement sourceInvolvement,
      FunctionalChainInvolvement targetInvolvement) {
    return new ArrayList<>(FunctionalChainExt.getFlatCommonFunctionalExchanges(sourceInvolvement, targetInvolvement));
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
   * Tests if an direct/indirect path exists between the current node and the goal node.
   * 
   * @param currentNode
   *          the current node being analyzed.
   * @param goalInvolvement
   *          the goal node that servers at target goal.
   * @param visitedNodes
   *          the already visited nodes.
   * @return true if a direct/indirect path exists between the current node and the goal node, false otherwise.
   */
  public boolean doesConnectionExist(EdgeTarget currentNode, EdgeTarget goalNode, Set<EdgeTarget> visitedNodes) {

    if (visitedNodes.contains(currentNode)) {
      return false;
    }

    if (currentNode.equals(goalNode)) {
      return true;
    }

    visitedNodes.add(currentNode);

    for (DEdge edge : currentNode.getOutgoingEdges()) {
      EObject edgeTarget = edge.getTarget();
      if (edgeTarget instanceof FunctionalChainInvolvementLink) {
        EdgeTarget nextNode = edge.getTargetNode();

        if (doesConnectionExist(nextNode, goalNode, visitedNodes)) {
          return true;
        }
      }
    }

    return false;
  }

  public MappingWithInterpreterHelper getMappingHelper(DSemanticDecorator semanticDecorator) {
    return new MappingWithInterpreterHelper(
        SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator.getTarget()));
  }

  /**
   * Get the container for functional chain from the container for functional chain reference.
   * 
   * @param fcRefContainer
   * @return
   */
  public DNodeContainer getFCContainerOfFCRContainer(DDiagramElement fcRefContainer) {
    if (fcRefContainer != null) {
      return fcRefContainer.eContents().stream().filter(DNodeContainer.class::isInstance)
          .map(DNodeContainer.class::cast).findFirst().orElse(null);
    }
    return null;
  }

  /**
   * Return the label of a FunctionalChainReference If the container is collapsed, the label is followed by this
   * indicator " [+]"
   * 
   * @param fcrContainer
   *          the FunctionalChainReference DNodeContainer
   * @return the label
   */
  public String getFunctionalChainReferenceLabel(DNodeContainer fcrContainer) {
    StringBuilder labelBuilder = new StringBuilder();
    if (fcrContainer != null) {
      FunctionalChainReference fcr = (FunctionalChainReference) fcrContainer.getTarget();
      InvolvedElement fc = (fcr != null) ? fcr.getInvolved() : null;

      if (fc != null) {
        labelBuilder.append(fc.getLabel());
      }

      DNodeContainer fcContainer = getFCContainerOfFCRContainer(fcrContainer);
      if (isContainerCollapsed(fcContainer)) {
        labelBuilder.append(FCR_CONTAINER_COLLAPSED_INDICATOR);
      }
    }
    return labelBuilder.toString();
  }

  public boolean isContainerCollapsed(DNodeContainer container) {
    Node gmfNode = SiriusGMFHelper.getGmfNode(container);

    if (gmfNode != null) {
      for (Object subNode : gmfNode.getChildren()) {
        if (subNode instanceof Node) {
          for (Object style : ((Node) subNode).getStyles()) {
            if (style instanceof DrawerStyle) {
              return ((DrawerStyle) style).isCollapsed();
            }
          }
        }
      }
    }

    return false;
  }

  public boolean isInCollapsedHierarchy(DDiagramElement diagramElement) {
    if (diagramElement != null) {
      EObject parent = diagramElement.eContainer();
      if (parent instanceof DNodeContainer) {
        boolean containerCollapsed = isContainerCollapsed((DNodeContainer) parent);
        if (containerCollapsed) {
          return true;
        }
      }
      if (parent instanceof DDiagram) {
        return false;
      }
      return isInCollapsedHierarchy((DDiagramElement) parent);
    }
    return false;
  }

  /**
   * Precondition used for displaying an edge referencing ReferenceHierarchyContext.
   * 
   * @param link
   *          the semantic ReferenceHierarchyContext element.
   * @param sourceView
   *          the source view
   * @param source
   *          the semantic source
   * @param targetView
   *          the target view
   * @param target
   *          the semantic target
   * @return true if the edge should be displayed, false otherwise.
   */
  public boolean canCreateFCILEdge(ReferenceHierarchyContext link, DDiagramElement sourceView, EObject source,
      DDiagramElement targetView, EObject target) {

    if (source == null || target == null)
      return false;
    if (sourceView == null || targetView == null)
      return false;
    if (source.equals(target))
      return false;
    if (sourceView.equals(targetView))
      return false;

    if (sourceView instanceof DNodeContainer && source instanceof FunctionalChainReference) {
      DNodeContainer fcContainer = getFCContainerOfFCRContainer(sourceView);
      if (!isContainerCollapsed(fcContainer))
        return false;
    }

    if (targetView instanceof DNodeContainer && target instanceof FunctionalChainReference) {
      DNodeContainer fcContainer = getFCContainerOfFCRContainer(targetView);
      if (!isContainerCollapsed(fcContainer))
        return false;
    }

    return checkRefHierarchyOfLink(link, sourceView, targetView);
  }

  /**
   * Precondition used for displaying an edge between a FCILink and a SequenceLink.
   * 
   * @param sourceEdge
   *          the source FCILink edge
   * @param targetEdge
   *          the target SequenceLink edge
   * @return true if the edge should be displayed, false otherwise.
   */
  public boolean canCreateLinksEdge(DEdge sourceEdge, DEdge targetEdge) {

    EdgeTarget sourceNode1 = sourceEdge.getSourceNode();
    EdgeTarget sourceNode2 = targetEdge.getSourceNode();

    List<FunctionalChainReference> sourceNode1Hierarchy = FunctionalChainReferenceHierarchyHelper
        .computeHierarchy(sourceNode1);
    List<FunctionalChainReference> sourceNode2Hierarchy = FunctionalChainReferenceHierarchyHelper
        .computeHierarchy(sourceNode2);

    if (!sourceNode1Hierarchy.equals(sourceNode2Hierarchy)) {
      return false;
    }

    EdgeTarget targetNode1 = sourceEdge.getTargetNode();
    EdgeTarget targetNode2 = targetEdge.getTargetNode();

    List<FunctionalChainReference> targetNode1Hierarchy = FunctionalChainReferenceHierarchyHelper
        .computeHierarchy(targetNode1);
    List<FunctionalChainReference> targetNode2Hierarchy = FunctionalChainReferenceHierarchyHelper
        .computeHierarchy(targetNode2);

    return targetNode1Hierarchy.equals(targetNode2Hierarchy);
  }

  /**
   * Provides the semantic targets for the computed edge (between SL/FCIL to FCIL/SL) coming from source.
   * 
   * @param source
   *          the semantic source
   * @return semantic targets for the computed edge coming from source.
   */
  public List<ReferenceHierarchyContext> getLinksEdgeTargets(ReferenceHierarchyContext source) {

    if (source instanceof SequenceLink) {
      SequenceLink sequenceLink = (SequenceLink) source;
      return new ArrayList<>(sequenceLink.getLinks());
    }

    return Collections.emptyList();
  }

  /**
   * targetNode2Hierarchygiven link must appears in the diagram
   * 
   * @param link
   *          the given link
   * @param sourceView
   *          the source view of the link in the diagram
   * @param targetView
   *          the target view of the link in the diagram
   * @return true if the given FunctionalChainInvolvementLink must appears in the diagram
   */
  public boolean checkRefHierarchyOfLink(ReferenceHierarchyContext link, DDiagramElement sourceView,
      DDiagramElement targetView) {

    // get the valid top container for the source reference Hierarchy
    EObject topSourceHierarchyContainer = FunctionalChainReferenceHierarchyHelper
        .getDiagramElementForTopHierarchy(link.getSourceReferenceHierarchy(), sourceView);

    if (topSourceHierarchyContainer == null) {
      return false;
    }

    // get the valid top container for the target reference Hierarchy
    EObject topTargetHierarchyContainer = FunctionalChainReferenceHierarchyHelper
        .getDiagramElementForTopHierarchy(link.getTargetReferenceHierarchy(), targetView);

    if (topTargetHierarchyContainer == null) {
      return false;
    }

    // both hierarchies are valid independently -> check if they valid together -> they are at the same level -> they
    // have the same parent
    EObject parentSourceHierarchyContainer = topSourceHierarchyContainer.eContainer();
    EObject parentTargetHierarchyContainer = topTargetHierarchyContainer.eContainer();

    return parentSourceHierarchyContainer == parentTargetHierarchyContainer;
  }

  /**
   * Return the label of the given ControlNode
   * 
   * @param controlNode
   *          the given controlNode
   * @return its label
   */
  public String getControlNodeLabel(ControlNode controlNode) {
    return controlNode.getKind() == ControlNodeKind.ITERATE ? IT : controlNode.getKind().getLiteral();
  }

  /**
   * Return the label of the given SequenceLink
   * 
   * @param sequenceLink
   *          the given SequenceLink
   * @return its label
   */
  public String getSequenceLinkLabel(SequenceLink sequenceLink, DDiagram diagram) {
    String label = "";
    // Does the sequence link have a condition?
    if (sequenceLink.getCondition() != null) {
      String constraint = CapellaServices.getService().getConstraintLabel(sequenceLink.getCondition());
      label = constraint.isEmpty() ? "" : "[" + constraint + "]";
    }

    // Show the Functional Exchanges linked by this Sequence Link
    Set<String> filterNames = diagram.getActivatedFilters().stream().map(FilterDescription::getName)
        .collect(Collectors.toSet());
    boolean mergeFESL = filterNames.contains(IMappingNameConstants.MERGE_ASSOCIATED_FE_AND_SL);
    if (mergeFESL) {
      String labelFe = sequenceLink.getLinks().stream().filter(x -> x.getInvolved() instanceof FunctionalExchange)
          .map(x -> ((FunctionalExchange) x.getInvolved()).getName()).collect(Collectors.joining(", "));
      label = labelFe.isEmpty() ? label : label + " " + labelFe;
    }

    return label;
  }

  /**
   * Returns all the Sequence links for a given functional chain, including those on recursive levels. This function is
   * tail recursive.
   * 
   * @param chain
   *          the given functional chain.
   * @return all the Sequence links for a function chain, including those on recursive levels.
   */
  public Collection<SequenceLink> getAllSequenceLinks(FunctionalChain chain) {

    Set<SequenceLink> result = new HashSet<>();
    getAllSequenceLinks(chain, result);

    return result;
  }

  /**
   * This is a tail recursive version that returns all the Sequence links for a functional chain, including those on
   * recursive levels.
   * 
   * @param chain
   *          the functional chain.
   * @param linksAcumulator
   *          Sequence links acumulator.
   */
  private void getAllSequenceLinks(FunctionalChain chain, Collection<SequenceLink> linksAcumulator) {
    if (chain != null) {
      linksAcumulator.addAll(chain.getOwnedSequenceLinks());

      for (FunctionalChainInvolvement involvement : chain.getOwnedFunctionalChainInvolvements()) {
        if (involvement instanceof FunctionalChainReference) {
          FunctionalChain referencedChain = ((FunctionalChainReference) involvement).getReferencedFunctionalChain();

          getAllSequenceLinks(referencedChain, linksAcumulator);
        }
      }
    }
  }

  /**
   * Precondition for the creation of a Sequence Link.
   * 
   * @param source
   *          the semantic source
   * @param target
   *          the semantic target
   * 
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidSequenceLink(SequenceLinkEnd source, SequenceLinkEnd target) {

    if (source == target) {
      return false;
    }

    if (source instanceof FunctionalChainInvolvement && target instanceof FunctionalChainInvolvement) {
      FunctionalChainInvolvement sourceInvolvement = (FunctionalChainInvolvement) source;
      FunctionalChainInvolvement targetInvolvement = (FunctionalChainInvolvement) target;
      if (isSameFunctionInvolved(sourceInvolvement, targetInvolvement)) {
        return false;
      }
    }

    return !doesConnectionExistBetweenSequenceLinkEnds(target, source, new HashSet<>());
  }

  /**
   * Precondition for the creation of a link between a Sequence Link and a FCIL to a FE
   * 
   * @param the
   *          semantic source
   * @param the
   *          semantic target
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidLinks(ReferenceHierarchyContext source, ReferenceHierarchyContext target) {
    return isValidAssociation(source, target) || isValidAssociation(target, source);
  }

  /**
   * Return true if first node is a SequenceLink and second node a FunctionalChainInvolvementLink Also checks that first
   * and second node have the same source and targets
   * 
   * @param first:
   *          semantic element
   * @param second:
   *          semantic element
   * @return boolean.
   */
  private boolean isValidAssociation(ReferenceHierarchyContext first, ReferenceHierarchyContext second) {
    if (first instanceof SequenceLink) {
      if (second instanceof FunctionalChainInvolvementLink) {
        FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) second;
        if (link.getInvolved() instanceof FunctionalExchange) {
          SequenceLink seqLink = (SequenceLink) first;
          // check that seqLinks has not already a link to link (fcil)
          if (!seqLink.getLinks().contains(second)) {
            // check that the source and target of first and second nodes are the same
            Set<FunctionalChainInvolvementFunction> slClosestFCIFSources = SequenceLinkExt
                .findClosestSemanticFCIFunctionsAsSources(seqLink);
            Set<FunctionalChainInvolvementFunction> slClosestFCIFTargets = SequenceLinkExt
                .findClosestSemanticFCIFunctionsAsTargets(seqLink);
            return (slClosestFCIFSources.contains(link.getSource()) && slClosestFCIFTargets.contains(link.getTarget()));
          }
        }
      }
    }
    return false;
  }

  /**
   * Tests if an direct/indirect connection exists between the current Sequence Link End and the goal Sequence Link End.
   * 
   * @param currentSequenceLinkEnd
   *          the current Sequence Link End being analyzed.
   * @param goalSequenceLinkEnd
   *          the goal Sequence Link End that servers at target goal.
   * @param visitedSequenceLinkEnds
   *          the already visited Sequence Link Ends.
   * @return true if a direct/indirect connection exists between the current Sequence Link End and the goal Sequence
   *         Link End, false otherwise.
   */
  public boolean doesConnectionExistBetweenSequenceLinkEnds(SequenceLinkEnd currentSequenceLinkEnd,
      SequenceLinkEnd goalSequenceLinkEnd, Set<SequenceLinkEnd> visitedSequenceLinkEnds) {

    // avoid infinite loops
    if (visitedSequenceLinkEnds.contains(currentSequenceLinkEnd)) {
      return false;
    }

    if (currentSequenceLinkEnd.equals(goalSequenceLinkEnd)) {
      return true;
    }

    // cycles are possible with ITERATE ControlNode
    if (currentSequenceLinkEnd instanceof ControlNode
        && ((ControlNode) currentSequenceLinkEnd).getKind() == ControlNodeKind.ITERATE) {
      return false;
    }

    // create a copy to insure that each recursive call does not contain Sequence Link of other same level calls
    Set<SequenceLinkEnd> visitedSequenceLinkEndsCopy = new HashSet<>(visitedSequenceLinkEnds);
    visitedSequenceLinkEndsCopy.add(currentSequenceLinkEnd);

    // depth first recursive call
    for (SequenceLink nextSequenceLink : SequenceLinkEndExt.getOutgoingSequenceLinks(currentSequenceLinkEnd)) {
      if (doesConnectionExistBetweenSequenceLinkEnds(nextSequenceLink.getTarget(), goalSequenceLinkEnd,
          visitedSequenceLinkEndsCopy)) {
        return true;
      }
    }
    return false;
  }

  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the outgoing edges
   * to the target of the given edge.
   * 
   * @param seqLinkEdge
   * @param closestFCIFunctionViews
   * @param ignoreFunctionsInCollapseHierarchy
   *          If this parameter is true, any view in collapsed hierarchy will be ignored.
   */
  private void findFlatClosestFCIFunctionViewsAsTarget(DEdge seqLinkEdge, List<DNode> closestFCIFunctionViews,
      boolean ignoreFunctionsInCollapseHierarchy) {
    EdgeTarget targetNode = seqLinkEdge.getTargetNode();
    if (targetNode instanceof DNode && ((DNode) targetNode).getTarget() instanceof FunctionalChainInvolvementFunction) {
      if (!ignoreFunctionsInCollapseHierarchy || !isInCollapsedHierarchy((DDiagramElement) targetNode)) {
        closestFCIFunctionViews.add((DNode) targetNode);
      }
    } else {
      targetNode.getOutgoingEdges() //
          .stream() //
          .filter(e -> e.getTarget() instanceof SequenceLink) //
          .forEach(s -> findFlatClosestFCIFunctionViewsAsTarget(s, closestFCIFunctionViews,
              ignoreFunctionsInCollapseHierarchy)); //
    }
  }

  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the outgoing edges
   * to the target of the given edge.
   * 
   * @param seqLinkEdge
   * @param ignoreFunctionsInCollapseHierarchy
   *          If this parameter is true, any view in collapsed hierarchy will be ignored.
   * @return
   */
  public List<DNode> findFlatClosestFCIFunctionViewsAsTarget(DEdge seqLinkEdge,
      boolean ignoreFunctionsInCollapseHierarchy) {
    List<DNode> closestFCIFunctionViews = new ArrayList<>();
    findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge, closestFCIFunctionViews, ignoreFunctionsInCollapseHierarchy);
    return closestFCIFunctionViews;
  }

  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the incoming edges
   * to the source of the given edge.
   * 
   * @param seqLinkEdge
   * @param closestFCIFunctionViews
   * @param ignoreFunctionsInCollapseHierarchy
   *          If this parameter is true, any view in collapsed hierarchy will be ignored.
   */
  private void findFlatClosestFCIFunctionViewsAsSource(DEdge seqLinkEdge, List<DNode> closestFCIFunctionViews,
      boolean ignoreFunctionsInCollapseHierarchy) {
    EdgeTarget sourceNode = seqLinkEdge.getSourceNode();
    if (sourceNode instanceof DNode && ((DNode) sourceNode).getTarget() instanceof FunctionalChainInvolvementFunction) {
      if (!ignoreFunctionsInCollapseHierarchy || !isInCollapsedHierarchy((DDiagramElement) sourceNode)) {
        closestFCIFunctionViews.add((DNode) sourceNode);
      }
    } else {
      sourceNode.getIncomingEdges() //
          .stream() //
          .filter(e -> e.getTarget() instanceof SequenceLink) //
          .forEach(s -> findFlatClosestFCIFunctionViewsAsSource(s, closestFCIFunctionViews,
              ignoreFunctionsInCollapseHierarchy)); //
    }
  }

  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the incoming edges
   * to the source of the given edge.
   * 
   * @param seqLinkEdge
   * @param ignoreFunctionsInCollapseHierarchy
   *          If this parameter is true, any view in collapsed hierarchy will be ignored.
   * @return
   */
  public List<DNode> findFlatClosestFCIFunctionViewsAsSource(DEdge seqLinkEdge,
      boolean ignoreFunctionsInCollapseHierarchy) {
    List<DNode> firstLevelViews = new ArrayList<>();
    findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge, firstLevelViews, ignoreFunctionsInCollapseHierarchy);
    return firstLevelViews;
  }

  private void findFirstLevelFCIFOrFCRViewsAsSource(DEdge edge, Set<DSemanticDecorator> firstLevelViews) {
    EdgeTarget edgeTarget = edge.getSourceNode();
    if (edgeTarget instanceof DSemanticDecorator) {
      DSemanticDecorator decorator = (DSemanticDecorator) edgeTarget;
      EObject target = decorator.getTarget();

      if (target instanceof FunctionalChainInvolvementFunction || target instanceof FunctionalChainReference) {
        firstLevelViews.add(decorator);
      } else {
        edgeTarget.getIncomingEdges() //
            .stream() //
            .forEach(incomingEdge -> findFirstLevelFCIFOrFCRViewsAsSource(incomingEdge, firstLevelViews)); //
      }
    }
  }

  /**
   * For a given DEdge, find all first level DSemanticDecorators representing a FCIF or a FCR, by following the incoming
   * edges to the source of the given edge.
   * 
   * @param edge
   *          the given edge
   * @return the first level DSemanticDecorators representing a FCIF or a FCR, by following the incoming edges to the
   *         source of the given edge.
   */
  public Set<DSemanticDecorator> findFirstLevelFCIFOrFCRViewsAsSource(DEdge edge) {
    Set<DSemanticDecorator> firstLevelViews = new HashSet<>();
    findFirstLevelFCIFOrFCRViewsAsSource(edge, firstLevelViews);
    return firstLevelViews;
  }

  private void findFirstLevelFCIFOrFCRViewsAsTarget(DEdge edge, Set<DSemanticDecorator> firstLevelViews) {
    EdgeTarget edgeTarget = edge.getTargetNode();
    if (edgeTarget instanceof DSemanticDecorator) {
      DSemanticDecorator decorator = (DSemanticDecorator) edgeTarget;
      EObject target = decorator.getTarget();

      if (target instanceof FunctionalChainInvolvementFunction || target instanceof FunctionalChainReference) {
        firstLevelViews.add(decorator);
      } else {
        edgeTarget.getOutgoingEdges()//
            .stream() //
            .forEach(outgoingEdge -> findFirstLevelFCIFOrFCRViewsAsTarget(outgoingEdge, firstLevelViews)); //
      }
    }
  }

  /**
   * For a given DEdge, find all first level DSemanticDecorators representing a FCIF or a FCR, by following the outgoing
   * edges to the target of the given edge.
   * 
   * @param edge
   *          the given edge
   * @return the first level DSemanticDecorators representing a FCIF or a FCR, by following the incoming edges to the
   *         source of the given edge.
   */
  public Set<DSemanticDecorator> findFirstLevelFCIFOrFCRViewsAsTarget(DEdge edge) {
    Set<DSemanticDecorator> closestFCIFunctionViews = new HashSet<>();
    findFirstLevelFCIFOrFCRViewsAsTarget(edge, closestFCIFunctionViews);
    return closestFCIFunctionViews;
  }

  /**
   * From a list of DNode whose target is type of FCIF, returns list of Abstract Function that are involved.
   * 
   * @param fcifNodes
   * @return
   */
  public Set<AbstractFunction> getFunctionsFromFCIFDNodes(List<DNode> fcifNodes) {
    return fcifNodes.stream() //
        .map(DNode::getTarget) //
        .map(FunctionalChainInvolvementFunction.class::cast) //
        .map(FunctionalChainInvolvementFunction::getInvolved) //
        .map(AbstractFunction.class::cast) //
        .collect(Collectors.toSet()); //
  }

  /**
   * For a given AbstractFunction and list of DNode whose target is of type FCIF, return list of DNode involving the
   * given function
   * 
   * @param fcifViews
   * @param function
   * @return
   */
  public List<DNode> getFCIFViewsInvolvingFunction(List<DNode> fcifViews, AbstractFunction function) {
    return fcifViews.stream() //
        .filter(s -> ((FunctionalChainInvolvementFunction) s.getTarget()).getInvolved().equals(function)) //
        .collect(Collectors.toList()); //
  }

  public EObject accelerateOnSequenceLinkEdge(DEdge seqLinkEdge) {
    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    String messageDialogTitle = "Accelerator Information";

    List<DNode> availableSourceFCIFViews = findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge, true);
    if (availableSourceFCIFViews.isEmpty()) {
      MessageDialog.openInformation(shell, messageDialogTitle,
          "There is not any Functional Chain Involvement Function as source for the selected sequence link or they are all in collapsed container.");
      return null;
    }

    List<DNode> availableTargetFCIFViews = findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge, true);
    if (availableTargetFCIFViews.isEmpty()) {
      MessageDialog.openInformation(shell, messageDialogTitle,
          "There is not any Functional Chain Involvement Function as target for the selected sequence link or they are all in collapsed container.");
      return null;
    }

    Set<AbstractFunction> availableSourceFunctions = getFunctionsFromFCIFDNodes(availableSourceFCIFViews);
    Set<AbstractFunction> availableTargetFunctions = getFunctionsFromFCIFDNodes(availableTargetFCIFViews);

    Set<FunctionalExchange> availableFEs = new HashSet<>();

    for (AbstractFunction sourceFunction : availableSourceFunctions) {
      for (AbstractFunction targetFunction : availableTargetFunctions) {
        List<FunctionalExchange> commonEdges = FunctionExt.getOutGoingExchange(sourceFunction);
        commonEdges.retainAll(FunctionExt.getIncomingExchange(targetFunction));

        availableFEs.addAll(commonEdges);
      }
    }

    SelectOrCreateFunctionalExchangeDialog dialog = new SelectOrCreateFunctionalExchangeDialog(shell, availableFEs,
        availableSourceFunctions, availableTargetFunctions);
    int returnCode = dialog.open();

    NewFEData newFEData = null;
    AbstractFunction feSource = null;
    AbstractFunction feTarget = null;
    FunctionalExchange involvedFE = null;

    if (returnCode == SelectOrCreateFunctionalExchangeDialog.CREATION) {
      newFEData = dialog.getCreation();
      feSource = newFEData.getSource();
      feTarget = newFEData.getTarget();
    }

    if (returnCode == SelectOrCreateFunctionalExchangeDialog.SELECTION) {
      involvedFE = dialog.getSelection().stream().findFirst().orElse(null);
      feSource = FunctionalExchangeExt.getSourceFunction(involvedFE);
      feTarget = FunctionalExchangeExt.getTargetFunction(involvedFE);
    }

    List<DNode> possibleSourceFCIFNodes = getFCIFViewsInvolvingFunction(availableSourceFCIFViews, feSource);
    List<DNode> possibleTargetFCIFNodes = getFCIFViewsInvolvingFunction(availableTargetFCIFViews, feTarget);

    int sourceSize = possibleSourceFCIFNodes.size();
    int targetSize = possibleTargetFCIFNodes.size();
    if (sourceSize > 1 || targetSize > 1) {
      MessageDialog.openInformation(shell, messageDialogTitle,
          "Impossible to create Functional Chain Involvement Link due to ambiguity of source and target");
    }

    if (sourceSize == 1 && targetSize == 1) {
      if (newFEData != null) {
        involvedFE = FunctionalExchangeExt.createFunctionalExchange(feSource, feTarget);
        involvedFE.setName(newFEData.getName());
        ((AbstractFunction) feSource.eContainer()).getOwnedFunctionalExchanges().add(involvedFE);
      }
      DNode sourceFCIFNode = possibleSourceFCIFNodes.get(0);
      DNode targetFCIFNode = possibleTargetFCIFNodes.get(0);
      createFCILink(sourceFCIFNode, targetFCIFNode, involvedFE, seqLinkEdge);
    }

    return seqLinkEdge;
  }

  public EObject accelerateOnFCILEdge(FunctionalChainInvolvementLink link) {
    if (link != null) {
      FunctionalChainExt.createSequenceLink(link);
    }
    return link;
  }

  /**
   * - Create a new FCILink between 2 FCIFunction nodes and involve the given functional exchange. - Add the new created
   * FCILink into list of links of the given sequence link edge. - Add the new created FCILink to list of involvements
   * of the given sequence link edge's functional chain
   * 
   * @param sourceFCIF
   * @param targetFCIF
   * @param functionalExchange
   * @param sequenceLinkEdge
   * @return the new FCILink
   */
  public FunctionalChainInvolvementLink createFCILink(DNode sourceFCIF, DNode targetFCIF,
      FunctionalExchange functionalExchange, DEdge sequenceLinkEdge) {

    SequenceLink sequenceLink = (SequenceLink) sequenceLinkEdge.getTarget();
    FunctionalChain commonFC = computeContainerFunctionalChain(sourceFCIF, targetFCIF);

    FunctionalChainInvolvementLink newFCIL = FunctionalChainExt.createInvolvementLink(commonFC, functionalExchange);
    newFCIL.setSource((FunctionalChainInvolvementFunction) sourceFCIF.getTarget());
    newFCIL.setTarget((FunctionalChainInvolvementFunction) targetFCIF.getTarget());

    newFCIL.getSourceReferenceHierarchy().addAll(computeFCReferenceHierarchy(sourceFCIF, commonFC));
    newFCIL.getTargetReferenceHierarchy().addAll(computeFCReferenceHierarchy(targetFCIF, commonFC));

    sequenceLink.getLinks().add(newFCIL);

    return newFCIL;
  }

  /**
   * On a FCD diagram, the Delete From Model icon for a selected element is visible or not based on the condition.
   * 
   * @param element
   *          the semantic element to be deleted
   * @return
   */
  public boolean checkDeleteConditionFCD(EObject element) {
    // If it is a Functional Chain, it is a sub-region in the container of FCR, so it should not be deleted, because it
    // might affect other diagrams.
    if (element instanceof FunctionalChain) {
      return false;
    }
    return true;
  }

  /**
   * This method checks if a ControlNode, represented by the graphical element passed as argument, has an edge from or
   * towards a collapsed container (which in this diagram is a FunctionalChainReference)
   * 
   * @param controlNode
   *          The graphical element representing a semantic ControlNode
   * @return Returns true if the graphical element has edges from or towards a collapsed container
   */
  public boolean controlNodeLinkedToCollapsedFCR(DNode controlNode) {

    EList<DEdge> incomingSequenceLinks = controlNode.getIncomingEdges();
    EList<DEdge> outgoingSequenceLinks = controlNode.getOutgoingEdges();

    for (DEdge outgoingSeqLink : outgoingSequenceLinks) {

      EdgeTarget targetNode = outgoingSeqLink.getTargetNode();

      if (targetNode instanceof DNodeContainer
          && ((DNodeContainer) targetNode).getTarget() instanceof FunctionalChainReference) {
        return true;
      }
    }

    for (DEdge incomingSeqLink : incomingSequenceLinks) {

      EdgeTarget sourceNode = incomingSeqLink.getSourceNode();

      if (sourceNode instanceof DNodeContainer
          && ((DNodeContainer) sourceNode).getTarget() instanceof FunctionalChainReference) {
        return true;
      }
    }

    return false;
  }

  /**
   * Hide FCIL that have associated Sequence Link
   * 
   * @param edge
   *          to check if it has an associated SL
   * @return true if FunctionalChainInvolvementLink has an associated Sequence Link.
   */
  public boolean isFEWithAssociatedSL(DEdge edge) {
    if (edge.getTarget() instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink fcil = (FunctionalChainInvolvementLink) edge.getTarget();
      List<SequenceLink> listSL = EObjectExt.getReferencers(fcil, FaPackage.Literals.SEQUENCE_LINK__LINKS);
      for (SequenceLink sl : listSL) {
        if (fcil.getSource() == sl.getSource() && fcil.getTarget() == sl.getTarget()) {
          return true;
        }
      }
    }
    return false;
  }
}
