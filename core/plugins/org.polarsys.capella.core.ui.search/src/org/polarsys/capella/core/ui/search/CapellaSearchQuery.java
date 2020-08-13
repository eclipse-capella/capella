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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorContentProvider;
import org.polarsys.capella.core.ui.search.match.LineSearchMatchChild;
import org.polarsys.capella.core.ui.search.match.ListElementSearchMatchChild;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForNoteItem;

public class CapellaSearchQuery implements ISearchQuery {

  private final CapellaSearchResult capellaSearchResult = new CapellaSearchResult(this);
  private final CapellaSearchSettings capellaSearchSettings;

  private final ITreeContentProvider contentProvider = new CapellaNavigatorContentProvider() {
    // Extend the search scope to include note from diagram
    @Override
    public Object[] getChildren(Object element) {
      if (element instanceof DRepresentationDescriptor
          && ((DRepresentationDescriptor) element).isLoadedRepresentation()) {
        DRepresentation representation = ((DRepresentationDescriptor) element).getRepresentation();
        if (representation instanceof DDiagram) {
          List<Shape> notes = new ArrayList<>();
          DDiagramGraphicalQuery query = new DDiagramGraphicalQuery((DDiagram) representation);
          Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
          if (gmfDiagram.some()) {
            for (Object child : gmfDiagram.get().getChildren()) {
              if (child instanceof Shape && ViewType.NOTE.equals(((Shape) child).getType())) {
                notes.add((Shape) child);
              }
            }
          }
          return notes.toArray();
        }
      }
      return super.getChildren(element);
    }
  };

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
   * @param element
   *          the entry point to launch the search
   * @param project
   */
  private void search(Pattern pattern, Object element, IProject project) {
    if (element instanceof EObject) {
      EObject eObj = (EObject) element;

      Set<Object> searchClassItems = capellaSearchSettings.getSearchClassItems();
      for (Object searchClassItem : searchClassItems) {
        if (searchClassItem instanceof SearchForClassItem && ((SearchForClassItem) searchClassItem).covers(eObj)) {

          if (searchClassItem instanceof SearchForNoteItem) {
            searchForAttribute(pattern, project, eObj, (SearchForNoteItem) searchClassItem);
          } else {
            Set<Object> searchAttributeItems = capellaSearchSettings.getSearchAttributeItems();
            for (Object searchAttributeItem : searchAttributeItems) {
              if (searchAttributeItem instanceof SearchForAttributeItem) {
                searchForAttribute(pattern, project, eObj, (SearchForAttributeItem) searchAttributeItem);
              }
            }

          }
        }
      }
    }

    Object[] children = contentProvider.getChildren(element);
    for (int i = 0; i < children.length; i++) {
      // search recursively in all the elements in the project tree
      search(pattern, children[i], project);
    }
  }

  protected void searchForAttribute(Pattern pattern, IProject project, EObject inputObject,
      SearchForAttributeItem attributeItem) {

    Object searchAttribute = attributeItem.getAttributeFor(inputObject);
    if (searchAttribute != null) {
      Object searchData = attributeItem.getRelevantSearchData(inputObject);

      if (searchData instanceof String) {
        String searchText = (String) searchData;
        String[] searchTextLines = searchText.split("\n");

        if (searchTextLines.length == 1) {
          if (isMatchOccurrences(pattern, searchText)) {
            SearchMatch result = new SearchMatch(inputObject, searchText, project, searchAttribute);
            capellaSearchResult.addMatch(result);
            capellaSearchResult.getTreeData().addElement(inputObject);
          }
        } else {
          SearchMatch parentSearchMatch = new SearchMatch(inputObject, null, project, searchAttribute);
          boolean matched = false;
          for (int number = 0; number < searchTextLines.length; number++) {
            String searchTextLine = searchTextLines[number];

            if (isMatchOccurrences(pattern, searchTextLine)) {
              LineSearchMatchChild childSearchMatch = new LineSearchMatchChild(inputObject, searchTextLine, project,
                  parentSearchMatch, number);

              parentSearchMatch.getChildren().add(childSearchMatch);
              capellaSearchResult.addMatch(childSearchMatch);
              matched = true;
            }
          }

          if (matched) {
            capellaSearchResult.addMatch(parentSearchMatch);
            capellaSearchResult.getTreeData().addElement(inputObject);
          }
        }

      }

      else if (searchData instanceof List) {
        List<?> searchDataList = ((List<?>) searchData);
        SearchMatch parentSearchMatch = new SearchMatch(inputObject, null, project, searchAttribute);
        boolean matched = false;

        for (int index = 0; index < searchDataList.size(); index++) {
          Object searchElement = searchDataList.get(index);

          if (searchElement instanceof String) {
            String searchText = (String) searchDataList.get(index);

            if (isMatchOccurrences(pattern, searchText)) {
              ListElementSearchMatchChild childSearchMatch = new ListElementSearchMatchChild(inputObject, searchText,
                  project, parentSearchMatch, index);
              parentSearchMatch.getChildren().add(childSearchMatch);
              capellaSearchResult.addMatch(childSearchMatch);
              matched = true;
            }
          }
        }

        if (matched) {
          capellaSearchResult.addMatch(parentSearchMatch);
          capellaSearchResult.getTreeData().addElement(inputObject);
        }
      }
    }
  }

  protected void searchForAttribute(Pattern pattern, IProject project, EObject eObj, SearchForNoteItem searchNoteItem) {
    Object searchData = searchNoteItem.getRelevantSearchData(eObj);

    if (searchData instanceof String) {
      String textToSearch = (String) searchData;
      EAttribute shapeDescriptionAttribute = searchNoteItem.getContentAttribute();
      if (isMatchOccurrences(pattern, textToSearch)) {
        SearchMatch result = new SearchMatch(eObj, textToSearch, project, shapeDescriptionAttribute);
        capellaSearchResult.addMatch(result);
        // use tree data if we want to display the result as a tree
        capellaSearchResult.getTreeData().addElement(eObj);
      }
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
