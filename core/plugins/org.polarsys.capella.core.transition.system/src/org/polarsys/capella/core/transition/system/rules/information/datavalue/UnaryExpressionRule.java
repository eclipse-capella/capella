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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class UnaryExpressionRule extends ExpressionValueRule {

  public UnaryExpressionRule() {
    super();
    registerUpdatedAttribute(DatavaluePackage.Literals.UNARY_EXPRESSION__OPERATOR);
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.UNARY_EXPRESSION;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    UnaryExpression element = (UnaryExpression) source_p;
    result_p.add(element.getOwnedOperand());
  }

}
