/*******************************************************************************
 * This program is the Confidential and Proprietary product of Thales Global   *
 * Services. Any unauthorized use, reproduction or transfer of this program is *
 * strictly prohibited.                                                        *
 *                                                                             *
 * Copyright (c) 2022 Thales Global Services. All Rights Reserved.             *
 * *****************************************************************************/
package org.polarsys.capella.core.semantic.queries.technical.queries;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;

public class FunctionalChainInternalLinks implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    InvolvementGraph graph = new InvolvementGraph((FunctionalChain) object);
    if (!(object instanceof OperationalProcess)) {
      InternalLinksGraph iGraph = new InternalLinksGraph(graph);
      return iGraph.getEdges().values().stream().distinct()
          .collect(Collectors.toCollection(() -> new TreeSet<>(
              Comparator.comparing((InternalLinksGraph.InternalLinkEdge e) -> EcoreUtil.getID(e.getSource().getSemantic()))
                  .thenComparing((InternalLinksGraph.InternalLinkEdge e) -> EcoreUtil.getID(e.getTarget().getSemantic())))))
          .stream().collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
