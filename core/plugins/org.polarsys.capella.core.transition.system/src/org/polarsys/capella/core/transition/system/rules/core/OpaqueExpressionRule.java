/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.core;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;

public class OpaqueExpressionRule extends AbstractCapellaElementRule {

  public OpaqueExpressionRule() {
    super();
    registerUpdatedAttribute(DatavaluePackage.Literals.OPAQUE_EXPRESSION__BODIES);
    registerUpdatedAttribute(DatavaluePackage.Literals.OPAQUE_EXPRESSION__LANGUAGES);
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.OPAQUE_EXPRESSION;
  }
}
