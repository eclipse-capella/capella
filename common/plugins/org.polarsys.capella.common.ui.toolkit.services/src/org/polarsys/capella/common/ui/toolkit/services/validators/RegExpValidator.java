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
package org.polarsys.capella.common.ui.toolkit.services.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implements a validator that checks if given value matches a regular expression.
 */
public class RegExpValidator extends AbstractValidator {
  
  public static final int SUPPORT_EMPTY_VALUE = 1<<0;
  public static final int NONE = 0;
  /**
   * The regular expression to match.
   */
  private Pattern _regExpPattern;

  private int _style;
  
  /**
   * Constructs the regular expression validator.
   * @param errorMessage_p the error message displayed when given value do not match the regular expression.
   * @param regularExpression_p the regular expression to match.
   */
  public RegExpValidator(String errorMessage_p, String regularExpression_p, int style_p) {
    super(errorMessage_p);
    _regExpPattern = Pattern.compile(regularExpression_p);
    _style = style_p;
  }

  /**
   * Return the regular expression to match.
   * @return The regular expression pattern.
   */
  public Pattern getPattern() {
    return _regExpPattern;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.validators.IValidator#isValid(Object)
   */
  public String isValid(Object value_p) {
    boolean isValid = false;
    // Pre-condition : given object must be a string.
    if (value_p instanceof String) {
      String value = (String) value_p;
      if (value.length() > 0) {
        // Create a matcher for given value.
        Matcher matcher = _regExpPattern.matcher(value);
        // Does it match the regular expression ?
        isValid = matcher.matches();
      }
    }
    // If given value is not valid, returns the error message; otherwise null that means 'valid'.
    return (!isValid && !checkEmptyValue(value_p)) ? getErrorMessage() : null;
  }
  
  protected boolean checkEmptyValue(Object value_p) {
    if ((SUPPORT_EMPTY_VALUE & _style) != 0) {
      if (value_p instanceof String) {
        String value = (String) value_p;
        return (value.length() == 0);
      }
    }
    
    return false;
  }
}
