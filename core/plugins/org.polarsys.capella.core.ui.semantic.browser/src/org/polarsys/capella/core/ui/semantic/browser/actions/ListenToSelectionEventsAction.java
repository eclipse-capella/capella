/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.semantic.browser.actions;

import org.eclipse.jface.action.IAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;
import org.polarsys.capella.core.ui.semantic.browser.view.Messages;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

public class ListenToSelectionEventsAction extends RefreshAction {

  public ListenToSelectionEventsAction(ISemanticBrowserViewPart semanticBrowserViewPart, boolean isChecked) {
    super(semanticBrowserViewPart, IAction.AS_CHECK_BOX);
    setText(Messages.SemanticBrowserView_ListeningToPageSelectionEventsAction_Title);
    setToolTipText(Messages.SemanticBrowserView_ListeningToPageSelectionEventsAction_Tooltip);
    setImageDescriptor(
        CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_LISTENING_TO_PAGE_SELECTION_EVENTS));
    setChecked(isChecked);
  }

  @Override
  public void run() {
    if (isChecked()) {
      ((SemanticBrowserView) semanticBrowserViewPart).activateListeningToPageSelectionEvents();
      doRun();
    } else {
      ((SemanticBrowserView) semanticBrowserViewPart).deactivateListeningToPageSelectionEvents();
    }
  }
}
