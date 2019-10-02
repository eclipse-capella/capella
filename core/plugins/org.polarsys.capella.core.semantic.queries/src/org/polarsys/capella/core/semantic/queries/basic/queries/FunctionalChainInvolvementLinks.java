/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueryHelper;

public class FunctionalChainInvolvementLinks implements IQuery {

  public FunctionalChainInvolvementLinks() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    FunctionalChain functionalChain = QueryHelper.getFunctionalChain(object);

    if (functionalChain != null) {
      return functionalChain.getInvolvedFunctionalChainInvolvements().stream()
          .filter(FunctionalChainInvolvementLink.class::isInstance).map(FunctionalChainInvolvementLink.class::cast)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
