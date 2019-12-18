/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorContentProvider;

public class CapellaSearchQuery implements ISearchQuery {

  private final CapellaSearchResult capellaSearchResult = new CapellaSearchResult(this);
  private final CapellaSearchSettings capellaSearchSettings;

  // TODO: is it possible to make it independently from ITreeContentProvider and CapellaNavigatorContentProvider?
  private final ITreeContentProvider contentProvider = new CapellaNavigatorContentProvider();

  public CapellaSearchQuery(CapellaSearchSettings capellaSearchSettings) {
    this.capellaSearchSettings = capellaSearchSettings;
  }

  @Override
  public IStatus run(IProgressMonitor monitor) {
    capellaSearchResult.removeAll();
    try {
      Pattern pattern = capellaSearchSettings.createPattern();
      IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
      Set<String> selectedProjects = capellaSearchSettings.getProjects();

      SubMonitor subMonitor = SubMonitor.convert(monitor, selectedProjects.size());
      subMonitor.setTaskName(String.format(Messages.SearchJob_Title, capellaSearchSettings.getTextPattern()));
      for (String projectName : selectedProjects) {
        subMonitor.subTask(String.format(Messages.SearchJob_SubTitle, projectName));
        IProject project = workspaceRoot.getProject(projectName);
        search(pattern, project, project);
        subMonitor.split(1);
      }
      return Status.OK_STATUS;
    } catch (PatternSyntaxException e) {
      String message = String.format(Messages.CapellaSearchQuery_Search_Pattern_Not_Validated_Message, e.getPattern(),
          e.getDescription());
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    } catch (Exception e) {
      String message = e.getMessage();
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    }
  }

  private void search(Pattern pattern, Object element, IProject project) {
    capellaSearchSettings.getSearchFields().forEach(searchField -> {
      String text = searchField.getText(element);
      if (text != null) {
        String[] lines = text.split(System.lineSeparator()); // $NON-NLS-1$
        for (int i = 0; i < lines.length; i++) {
          String line = lines[i];
          List<CapellaSearchMatchOccurrence> matchOccurrences = findMatchOccurrences(pattern, line);
          if (!matchOccurrences.isEmpty()) {
            int lineNumber = i + 1; // Line number starts from 1
            CapellaSearchMatch capellaSearchMatch = new CapellaSearchMatch(element, project, lineNumber, line, text,
                searchField);
            matchOccurrences.stream().forEach(capellaSearchMatch::addMatchOccurrence);
            capellaSearchResult.addMatch(capellaSearchMatch);
          }
        }
      }
    });

    Object[] children = contentProvider.getChildren(element);
    for (int i = 0; i < children.length; i++) {
      search(pattern, children[i], project);
    }
  }

  private List<CapellaSearchMatchOccurrence> findMatchOccurrences(Pattern pattern, String text) {
    List<CapellaSearchMatchOccurrence> matchOccurrences = new ArrayList<>();

    if (text != null && !text.isEmpty()) {
      Matcher matcher = pattern.matcher(text);
      while (matcher.find()) {
        int offset = matcher.start();
        int length = matcher.end() - offset;
        matchOccurrences.add(new CapellaSearchMatchOccurrence(offset, length));
      }
    }

    return matchOccurrences;
  }

  @Override
  public String getLabel() {
    return capellaSearchSettings.getTextPattern();
  }

  @Override
  public boolean canRerun() {
    return true;
  }

  @Override
  public boolean canRunInBackground() {
    return true;
  }

  @Override
  public CapellaSearchResult getSearchResult() {
    return capellaSearchResult;
  }

  public CapellaSearchSettings getCapellaSearchSettings() {
    return capellaSearchSettings;
  }
}
