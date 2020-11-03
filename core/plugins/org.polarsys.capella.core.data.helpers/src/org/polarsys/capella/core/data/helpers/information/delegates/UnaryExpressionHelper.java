/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.information.services.DataValueNamingHelper;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;

public class UnaryExpressionHelper {
  private static UnaryExpressionHelper instance;

  private UnaryExpressionHelper() {
    // do nothing
  }

  public static UnaryExpressionHelper getInstance() {
    if (instance == null) {
    	instance = new UnaryExpressionHelper();
    }
    return instance;
  }

  public Object doSwitch(UnaryExpression element, EStructuralFeature feature) {

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION)) {
      return getExpression(element);
    }

    // no helper found... searching in super classes...
    return AbstractExpressionValueHelper.getInstance().doSwitch(element, feature);
  }

  protected String getExpression(UnaryExpression element) {
    // If operator is the UNSET one, we directly use the unparsed expression.
    if (UnaryOperator.UNSET.equals(element.getOperator())) {
      return element.getUnparsedExpression();
    }
    return DataValueNamingHelper.getValue(element, null, true);
  }
}
