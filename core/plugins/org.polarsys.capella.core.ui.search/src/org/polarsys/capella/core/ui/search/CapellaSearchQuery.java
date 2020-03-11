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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
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
      Set<Object> selectedObjects = capellaSearchSettings.getObjectsToSearch();

      SubMonitor subMonitor = SubMonitor.convert(monitor, selectedObjects.size());
      subMonitor
          .setTaskName(String.format(CapellaSearchConstants.SearchJob_Title, capellaSearchSettings.getTextPattern()));
      for (Object selectedObj : selectedObjects) {
        IProject project = null;
        if (selectedObj instanceof IProject) {
          subMonitor
              .subTask(String.format(CapellaSearchConstants.SearchJob_SubTitle, ((IProject) selectedObj).getName()));
          project = workspaceRoot.getProject(((IProject) selectedObj).getName());
        } else if (selectedObj instanceof EObject) {
          project = PreferencesHelper.getProject((EObject) selectedObj);
        }
        // search the pattern in the projects
        if (project != null) {
          search(pattern, selectedObj, project);
          subMonitor.split(1);
        }
      }
      return Status.OK_STATUS;
    } catch (PatternSyntaxException e) {
      String message = String.format(CapellaSearchConstants.CapellaSearchQuery_Search_Pattern_Not_Validated_Message,
          e.getPattern(), e.getDescription());
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    } catch (Exception e) {
      String message = e.getMessage();
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    }
  }

  /**
   * 
   * @param pattern
   * @param element the entry point to launch the search
   * @param project
   */
  private void search(Pattern pattern, Object element, IProject project) {
    if (element instanceof EObject) {
      EObject eObj = (EObject) element;
      capellaSearchSettings.getSearchMetaClasses().forEach(metaObj -> {
        if ((metaObj instanceof EClass)) {
          EClass eCls = (EClass) metaObj;
          if (eObj.eClass().equals(eCls)) {
            capellaSearchSettings.getSearchAttributes().forEach(attrObj -> {
              EAttribute attribute = (EAttribute) attrObj;
              try {
                Object attrValue = eObj.eGet(attribute);
                if (attrValue instanceof String) {
                  String attrString = (String) attrValue;
                  if (isMatchOccurrences(pattern, attrString)) {
                    CapellaSearchMatchEntry result = new CapellaSearchMatchEntry(element, attrString, project,
                        attribute);
                    capellaSearchResult.addMatch(result);
                    // use tree data if we want to display the result as a tree
                    capellaSearchResult.getTreeData().addElement(element);
                  }
                }
              } catch (Exception e) {
              }
            });
          }
        }
      });
    }
    Object[] children = contentProvider.getChildren(element);
    for (int i = 0; i < children.length; i++) {
      // search recursively in all the elements in the project tree
      search(pattern, children[i], project);
    }
  }

  private boolean isMatchOccurrences(Pattern pattern, String text) {
    if (text != null && !text.isEmpty()) {
      Matcher matcher = pattern.matcher(text);
      try {
        if (matcher.find()) {
          return true;
        }
      } catch (Exception e) {
        System.err.println(e);
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
