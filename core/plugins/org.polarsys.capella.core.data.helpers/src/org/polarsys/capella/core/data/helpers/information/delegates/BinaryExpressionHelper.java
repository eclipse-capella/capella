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
package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.information.services.DataValueNamingHelper;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class BinaryExpressionHelper {
  private static BinaryExpressionHelper instance;

  private BinaryExpressionHelper() {
    // do nothing
  }

  public static BinaryExpressionHelper getInstance() {
    if (instance == null)
      instance = new BinaryExpressionHelper();
    return instance;
  }

  public Object doSwitch(BinaryExpression element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION)) {
      return ret = getExpression(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractExpressionValueHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected String getExpression(BinaryExpression element_p) {
    // If operator is the UNSET one, we directly use the unparsed expression.
    if (BinaryOperator.UNSET.equals(element_p.getOperator())) {
      return element_p.getUnparsedExpression();
    }
    return DataValueNamingHelper.getValue(element_p, null, true);
  }
}
