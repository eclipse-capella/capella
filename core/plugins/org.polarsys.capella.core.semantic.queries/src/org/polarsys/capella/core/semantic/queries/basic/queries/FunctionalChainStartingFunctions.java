/*******************************************************************************
 * This program is the Confidential and Proprietary product of Thales Global   *
 * Services. Any unauthorized use, reproduction or transfer of this program is *
 * strictly prohibited.                                                        *
 *                                                                             *
 * Copyright (c) 2022 Thales Global Services. All Rights Reserved.             *
 * *****************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;

public class FunctionalChainStartingFunctions implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    InvolvementGraph graph = new InvolvementGraph((FunctionalChain) object);
    return graph.getNodes().values().stream().filter(n -> graph.isStartingFunction(n))
        .map(n -> n.getSemantic()).filter(FunctionalChainInvolvementFunction.class::isInstance)
        .map(fci -> ((FunctionalChainInvolvementFunction) fci).getInvolved()).distinct().collect(Collectors.toList());
  }

}
