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
import org.eclipse.emf.ecore.ETypedElement;
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

  public IStatus run(IProgressMonitor monitor, Set<AbstractCapellaSearchEntry> allMatches, String replacement) {
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
        if (match instanceof CapellaSearchMatchEntry) {
          countTotalOccurrences += ((CapellaSearchMatchEntry) match).getMatchOccurrences().size();
        }
      }
      SubMonitor subMonitor = SubMonitor.convert(monitor, countTotalOccurrences);
      subMonitor.setTaskName(String.format(Messages.ReplaceJob_Title, replacement));
      for (Match match : allMatches) {
        if (match instanceof CapellaSearchMatchEntry) {
          CapellaSearchMatchEntry capellaMatch = (CapellaSearchMatchEntry) match;

          int countOccurrences = capellaMatch.getMatchOccurrences().size();
          String projectName = capellaMatch.getProject().getName();
          subMonitor.subTask(String.format(Messages.ReplaceJob_SubTitle, countOccurrences, projectName));

          for(Object childMatch : capellaMatch.getChildren()) {
            if(childMatch instanceof CapellaSearchMatchOccurence)
              replace((CapellaSearchMatchOccurence)childMatch, searchPattern, replacement);
          }

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

  public void replace(CapellaSearchMatchOccurence capellaMatch, Pattern searchPattern, String replacement) {
    Object element = capellaMatch.getElement();

    if (element instanceof EAttribute) {
      String oldLine = capellaMatch.getText();
      String newContent = searchPattern.matcher(oldLine).replaceAll(replacement);

      // Build the new content by replacing the oldLine by the newLine at exactly that line number
      EAttribute attribute = (EAttribute) element;
      if (attribute != null) {
        AbstractCapellaSearchEntry parent = capellaMatch.getParent();
        EObject parentElement = (EObject) parent.getElement();
       
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(parentElement);
        Command setCommand = SetCommand.create(domain, parentElement, attribute, newContent);
        domain.getCommandStack().execute(setCommand);
        replacedProjects.add(capellaMatch.getProject());
        replacedElements.add(parentElement);
        replacedOccurrenceCount += 1;
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
