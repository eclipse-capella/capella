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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.search.ui.text.Match;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class CapellaReplaceQuery {

  private final CapellaSearchSettings capellaSearchSettings;

  Set<EObject> replacedElements = new HashSet<>();
  Set<IProject> replacedProjects = new HashSet<>();
  int replacedOccurrenceCount = 0;

  public CapellaReplaceQuery(CapellaSearchSettings capellaSearchSettings) {
    this.capellaSearchSettings = capellaSearchSettings;
  }

  public IStatus run(IProgressMonitor monitor, Set<CapellaSearchMatch> allMatches, String replacement) {
    replacedElements = new HashSet<>();
    replacedProjects = new HashSet<>();
    replacedOccurrenceCount = 0;
    if (replacement == null) {
      // Replacement can be empty if users want to remove those occurrences matching search pattern.
      // But can not be null
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.CapellaReplaceQuery_Validation_Replacement_Null);
    }
    try {
      Pattern searchPattern = capellaSearchSettings.createPattern();
      int countTotalOccurrences = 0;
      for (Match match : allMatches) {
        if (match instanceof CapellaSearchMatch) {
          countTotalOccurrences += ((CapellaSearchMatch) match).getMatchOccurrences().size();
        }
      }
      SubMonitor subMonitor = SubMonitor.convert(monitor, countTotalOccurrences);
      subMonitor.setTaskName(String.format(Messages.ReplaceJob_Title, replacement));
      for (Match match : allMatches) {
        if (match instanceof CapellaSearchMatch) {
          CapellaSearchMatch capellaSearchMatch = (CapellaSearchMatch) match;

          int countOccurrences = capellaSearchMatch.getMatchOccurrences().size();
          String projectName = capellaSearchMatch.getProject().getName();
          subMonitor.subTask(String.format(Messages.ReplaceJob_SubTitle, countOccurrences, projectName));

          replace(capellaSearchMatch, searchPattern, replacement);

          subMonitor.split(countOccurrences);
        }
      }
      return Status.OK_STATUS;
    } catch (Exception e) {
      String message = e.getMessage();
      if (e instanceof PatternSyntaxException) {
        message = String.format(Messages.CapellaSearchQuery_Search_Pattern_Not_Validated_Message,
            ((PatternSyntaxException) e).getPattern(), ((PatternSyntaxException) e).getDescription());
      }
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    }
  }

  public void replace(CapellaSearchMatch capellaMatch, Pattern searchPattern, String replacement) {
    Object element = capellaMatch.getElement();

    if (element instanceof EObject) {
      CapellaSearchField searchField = capellaMatch.getSearchField();
      String oldLine = capellaMatch.getLineContent();
      String newLine = searchPattern.matcher(oldLine).replaceAll(replacement);

      // Build the new content by replacing the oldLine by the newLine at exactly that line number
      String oldContent = searchField.getText(element);
      StringBuilder newContentBuilder = new StringBuilder();
      String systemLineSeparator = System.lineSeparator();
      String[] lines = oldContent.split(systemLineSeparator);
      for (int i = 0; i < lines.length; i++) {
        if (capellaMatch.getLineNumber() == i + 1) {
          newContentBuilder.append(newLine);
        } else {
          newContentBuilder.append(lines[i]);
        }
        if (i < lines.length - 1) {
          newContentBuilder.append(systemLineSeparator);
        }
      }
      String newContent = newContentBuilder.toString();

      EAttribute attribute = searchField.getEAttribute(element);
      if (attribute != null) {
        // TODO:
        // - should we make all replaces in one command to facilitate the undo
        // - will it cause performance issue
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject) element);
        Command setCommand = SetCommand.create(domain, element, attribute, newContent);
        domain.getCommandStack().execute(setCommand);
        replacedProjects.add(capellaMatch.getProject());
        replacedElements.add((EObject) element);
        replacedOccurrenceCount += capellaMatch.getMatchOccurrences().size();
      }
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
}
