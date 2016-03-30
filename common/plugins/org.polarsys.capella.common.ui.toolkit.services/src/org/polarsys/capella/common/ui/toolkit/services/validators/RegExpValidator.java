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
   * @param errorMessage the error message displayed when given value do not match the regular expression.
   * @param regularExpression the regular expression to match.
   */
  public RegExpValidator(String errorMessage, String regularExpression, int style) {
    super(errorMessage);
    _regExpPattern = Pattern.compile(regularExpression);
    _style = style;
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
  public String isValid(Object value) {
    boolean isValid = false;
    // Pre-condition : given object must be a string.
    if (value instanceof String) {
      String val = (String) value;
      if (val.length() > 0) {
        // Create a matcher for given value.
        Matcher matcher = _regExpPattern.matcher(val);
        // Does it match the regular expression ?
        isValid = matcher.matches();
      }
    }
    // If given value is not valid, returns the error message; otherwise null that means 'valid'.
    return (!isValid && !checkEmptyValue(value)) ? getErrorMessage() : null;
  }
  
  protected boolean checkEmptyValue(Object value) {
    if ((SUPPORT_EMPTY_VALUE & _style) != 0) {
      if (value instanceof String) {
        return (((String) value).length() == 0);
      }
    }
    
    return false;
  }
}
