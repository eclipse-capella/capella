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
