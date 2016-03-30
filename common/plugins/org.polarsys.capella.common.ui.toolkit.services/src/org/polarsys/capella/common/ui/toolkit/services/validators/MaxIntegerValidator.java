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


/**
 * Implements a validator that checks if given value is not an empty string.
 */
public class MaxIntegerValidator extends AbstractValidator {
  /**
   * Constructs an integer validator accepting any value <= Integer.MAX_VALUE or '*'.
   * 
   * @param errorMessage The message displayed when {@link #isValid(Object)} returned <code>false</code>.
   */
  public MaxIntegerValidator(String errorMessage) {
    super(errorMessage);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.validators.IValidator#isValid(java.lang.Object)
   */
  public String isValid(Object value) {
    String result = getErrorMessage();

    if (!value.equals("")) { //$NON-NLS-1$
      if (value.equals("*")) { //$NON-NLS-1$
        // Returning null indicates that the value is valid.
        result = null;
      }
      else {
        try {
          long val = Long.parseLong((String)value);
          if (val <= Integer.MAX_VALUE) {
            // Returning null indicates that the value is valid.
            result = null;
          }
        }
        catch (ClassCastException ex) {
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
