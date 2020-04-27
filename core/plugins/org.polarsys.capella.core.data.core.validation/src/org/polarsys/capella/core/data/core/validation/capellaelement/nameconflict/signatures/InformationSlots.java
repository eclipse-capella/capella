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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.util.InformationSwitch;

/**
 * Slot associations for elements in the InformationPackage.
 * 
 */
public class InformationSlots extends InformationSwitch<Object> {

  /**
   * Associations never collide with other associations.
   * {@inheritDoc}
   */
  @Override
  public Object caseAssociation(Association association){
    return association;
  }
  
}
