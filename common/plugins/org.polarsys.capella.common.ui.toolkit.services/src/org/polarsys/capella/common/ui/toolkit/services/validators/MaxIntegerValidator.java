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


/**
 * Implements a validator that checks if given value is not an empty string.
 */
public class MaxIntegerValidator extends AbstractValidator {
  /**
   * Constructs an integer validator accepting any value <= Integer.MAX_VALUE or '*'.
   * 
   * @param errorMessage_p The message displayed when {@link #isValid(Object)} returned <code>false</code>.
   */
  public MaxIntegerValidator(String errorMessage_p) {
    super(errorMessage_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.validators.IValidator#isValid(java.lang.Object)
   */
  public String isValid(Object value_p) {
    String result = getErrorMessage();

    if (!value_p.equals("")) { //$NON-NLS-1$
      if (value_p.equals("*")) { //$NON-NLS-1$
        // Returning null indicates that the value is valid.
        result = null;
      }
      else {
        try {
          long value = Long.parseLong((String)value_p);
          if (value <= Integer.MAX_VALUE) {
            // Returning null indicates that the value is valid.
            result = null;
          }
        }
        catch (ClassCastException ex_p) {
          //
        }
        catch (NumberFormatException ex) {
          //
        }
      }
    }

    return result;
  }
}
