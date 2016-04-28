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

package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;

public class ConstraintExt {

  /**
   * The language key used in opaque expressions to denote linked text bodies
   */
  public static final String OPAQUE_EXPRESSION_LINKED_TEXT = "capella:linkedText"; //$NON-NLS-1$

  public static String getBody(OpaqueExpression expression, String language) {
    if (!expression.getLanguages().contains(language)) {
      return null;
    }

    int position = expression.getLanguages().indexOf(language);
    if (position < expression.getBodies().size()) {
      return expression.getBodies().get(position);
    }

    return null;
  }

  public static void setBody(OpaqueExpression expression, String language, String body) {
    // Add the specified language
    if (!expression.getLanguages().contains(language)) {
      expression.getLanguages().add(language);
    }

    // Add the specified body at the language position
    int position = expression.getLanguages().indexOf(language);
    while (expression.getBodies().size() < position) {
      expression.getBodies().add(ICommonConstants.EMPTY_STRING);
    }
    expression.getBodies().set(position, body);
  }

}
