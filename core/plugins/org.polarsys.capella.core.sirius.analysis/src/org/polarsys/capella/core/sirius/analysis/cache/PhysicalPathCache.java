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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.HashMap;
import java.util.Map;

import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.PhysicalPathInvolvementGraph;

public class PhysicalPathCache {

  private static PhysicalPathCache instance;

  public static PhysicalPathCache getInstance() {
    if (instance == null) {
      instance = new PhysicalPathCache();
    }
    return instance;
  }

  private Map<PhysicalPath, PhysicalPathInvolvementGraph> involvementGraphs = new HashMap<>();

  private Map<PhysicalPathInvolvementGraph, PhysicalPathInternalLinksGraph> internalLinksGraphs = new HashMap<>();

  private PhysicalPathCache() {
  }

  public PhysicalPathInvolvementGraph getInvolvementGraph(PhysicalPath path) {
    return involvementGraphs.computeIfAbsent(path, PhysicalPathInvolvementGraph::new);
  }

  public PhysicalPathInternalLinksGraph getInternalLinksGraph(PhysicalPathInvolvementGraph graph) {
    return internalLinksGraphs.computeIfAbsent(graph, PhysicalPathInternalLinksGraph::new);
  }

  public void reset() {
    involvementGraphs.clear();
    internalLinksGraphs.clear();
  }

}
