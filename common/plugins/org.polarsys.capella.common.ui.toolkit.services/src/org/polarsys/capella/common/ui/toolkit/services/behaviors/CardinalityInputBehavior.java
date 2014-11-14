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

import org.polarsys.capella.common.ui.toolkit.services.formats.CardinalityBoundariesFormat;
import org.polarsys.capella.common.ui.toolkit.services.validators.CardinalityBoundariesValidator;
import org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidator;

/**
 * The cardinality input behavior.
 */
public class CardinalityInputBehavior extends AbstractInputBehavior {
  // The unlimited bound constant.
  private static final String UNLIMITED_BOUND = "*"; //$NON-NLS-1$
  // The empty string constant.
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  /**
   * Constructs the cardinality input behavior.
   */
  public CardinalityInputBehavior() {
    super(new CardinalityBoundariesValidator("", RegExpValidator.SUPPORT_EMPTY_VALUE), new CardinalityBoundariesFormat(), new String[] { "0..1", "1..1", "1..*", "*" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
  }

  /**
   * Gets the cardinality bounds values from the input value.
   * @param input_p The input value.
   * @return The cardinality bounds.
   */
  @Override
  public int[] getValue(String input_p) {
    int[] bounds = new int[2];

    Matcher matcher = getValidator().getPattern().matcher(input_p);
    if (!matcher.matches()) {
      bounds[0] = Integer.MIN_VALUE;
      bounds[1] = Integer.MIN_VALUE;
      return bounds;
    }

    if (CardinalityInputBehavior.UNLIMITED_BOUND.equalsIgnoreCase(matcher.group(0))) {
      bounds[0] = Integer.MAX_VALUE;
      bounds[1] = Integer.MAX_VALUE;
    } else if ((null == matcher.group(1)) && (null != matcher.group(0))) {
      bounds[0] = Integer.parseInt(matcher.group(0));
      bounds[1] = Integer.parseInt(matcher.group(0));
    } else {
      if (CardinalityInputBehavior.UNLIMITED_BOUND.equalsIgnoreCase(matcher.group(1))) {
        bounds[0] = Integer.MAX_VALUE;
      } else {
        bounds[0] = Integer.parseInt(matcher.group(1));
      }

      if (CardinalityInputBehavior.UNLIMITED_BOUND.equalsIgnoreCase(matcher.group(3))) {
        bounds[1] = Integer.MAX_VALUE;
      } else {
        bounds[1] = Integer.parseInt(matcher.group(3));
      }
    }
    return bounds;
  }

  /**
   * Converts the cardinality bounds values into a string representation.
   * @param min_p The lower cardinality bound.
   * @param max_p The uppper cardinality bound.
   * @return The string representation value.
   */
  public String convertBounds(int min_p, int max_p) {
    // The minimum bound.
    String strMin = CardinalityInputBehavior.EMPTY_STRING;
    if (Integer.MAX_VALUE == min_p) {
      strMin = CardinalityInputBehavior.UNLIMITED_BOUND;
    } else {
      strMin = Integer.toString(min_p);
    }

    // The maximum bound.
    String strMax = CardinalityInputBehavior.EMPTY_STRING;
    if (Integer.MAX_VALUE == max_p) {
      strMax = CardinalityInputBehavior.UNLIMITED_BOUND;
    } else {
      strMax = Integer.toString(max_p);
    }

    // Returns the converted value.
    if (!CardinalityInputBehavior.EMPTY_STRING.equals(strMin) && !CardinalityInputBehavior.EMPTY_STRING.equals(strMax)) {
      return format(strMin + " " + strMax); //$NON-NLS-1$
    }
    return CardinalityInputBehavior.EMPTY_STRING;
  }
}
