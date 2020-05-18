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
package org.polarsys.capella.common.tools.report.appenders.console;

import java.io.OutputStream;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.eclipse.ui.console.MessageConsoleStream;

public interface IReportConsole {


  OutputStream getErrorStream();

  
  HashMap<Level,MessageConsoleStream> getOutputStreams();
}
