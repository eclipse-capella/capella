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
package org.polarsys.capella.common.re.handlers.location;

import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class LocationHandlerHelper {

  public static ILocationHandler getInstance(IContext context_p) {
    if (!context_p.exists(IReConstants.LOCATION_HANDLER)) {
      IHandler handler = new DefaultLocationHandler();
      context_p.put(IReConstants.LOCATION_HANDLER, handler);
    }
    return (ILocationHandler) context_p.get(IReConstants.LOCATION_HANDLER);
  }
}
