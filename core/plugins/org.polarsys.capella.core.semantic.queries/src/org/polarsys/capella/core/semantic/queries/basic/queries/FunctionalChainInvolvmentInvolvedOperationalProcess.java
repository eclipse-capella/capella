/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.oa.OperationalProcess;

/**
 */
public class FunctionalChainInvolvmentInvolvedOperationalProcess extends FunctionalChainInvolvmentInvolvedFunctionalChain {
  /**
   *  default
   */
  public FunctionalChainInvolvmentInvolvedOperationalProcess() {
    // Does nothing
  }

  @Override
  public boolean isValidInstanceOf(Object element_p) {
    if ((null != element_p) && (element_p instanceof FunctionalChainInvolvement)) {
      FunctionalChainInvolvement involvment = (FunctionalChainInvolvement) element_p;
      if ((involvment.getInvolved() instanceof FunctionalChain)) {
        if ((involvment.getInvolver() instanceof OperationalProcess)) {
          return true;
        }
      }
    }
    return false;
  }
}
