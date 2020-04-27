/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch;

/**
 * Slot associations for elements in the Information package.
 * 
 */
public class DatavalueSlots extends DatavalueSwitch<Object> {

  /**
   * Each DataValue gets its own slot => never collides
   * {@inheritDoc}
   */
  @Override
  public Object caseDataValue(DataValue dv){
    return dv;
  }
  
}
