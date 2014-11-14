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
package org.polarsys.capella.common.ui.toolkit.browser.action;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;

/**
 *
 */
public abstract class BrowserActionFactory {
  String actionId_p;
  /**
   * Creates a new workbench action factory with the given id.
   * 
   * @param actionId
   *            the id of actions created by this action factory
   */
  protected BrowserActionFactory(String actionId) {
      actionId_p = actionId;
  }
  
  public abstract IWorkbenchAction create(IWorkbenchWindow window, ISemanticBrowserViewPart semanticBrowserView_p);
  
  /**
   * Workbench action (id "backwardHistory"): Backward in the browser navigation
   * history. This action maintains its enablement state.
   */
  public static final BrowserActionFactory BACKWARD_HISTORY = new BrowserActionFactory("backwardHistory") {//$NON-NLS-1$
      /**
       * @see org.eclipse.ui.actions.ActionFactory#create(org.eclipse.ui.IWorkbenchWindow)
       */
      @Override
      public IWorkbenchAction create(IWorkbenchWindow window,  ISemanticBrowserViewPart semanticBrowserView_p) {
          if (window == null) {
              throw new IllegalArgumentException();
          }
          IWorkbenchAction action = new SemanticBrowserHistoryAction(window, semanticBrowserView_p, false);
          return action;
      }
  };
  
  /**
   * Workbench action (id "forwardHistory"): Forward in the browser navigation
   * history. This action maintains its enablement state.
   */
  public static final BrowserActionFactory FORWARD_HISTORY = new BrowserActionFactory("forwardHistory") {//$NON-NLS-1$
      /**
       * @see org.eclipse.ui.actions.ActionFactory#create(org.eclipse.ui.IWorkbenchWindow)
       */
      @Override
      public IWorkbenchAction create(IWorkbenchWindow window,  ISemanticBrowserViewPart semanticBrowserView_p) {
          if (window == null) {
              throw new IllegalArgumentException();
          }
          IWorkbenchAction action = new SemanticBrowserHistoryAction(window, semanticBrowserView_p, true);
          return action;
      }
  };
}
