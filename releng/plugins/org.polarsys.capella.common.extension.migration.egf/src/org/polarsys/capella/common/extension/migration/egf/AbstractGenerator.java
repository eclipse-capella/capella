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
package org.polarsys.capella.common.extension.migration.egf;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * Base class generator implementation.<br>
 */
public abstract class AbstractGenerator {
  /**
   * Log4j reference logger.
   */
  private static final Logger _logger = Logger.getLogger(AbstractGenerator.class.getPackage().getName());

  /**
   * Handle a diagnostic
   * @param diagnostic_p
   * @param message_p
   *          the displayed message if an error occurs.
   * @return false if an error occurs; true otherwise.
   */
  protected boolean handleDiagnostic(Diagnostic diagnostic_p, String message_p) {
    boolean result = true;
    if (Diagnostic.OK != diagnostic_p.getSeverity()) {
      StringBuffer loggerMessage = new StringBuffer("AbstractGenerator.handleDiagnostic(..) _ "); //$NON-NLS-1$
      loggerMessage.append(message_p);
      loggerMessage.append(' ');
      loggerMessage.append(diagnostic_p.getMessage());
      for (Diagnostic diagnostic : diagnostic_p.getChildren()) {
        handleDiagnostic(diagnostic, message_p);
      }
      Throwable exception = diagnostic_p.getException();
      if (null != exception) {
        loggerMessage.append(exception.getMessage());
        _logger.fatal(loggerMessage.toString(), exception);
      } else {
        _logger.fatal(loggerMessage.toString());
      }
      result = false;
    }
    return result;
  }
}
