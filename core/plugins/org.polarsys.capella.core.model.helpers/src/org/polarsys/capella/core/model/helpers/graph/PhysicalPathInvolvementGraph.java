/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph.InvolvementEdge;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph.InvolvementNode;

public class PhysicalPathInvolvementGraph
    extends Graph<PhysicalPath, PhysicalPathInvolvement, PhysicalPathInvolvementLink, InvolvementNode, InvolvementEdge> {

  public class InvolvementEdge extends GraphEdge<PhysicalPathInvolvementLink, InvolvementNode> {
    public InvolvementEdge(PhysicalPathInvolvementLink semantic) {
      super(semantic);
    }
  }

  public class InvolvementNode extends GraphNode<PhysicalPathInvolvement, InvolvementEdge> {
    public InvolvementNode(PhysicalPathInvolvement semantic) {
      super(semantic);
    }
  }

  public PhysicalPathInvolvementGraph(PhysicalPath path) {
    super(path);
    for (PhysicalPathInvolvement inv : PhysicalPathExt.getFlatInvolvements(path)) {
      if (inv.getInvolved() instanceof Component) {
        getOrCreateNode(inv);
      } else if (inv.getInvolved() instanceof PhysicalLink) {
        for (PhysicalPathInvolvement source : PhysicalPathExt.getFlatPreviousPhysicalPathInvolvements(inv)) {
          for (PhysicalPathInvolvement target : PhysicalPathExt.getFlatNextPhysicalPathInvolvements(inv)) {
            PhysicalPathInvolvementLink ppInvLink = new PhysicalPathInvolvementLink(inv, source, target);
            if (!hasEdge(ppInvLink)) {
              InvolvementEdge invEdge = getOrCreateEdge(ppInvLink);
              invEdge.setSource(getOrCreateNode(source));
              invEdge.setTarget(getOrCreateNode(target));
            }
          }  
        }
      }
    }
  }

  public boolean isInvolvingPhysicalLink(InvolvementEdge edge) {
    PhysicalPathInvolvementLink semantic = edge.getSemantic();
    return semantic != null
        && semantic.getInvolvement().getInvolved() instanceof PhysicalLink;
  }

  public PhysicalLink getInvolvedPhysicalLink(InvolvementEdge edge) {
    PhysicalPathInvolvementLink semantic = edge.getSemantic();
    if (semantic != null) {
      return (PhysicalLink) semantic.getInvolvement().getInvolved();
    }
    return null;
  }
  
  public static Set<PhysicalPort> getInvolvedPhysicalPorts(InvolvementEdge edge) {
    PhysicalPathInvolvementLink semantic = edge.getSemantic();
    if (semantic != null) {
      PhysicalLink pl = (PhysicalLink) semantic.getInvolvement().getInvolved();
      return new HashSet<>(Arrays.asList(pl.getSourcePhysicalPort(), pl.getTargetPhysicalPort()));
    }
    return Collections.emptySet();
  }
  
  public static boolean containsPort(InvolvementNode node, PhysicalPort port) {
    PhysicalPathInvolvement semantic = node.getSemantic();
    if (semantic != null) {
      return semantic.getInvolvedComponent().getContainedPhysicalPorts().contains(port);
    }
    return false;
  }

  /**
   * 
   * @param edge
   * @param node
   * @return the physical port that connects the edge and the node
   */
  public static PhysicalPort getInvolvedPhysicalPort(InvolvementEdge edge, InvolvementNode node) {
    for (PhysicalPort port : getInvolvedPhysicalPorts(edge)) {
      if (containsPort(node, port)) {
        return port;
      }
    }
    return null;
  }

  @Override
  public InvolvementNode createNode(PhysicalPathInvolvement semantic) {
    return new InvolvementNode(semantic);
  }

  @Override
  public InvolvementEdge createEdge(PhysicalPathInvolvementLink semantic) {
    return new InvolvementEdge(semantic);
  }
}