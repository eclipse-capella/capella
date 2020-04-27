/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.handlers.log;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.context.TransitionContext;

/**
 * The LogHandler must be IContext independent since it should work even if an error occurred before context creation.
 */
public class LogHelper {

  private static ILogHandler handler;

  public static void setInstance(ILogHandler newHandler) {
    if (handler != null) {
      handler.dispose(TransitionContext.EMPTY_CONTEXT);
    }
    handler = newHandler;
  }

  public static ILogHandler getInstance() {

    if (handler == null) {
      handler = new DefaultLogHandler(ITransitionConstants.DEFAULT_REPORT_COMPONENT);
      handler.init(TransitionContext.EMPTY_CONTEXT);
    }
    return handler;
  }

  public static void dispose() {
    if (handler != null) {
      handler.dispose(TransitionContext.EMPTY_CONTEXT);
    }
    handler = null;
  }

}
