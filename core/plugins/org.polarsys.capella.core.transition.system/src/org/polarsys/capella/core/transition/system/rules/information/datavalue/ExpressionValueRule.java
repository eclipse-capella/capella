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
package org.polarsys.capella.core.transition.system.rules.information.datavalue;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class ExpressionValueRule extends NumericValueRule {

  public ExpressionValueRule() {
    super();
    registerUpdatedAttribute(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION);
    registerUpdatedAttribute(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION);
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE;
  }

}
