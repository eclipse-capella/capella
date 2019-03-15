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
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.SimpleOrientedGraph;
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
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ConstraintExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.SequenceLinkEndExt;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph.InvolvementNode;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog;
import org.polarsys.capella.core.sirius.analysis.accelerators.SelectOrCreateFunctionalExchangeDialog.NewFEData;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.helpers.FunctionalChainReferenceHierarchyHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.DiagramsPreferencePage;
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

    for (Entry<FunctionalChain, DNode> entry : functionalChains.entrySet()) {
      FunctionalChain chain = entry.getKey();
      DNode chainView = entry.getValue();
      updateFunctionalChainNodeColor(chainView, functionalChains.values());

      InvolvementGraph g = FunctionalChainCache.getInstance().getInvolvementGraph(chain);

      RGBValues color = ShapeUtil.getNodeColorStyle(chainView);
      if (color == null) {
        continue;
      }

      // Add color for starting and ending functions
      for (InvolvementNode node : g.getNodes().values()) {
        if (g.isInvolvingFunction(node) && (g.isStartingFunction(node) || g.isEndingFunction(node))) {
          AbstractFunction function = g.getInvolvedFunction(node);
          Collection<DSemanticDecorator> views = getBestDisplayedFunctionNode(function, diagram);
          for (DSemanticDecorator view : views) {
            colors.put(view, color);
          }
        }
      }

      // Add color for related functional exchanges
      for (InvolvementEdge edge : g.getEdges().values()) {
        if (g.isInvolvingFunctionalExchange(edge)) {
          FunctionalExchange fe = g.getInvolvedFunctionalExchange(edge);
          for (DSemanticDecorator view : DiagramServices.getDiagramServices().getDiagramElements(diagram, fe)) {
            colors.put(view, color);
          }
        }
      }

      // Create and highlight internal links
      if (!(chain instanceof OperationalProcess)) {
        InternalLinksGraph linksGraph = FunctionalChainCache.getInstance().getInternalLinksGraph(g);
        for (InternalLinkEdge edge : linksGraph.getEdges().values()) {
          FunctionPort source = edge.getSource().getSemantic();
          FunctionPort target = edge.getTarget().getSemantic();
          DDiagramElement sourceNode = (DDiagramElement) DiagramServices.getDiagramServices().getDiagramElement(diagram,
              (EObject) source);
          DDiagramElement targetNode = (DDiagramElement) DiagramServices.getDiagramServices().getDiagramElement(diagram,
              (EObject) target);
          if (sourceNode != null && targetNode != null) {
            DEdge link = createInternalLink((EdgeTarget) sourceNode, (EdgeTarget) targetNode, chain);
            if (link != null) {
              customizeFunctionalChainEdgeStyle(link, color);
            }
          }
        }
      }
    }

    // Browse all nodes/containers looking for functions
    for (DDiagramElement view : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = view.getTarget();
      if (target instanceof AbstractFunction) {
        if (colors.containsKey(view)) {
          customizeSourceFunctionStyle((AbstractDNode) view, ShapeUtil.getColor(colors.get(view)));
        } else {
          resetFunctionStyle(view);
        }
      }
    }

    // Update all functional exchanges
    for (DEdge view : diagram.getEdges()) {
      EObject target = view.getTarget();
      if (target instanceof FunctionalExchange) {
        if (colors.containsKey(view)) {
          customizeFunctionalChainEdgeStyle(view, ShapeUtil.getColor(colors.get(view)));
        } else {
          resetFunctionalExchangeStyle(view);
        }
      }
    }

  }

  /**
   * Retrieve map of displayed functional chains and associated DNode
   */
  private HashMap<FunctionalChain, DNode> getDisplayedFunctionalChains(DDiagram diagram) {
    HashMap<FunctionalChain, DNode> functionalChainToNodeMap = new HashMap<>();
    for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
      if ((aNode instanceof DNode) && (aNode.getTarget() instanceof FunctionalChain)) {
        functionalChainToNodeMap.put((FunctionalChain) aNode.getTarget(), (DNode) aNode);
      }
    }
    return functionalChainToNodeMap;
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

    return g2.hasInternalLink(((DDiagramElement) currentSourceNode).getTarget(),
        ((DDiagramElement) currentTargetNode).getTarget());
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
    if (isValidInternalLinkEdge(fc, sourceNode, targetNode)) {
      DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceNode);
      EdgeMapping mapping = getInternLinkEdgeMapping(diagram);
      DEdge newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode, targetNode, fc,
          mapping);
      if (newEdge == null) {
        DiagramServices.getDiagramServices().createEdge(mapping, sourceNode, targetNode, fc);
        newEdge = DiagramServices.getDiagramServices().findDEdgeElement(diagram, sourceNode, targetNode, fc, mapping);
      }
      return newEdge;
    }
    return null;
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
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(chain));
      displayInvalidLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL,
          PreferencesHelper.getProject(chain));
    } else {
      displayIncompleteLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL,
          PreferencesHelper.getProject(chain));
      displayInvalidLabel = ScopedCapellaPreferencesStore.getBoolean(
          DiagramsPreferencePage.NAME_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL,
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
   * @param source
   *          the semantic source
   * 
   * @param target
   *          the semantic target
   * 
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidFCILinkExchange(FunctionalChainInvolvementFunction source,
      FunctionalChainInvolvementFunction target) {

    Collection<FunctionalExchange> commonExchanges = getFCDCommonFunctionalExchanges(source, target);

    // common exchanges exists and the new link does not create a cycle
    return !commonExchanges.isEmpty() && !doesConnectionExist(target, source, new HashSet<>());
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
    return sourceView != targetView && isSameFunctionInvolved(source, target);
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
    return new ArrayList<>(getFCDCommonFunctionalExchanges(sourceInvolvement, targetInvolvement));
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
  public String getSequenceLinkLabel(SequenceLink sequenceLink) {
    String label = "";

    // Does the sequence link have a condition?
    if (sequenceLink.getCondition() != null) {
      ValueSpecification expression = sequenceLink.getCondition().getOwnedSpecification();
      if (expression instanceof OpaqueExpression) {
        label += "[" + ConstraintExt.getPrimaryBody((OpaqueExpression) expression) + "]";
      }
    }

    // Show the Functional Exchanges linked by this Sequence Link
    boolean firstLink = true;
    for (FunctionalChainInvolvementLink link : sequenceLink.getLinks()) {
      InvolvedElement involved = link.getInvolved();
      if (involved instanceof FunctionalExchange) {
        FunctionalExchange exchange = (FunctionalExchange) involved;
        if (firstLink) {
          firstLink = false;
          if (!label.isEmpty()) {
            label += " ";
          }
        } else {
          label += ", ";
        }
        label += exchange.getName();
      }
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
   *          semantic target
   * @return true if a link can be created, false otherwise.
   */
  public boolean isValidLinks(EObject target) {
    if (target instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) target;
      return link.getInvolved() instanceof FunctionalExchange;
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
   */
  private void findFlatClosestFCIFunctionViewsAsTarget(DEdge seqLinkEdge, List<DNode> closestFCIFunctionViews) {
    EdgeTarget targetNode = seqLinkEdge.getTargetNode();
    if (targetNode instanceof DNode && ((DNode) targetNode).getTarget() instanceof FunctionalChainInvolvementFunction) {
      closestFCIFunctionViews.add((DNode) targetNode);
    } else {
      targetNode.getOutgoingEdges() //
          .stream() //
          .filter(e -> e.getTarget() instanceof SequenceLink) //
          .forEach(s -> findFlatClosestFCIFunctionViewsAsTarget(s, closestFCIFunctionViews)); //
    }
  }
  
  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the outgoing edges
   * to the target of the given edge.
   * 
   * @param seqLinkEdge
   * @return
   */
  public List<DNode> findFlatClosestFCIFunctionViewsAsTarget(DEdge seqLinkEdge) {
    List<DNode> closestFCIFunctionViews = new ArrayList<>();
    findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge, closestFCIFunctionViews);
    return closestFCIFunctionViews;
  }

  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the incoming edges
   * to the source of the given edge.
   * 
   * @param seqLinkEdge
   * @param closestFCIFunctionViews
   */
  private void findFlatClosestFCIFunctionViewsAsSource(DEdge seqLinkEdge, List<DNode> closestFCIFunctionViews) {
    EdgeTarget sourceNode = seqLinkEdge.getSourceNode();
    if (sourceNode instanceof DNode && ((DNode) sourceNode).getTarget() instanceof FunctionalChainInvolvementFunction) {
      closestFCIFunctionViews.add((DNode) sourceNode);
    } else {
      sourceNode.getIncomingEdges() //
          .stream() //
          .filter(e -> e.getTarget() instanceof SequenceLink) //
          .forEach(s -> findFlatClosestFCIFunctionViewsAsSource(s, closestFCIFunctionViews)); //
    }
  }
  
  /**
   * For a given sequenceLink DEdge, find all first-meet DNodes representing FCIFunction by following the incoming edges
   * to the source of the given edge.
   * 
   * @param seqLinkEdge
   * @return
   */
  public List<DNode> findFlatClosestFCIFunctionViewsAsSource(DEdge seqLinkEdge) {
    List<DNode> closestFCIFunctionViews = new ArrayList<>();
    findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge, closestFCIFunctionViews);
    return closestFCIFunctionViews;
  }
  
  /**
   * From a list of DNode whose target is type of FCIF, returns list of Abstract Function that are involved.
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
   * For a given AbstractFunction and list of DNode whose target is of type FCIF, 
   * return list of DNode involving the given function
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
    List<DNode> availableSourceFCIFViews = findFlatClosestFCIFunctionViewsAsSource(seqLinkEdge);
    List<DNode> availableTargetFCIFViews = findFlatClosestFCIFunctionViewsAsTarget(seqLinkEdge);

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

    Session session = SessionManager.INSTANCE.getSession(seqLinkEdge.getTarget());
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();

    SelectOrCreateFunctionalExchangeDialog dialog = new SelectOrCreateFunctionalExchangeDialog(shell, ted, adapterFactory, availableFEs,
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
      String title = "Accelerator Information";
      String message = "Impossible to create Functional Chain Involvement Link due to ambiguity of source and target";
      MessageDialog.openInformation(shell, title, message);
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
   * - Create a new FCILink between 2 FCIFunction nodes and involve the given functional exchange. 
   * - Add the new created FCILink into list of links of the given sequence link edge. 
   * - Add the new created FCILink to list of involvements of the given sequence link edge's functional chain
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
    FunctionalChain functionalChain = (FunctionalChain) sequenceLink.eContainer();

    FunctionalChainInvolvementLink newFCIL = FunctionalChainExt.createInvolvementLink(functionalChain,
        functionalExchange);
    newFCIL.setSource((FunctionalChainInvolvementFunction) sourceFCIF.getTarget());
    newFCIL.setTarget((FunctionalChainInvolvementFunction) targetFCIF.getTarget());

    FunctionalChain commonFC = computeContainerFunctionalChain(sourceFCIF, targetFCIF);
    newFCIL.getSourceReferenceHierarchy().addAll(computeFCReferenceHierarchy(sourceFCIF, commonFC));
    newFCIL.getTargetReferenceHierarchy().addAll(computeFCReferenceHierarchy(targetFCIF, commonFC));

    functionalChain.getOwnedFunctionalChainInvolvements().add(newFCIL);
    sequenceLink.getLinks().add(newFCIL);

    return newFCIL;
  }
}
