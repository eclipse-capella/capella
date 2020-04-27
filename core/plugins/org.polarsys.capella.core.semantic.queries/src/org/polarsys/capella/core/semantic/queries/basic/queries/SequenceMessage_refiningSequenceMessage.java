/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class SequenceMessage_refiningSequenceMessage implements IQuery {

  public SequenceMessage_refiningSequenceMessage() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof SequenceMessage) {
      SequenceMessage seqMess = (SequenceMessage) object;
      for (CapellaElement meloElt : RefinementLinkExt.getRefinementRelatedTargetElements(seqMess, InteractionPackage.Literals.SEQUENCE_MESSAGE)) {
        SequenceMessage tgtSeqMess = (SequenceMessage) meloElt;
        if (!((Scenario) tgtSeqMess.eContainer()).isMerged()) {
          // Skip Scenario parent is Merge 
          result.add(tgtSeqMess);
        }
      }
    }
    return result;
  }
}
