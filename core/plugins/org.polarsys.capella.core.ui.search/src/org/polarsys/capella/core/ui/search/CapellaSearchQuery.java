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
          if (isMatchOccurrences(pattern, line)) {
            AbstractCapellaSearchEntry result = buildSearchResultOccurenceHierarchy(project, element, searchField.getEAttribute(element), text, false);
          }
        }
      }
    });

    Object[] children = contentProvider.getChildren(element);
    for (int i = 0; i < children.length; i++) {
      search(pattern, children[i], project);
    }
    capellaSearchResult.updateMapElementsToMatches();
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
  
  public AbstractCapellaSearchEntry buildSearchResultOccurenceHierarchy(IProject project, Object eObj, Object eTypedElem, String valuation, boolean notify) {
    AbstractCapellaSearchEntry entry = buildSearchResultEntryHierarchy(project, eObj);
    
    capellaSearchResult.insert(project, entry, notify);
    AbstractCapellaSearchEntry entryIntoWhichInsert = getLeafEntryFromWantedEObject(entry, eObj);
    capellaSearchResult.addMatch(entryIntoWhichInsert);
    
    return capellaSearchResult.insert(project, entryIntoWhichInsert, eTypedElem, valuation, notify);
  }
  
  protected AbstractCapellaSearchEntry buildSearchResultHierarchy(IProject project, CapellaSearchMatchEntry intermediate, Object container) {
    if (container instanceof EObject ) {
      CapellaSearchMatchEntry entryContainer = new CapellaSearchMatchEntry(null, container, false, project);
      entryContainer.addChildren(intermediate);
      intermediate.setParent(entryContainer);
      return buildSearchResultHierarchy(project, entryContainer, ((EObject)container).eContainer());
    }
    return intermediate;
  }

  public AbstractCapellaSearchEntry buildSearchResultEntryHierarchy(IProject project, Object o) {
    AbstractCapellaSearchEntry e = new CapellaSearchMatchEntry(null, o, true, project);
    if (o instanceof EObject) {
        return buildSearchResultEntryHierarchy(project, e, ((EObject)o).eContainer());
    }
    return e;
  }
  
  // Recursively build an EObject ascending containment hierarchy
  protected AbstractCapellaSearchEntry buildSearchResultEntryHierarchy(IProject project, AbstractCapellaSearchEntry intermediate, Object container) {
    if (container instanceof EObject ) {
      AbstractCapellaSearchEntry entryContainer = new CapellaSearchMatchEntry(null, container, false, project);
      entryContainer.addChildren(intermediate);
      intermediate.setParent(entryContainer);
      return buildSearchResultEntryHierarchy(project, entryContainer, ((EObject)container).eContainer());
    }
    return intermediate;
  }
  
  private AbstractCapellaSearchEntry getLeafEntryFromWantedEObject(AbstractCapellaSearchEntry entry, Object eObj) {
    if (entry instanceof AbstractCapellaSearchEntry) {
      AbstractCapellaSearchEntry e = (AbstractCapellaSearchEntry) entry;
      if (e.getElement().equals(eObj)) {
        return e;
      }
      if (e.getChildren().size()==1) {
        if (e.getChildren().toArray()[0] instanceof AbstractCapellaSearchEntry) {
          return getLeafEntryFromWantedEObject((AbstractCapellaSearchEntry) e.getChildren().toArray()[0], eObj);
        }
      }
    }
    return null;
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
