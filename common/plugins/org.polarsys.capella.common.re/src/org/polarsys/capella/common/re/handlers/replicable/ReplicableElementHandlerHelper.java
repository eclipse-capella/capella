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

package org.polarsys.capella.common.re.handlers.replicable;

import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandlerHelper {

  public static ReplicableElementHandler getInstance(IContext context) {
    if (context != null) {
      if (!context.exists(IReConstants.REPLICABLE_ELEMENT_HANDLER)) {
        ReplicableElementHandler result = new ReplicableElementHandler();
        context.put(IReConstants.REPLICABLE_ELEMENT_HANDLER, result);
      }
      return (ReplicableElementHandler) context.get(IReConstants.REPLICABLE_ELEMENT_HANDLER);
    }
    return new ReplicableElementHandler();
  }

}
