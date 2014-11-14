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
package org.polarsys.capella.common.ui.toolkit.services.formats;

import java.util.regex.Pattern;

/**
 * The default format. This default implementation returns the input value.
 */
public class DefaultFormat implements IFormat {
  /**
   * Constructs the default format.
   */
  public DefaultFormat() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.formats.IFormat#format(String, Pattern)
   */
  public String format(String input_p, Pattern pattern_p) {
    String value = input_p;
    if (null != pattern_p) {
      value = doFormatText(input_p, pattern_p);
    }
    return value;
  }

  /**
   * Does the format action with the specified input accordin to the specified pattern. <b>This default implementation returns the input value.</b>
   * @param input_p The input to format.
   * @param pattern_p The pattern cannot be null.
   * @return The formatted value else the input.
   * @see #format(String, Pattern)
   */
  protected String doFormatText(String input_p, Pattern pattern_p) {
    return input_p;
  }
}
