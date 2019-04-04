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
package org.polarsys.capella.core.data.fa.validation.sequenceLink;

import java.util.HashSet;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;

/*
 * DWF_DF_20 - SequenceLink is in opposite direction to involved FunctionalExchange
 */
public class SequenceLinkOppositeDirectionToFE extends SequenceLinkWithInconsistentAssociatedLinks {
  public static final String SequenceLink_Opposite_Direction = "(SequenceLink) between {0}({1}) and {2}({3}) is in opposite direction to involved {4}(FunctionalExchange).";

  @Override
  protected boolean isValid(FunctionalChainInvolvementLink link,
      HashSet<FunctionalChainInvolvementFunction> slClosestFCIFSources,
      HashSet<FunctionalChainInvolvementFunction> slClosestFCIFTargets) {
    // link is opposite to FE (source of SL is in target of FE and same for target)
    return !checkConditionOppositeDirection(link, slClosestFCIFSources, slClosestFCIFTargets);
  }

  @Override
  protected String getMessagePattern() {
    return SequenceLink_Opposite_Direction;
  }
}
