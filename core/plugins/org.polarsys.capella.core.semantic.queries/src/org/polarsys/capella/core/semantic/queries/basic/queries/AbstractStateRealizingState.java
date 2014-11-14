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

import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Return all the available Elements of State & Mode
 *
 */
public  class AbstractStateRealizingState extends AbstractStateRealizingStateAndMode{

  public AbstractStateRealizingState() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractStateRealizationStateAndMode#getTargetOrSourceElement(org.polarsys.capella.core.data.capellacommon.AbstractStateRealization)
   */
  @Override
  public TraceableElement getTargetOrSourceElement(AbstractStateRealization realization_p) {
    return realization_p.getSourceElement();
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractStateRealizationStateAndMode#isValidElement(org.polarsys.capella.core.data.capellacommon.AbstractState)
   */
  @Override
  public boolean isValidElement(IState abstractState_p) {
    if (abstractState_p != null && !(abstractState_p instanceof Mode)) {
      return true;
    }
    
    return false;
  }
  
  
  
}
