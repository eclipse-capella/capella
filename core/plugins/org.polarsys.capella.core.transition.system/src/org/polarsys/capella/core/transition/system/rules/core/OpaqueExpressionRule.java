/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
