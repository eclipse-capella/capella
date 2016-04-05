/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.handlers.scope;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ScopeHandlerHelper {

  public static IScopeHandler getInstance(IContext context) {
    return getInstance(ICommonConstants.EMPTY_STRING, context);
  }

  public static IScopeHandler getInstance(String identifier, IContext context) {
    IScopeHandler handler = (IScopeHandler) context.get(ITransitionConstants.SCOPE_HANDLER + identifier);
    if (handler == null) {
      handler = new DefaultScopeHandler();
      handler.init(context);
      context.put(ITransitionConstants.SCOPE_HANDLER + identifier, handler);
    }
    return handler;
  }

  public static String getContextIdentifier(String identifier) {
    return ITransitionConstants.SCOPE_HANDLER + identifier;
  }

}
