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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.SequenceLink;

public class GetCurrent_SequenceLink_Links extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {

    CapellaElement capellaElement = (CapellaElement) input;
    List<Object> currentElements = getCurrentElements(capellaElement);

    return currentElements;
  }

  private List<Object> getCurrentElements(CapellaElement element) {

    List<Object> currentElements = new ArrayList<Object>();
    if (element instanceof SequenceLink) {
      SequenceLink seqLink = (SequenceLink) element;
      currentElements.addAll(seqLink.getLinks());
    }

    return currentElements;
  }
}
