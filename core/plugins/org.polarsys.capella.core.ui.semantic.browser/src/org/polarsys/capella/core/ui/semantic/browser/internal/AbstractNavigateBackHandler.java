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
package org.polarsys.capella.core.ui.semantic.browser.internal;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.common.ui.toolkit.browser.action.SemanticBrowserHistoryAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Base class to implement the handler for backward & forward navigation in the semantic browser.
 */
public abstract class AbstractNavigateBackHandler extends AbstractUiHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
    ISemanticBrowserViewPart semanticBrowser = (ISemanticBrowserViewPart) activePage.findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
    if (null != semanticBrowser) {
      new SemanticBrowserHistoryAction(activeWorkbenchWindow, semanticBrowser, isForwardNavigation()).run();
    }
    return null;
  }

  /**
   * Is given action the forward action ?
   * @return <code>true</code> means yes;<code>false</code> means backward navigation.
   */
  protected abstract boolean isForwardNavigation();
}
