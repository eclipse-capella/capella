/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.explorer.activity.ui.pages;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.input.ActivityExplorerEditorInput;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.OverviewActivityExplorerPage;

public class CapellaActivityExplorerWorkflowPage extends OverviewActivityExplorerPage {

  @Override
  protected String getHeaderTitle() {
    return Messages.CapellaActivityExplorerWorkflowPageTitle +((ActivityExplorerEditorInput)getEditorInput()).getName();
  }
}
