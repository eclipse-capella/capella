/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.browser.action;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;

/**
 *
 */
public abstract class BrowserActionFactory {
  String actionId;
  /**
   * Creates a new workbench action factory with the given id.
   * 
   * @param actionId
   *            the id of actions created by this action factory
   */
  protected BrowserActionFactory(String actionId) {
      this.actionId = actionId;
  }
  
  public abstract IWorkbenchAction create(IWorkbenchWindow window, ISemanticBrowserViewPart semanticBrowserView);
  
  /**
   * Workbench action (id "backwardHistory"): Backward in the browser navigation
   * history. This action maintains its enablement state.
   */
  public static final BrowserActionFactory BACKWARD_HISTORY = new BrowserActionFactory("backwardHistory") {//$NON-NLS-1$
      /**
       * @see org.eclipse.ui.actions.ActionFactory#create(org.eclipse.ui.IWorkbenchWindow)
       */
      @Override
      public IWorkbenchAction create(IWorkbenchWindow window,  ISemanticBrowserViewPart semanticBrowserView) {
          if (window == null) {
              throw new IllegalArgumentException();
          }
          IWorkbenchAction action = new SemanticBrowserHistoryAction(window, semanticBrowserView, false);
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
      public IWorkbenchAction create(IWorkbenchWindow window,  ISemanticBrowserViewPart semanticBrowserView) {
          if (window == null) {
              throw new IllegalArgumentException();
          }
          IWorkbenchAction action = new SemanticBrowserHistoryAction(window, semanticBrowserView, true);
          return action;
      }
  };
}
