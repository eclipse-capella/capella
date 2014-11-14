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
package org.polarsys.capella.core.refinement.scheduler;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.Node;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.SchedulerException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IScheduler;

/**
 */
public class StandardScheduler implements IScheduler {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Standard Scheduler"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IScheduler#doOrdering()
   */
  public void doOrdering(ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, AbstractInstance finalAbstractInstance, Node<InteractionFragment> currentSrcNode, NamedElement tgtElement) throws SchedulerException {
	  boolean wrongParentNode = false;
	  boolean wrongPreviousNode = false;
	  Node<InteractionFragment> currentTgtNode = null;
	  Node<InteractionFragment> realPreviousNode = null;
	  Node<InteractionFragment> parentNodeFromSource = null;
	  Node<InteractionFragment> parentNodeFromTarget = null;
	  Node<InteractionFragment> previousNodeFromSource = null;
	  Node<InteractionFragment> previousNodeFromTarget = null;

	  /**
	   * Case of a source 'Message' not linked to a target 'Message'
	   * Creation of a new 'Message' node in target tree by cloning current source node
	   */
	  if (currentSrcNode.relatedNode == null) {
		  currentTgtNode = tgtTree.cloneNode(currentSrcNode, finalAbstractInstance, tgtElement);

		  /**
		   * Tentatively, the new node is placed as the last child of 'MessageRoot' in the target tree because 
		   * it will be automatically repositioned below.
		   */
		  tgtTree.addLastChild(tgtTree.getRootNode(), currentTgtNode);
	  }
	  /**
	   * Search node location in tree.
	   */
//	  if (finalPart != null) {
		  if (currentSrcNode.relatedNode != null) {
			  currentTgtNode = currentSrcNode.relatedNode;
			  
			  // Check if the source operation have been changed still the previous refinement 
			  tgtTree.updateOperationNode(currentSrcNode, currentTgtNode);

			  /**
			   * Search first parent node, in source tree, linked to a target tree node.
			   * Get node of target tree.
			   */
			  Node<InteractionFragment> firstLinkedParent = currentSrcNode.getFirstLinkedParent();
			  if (firstLinkedParent != null) parentNodeFromSource = firstLinkedParent.relatedNode;

			  /**
			   * Search first predecessor, in source tree, linked to a target tree node.
			   * Get node of target tree.
			   */
			  Node<InteractionFragment> firstLinkedPredecessor = currentSrcNode.getFirstLinkedPredecessor();
			  if (firstLinkedPredecessor != null) previousNodeFromSource = firstLinkedPredecessor.relatedNode;

			  /**
			   * Search first predecessor, in target tree, linked to a source tree node.
			   * Get node of target tree.
			   */
			  previousNodeFromTarget = currentTgtNode.getFirstLinkedPredecessor();

			  /**
			   * Search first parent node, in target tree, linked to a source tree node.
			   * Get node of target tree.
			   */
			  parentNodeFromTarget = currentTgtNode.getFirstLinkedParent();

			  /**
			   * Synthesis of various researches in order to detect possible positioning errors.
			   */
			  wrongPreviousNode = (previousNodeFromSource != previousNodeFromTarget);
			  wrongParentNode   = (parentNodeFromTarget   != parentNodeFromSource);

			  /**
			   * If the first linked parent is common, it is not necessary to change the parent of the target node
			   * (
			   * (presence of not linked intermediate node(s)).
			   */
			  if (!wrongParentNode) {
				  parentNodeFromSource = currentTgtNode.getParent();
			  }
		  }
		  else {
			  /**
			   * Search first parent node, in source tree, linked to a target tree node.
			   * Get node of target tree.
			   */
			  Node<InteractionFragment> firstLinkedParent = currentSrcNode.getFirstLinkedParent();
			  if (firstLinkedParent != null) parentNodeFromSource = firstLinkedParent.relatedNode;

			  /**
			   * Search first predecessor, in source tree, linked to a target tree node.
			   * Get node of target tree.
			   */
			  Node<InteractionFragment> firstLinkedPredecessor = currentSrcNode.getFirstLinkedPredecessor();
			  if (firstLinkedPredecessor != null) previousNodeFromSource = firstLinkedPredecessor.relatedNode;

			  /**
			   * In creation case, the positioning is forced.
			   */
			  wrongPreviousNode = true;
			  wrongParentNode   = true;
		  }

		  if ((parentNodeFromSource != null) && (wrongParentNode || wrongPreviousNode)) {
			  /**
			   * Move of target node to its new location in tree
			   */
			  tgtTree.unChainCurrentNodeMessage(currentTgtNode);
			  realPreviousNode = srcTree.getChildBranchContainingNode(parentNodeFromSource, previousNodeFromSource);
			  tgtTree.addChildAfter(parentNodeFromSource, currentTgtNode, realPreviousNode);
		  }
  }
}
