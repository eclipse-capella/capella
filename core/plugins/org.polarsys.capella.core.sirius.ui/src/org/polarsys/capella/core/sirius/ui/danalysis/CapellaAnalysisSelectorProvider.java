/*******************************************************************************
 * Copyright (c) 2006, 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.danalysis;

import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorProvider;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.internal.session.analysis.SmartDialogAnalysisSelectorProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Provides a customized {@link DAnalysisSelector}.
 */
public class CapellaAnalysisSelectorProvider implements DAnalysisSelectorProvider {

  private int priority;

  public CapellaAnalysisSelectorProvider() {
    this.priority = new SmartDialogAnalysisSelectorProvider().getPriority() + 1;
  }

  @Override
  public DAnalysisSelector getSelector(DAnalysisSession session) {
    return new CapellaAnalysisSelector();
  }

  @Override
  public boolean provides(DAnalysisSession session) {
    // do not use SessionHelper.getCapellaProject(session) because when creating a new Capella project it returns
    // false until a capella resource is added
    return CapellaResourceHelper.isCapellaProject(session.getSessionResource());
  }

  @Override
  public int getPriority() {
    return this.priority;
  }
}
