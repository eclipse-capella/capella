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

package org.polarsys.capella.common.re.ui.handlers.uihead;

import org.polarsys.capella.common.re.ui.handlers.merge.ReMergeUIDifferencesHandler;
import org.polarsys.capella.common.re.ui.handlers.scope.ScopeUIHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;

/**
 *
 */
public class UIHeadHandler extends SharedWorkflowActivityParameter {

  public UIHeadHandler(boolean showOptionsUI, boolean showDiffMergeUI) {
    if (showOptionsUI) {
      addSharedParameter(
          new GenericParameter<IHandler>(ITransitionConstants.SCOPE_HANDLER, new ScopeUIHandler(), "Options handler"));
    }

    if (showDiffMergeUI) {
      addSharedParameter(new GenericParameter<IHandler>(ITransitionConstants.MERGE_DIFFERENCES_HANDLER,
          new ReMergeUIDifferencesHandler(), "Filtering differences UI handler"));
    }
  }

  public UIHeadHandler(boolean showDiffMergeUI) {
    this(true, showDiffMergeUI);
  }

}
