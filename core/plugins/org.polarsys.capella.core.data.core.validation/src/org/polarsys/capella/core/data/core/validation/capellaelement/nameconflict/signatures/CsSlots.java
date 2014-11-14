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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.util.CsSwitch;

/**
 * Slot assignment for elements in the Cs Package.
 * 
 */
public class CsSlots extends CsSwitch<Object> {

  /**
   * Each physical link gets its own slot => never conflicts
   * Currently covered by I_09, seems hard to implement with the 
   * slot/signature method
   * 
   * @param fe
   * @return
   */
  @Override
  public Object casePhysicalLink(PhysicalLink pl){
    return pl;
  }
  
}
