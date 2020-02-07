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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorContentProvider;

public class CapellaSearchQuery implements ISearchQuery {

  private final CapellaSearchResult capellaSearchResult = new CapellaSearchResult(this);
  private final CapellaSearchSettings capellaSearchSettings;

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
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
          String line = lines[i];
          if (isMatchOccurrences(pattern, line)) {
            CapellaSearchMatchEntry result = new CapellaSearchMatchEntry(element, text, project, searchField.getEAttribute(element));
            capellaSearchResult.addMatch(result);
            capellaSearchResult.getTreeData().addElement(element);
          }
        }
      }
    });

    Object[] children = contentProvider.getChildren(element);
    for (int i = 0; i < children.length; i++) {
      search(pattern, children[i], project);
    }
  }

  private boolean isMatchOccurrences(Pattern pattern, String text) {
    if (text != null && !text.isEmpty()) {
      Matcher matcher = pattern.matcher(text);
      if (matcher.find()) {
        return true;
      }
    }

    return false;
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
