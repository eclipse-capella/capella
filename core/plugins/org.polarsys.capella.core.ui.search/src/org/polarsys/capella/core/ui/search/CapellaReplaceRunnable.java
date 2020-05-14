/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.ui.search.match.SearchMatch;

public class CapellaReplaceRunnable implements IRunnableWithProgress {
  private final CapellaSearchQuery searchQuery;
  private final Set<SearchMatch> matches;
  private String replacement = null;
  private final boolean rerunQueryAfterReplacement;

  private final CapellaReplaceQuery replaceQuery;

  public CapellaReplaceRunnable(CapellaSearchQuery searchQuery, Set<SearchMatch> matches,
      boolean rerunQueryAfterReplacement) {
    this.searchQuery = searchQuery;
    this.matches = matches;
    this.rerunQueryAfterReplacement = rerunQueryAfterReplacement;
    replaceQuery = new CapellaReplaceQuery(searchQuery.getCapellaSearchSettings());
  }

  @Override
  public void run(IProgressMonitor monitor) throws InterruptedException {
    IStatus replaceStatus = replaceQuery.run(monitor, matches, replacement);
    if (replaceStatus.getSeverity() == IStatus.ERROR) {
      throw new InterruptedException(replaceStatus.getMessage());
    }
  }

  public boolean askForReplacementText() {
    int occurrencesCount = matches.size();

    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    String pattern = searchQuery.getCapellaSearchSettings().getTextPattern();
    String title = String.format(CapellaSearchConstants.ReplaceDialog_Label, occurrencesCount);

    CapellaReplaceDialog capellaReplaceDialog = new CapellaReplaceDialog(shell, pattern, title);
    if (capellaReplaceDialog.open() == Window.OK) {
      replacement = capellaReplaceDialog.getReplacement();
      searchQuery.getCapellaSearchSettings().setReplaceTextPattern(replacement);
      return true;
    }

    return false;
  }

  public CapellaSearchQuery getSearchQuery() {
    return searchQuery;
  }

  public CapellaReplaceQuery getReplaceQuery() {
    return replaceQuery;
  }

  public String getReplacement() {
    return replacement;
  }

  public boolean isRerunQueryAfterReplacement() {
    return rerunQueryAfterReplacement;
  }
}
