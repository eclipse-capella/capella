/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.file;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.eclipse.core.resources.ResourcesPlugin;
import org.polarsys.capella.common.application.ArgumentsHelper;
import org.polarsys.capella.common.application.CommonArgumentsConstants;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
/**
 *
 */
public class ReportFileAppender extends FileAppender {

  public ReportFileAppender() throws IOException {
    super(new HTMLLayout(), getLogFile());
    
    this.setName(ReportManagerConstants.LOG_OUTPUT_FILE);
  }
  
  private static String getLogFile() {
    String logFile = ArgumentsHelper.getInstance().getString(CommonArgumentsConstants.LOG_FILE_PATH);
    if (null == logFile) {
      String workspaceRootLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
      // By default, log file will be written in the workspace root directory
      logFile = workspaceRootLocation + File.separator + "MDEReport.html"; //$NON-NLS-1$
    }
    return logFile;
  }
  
}
