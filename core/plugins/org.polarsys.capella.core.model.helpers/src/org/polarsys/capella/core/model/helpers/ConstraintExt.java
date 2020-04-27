/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
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

  public Couple<String, String> getFirstOpaqueExpressionElement(EObject eObject) {
    if (!(eObject instanceof OpaqueExpression)) {
      return null;
    }
    OpaqueExpression oe = (OpaqueExpression) eObject;
    if (oe.getLanguages().isEmpty() || oe.getBodies().isEmpty()) {
      return null;
    }
    return new Couple<>(oe.getLanguages().get(0), oe.getBodies().get(0));
  }

  public static boolean hasBodies(OpaqueExpression expression) {
    if (expression == null) {
      return false;
    }
    return !expression.getBodies().isEmpty();
  }

  public static boolean hasPrimaryLinkedText(OpaqueExpression expression) {
    if (expression == null) {
      return false;
    }
    if (expression.getLanguages().isEmpty()) {
      return false;
    }
    return OPAQUE_EXPRESSION_LINKED_TEXT.equals(expression.getLanguages().get(0));
  }

  public static String getPrimaryBody(OpaqueExpression expression) {
    if (expression == null) {
      return null;
    }
    if (expression.getBodies().isEmpty()) {
      return null;
    }
    return expression.getBodies().get(0);
  }

  public static void setBody(OpaqueExpression expression, String language, String body) {
    if (expression == null) {
      return;
    }
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
