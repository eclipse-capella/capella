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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.ui.search.match.SearchMatch;

public class CapellaReplaceQuery {

  private final CapellaSearchSettings capellaSearchSettings;

  Set<EObject> replacedElements = new HashSet<>();
  Set<IProject> replacedProjects = new HashSet<>();
  int replacedOccurrenceCount = 0;

  public CapellaReplaceQuery(CapellaSearchSettings capellaSearchSettings) {
    this.capellaSearchSettings = capellaSearchSettings;
  }

  public IStatus run(IProgressMonitor monitor, Set<SearchMatch> allMatches, String replacement) {
    replacedElements = new HashSet<>();
    replacedProjects = new HashSet<>();
    replacedOccurrenceCount = 0;
    if (replacement == null) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, CapellaSearchConstants.CapellaReplaceQuery_Validation_Replacement_Null);
    }
    try {
      Pattern searchPattern = capellaSearchSettings.createPattern();
      Set<SearchMatch> matches = getMinimalSearchMatches(allMatches);

      int countTotalOccurrences = matches.size();
      SubMonitor subMonitor = SubMonitor.convert(monitor, countTotalOccurrences);
      subMonitor.setTaskName(String.format(CapellaSearchConstants.ReplaceJob_Title, replacement));

      for (SearchMatch match : matches) {
        int countOccurrences = 1;
        String projectName = match.getProject().getName();
        subMonitor.subTask(String.format(CapellaSearchConstants.ReplaceJob_SubTitle, countOccurrences, projectName));
        replace(match, searchPattern, replacement);
        subMonitor.split(countOccurrences);

      }
      return Status.OK_STATUS;
    } catch (Exception e) {
      String message = e.getMessage();
      if (e instanceof PatternSyntaxException) {
        message = String.format(CapellaSearchConstants.CapellaSearchQuery_Search_Pattern_Not_Validated_Message,
            ((PatternSyntaxException) e).getPattern(), ((PatternSyntaxException) e).getDescription());
      }
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    }
  }

  public void replace(SearchMatch capellaMatch, Pattern searchPattern, String replacement) {
    boolean replaced = capellaMatch.replace(searchPattern, replacement);
    if (replaced) {
      replacedProjects.add(capellaMatch.getProject());
      replacedElements.add((EObject) capellaMatch.getElement());
      replacedOccurrenceCount += capellaMatch.getChildren().isEmpty() ? 1 : capellaMatch.getChildren().size();
    }
  }

  public int getReplacedOccurrenceCount() {
    return replacedOccurrenceCount;
  }

  public int getReplacedElementCount() {
    return replacedElements.size();
  }

  public int getReplacedProjectCount() {
    return replacedProjects.size();
  }

  /**
   * Returns the minimal search matches required to fully perform all the replacements. The original matches might
   * contain both parent and children matches, but replacing in the parent already replaced in the children matches.
   * 
   * @param allMatches
   *          all the matches.
   * @return the minimal search matches required to fully perform all the replacements.
   */
  private Set<SearchMatch> getMinimalSearchMatches(Set<SearchMatch> allMatches) {
    Set<SearchMatch> minimalMatches = new HashSet<>(allMatches);

    for (SearchMatch originalMatch : allMatches) {
      minimalMatches.removeAll(originalMatch.getChildren());
    }

    return minimalMatches;
  }
}
