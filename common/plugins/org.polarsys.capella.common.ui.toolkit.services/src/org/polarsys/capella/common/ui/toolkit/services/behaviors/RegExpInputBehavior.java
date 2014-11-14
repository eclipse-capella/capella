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
package org.polarsys.capella.common.ui.toolkit.services.behaviors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidator;

/**
 * The regular expression input behavior.
 * @deprecated since 0.3.0, please use {@link RegExpValidator} to validate 
 */
public abstract class RegExpInputBehavior implements IInputBehavior {
  // The regular expression pattern.
  private Pattern _pattern = null;

  /**
   * Constructs the regular expression behavior.
   * @param The regular expression.
   * @deprecated
   */
  protected RegExpInputBehavior(String regularExpression_p) {
    _pattern = Pattern.compile(regularExpression_p);
  }

  /**
   * Gets the regular expression pattern.
   * @return The regular expression pattern.
   * @deprecated
   */
  public Pattern getPattern() {
    return _pattern;
  }

  /**
   * @deprecated
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#format(java.lang.String)
   */
  public String format(String input_p) {
    if (isValid(input_p)) {
      return doFormatText(input_p);
    }
    return input_p;
  }

  /**
   * Does the text formatting. This is the default implementation which returns the input.
   * @param input_p The input to format.
   * @return The formatted value.
   * @deprecated
   */
  protected String doFormatText(String input_p) {
    return input_p;
  }

  /**
   * @deprecated
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#isValid(java.lang.String)
   */
  public boolean isValid(String value_p) {
    boolean isValid = true;
    if ((null != value_p) && (0 != value_p.length())) {
      Matcher matcher = _pattern.matcher(value_p);
      isValid = matcher.matches();
    }
    return isValid;
  }
}
