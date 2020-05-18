/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 */
public class CommandLineConsoleAppender extends ConsoleAppender {

  /**
   * 
   */
  public CommandLineConsoleAppender() {
    super(new PatternLayout("%d{MM-dd HH:mm:ss} %-5.5p %m%n")); //$NON-NLS-1$
    setName(ReportManagerConstants.LOG_OUTPUT_CONSOLE);
  }

}
