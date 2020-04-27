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

package org.polarsys.capella.core.transition.common.handlers.selection;

import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultSelectionContextsHandler implements CompoundSelectionContextHandler, ISelectionContextsHandler {

  public static final String SELECTION_CONTEXTS_HANDLER_MAP = "SELECTION_CONTEXTS_HANDLER_MAP"; //$NON-NLS-1$

  public static final ISelectionContext EMPTY_CONTEXT = new ISelectionContext() {

    public boolean match(EObject source, EObject target, IContext context) {
      return true;
    }

  };

  public ISelectionContext getSelectionContext(IContext context, String id) {

    if (!context.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      context.put(SELECTION_CONTEXTS_HANDLER_MAP, map);
    }

    HashMap<String, ISelectionContext> map = (HashMap<String, ISelectionContext>) context.get(SELECTION_CONTEXTS_HANDLER_MAP);
    if (!map.containsKey(id)) {
      return EMPTY_CONTEXT;
    }
    return map.get(id);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {

    if (context.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      map.clear();
    }

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionContext(IContext context, String id, ISelectionContext sContext) {

    if (!context.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      context.put(SELECTION_CONTEXTS_HANDLER_MAP, map);
    }

    HashMap<String, ISelectionContext> map = (HashMap<String, ISelectionContext>) context.get(SELECTION_CONTEXTS_HANDLER_MAP);
    map.put(id, sContext);
  }

  /**
   * {@inheritDoc}
   */
  public ISelectionContext getSelectionContext(IContext context, String id, EObject eContext, EObject result) {
    return getSelectionContext(context, id);
  }
}
