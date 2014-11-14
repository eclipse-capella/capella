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

    public boolean match(EObject source_p, EObject target_p, IContext context_p) {
      return true;
    }

  };

  public ISelectionContext getSelectionContext(IContext context_p, String id_p) {

    if (!context_p.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      context_p.put(SELECTION_CONTEXTS_HANDLER_MAP, map);
    }

    HashMap<String, ISelectionContext> map = (HashMap<String, ISelectionContext>) context_p.get(SELECTION_CONTEXTS_HANDLER_MAP);
    if (!map.containsKey(id_p)) {
      return EMPTY_CONTEXT;
    }
    return map.get(id_p);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {

    if (context_p.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      map.clear();
    }

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionContext(IContext context_p, String id_p, ISelectionContext sContext_p) {

    if (!context_p.exists(SELECTION_CONTEXTS_HANDLER_MAP)) {
      HashMap<String, ISelectionContext> map = new HashMap<String, ISelectionContext>();
      context_p.put(SELECTION_CONTEXTS_HANDLER_MAP, map);
    }

    HashMap<String, ISelectionContext> map = (HashMap<String, ISelectionContext>) context_p.get(SELECTION_CONTEXTS_HANDLER_MAP);
    map.put(id_p, sContext_p);
  }

  /**
   * {@inheritDoc}
   */
  public ISelectionContext getSelectionContext(IContext context_p, String id_p, EObject eContext_p, EObject result_p) {
    return getSelectionContext(context_p, id_p);
  }
}
