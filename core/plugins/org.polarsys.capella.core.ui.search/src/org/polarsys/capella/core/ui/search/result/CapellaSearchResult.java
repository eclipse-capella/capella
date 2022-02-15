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
package org.polarsys.capella.core.ui.search.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.MatchFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.CapellaSearchQuery;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.match.SearchMatchChild;

public class CapellaSearchResult extends AbstractTextSearchResult {

  private CapellaSearchQuery capellaSearchQuery;
  private TreeData treeData;

  public CapellaSearchResult(CapellaSearchQuery capellaSearchQuery) {
    this.capellaSearchQuery = capellaSearchQuery;
    setActiveMatchFilters(new MatchFilter[] {});
    treeData = new TreeData(new ArrayList<Object>(), null) {
      // Customize this to add notes into search scope
      @Override
      protected Object doGetParent(Object element) {
        if (element instanceof DRepresentationDescriptor) {
          return ((DRepresentationDescriptor) element).getTarget();
        } else if (element instanceof Shape) {
          Diagram diagram = ((Shape) element).getDiagram();
          EObject elt = diagram.getElement();
          if (elt instanceof DDiagram) {
            return RepresentationHelper.getRepresentationDescriptor((DDiagram) elt);
          }
        } else if (element instanceof SearchMatchChild) {
          return ((SearchMatchChild) element).getParent();
        }
        return super.doGetParent(element);
      }

      @Override
      public Object[] getChildren(Object element) {
        if (element instanceof SearchMatch) {
          return ((SearchMatch) element).getChildren().toArray();
        }
        return super.getChildren(element);
      }
    };
  }

  @Override
  public String getLabel() {
    int totalOccurrenceCount = getOccurrenceCount();
    int matchedElementsCount = getElements().length;
    int matchedProjectsCount = getProjects().size();
    String queryLabel = capellaSearchQuery.getLabel();
    int activeFilterCount = getActiveMatchFilters().length;
    if (activeFilterCount == 0) {
      return String.format(CapellaSearchConstants.CapellaSearchResult_Label, queryLabel, totalOccurrenceCount,
          matchedElementsCount, matchedProjectsCount);
    }
    int displayedOccurrenceCount = getOccurrenceCount();
    return String.format(CapellaSearchConstants.CapellaSearchResult_Label_With_Active_Filters, queryLabel,
        totalOccurrenceCount, matchedElementsCount, matchedProjectsCount,
        totalOccurrenceCount - displayedOccurrenceCount, activeFilterCount);
  }

  @Override
  public String getTooltip() {
    return getLabel();
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), "icons/search.gif");
  }

  @Override
  public CapellaSearchQuery getQuery() {
    return capellaSearchQuery;
  }

  @Override
  public IEditorMatchAdapter getEditorMatchAdapter() {
    return null;
  }

  @Override
  public IFileMatchAdapter getFileMatchAdapter() {
    return null;
  }

  /**
   * Get all projects from matches
   * 
   * @return
   */
  public Set<IProject> getProjects() {
    return getCapellaSearchMatchesStream() //
        .map(SearchMatch::getProject) //
        .collect(Collectors.toSet());
  }

  /**
   * Count all occurrences of matches
   * 
   * @return Assuming that integer is sufficient for this count
   */
  public int getOccurrenceCount() {
    int count = 0;

    Set<SearchMatch> searchMatches = getCapellaSearchMatchesStream() //
        .filter(match -> !(match instanceof SearchMatchChild)) //
        .collect(Collectors.toSet());

    for (SearchMatch searchMatch : searchMatches) {
      List<SearchMatchChild> children = searchMatch.getChildren();
      count += children.isEmpty() ? 1 : children.size();
    }

    return count;
  }

  private Stream<SearchMatch> getCapellaSearchMatchesStream() {
    return Stream.of(getElements()) //
        .flatMap(e -> Stream.of(getMatches(e))) //
        .filter(SearchMatch.class::isInstance) //
        .map(SearchMatch.class::cast);
  }

  public Set<SearchMatch> getDisplayedMatches() {
    return getCapellaSearchMatchesStream().collect(Collectors.toSet());
  }

  public Set<SearchMatch> getDisplayedMatches(Object element) {
    if (element == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> element.equals(m.getElement())) //
        .collect(Collectors.toSet());
  }

  public TreeData getTreeData() {
    return treeData;
  }

  public List<SearchMatch> getCapellaEntryMatches(Object element) {
    return Arrays.asList(getMatches(element)).stream().filter(e -> e.getClass().equals(SearchMatch.class))
        .map(SearchMatch.class::cast).collect(Collectors.toList());
  }
}
