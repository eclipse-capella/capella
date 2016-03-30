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
  private Pattern pattern = null;

  /**
   * Constructs the regular expression behavior.
   * @param The regular expression.
   * @deprecated
   */
  protected RegExpInputBehavior(String regularExpression) {
    pattern = Pattern.compile(regularExpression);
  }

  /**
   * Gets the regular expression pattern.
   * @return The regular expression pattern.
   * @deprecated
   */
  public Pattern getPattern() {
    return pattern;
  }

  /**
   * @deprecated
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#format(java.lang.String)
   */
  public String format(String input) {
    if (isValid(input)) {
      return doFormatText(input);
    }
    return input;
  }

  /**
   * Does the text formatting. This is the default implementation which returns the input.
   * @param input The input to format.
   * @return The formatted value.
   * @deprecated
   */
  protected String doFormatText(String input) {
    return input;
  }

  /**
   * @deprecated
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#isValid(java.lang.String)
   */
  public boolean isValid(String value) {
    boolean isValid = true;
    if ((null != value) && (0 != value.length())) {
      Matcher matcher = pattern.matcher(value);
      isValid = matcher.matches();
    }
    return isValid;
  }
}
