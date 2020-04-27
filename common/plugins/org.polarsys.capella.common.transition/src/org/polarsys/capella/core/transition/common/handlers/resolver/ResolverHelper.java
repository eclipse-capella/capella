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

        public IStatus init(IContext context) {
          return Status.OK_STATUS;
        }

        public IStatus dispose(IContext context) {
          return Status.OK_STATUS;
        }

        public List<EObject> resolve(EObject source, List<EObject> items, String title, String message, boolean multipleSelection,
            IContext context, EObject[] context2) {
          if ((items != null) && (items.size() > 0)) {
            return Collections.singletonList(items.get(0));
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
