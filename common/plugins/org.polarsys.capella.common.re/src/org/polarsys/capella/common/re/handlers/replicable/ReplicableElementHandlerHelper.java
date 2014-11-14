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
package org.polarsys.capella.common.re.handlers.replicable;

import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandlerHelper {

  public static ReplicableElementHandler getInstance(IContext context_p) {
    if (context_p != null) {
      if (!context_p.exists(IReConstants.REPLICABLE_ELEMENT_HANDLER)) {
        ReplicableElementHandler result = new ReplicableElementHandler();
        context_p.put(IReConstants.REPLICABLE_ELEMENT_HANDLER, result);
      }
      return (ReplicableElementHandler) context_p.get(IReConstants.REPLICABLE_ELEMENT_HANDLER);
    }
    return new ReplicableElementHandler();
  }

}
