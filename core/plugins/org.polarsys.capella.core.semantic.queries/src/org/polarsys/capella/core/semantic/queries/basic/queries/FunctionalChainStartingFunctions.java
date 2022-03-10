/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;

public class FunctionalChainStartingFunctions implements IQuery {

    @Override
    public List<Object> compute(Object object) {
        if (isValidInstanceOf(object)) {
            InvolvementGraph graph = new InvolvementGraph((FunctionalChain) object);
            return graph.getNodes().values().stream().filter(n -> graph.isStartingFunction(n)).map(n -> n.getSemantic()).filter(FunctionalChainInvolvementFunction.class::isInstance)
                    .map(fci -> ((FunctionalChainInvolvementFunction) fci).getInvolved()).distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
        

    }

    protected boolean isValidInstanceOf(Object object) {
        return !(object instanceof OperationalProcess);
    }

}
