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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The cardinality boundaries format. This format is used everywhere cardinality boundaries have to be displayed in the same text field. The display patterns
 * which this component respects is <i><b>lowerNumericBoundary </b>..<b> upperNumericBoundary</b></i> or <i><b>lowerNumericBoundary</b> space(s)
 * <b>upperNumericBoundary</b></i>.
 */
public class CardinalityBoundariesFormat extends DefaultFormat {
  // The cadinality boundaries separator.
  private static final String BOUNDS_SEPARATOR = ".."; //$NON-NLS-1$
  // The 'one or more' space(s) separator.
  private static final String SPACE_SEPARATOR = "\\s+"; //$NON-NLS-1$

  /**
   * Constructs the cardinality format.
   */
  public CardinalityBoundariesFormat() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.formats.DefaultFormat#doFormatText(String, Pattern)
   */
  @Override
  protected String doFormatText(String input_p, Pattern pattern_p) {
    String value = input_p;
    if (null != input_p) {
      // If the boundaries are equals only displays one.
      Matcher matcher = pattern_p.matcher(input_p);
      if (matcher.matches()) {
        if (1 < matcher.end()) {
          if (matcher.group(1).equalsIgnoreCase(matcher.group(3))) {
            value = matcher.group(1);
          }
        }
      }

      // Replaces all spaces with boundaries separator.
      value = value.replaceAll(CardinalityBoundariesFormat.SPACE_SEPARATOR, CardinalityBoundariesFormat.BOUNDS_SEPARATOR);
    }
    return value;
  }
}
