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
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    UnaryExpression element = (UnaryExpression) source;
    result.add(element.getOwnedOperand());
  }

}
