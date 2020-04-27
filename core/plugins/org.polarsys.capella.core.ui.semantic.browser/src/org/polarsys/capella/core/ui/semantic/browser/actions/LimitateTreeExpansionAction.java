/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;
import org.polarsys.capella.core.ui.semantic.browser.view.Messages;

public class LimitateTreeExpansionAction extends Action {

  private ISemanticBrowserViewPart semanticBrowserViewPart;

  public LimitateTreeExpansionAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    super(null, IAction.AS_CHECK_BOX);
    this.semanticBrowserViewPart = semanticBrowserViewPart;
    setChecked(semanticBrowserViewPart.getModel().doesLimitateTreeExpansion());
    setToolTipText(Messages.SemanticBrowserView_LimitateTreeExpansionAction_Tooltip);
    setImageDescriptor(CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_COLLAPSE_CATEGORIES));
  }

  @Override
  public void run() {
    semanticBrowserViewPart.getModel().setLimitateTreeExpansion(isChecked());
    Object input = semanticBrowserViewPart.getCurrentViewer().getInput();
    semanticBrowserViewPart.setInputOnViewers(input);
  }
}
