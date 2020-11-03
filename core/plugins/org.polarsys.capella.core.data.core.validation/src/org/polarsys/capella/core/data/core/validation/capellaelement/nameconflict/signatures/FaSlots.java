/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.util.FaSwitch;

/**
 * Slot associations for elements in the Fa Package.
 */
public class FaSlots extends FaSwitch<Object> {

  /**
   * Each functional exchange gets its own slot => never conflicts
   * Currently covered by I_09, seems hard to implement with the 
   * slot/signature method
   * 
   * @param fe
   * @return
   */
  @Override
  public Object caseFunctionalExchange(FunctionalExchange fe){
    return fe;
  }
  
  /**
   * Each component exchange gets its own slot => never conflicts
   * Currently covered by I_09, seems hard to implement with the 
   * slot/signature method
   * 
   * @param fe
   * @return
   */
  @Override
  public Object caseComponentExchange(ComponentExchange ce){
    return ce;
  }
  
}
