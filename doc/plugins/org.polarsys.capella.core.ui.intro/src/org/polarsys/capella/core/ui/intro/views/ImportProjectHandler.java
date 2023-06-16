/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.intro.views;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ImportResourcesAction;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class ImportProjectHandler extends HyperLinkHandler {
  @Override
  public void linkActivated(HyperlinkEvent e) {
    ImportResourcesAction importAction = new ImportResourcesAction(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow());
    importAction.run();
    importAction.dispose();
  }
}
