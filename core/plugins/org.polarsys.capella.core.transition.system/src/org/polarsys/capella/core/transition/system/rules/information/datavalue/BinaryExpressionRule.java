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

import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class BinaryExpressionRule extends ExpressionValueRule {

  public BinaryExpressionRule() {
    super();
    registerUpdatedAttribute(DatavaluePackage.Literals.BINARY_EXPRESSION__OPERATOR);
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.BINARY_EXPRESSION;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    BinaryExpression element = (BinaryExpression) source_p;
    result_p.add(element.getOwnedLeftOperand());
    result_p.add(element.getOwnedRightOperand());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedLeftOperand(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedRightOperand(), context_p);
    }

  }

}
