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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class SequenceLinkEndTargetSequenceLinks implements IQuery {

  public SequenceLinkEndTargetSequenceLinks() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    if (object instanceof SequenceLinkEnd) {
      SequenceLinkEnd sequenceLinkEnd = (SequenceLinkEnd) object;
      return new ArrayList<>(EObjectExt.getReferencers(sequenceLinkEnd, FaPackage.Literals.SEQUENCE_LINK__SOURCE));
    }
    return Collections.emptyList();
  }
}
