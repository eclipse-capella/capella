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

import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Return all the available Elements of State & Mode
 *
 */
public class AbstractStateRealizedMode extends AbstractStateRealizationStateAndMode{

  public AbstractStateRealizedMode() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractStateRealizationStateAndMode#getTargetOrSourceElement(org.polarsys.capella.core.data.capellacommon.AbstractStateRealization)
   */
  @Override
  public TraceableElement getTargetOrSourceElement(AbstractStateRealization realization) {
    return realization.getTargetElement();
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractStateRealizationStateAndMode#isValidElement(org.polarsys.capella.core.data.capellacommon.AbstractState)
   */
  @Override
  public boolean isValidElement(IState abstractState) {
    if (abstractState != null && abstractState instanceof Mode) {
      return true;
    }
    
    return false;
  }
  
  
}
