/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.sirius.view;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.ui.semantic.browser.handler.AbstractSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.handlers.NavigationSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.helpers.SiriusSelectionHelper;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Browser Semantic View. Load by extension point.
 */
public class SiriusSemanticBrowserView extends SemanticBrowserView {
  
  NavigationSemanticBrowserDoubleClickHandler navigationDoubleClickHandler ;
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part, ISelection selection) {
    return SiriusSelectionHelper.handleSelection(part, selection);
  }
  
  @Override
  public AbstractSemanticBrowserDoubleClickHandler getSemanticBrowserDoubleClickHandlerFor(DoubleClickEvent event) {
    if(navigationDoubleClickHandler == null) navigationDoubleClickHandler = new NavigationSemanticBrowserDoubleClickHandler();
    return navigationDoubleClickHandler;
  }
}
