/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.extension.migration.egf;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Base class generator implementation.<br>
 */
public abstract class AbstractGenerator {
  
  /**
   * Handle a diagnostic
   * @param diagnostic_p
   * @param message_p
   *          the displayed message if an error occurs.
   * @return false if an error occurs; true otherwise.
   */
  protected boolean handleDiagnostic(Diagnostic diagnostic, String message) {
    boolean result = true;
    if (Diagnostic.OK != diagnostic.getSeverity()) {
      StringBuffer loggerMessage = new StringBuffer(); //$NON-NLS-1$
      loggerMessage.append(message);
      loggerMessage.append(' ');
      loggerMessage.append(diagnostic.getMessage());
      loggerMessage.append(' ');
      for (Diagnostic child : diagnostic.getChildren()) {
        handleDiagnostic(child, message);
      }
      Throwable exception = diagnostic.getException();
      if (null != exception) {
        loggerMessage.append(exception.getMessage());
        Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, loggerMessage.toString(), exception));
      } else {
    	Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, loggerMessage.toString(), exception));
      }
      result = false;
    }
    return result;
  }
}
