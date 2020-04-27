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
package org.polarsys.capella.core.data.fa.validation.sequenceLink;

import java.util.Set;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;

/*
 * DWF_DF_20 - SequenceLink between the referenced Involvement Functions/OperationalActivities is in opposite direction to Involvement Links referencing them
 */
public class SequenceLinkOppositeDirectionToFE extends SequenceLinkWithInconsistentAssociatedLinks {
  public static final String SequenceLink_Opposite_Direction = "SequenceLink between {0}({1}) and {2}({3}) is in opposite direction to Involvement Links referencing them";

  @Override
  protected boolean isValid(FunctionalChainInvolvementLink link,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFSources,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFTargets) {
    // link is opposite to FE (source of SL is in target of FE and same for target)
    return !checkConditionOppositeDirection(link, slClosestFCIFSources, slClosestFCIFTargets);
  }

  @Override
  protected String getMessagePattern() {
    return SequenceLink_Opposite_Direction;
  }
}
