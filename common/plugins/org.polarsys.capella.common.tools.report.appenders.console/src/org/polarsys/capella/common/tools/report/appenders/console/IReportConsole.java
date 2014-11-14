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
package org.polarsys.capella.common.tools.report.appenders.console;

import java.io.OutputStream;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.eclipse.ui.console.MessageConsoleStream;

public interface IReportConsole {


  OutputStream getErrorStream();

  
  HashMap<Level,MessageConsoleStream> getOutputStreams();
}
