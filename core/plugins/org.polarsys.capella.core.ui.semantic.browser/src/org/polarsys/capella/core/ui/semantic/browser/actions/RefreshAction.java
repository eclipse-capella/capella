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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;

public class RefreshAction extends Action {

  protected ISemanticBrowserViewPart semanticBrowserViewPart;

  public RefreshAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    this(semanticBrowserViewPart, IAction.AS_PUSH_BUTTON);
  }

  public RefreshAction(ISemanticBrowserViewPart semanticBrowserViewPart, int type) {
    super(null, type);
    this.semanticBrowserViewPart = semanticBrowserViewPart;
    setImageDescriptor(CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_REFRESH));
  }

  @Override
  public void run() {
    semanticBrowserViewPart.refresh(true);
  }

}
