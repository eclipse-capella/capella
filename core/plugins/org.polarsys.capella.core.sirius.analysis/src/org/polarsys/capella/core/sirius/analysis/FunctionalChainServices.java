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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.tools.api.command.view.RefreshSiriusElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.RGB;
import org.polarsys.capella.common.helpers.EObjectExt;
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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
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
	 * Returns all semantics of source views for the given chain
	 */
	public Collection<EObject> getFunctionalChainSources(FunctionalChain chain) {
		HashSet<EObject> result = new HashSet<EObject>();

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
		HashSet<EObject> result = new HashSet<EObject>();

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

	public void updateFunctionalChainStyles(DDiagram diagram) {
		HashMap<FunctionalChain, DNode> displayedFC = new HashMap<FunctionalChain, DNode>(); // displayed Functional Chains
		HashMap<FunctionalExchange, Set<DEdge>> displayedFE = new HashMap<FunctionalExchange, Set<DEdge>>(); // displayed Functional Exchanges
		HashMap<FunctionalChain, Set<DEdge>> displayedIL = new HashMap<FunctionalChain, Set<DEdge>>(); // displayed Internal Links
		HashMap<AbstractFunction, Set<DDiagramElement>> displayedFunctions = new HashMap<AbstractFunction, Set<DDiagramElement>>(); // displayed functions
		HashMap<DDiagramElement, Set<FunctionalChain>> coloredFunctionNodes = new HashMap<DDiagramElement, Set<FunctionalChain>>(); // colored functions
		Set<FunctionalChain> incompleteFC = new HashSet<FunctionalChain>(); // incomplete displayed functional chains
		HashMap<DEdge, Set<FunctionalChain>> coloredFE = new HashMap<DEdge, Set<FunctionalChain>>(); // colored functional Exchanges
		Set<DEdge> updatedInternalLinks = new HashSet<DEdge>();

		// find displayed Functional chains and functions
		for (DDiagramElement aNode : diagram.getOwnedDiagramElements()) {
			if ((aNode instanceof DNode) && (aNode.getTarget() != null) && (aNode.getTarget() instanceof FunctionalChain)) {
				displayedFC.put((FunctionalChain) aNode.getTarget(), (DNode)aNode);
			}
		}
		// find displayed functions
		for (DNode aNode : diagram.getNodes()) {
			EObject target = aNode.getTarget();
			if (target instanceof AbstractFunction) {
				Set<DDiagramElement> set = displayedFunctions.get(target);
				if (set == null) {
					set = new HashSet<DDiagramElement>();
					displayedFunctions.put((AbstractFunction) target, set);
				}
				set.add(aNode);
			}
		}
		for (DDiagramElement aContainer : diagram.getContainers()) {
			EObject target = aContainer.getTarget();
			if ((target instanceof AbstractFunction)) {
				Set<DDiagramElement> set = displayedFunctions.get(target);
				if (set == null) {
					set = new HashSet<DDiagramElement>();
					displayedFunctions.put((AbstractFunction) target, set);
				}
				set.add(aContainer);
			}
		}

		// find displayed Functional Exchanges and Internal Links
		for (DEdge anEdge : diagram.getEdges()) {
			EObject edgeTarget = anEdge.getTarget();
			if (edgeTarget instanceof FunctionalExchange) {
				FunctionalExchange fe = (FunctionalExchange) edgeTarget;
				Set<DEdge> edges = displayedFE.get(fe);
				if (edges == null) {
					edges = new HashSet<DEdge>();
					displayedFE.put(fe, edges);
				}
				edges.add(anEdge);
			}
			if (edgeTarget instanceof FunctionalChain) {
				if (!displayedIL.containsKey(edgeTarget)) {
					Set<DEdge> newSet = new HashSet<DEdge>();
					newSet.add(anEdge);
					displayedIL.put((FunctionalChain) edgeTarget, newSet);
				} else {
					displayedIL.get(edgeTarget).add(anEdge);
				}
			}
		}

		// find source and target functions that must be colored
		for (Entry<FunctionalChain, DNode> me : displayedFC.entrySet()) {
			for (AbstractFunction aSourceFunction : FunctionalChainExt.getFlatFunctionalChainFirstFunctions(me.getKey())) {
			  // source Node of the functional chain
			  Set<DDiagramElement> sourceFunctionNodes = getBestDisplayedFunctionNode(aSourceFunction, displayedFunctions);
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
			  // target Node of the functional chain
			  Set<DDiagramElement> targetFunctionNodes = getBestDisplayedFunctionNode(aTargetFunction, displayedFunctions);
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
				}
			}
		}

		// update functions style
		for (Entry<AbstractFunction, Set<DDiagramElement>> me : displayedFunctions.entrySet()) {
			Set<DDiagramElement> functionNodes = me.getValue();
			for (DDiagramElement functionNode : functionNodes) {
				if (!coloredFunctionNodes.containsKey(functionNode)) {
					resetFunctionStyle(functionNode);
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
			// customize source function of the chain
			for (AbstractFunction aSourceFunction : FunctionalChainExt.getFlatFunctionalChainFirstFunctions(me.getKey())) {
			  // source Node of the functional chain
			  Set<DDiagramElement>  sourceFunctionNodes = getBestDisplayedFunctionNode(aSourceFunction, displayedFunctions);
				if (sourceFunctionNodes != null) {
					for (DDiagramElement sourceFunctionNode : sourceFunctionNodes) {
						if (coloredFunctionNodes.get(sourceFunctionNode).size() == 1) {
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
			// target Node of the functional chain
			  Set<DDiagramElement> targetFunctionNodes = getBestDisplayedFunctionNode(aTargetFunction, displayedFunctions);
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
					BlockArchitectureExt.getRootBlockArchitecture(((DSemanticDiagram) diagram).getTarget()) instanceof OperationalAnalysis;
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
           RefreshSiriusElement.refresh(me.getValue());
		}

		// destroy old internal links
		for (Set<DEdge> anInternalLinkSet : displayedIL.values()) {
			for (DEdge anInternalLink : anInternalLinkSet) {
				if (!updatedInternalLinks.contains(anInternalLink)) {
					DiagramServices.getDiagramServices().removeEdgeView(anInternalLink);
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

	protected Set<DEdge> updateInternalLinks(FunctionalChain fc, Map<FunctionalExchange, Set<DEdge>> displayedFunctionalExchanges,
			Map<FunctionalChain, Set<DEdge>> displayedIL, RGBValues color) {
		Set<DEdge> internalLinks = new HashSet<DEdge>();

		// iterate over involved functional exchange
		for (FunctionalChainInvolvement anInvolvement : FunctionalChainExt.getFlatInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
			FunctionalExchange currentExchange = (FunctionalExchange) anInvolvement.getInvolved();
			if (!displayedFunctionalExchanges.containsKey(currentExchange)) {
				continue;
			}

			Set<DEdge> currentEdges = displayedFunctionalExchanges.get(currentExchange);
			for (DEdge currentEdge : currentEdges) {
				if (currentEdge == null) {
				  continue;
				}
			  EdgeTarget currentSourceNode = currentEdge.getSourceNode();
				EdgeTarget currentTargetNode = currentEdge.getTargetNode();
				if (isValidNodeForInternalLink(currentSourceNode)) {

					Collection<FunctionalExchange> previousExchanges = getFlatPreviousFunctionalExchanges(fc, anInvolvement);
					Collection<FunctionalExchange> nextExchanges = getFlatNextFunctionalExchanges(fc, anInvolvement);

					// Display an internal link from previousExchange.target to exchange.source
					for (FunctionalExchange elt : previousExchanges) {
						if (displayedFunctionalExchanges.containsKey(elt)) {
							Set<DEdge> edges = displayedFunctionalExchanges.get(elt);
							for (DEdge edge : edges) {
								if ((edge != null) && isValidNodeForInternalLink(edge.getTargetNode())
										&& isValidInternalLinkEdge(fc, edge.getTargetNode(), currentSourceNode)) {
									internalLinks.add(retrieveInternalLink(edge.getTargetNode(), currentSourceNode, fc, color));
								}
							}
						}
					}

					// Display an internal link from exchange.target to nextExchange.source
					for (FunctionalExchange elt : nextExchanges) {
						if (displayedFunctionalExchanges.containsKey(elt)) {
							Set<DEdge> edges = displayedFunctionalExchanges.get(elt);
							for (DEdge edge : edges) {
								if ((edge != null) && isValidNodeForInternalLink(edge.getSourceNode())
										&& isValidInternalLinkEdge(fc, currentTargetNode, edge.getSourceNode())) {
									internalLinks.add(retrieveInternalLink(currentTargetNode, edge.getSourceNode(), fc, color));
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
	 * @param currentSourceNode
	 * @param currentTargetNode
	 * @return
	 */
	public boolean isValidInternalLinkEdge(FunctionalChain chain, EdgeTarget currentSourceNode, EdgeTarget currentTargetNode) {
		if (currentSourceNode == null) {
			return false;
		}
		if (currentTargetNode == null) {
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

	/**
	 * Create or return an internal link between both nodes.
	 */
	protected DEdge retrieveInternalLink(EdgeTarget sourceNode, EdgeTarget targetNode, FunctionalChain fc, RGBValues color) {
		HashMap<EdgeTarget, DEdge> outgoingEdges = new HashMap<EdgeTarget, DEdge>(); // outgoing Edges with targetNode as key

		// find displayed internal links
		for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(sourceNode)) {
			if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalChain) && (anEdge.getTarget().equals(fc))) {
				outgoingEdges.put(anEdge.getTargetNode(), anEdge);
			}
		}

		DEdge internalLink = outgoingEdges.get(targetNode);
		if (internalLink == null) {
			internalLink = createInternalLink(sourceNode, targetNode, fc, color);
		}
		if (internalLink != null) {
			customizeInternalLinksEdgeStyle(internalLink, color);
		}
		return internalLink;
	}

	/**
	 * @param fc
	 * @return
	 */
	protected Collection<FunctionalExchange> getFlatPreviousFunctionalExchanges(FunctionalChain fc, FunctionalChainInvolvement fci) {
		Collection<FunctionalExchange> result = new HashSet<FunctionalExchange>();

		for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatPreviousExchangeInvolvements(fci)) {
			if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof FunctionalExchange)) {
				result.add((FunctionalExchange) involvment.getInvolved());
			}
		}

		return result;
	}

	/**
	 * @param fc
	 * @return
	 */
	protected Collection<FunctionalExchange> getFlatNextFunctionalExchanges(FunctionalChain fc, FunctionalChainInvolvement fci) {
		Collection<FunctionalExchange> result = new HashSet<FunctionalExchange>();

		for (FunctionalChainInvolvement involvment : FunctionalChainExt.getFlatNextExchangeInvolvements(fci)) {
			if ((involvment.getInvolved() != null) && (involvment.getInvolved() instanceof FunctionalExchange)) {
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
	public DEdge createInternalLink(EdgeTarget sourceNode, EdgeTarget targetNode, FunctionalChain fc, RGBValues color) {
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
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram, MappingConstantsHelper.getInternLinkEdgeMapping(diagram));
	}

	/**
	 * @param aEdge
	 */
	 public void resetFunctionalExchangeStyle(DEdge aEdge) {
		 DiagramElementMapping mapping = DiagramServices.getDiagramServices().getEdgeMapping(aEdge);
		 if (mapping != null) {
			 // get default style size of an edge
			 EdgeStyleDescription desc =
					 (EdgeStyleDescription) getMappingHelper(aEdge).getBestStyleDescription(mapping, aEdge.getTarget(), aEdge, aEdge.eContainer(),
							 CapellaServices.getService().getDiagramContainer(aEdge));
			 String defaultStyleSize = desc.getSizeComputationExpression();
			 // get current style size of an edge
			 EdgeStyle edgeStyle = aEdge.getOwnedStyle();
			 Integer currentSize = edgeStyle.getSize();

			 if ((null != currentSize) && (null != defaultStyleSize)) {
				 // apply style & color : if currentSize is equal to default size + if current size is equal to default size of Functional Chain
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
			 BorderedStyleDescription desc =
					 (BorderedStyleDescription) getMappingHelper(functionNode).getBestStyleDescription(functionNode.getDiagramElementMapping(),
							 functionNode.getTarget(), functionNode, functionNode.eContainer(), CapellaServices.getService().getDiagramContainer(functionNode));

			 if(desc != null){
				 defaultStyleSize = desc.getBorderSizeComputationExpression();
				 style = (BorderedStyle) ShapeUtil.getCurrentStyle(node);
				 if(style != null){
					 currentSize = style.getBorderSize();
				 }


				 if ((null != currentSize) && (null != defaultStyleSize)) {
					 // apply style & color : if currentSize is equal to default size + if current size is equal to default size of Functional Chain
					 if (currentSize.equals(THICK_BORDER_SOURCE_FUNCTION) || currentSize.equals(THICK_BORDER_TARGET_FUNCTION) || currentSize.equals(defaultStyleSize)) {
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
			 EdgeStyleDescription desc =
					 (EdgeStyleDescription) getMappingHelper(edge).getBestStyleDescription(mapping, edge.getTarget(), edge, edge.eContainer(),
							 CapellaServices.getService().getDiagramContainer(edge));
			 if (null != desc) {
				 // assuming it is an integer value
				 String defaultSize = desc.getSizeComputationExpression();
				 if ((null != defaultSize) && (null != currentSize)
						 && (currentSize.equals(Integer.valueOf(defaultSize)) || (currentSize.equals(THICK_EDGE_FUNCTIONAL_CHAIN)))) {
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
	 public Set<DDiagramElement> getBestDisplayedFunctionNode(AbstractFunction function, Map<AbstractFunction, Set<DDiagramElement>> displayedFunctions) {
		 if (displayedFunctions.containsKey(function)) {
			 return displayedFunctions.get(function);
		 }
		 EObject ancestor = function.eContainer();
		 while ((ancestor != null) && (ancestor instanceof AbstractFunction)) {
			 if (displayedFunctions.containsKey(ancestor)) {
				 return displayedFunctions.get(ancestor);
			 }
			 ancestor = ancestor.eContainer();
		 }
		 return null;
	 }

	 public boolean isCompleteFunctionalChain(FunctionalChain fc, DDiagram diagram) {
		 Set<FunctionalExchange> visibleFE = new HashSet<FunctionalExchange>();
		 for (DEdge anEdge : diagram.getEdges()) {
			 if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalExchange)) {
				 visibleFE.add((FunctionalExchange) anEdge.getTarget());
			 }
		 }

		 for (FunctionalExchange anElement : FunctionalChainExt.getFlatFunctionalExchanges(fc)) {
			 if (!(visibleFE.contains(anElement))) {
				 return false;
			 }
		 }
		 return true;
	 }

	 public String getFunctionalChainLabel(FunctionalChain fc, DDiagram diagram) {
		 String label = EObjectExt.getText(fc);

		 boolean isComplete = isCompleteFunctionalChain(fc, diagram);
		 boolean isValid = FunctionalChainExt.isFunctionalChainValid(fc);
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

	 public void updateFunctionalChainNodeColor(DNode fcNode, Collection<DNode> visibleFunctionalChains) {
		 RGBValues color = ShapeUtil.getNodeColorStyle(fcNode);
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

	 public List<FunctionalExchange> getAvailableExchanges(EObject context, AbstractFunction source, AbstractFunction target) {
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

	 public List<FunctionalChainInvolvement> getPreviousInvolvements(FunctionalChainInvolvement involvement) {
		 return involvement.getPreviousFunctionalChainInvolvements();
	 }

	 public EObject createFunctionalChain(EObject context, List<EObject> views) {
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
		 return context;
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

	 public FunctionalChainAbstractCapabilityInvolvement createFunctionalChainAbstractCapabilityInvolvement(AbstractCapability capability,
			 FunctionalChain target) {
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

	 public List<AbstractFunction> getFCDInvolveFunctionScope(EObject container) {
		 BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(container);
		 return FunctionExt.getAllAbstractFunctions(architecture);
	 }

	 protected List<FunctionalExchange> getOutgoingEdgeFunctionalExchanges(DNode node) {
		 List<FunctionalExchange> existingInvolvedFE = new ArrayList<FunctionalExchange>();
		 for (DEdge anEdge : DiagramServices.getDiagramServices().getOutgoingEdges(node)) {

			 if ((anEdge.getTarget() != null) && (anEdge.getTarget() instanceof FunctionalChainInvolvement)) {
				 FunctionalChainInvolvement currentInv = (FunctionalChainInvolvement) anEdge.getTarget();
				 if ((currentInv.getInvolved() != null) && (currentInv.getInvolved() instanceof FunctionalExchange)) {
					 existingInvolvedFE.add((FunctionalExchange) currentInv.getInvolved());
				 }
			 }
		 }
		 return existingInvolvedFE;
	 }

	 public List<FunctionalExchange> getFCDInvolveFunctionalExchangeAndFunctionScope(DNode node) {
		 List<FunctionalExchange> returnedList = new ArrayList<FunctionalExchange>();
		 List<FunctionalExchange> existingInvolvedFE = getOutgoingEdgeFunctionalExchanges(node);

		 FunctionalChainInvolvement selectedInvolvement = (FunctionalChainInvolvement) node.getTarget();
		 for (FunctionalExchange aFE : FunctionalChainExt.getFlatOutgoingExchanges(selectedInvolvement)) {
			 if (!existingInvolvedFE.contains(aFE)) {
				 returnedList.add(aFE);
			 }
		 }

		 return returnedList;
	 }

	 public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainScope(DNode node) {
		 HashMapSet<FunctionalExchange, FunctionalChain> set = new HashMapSet<FunctionalExchange, FunctionalChain>();
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

		 List<FunctionalExchange> existingInvolvedFE = getOutgoingEdgeFunctionalExchanges(node);
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
	  * @param context
	  * @return
	  */
	  public HashMapSet<FunctionalExchange, FunctionalChain> getFCDInvolveFunctionalExchangeAndFunctionalChainInitialSelection(AbstractDNode context) {
		 return new HashMapSet<FunctionalExchange, FunctionalChain>();
	  }

	  /**
	   * @param context
	   * @param hashMapSet
	   * @param hashMapSet2
	   * @param hashMapSet3
	   */
	  public void involvedFCDFunctionalExchangeFunctionalChain(AbstractDNode context, HashMapSet<FunctionalExchange, FunctionalChain> scope,
			  HashMapSet<FunctionalExchange, FunctionalChain> initialSelection, HashMapSet<FunctionalExchange, FunctionalChain> selection) {

		  if ((context == null)) {
			  return;
		  }
		  DDiagram diagram = CapellaServices.getService().getDiagramContainer(context);
		  if ((diagram == null) || !(diagram instanceof DSemanticDecorator)) {
			  return;
		  }
		  FunctionalChain sourceFC = (FunctionalChain) ((DSemanticDecorator) diagram).getTarget();
		  EObject target = context.getTarget();
		  if ((target == null) || target.eIsProxy() || !(target instanceof FunctionalChainInvolvement)) {
			  return;
		  }

		  FunctionalChainInvolvement iSource = (FunctionalChainInvolvement) ((DSemanticDecorator) context).getTarget();

		  for (FunctionalExchange exchange : selection.keySet()) {
			  for (FunctionalChain chain : selection.get(exchange)) {
				  FunctionalChainInvolvement iExchange = FaFactory.eINSTANCE.createFunctionalChainInvolvement();
				  iExchange.setInvolved(exchange);
				  sourceFC.getOwnedFunctionalChainInvolvements().add(iExchange);
				  iSource.getNextFunctionalChainInvolvements().add(iExchange);

				  FunctionalChainReference iChain = FaFactory.eINSTANCE.createFunctionalChainReference();
				  iChain.setInvolved(chain);
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
				  DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) context, (EdgeTarget) node, iExchange);
			  }
		  }
	  }

	  @Deprecated
	  public List<FunctionalExchange> getAvailableFunctionalExchangeToInsertInFCD(DNode node) {
		  return getFCDInvolveFunctionalExchangeAndFunctionScope(node);
	  }

	  public boolean isValidFCDInvolveFunctionalExchange(EObject context, FunctionalChainInvolvement source, FunctionalChainInvolvement target) {
		  Collection<FunctionalExchange> commonExchanges = getFCDCommonFunctionalExchanges(source, target);
		  boolean result = !commonExchanges.isEmpty();
		  return (result && !findInvolvementInNext(target, source, new HashSet<FunctionalChainInvolvement>()));
	  }

	  public boolean isValidFCDInvolveFunctionalChain(DSemanticDecorator context) {
		  return true;
	  }

	  public boolean isValidFCDInvolveFunction(DSemanticDecorator context) {
		  return true;
	  }

	  public boolean isValidFCDInvolveFunctionalExchangeAndFunctionalChain(DSemanticDecorator context) {
		  if ((context == null) || (context instanceof DDiagram)) {
			  return false;
		  }
		  return true;
	  }

	  public boolean isValidFCDInvolveFunctionalExchangeAndFunction(DSemanticDecorator context) {
		  if ((context == null) || (context instanceof DDiagram)) {
			  return false;
		  }
		  return true;
	  }

	  @Deprecated
	  public boolean isAnEdgeInvolvementAvailableInFCD(final EObject context, final FunctionalChainInvolvement source, final FunctionalChainInvolvement target) {
		  return isValidFCDInvolveFunctionalExchange(context, source, target);
	  }

	  /**
	   * Returns common functional exchanges between both source and target involvement
	   * @param source
	   * @param target
	   */
	  private Collection<FunctionalExchange> getFCDCommonFunctionalExchanges(FunctionalChainInvolvement source, FunctionalChainInvolvement target) {
		  return FunctionalChainExt.getFlatCommonFunctionalExchanges(source, target);
	  }

	  public Collection<FunctionalExchange> getFCDInvolveFunctionalExchangeScope(EObject context, EObject source, EObject target) {
		  Collection<FunctionalExchange> commonExchanges =
				  getFCDCommonFunctionalExchanges((FunctionalChainInvolvement) source, (FunctionalChainInvolvement) target);
		  return commonExchanges;
	  }

	  public Collection<FunctionalChain> getFCDInvolveFunctionalChainScope(DSemanticDecorator diagram) {
		  EObject chain = diagram.getTarget();
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

	  public boolean findInvolvementInNext(final FunctionalChainInvolvement currentInvolvement, final FunctionalChainInvolvement involvementToFind,
			  final Set<FunctionalChainInvolvement> visitedInvolvements) {
		  if (visitedInvolvements.contains(currentInvolvement)) {
			  // to avoid infinite loop (cycle)
			  return false;
		  }
		  if (currentInvolvement.equals(involvementToFind)) {
			  return true;
		  }
		  Set<FunctionalChainInvolvement> involvements = new HashSet<FunctionalChainInvolvement>(visitedInvolvements);
		  involvements.add(currentInvolvement);
		  for (FunctionalChainInvolvement aNext : currentInvolvement.getNextFunctionalChainInvolvements()) {
			  if (findInvolvementInNext(aNext, involvementToFind, involvements)) {
				  return true;
			  }
		  }
		  return false;
	  }

	  public MappingHelper getMappingHelper(DSemanticDecorator semanticDecorator) {
		  return new MappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator.getTarget()));
	  }
}
