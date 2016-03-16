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
  public String format(String input, Pattern pattern) {
    String value = input;
    if (null != pattern) {
      value = doFormatText(input, pattern);
    }
    return value;
  }

  /**
   * Does the format action with the specified input accordin to the specified pattern. <b>This default implementation returns the input value.</b>
   * @param input The input to format.
   * @param pattern The pattern cannot be null.
   * @return The formatted value else the input.
   * @see #format(String, Pattern)
   */
  protected String doFormatText(String input, Pattern pattern) {
    return input;
  }
}
