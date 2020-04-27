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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueryHelper;

public class FunctionalChainInvolvementFunctions implements IQuery {

  /**
   * default
   */
  public FunctionalChainInvolvementFunctions() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    FunctionalChain functionalChain = QueryHelper.getFunctionalChain(object);

    if (isValidInstanceOf(functionalChain)) {
      return functionalChain.getOwnedFunctionalChainInvolvements().stream()
          .filter(FunctionalChainInvolvementFunction.class::isInstance)
          .map(FunctionalChainInvolvementFunction.class::cast).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  protected boolean isValidInstanceOf(FunctionalChain object) {
    return !(object instanceof OperationalProcess);
  }
}
