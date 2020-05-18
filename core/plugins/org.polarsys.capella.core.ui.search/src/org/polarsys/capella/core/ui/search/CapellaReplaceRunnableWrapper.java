/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class CapellaReplaceRunnableWrapper {
  private final CapellaReplaceRunnable capellaReplaceRunnable;

  public CapellaReplaceRunnableWrapper(CapellaReplaceRunnable capellaReplaceRunnable) {
    this.capellaReplaceRunnable = capellaReplaceRunnable;
  }

  public void run() {
    // Ask for the replacement text
    if (capellaReplaceRunnable.askForReplacementText()) {
      Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

      // Do replace
      try {
        new ProgressMonitorDialog(shell).run(true, true, capellaReplaceRunnable);
      } catch (InvocationTargetException | InterruptedException e) {
        // Show error dialog if any
        String message = e.getMessage();
        if (message != null && !message.isEmpty()) {
          MessageDialog.openError(shell, CapellaSearchConstants.ReplaceDialog_Title, message);
        }
      }

      CapellaSearchQuery searchQuery = capellaReplaceRunnable.getSearchQuery();
      CapellaReplaceQuery replaceQuery = capellaReplaceRunnable.getReplaceQuery();
      String replacement = capellaReplaceRunnable.getReplacement();
      int replacedOccurrenceCount = replaceQuery.getReplacedOccurrenceCount();
      String searchPattern = searchQuery.getCapellaSearchSettings().getTextPattern();
      int replacedElementCount = replaceQuery.getReplacedElementCount();
      int replacedProjectCount = replaceQuery.getReplacedProjectCount();

      // Inform how many replaces were done
      if (replacement != null) {
        String message = String.format(CapellaSearchConstants.ReplaceDialog_Finished_Or_Canceled_Message, searchPattern,
            replacement, replacedOccurrenceCount, replacedElementCount, replacedProjectCount);

        MessageDialog.openInformation(shell, CapellaSearchConstants.ReplaceDialog_Title, message);
      }

      // Re-run search query if required
      boolean atLeastOneOccurrenceReplaced = replacedOccurrenceCount > 0;
      boolean reSearchRequired = capellaReplaceRunnable.isRerunQueryAfterReplacement();
      if (atLeastOneOccurrenceReplaced && reSearchRequired) {
        NewSearchUI.runQueryInBackground(searchQuery);
      }
    }
  }
}
