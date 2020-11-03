/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
      super.run();
    } else {
      ((SemanticBrowserView) semanticBrowserViewPart).deactivateListeningToPageSelectionEvents();
    }
  }
}
