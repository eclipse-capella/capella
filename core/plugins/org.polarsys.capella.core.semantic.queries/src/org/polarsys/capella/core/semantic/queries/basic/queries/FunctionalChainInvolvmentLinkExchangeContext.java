/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;

public class FunctionalChainInvolvmentLinkExchangeContext implements IQuery {

  public List<Object> compute(Object object) {

    if (object instanceof FunctionalChainInvolvementLink) {
      FunctionalChainInvolvementLink link = (FunctionalChainInvolvementLink) object;
      Constraint exchangeContext = link.getExchangeContext();
      if (exchangeContext != null) {
        return Arrays.asList(exchangeContext);
      }
    }
    return Collections.emptyList();
  }

}
