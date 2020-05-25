/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.ui.handlers.merge;

import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.transition.common.ui.dialogs.TransitionDiffMergeDialog;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeEMFDiffNode;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeUIDifferencesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ReMergeUIDifferencesHandler extends MergeUIDifferencesHandler {

  @Override
  protected void registerUserProperties(EMFDiffNode diffNode) {
    super.registerUserProperties(diffNode);
    diffNode.setUserPropertyValue(DefaultUserProperties.P_DEFAULT_INCREMENTAL_MODE, false);
  }

  @Override
  protected DiffMergeDialog createDiffDialog(IContext context, Display display, MergeEMFDiffNode diffNode) {
    DiffMergeDialog diffMergeDialog = super.createDiffDialog(context, display, diffNode);

    if (diffMergeDialog instanceof TransitionDiffMergeDialog) {
      TransitionDiffMergeDialog transitionDiffMergeDialog = (TransitionDiffMergeDialog) diffMergeDialog;
      transitionDiffMergeDialog.forceOkButtonEnablement(true);
    }

    return diffMergeDialog;
  }
}
