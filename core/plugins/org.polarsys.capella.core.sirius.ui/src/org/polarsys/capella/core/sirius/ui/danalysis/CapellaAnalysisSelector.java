/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.sirius.ui.business.api.session.analysis.SmartDialogAnalysisSelector;
import org.eclipse.sirius.ui.tools.api.dialogs.AnalysisSelectorFilteredItemsSelectionDialog;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Shell;

/**
 * Capella specific analysis selector.
 */
public class CapellaAnalysisSelector extends SmartDialogAnalysisSelector {

  @Override
  public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation,
      Collection<DAnalysis> allAnalysis) {
    DAnalysis dAnalysisSelector = super.selectSmartlyAnalysis(allAnalysis, representation);
    if (dAnalysisSelector == null) {
      throw new OperationCanceledException(Messages.CapellaAnalysisSelector_Cancel);
    }
    return dAnalysisSelector;
  }

  @Override
  protected AnalysisSelectorFilteredItemsSelectionDialog createAnalysisSelectorDialog(Shell shell,
      DAnalysis bestCandidate, Collection<DAnalysis> allAnalysis, List<DAnalysis> bestCandidates,
      DRepresentation representation) {
    return new AnalysisSelectorFilteredItemsSelectionDialog(shell,
        allAnalysis.iterator().next(), allAnalysis, new ArrayList<>(allAnalysis), true);
  }
}
