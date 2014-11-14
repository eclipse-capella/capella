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

import org.apache.log4j.Logger;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * Implements a validator that checks if given value is not an empty string.
 */
public class NotEmptyStringValidator extends AbstractValidator {
  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Constructs the not empty string validator.
   * @param errorMessage_p The message displayed when {@link #isValid(Object)} returned <code>false</code>.
   */
  public NotEmptyStringValidator(String errorMessage_p) {
    super(errorMessage_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.validators.IValidator#isValid(java.lang.Object)
   */
  public String isValid(Object value_p) {
    String result = getErrorMessage();
    // Value must be a string here.
    String stringValue = null;
    try {
      stringValue = (String) value_p;
      // Test that the string is not empty.
      boolean isValid = (null != stringValue) && (stringValue.length() > 0);
      if (isValid) {
        // Returning null indicates that the value is valid.
        result = null;
      }
    } catch (ClassCastException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("NotEmptyStringValidator.isValid(..) _ "); //$NON-NLS-1$
      __logger.debug(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
    }
    return result;
  }
}
