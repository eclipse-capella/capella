/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
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
  
  public static void log(Logger logger, IStatus status) {
    logger.log(Level.toLevel(toPriority(status)), status);
  }

  public static void log(String model, IStatus status) {
    log(ReportManagerRegistry.getInstance().subscribe(model), status);
  }
}
