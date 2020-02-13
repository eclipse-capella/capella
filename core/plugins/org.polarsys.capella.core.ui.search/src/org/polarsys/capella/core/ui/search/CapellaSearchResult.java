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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.MatchFilter;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;

public class CapellaSearchResult extends AbstractTextSearchResult {

  private CapellaSearchQuery capellaSearchQuery;
  private TreeData treeData;
  
  public CapellaSearchResult(CapellaSearchQuery capellaSearchQuery) {
    this.capellaSearchQuery = capellaSearchQuery;
    setActiveMatchFilters(new MatchFilter[] {});
    treeData = new TreeData(new ArrayList<Object>(), null);
  }
  
  @Override
  public String getLabel() {
    int totalOccurrenceCount = getOccurrenceCount();
    int matchedElementsCount = getElements().length;
    int matchedProjectsCount = getProjects().size();
    String queryLabel = capellaSearchQuery.getLabel();
    int activeFilterCount = getActiveMatchFilters().length;
    if (activeFilterCount == 0) {
      return String.format(CapellaSearchConstants.CapellaSearchResult_Label, queryLabel, totalOccurrenceCount, matchedElementsCount,
          matchedProjectsCount);
    }
    int displayedOccurrenceCount = getOccurrenceCount();
    return String.format(CapellaSearchConstants.CapellaSearchResult_Label_With_Active_Filters, queryLabel, totalOccurrenceCount,
        matchedElementsCount, matchedProjectsCount, totalOccurrenceCount - displayedOccurrenceCount, activeFilterCount);
  }

  @Override
  public String getTooltip() {
    return getLabel();
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return Activator.getDefault().getImageDescriptor("search.gif"); //$NON-NLS-1$
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
        .map(CapellaSearchMatchEntry::getProject) //
        .collect(Collectors.toSet());
  }

  /**
   * Count all occurrences of matches
   * @return Assuming that integer is sufficient for this count
   */
  public int getOccurrenceCount() {
    long count = getElements().length;
    return Math.toIntExact(count);
  }
  
  private Stream<CapellaSearchMatchEntry> getCapellaSearchMatchesStream() {
    return Stream.of(getElements()) //
        .flatMap(e -> Stream.of(getMatches(e))) //
        .filter(CapellaSearchMatchEntry.class::isInstance) //
        .map(CapellaSearchMatchEntry.class::cast);
  }
  
  public Set<CapellaSearchMatchEntry> getDisplayedMatches() {
    return getCapellaSearchMatchesStream()
        .collect(Collectors.toSet());
  }
  
  public Set<CapellaSearchMatchEntry> getDisplayedMatches(Object element) {
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
}
