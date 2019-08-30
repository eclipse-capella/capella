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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class SequenceLinkEndStatusHelper {

  // form the objects to be binded in the message that will be displayed
  public static Object[] getStatusInfo(SequenceLinkEnd seqLinkEnd) {
    String nodeName = "";
    String nodeClassName = "ControlNode";
    if (seqLinkEnd instanceof FunctionalChainInvolvementFunction) {
      nodeName = ((AbstractFunction) ((FunctionalChainInvolvementFunction) seqLinkEnd).getInvolved()).getName();
      nodeClassName = "FunctionalChainInvolvementFunction";
    }
    return new Object[] { nodeName, nodeClassName };
  }
}
