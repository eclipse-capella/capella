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
package org.polarsys.capella.core.transition.common.handlers.resolver;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ResolverHelper {

  public static IResolverHandler getInstance(IContext context) {
    IResolverHandler handler = (IResolverHandler) context.get(ITransitionConstants.RESOLVER_HANDLER);
    if (handler == null) {
      handler = new IResolverHandler() {

        public IStatus init(IContext context_p) {
          return Status.OK_STATUS;
        }

        public IStatus dispose(IContext context_p) {
          return Status.OK_STATUS;
        }

        public List<EObject> resolve(EObject source_p, List<EObject> items_p, String title_p, String message_p, boolean multipleSelection_p,
            IContext context_p, EObject[] context2_p) {
          if ((items_p != null) && (items_p.size() > 0)) {
            return Collections.singletonList(items_p.get(0));
          }
          return null;
        }

      };
      handler.init(context);
      context.put(ITransitionConstants.RESOLVER_HANDLER, handler);
    }
    return handler;
  }

}
