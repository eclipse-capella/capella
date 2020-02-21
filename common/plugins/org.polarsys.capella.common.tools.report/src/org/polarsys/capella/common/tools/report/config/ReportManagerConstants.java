/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.config;

import org.eclipse.core.runtime.QualifiedName;

@SuppressWarnings("nls")
public interface ReportManagerConstants {
  
  String LOG_LEVEL = "Log Level";

  String LOG_LEVEL_FATAL = "FATAL";

  String LOG_LEVEL_ERROR = "ERROR";

  String LOG_LEVEL_WARN = "WARN";

  String LOG_LEVEL_INFO = "INFO";

  String LOG_LEVEL_DEBUG = "DEBUG";

  String LOG_OUTPUT_TYPE = "Log Output Type";

  String LOG_OUTPUT_CONSOLE = "Console";

  String LOG_OUTPUT_REPORTWINDOW = "Report Window";

  String LOG_OUTPUT_PROBLEMS = "Problems View";

  String LOG_OUTPUT_DEFAULT = "Default";

  String LOG_OUTPUT_FILE = "File";

  String JAXB_INSTANCE = "org.polarsys.capella.common.tools.report.config.persistence";

  String REPORTMANAGER_VERSION = "1.1";

  String FILEFORMAT_VERSION = "1.0.1";

  String LOG_OUTPUT_PROBLEMS_VIEW = "Information";
}
