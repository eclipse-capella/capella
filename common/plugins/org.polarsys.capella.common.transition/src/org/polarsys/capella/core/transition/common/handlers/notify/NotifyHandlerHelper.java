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

package org.polarsys.capella.core.transition.common.handlers.notify;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class NotifyHandlerHelper {

  public static INotifyHandler getInstance(IContext context) {
    INotifyHandler handler = (INotifyHandler) context.get(ITransitionConstants.NOTIFY_HANDLER);
    if (handler == null) {
      handler = new DefaultNotifyHandler();
      handler.init(context);
      context.put(ITransitionConstants.NOTIFY_HANDLER, handler);
    }
    return handler;
  }

}
