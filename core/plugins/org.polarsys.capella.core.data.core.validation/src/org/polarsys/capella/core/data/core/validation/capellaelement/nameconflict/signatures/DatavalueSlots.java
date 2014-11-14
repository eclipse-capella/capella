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
