/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;

public class UnaryExpressionHelper {
  private static UnaryExpressionHelper instance;

  private UnaryExpressionHelper() {
    // do nothing
  }

  public static UnaryExpressionHelper getInstance() {
    if (instance == null)
      instance = new UnaryExpressionHelper();
    return instance;
  }

  public Object doSwitch(UnaryExpression element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION)) {
      return ret = getExpression(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractExpressionValueHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected String getExpression(UnaryExpression element) {
    // If operator is the UNSET one, we directly use the unparsed expression.
    if (UnaryOperator.UNSET.equals(element.getOperator())) {
      return element.getUnparsedExpression();
    }
    return DataValueNamingHelper.getValue(element, null, true);
  }
}
