/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.SequenceLink;

public class SequenceLinkLinks implements IQuery {

  /**
   * 
   */
  public SequenceLinkLinks() {
    // do nothing
  }

  /**
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    if (object instanceof SequenceLink) {
      SequenceLink sequenceLink = (SequenceLink) object;
      return new ArrayList<>(sequenceLink.getLinks());
    }
    return Collections.emptyList();
  }
}
