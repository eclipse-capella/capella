/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

/**
 * An edge
 */
public class GraphEdge<ES, N extends GraphNode> {
  
  protected ES semantic;

  protected N source;
  
  protected N target;

  public GraphEdge(ES semantic) {
    this.semantic = semantic;
  }

  public ES getSemantic() {
    return semantic;
  }

  public N getSource() {
    return source;
  }

  public N getTarget() {
    return target;
  }

  public void setSource(N newSource) {
    if (source != null) {
      source.outgoingEdges.remove(this);
    }
    source = newSource;
    if (source != null) {
      source.outgoingEdges.add(this);
    }
  }

  public void setTarget(N newTarget) {
    if (target != null) {
      target.incomingEdges.remove(this);
    }
    target = newTarget;
    if (target != null) {
      target.incomingEdges.add(this);
    }
  }

  public String toString() {
    return "e(" + EObjectLabelProviderHelper.getText(semantic) + ")";
  }
}