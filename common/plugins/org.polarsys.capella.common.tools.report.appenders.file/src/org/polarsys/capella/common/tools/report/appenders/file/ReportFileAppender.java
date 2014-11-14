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
package org.polarsys.capella.common.tools.report.appenders.file;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;

import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
/**
 *
 */
public class ReportFileAppender extends FileAppender {

  /**
   * @throws IOException
   */
  public ReportFileAppender() throws IOException {
    super(new HTMLLayout(), System.getProperty("user.home") + File.separator + "MDEReport.html"); //$NON-NLS-1$ //$NON-NLS-2$
    this.setName(ReportManagerConstants.LOG_OUTPUT_FILE);
  }
}
