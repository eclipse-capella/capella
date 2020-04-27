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
package org.polarsys.capella.core.projection.common.handlers.log;

import org.polarsys.capella.core.projection.common.constants.ITransitionConstants;

/**
 * The LogHandler must be IContext independent since it should work even if an error occurred before context creation.
 */
public class LogHelper {

  private static ILogHandler handler;

  public static void setInstance(ILogHandler handler_p) {
    if (handler != null) {
      handler.dispose(null);
    }
    handler = handler_p;
  }

  public static ILogHandler getInstance() {

    if (handler == null) {
      handler = new DebugTraceLogHandler(ITransitionConstants.DEFAULT_REPORT_COMPONENT);
      handler.init(null);
    }
    return handler;
  }

  public static void dispose() {
    if (handler != null) {
      handler.dispose(null);
    }
    handler = null;
  }

}
