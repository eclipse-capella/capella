/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.eclipse.core.resources.ResourcesPlugin;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;

/**
 *
 */
public class CommandLineFileAppender extends FileAppender {

  /**
   * @throws IOException
   */
  public CommandLineFileAppender() throws IOException {

    super(new HTMLLayout(), getLogFile());

    this.setName(ReportManagerConstants.LOG_OUTPUT_FILE);

  }

  /**
   * @return
   */
  private static String getLogFile() {
    String logFile = CommandLineArgumentHelper.getInstance().getLogFilePath();
    if (null == logFile) {
      String workspaceRootLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
      // By default, log file will be written in the workspace root directory
      logFile = workspaceRootLocation + File.separator + Messages.CommandLineFileAppender_DefaultLogFileName; //$NON-NLS-1$
    }
    return logFile;
  }

}
