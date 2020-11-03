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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;

public class FunctionalChainCache {

  private static FunctionalChainCache instance;

  public static FunctionalChainCache getInstance() {
    if (instance == null) {
      instance = new FunctionalChainCache();
    }
    return instance;
  }

  private Map<FunctionalChain, InvolvementGraph> involvmentGraphs = new HashMap<>();

  private Map<InvolvementGraph, InternalLinksGraph> internalLinksGraphs = new HashMap<>();

  private FunctionalChainCache() {
  }

  public InvolvementGraph getInvolvementGraph(FunctionalChain chain) {
    return involvmentGraphs.computeIfAbsent(chain, InvolvementGraph::new);
  }

  public InternalLinksGraph getInternalLinksGraph(InvolvementGraph graph) {
    return internalLinksGraphs.computeIfAbsent(graph, InternalLinksGraph::new);
  }

  public void reset() {
    involvmentGraphs.clear();
    internalLinksGraphs.clear();
  }

}
