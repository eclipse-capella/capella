/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

public class LogExt {

  public static String toPriority(IStatus status) {
    switch (status.getSeverity()) {
      case IStatus.OK:
        return ReportManagerConstants.LOG_LEVEL_INFO;
      case IStatus.CANCEL:
        return ReportManagerConstants.LOG_LEVEL_WARN;
      case IStatus.WARNING:
        return ReportManagerConstants.LOG_LEVEL_WARN;
      case IStatus.ERROR:
        return ReportManagerConstants.LOG_LEVEL_ERROR;
      case IStatus.INFO:
        return ReportManagerConstants.LOG_LEVEL_INFO;
      default:
        return ReportManagerConstants.LOG_LEVEL_INFO;
    }
  }
  
  /**
   * Maps a diagnostic's severity to a Log4j Logger Priority
   */
  public static Priority convertSeverityToPriority(Diagnostic diag) {
    Priority prio = null;
    switch (diag.getSeverity()) {
    case Diagnostic.ERROR: prio = Level.ERROR; break;
    case Diagnostic.WARNING: prio = Level.WARN; break;
    default: prio = Level.INFO;
    }
    return prio;
  }

  public static void log(Logger logger, IStatus status) {
    logger.log(Level.toLevel(toPriority(status)), status);
  }

  public static void log(String model, IStatus status) {
    log(ReportManagerRegistry.getInstance().subscribe(model), status);
  }
}
