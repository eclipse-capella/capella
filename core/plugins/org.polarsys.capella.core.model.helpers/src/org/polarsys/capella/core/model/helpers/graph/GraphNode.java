/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

import com.google.common.collect.Streams;

/**
 * A node. A node can be connected to other nodes through edges
 */
public class GraphNode<NS, E extends GraphEdge> {
  
  protected NS semantic;

  protected Collection<E> outgoingEdges = new ArrayList<>();
  
  protected Collection<E> incomingEdges = new ArrayList<>();

  public GraphNode(NS semantic) {
    this.semantic = semantic;
  }

  public NS getSemantic() {
    return semantic;
  }

  public Collection<E> getOutgoingEdges() {
    return Collections.unmodifiableCollection(outgoingEdges);
  }

  public Collection<E> getIncomingEdges() {
    return Collections.unmodifiableCollection(incomingEdges);
  }

  public Collection<E> getConnectedEdges() {
    return Streams.concat(incomingEdges.stream(), outgoingEdges.stream()).collect(Collectors.toList());
  }
  
  public String toString() {
    return "n(" + EObjectLabelProviderHelper.getText(semantic) + ")";
  }
}
