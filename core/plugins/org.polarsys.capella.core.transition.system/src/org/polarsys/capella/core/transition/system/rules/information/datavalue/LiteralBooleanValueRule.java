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

package org.polarsys.capella.core.transition.system.rules.information.datavalue;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class LiteralBooleanValueRule extends DataValueRule {

  public LiteralBooleanValueRule() {
    super();
    registerUpdatedAttribute(DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE__VALUE);
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE;
  }

}
