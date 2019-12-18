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

public class CapellaSearchResult extends AbstractTextSearchResult {

  private CapellaSearchQuery capellaSearchQuery;

  public CapellaSearchResult(CapellaSearchQuery capellaSearchQuery) {
    this.capellaSearchQuery = capellaSearchQuery;
    setActiveMatchFilters(new MatchFilter[] {}); // By default, no filter is activated
  }

  @Override
  public String getLabel() {
    int totalOccurrenceCount = getOccurrenceCount();
    int matchedElementsCount = getElements().length;
    int matchedProjectsCount = getProjects().size();
    String queryLabel = capellaSearchQuery.getLabel();
    int activeFilterCount = getActiveMatchFilters().length;
    if (activeFilterCount == 0) {
      return String.format(Messages.CapellaSearchResult_Label, queryLabel, totalOccurrenceCount, matchedElementsCount,
          matchedProjectsCount);
    }
    int displayedOccurrenceCount = getDisplayedOccurrenceCount();
    return String.format(Messages.CapellaSearchResult_Label_With_Active_Filters, queryLabel, totalOccurrenceCount,
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
   * Count all occurrences of matches
   * 
   * @return Assuming that integer is sufficient for this count
   */
  public int getOccurrenceCount() {
    long count = getCapellaSearchMatchesStream() //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all occurrences of matches whose element equals to the element
   * 
   * @param element
   * @return Assuming that integer is sufficient for this count
   */
  public int getOccurrenceCount(Object element) {
    if (element == null) {
      return 0;
    }
    long count = (int) getCapellaSearchMatchesStream() //
        .filter(m -> element.equals(m.getElement())) //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all occurrences of matches whose project equals to the project
   * 
   * @param project
   * @return Assuming that integer is sufficient for this count
   */
  public int getOccurrenceCount(IProject project) {
    if (project == null) {
      return 0;
    }
    long count = (int) getCapellaSearchMatchesStream() //
        .filter(m -> project.equals(m.getProject())) //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all occurrences whose match are not filtered by {@link MatchFilter}
   * 
   * @return Assuming that integer is sufficient for this count
   */
  public int getDisplayedOccurrenceCount() {
    long count = (int) getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all occurrences whose match are not filtered by {@link MatchFilter} and whose element equals to the element
   * 
   * @param element
   * @return Assuming that integer is sufficient for this count
   */
  public int getDisplayedOccurrenceCount(Object element) {
    if (element == null) {
      return 0;
    }
    long count = getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .filter(m -> element.equals(m.getElement())) //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all matches who are not filtered by {@link MatchFilter} and whose project equals to the project
   * 
   * @param project
   * @return Assuming that integer is sufficient for this count
   */
  public int getDisplayedOccurrenceCount(IProject project) {
    if (project == null) {
      return 0;
    }
    long count = getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .filter(m -> project.equals(m.getProject())) //
        .flatMap(m -> m.getMatchOccurrences().stream()) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Count all matches whose project equals to the project
   * 
   * @param project
   * @return Assuming that integer is sufficient for this count
   */
  public int getMatchCount(IProject project) {
    if (project == null) {
      return 0;
    }
    long count = getCapellaSearchMatchesStream() //
        .filter(m -> project.equals(m.getProject())) //
        .count();
    return Math.toIntExact(count);
  }

  /**
   * Get all projects from matches
   * 
   * @return
   */
  public Set<IProject> getProjects() {
    return getCapellaSearchMatchesStream() //
        .map(CapellaSearchMatch::getProject) //
        .collect(Collectors.toSet());
  }

  /**
   * Get all elements whose match belongs to the project
   * 
   * @param project
   * @return empty set if the project is null
   */
  public Set<Object> getElements(IProject project) {
    if (project == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> project.equals(m.getProject())) //
        .map(CapellaSearchMatch::getElement) //
        .collect(Collectors.toSet());
  }

  /**
   * Get all elements whose match is not filtered by {@link MatchFilter} and belongs to the project
   * 
   * @param project
   * @return empty set if project is null
   */
  public Set<Object> getDisplayedElements(IProject project) {
    if (project == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .filter(m -> project.equals(m.getProject())) //
        .map(CapellaSearchMatch::getElement) //
        .collect(Collectors.toSet());
  }

  private Stream<CapellaSearchMatch> getCapellaSearchMatchesStream() {
    return Stream.of(getElements()) //
        .flatMap(e -> Stream.of(getMatches(e))) //
        .filter(CapellaSearchMatch.class::isInstance) //
        .map(CapellaSearchMatch.class::cast);
  }

  /**
   * Get all matches
   * 
   * @return
   */
  public Set<CapellaSearchMatch> getMatches() {
    return getCapellaSearchMatchesStream() //
        .collect(Collectors.toSet());
  }

  /**
   * Get all matches belonging to the project
   * 
   * @param project
   * @return empty set if project is null
   */
  public Set<CapellaSearchMatch> getMatches(IProject project) {
    if (project == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> project.equals(m.getProject())) //
        .collect(Collectors.toSet());
  }

  /**
   * Get all matches not filtered by {@link MatchFilter}
   * 
   * @return
   */
  public Set<CapellaSearchMatch> getDisplayedMatches() {
    return getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .collect(Collectors.toSet());
  }

  /**
   * Get all matches not filtered by {@link MatchFilter} and belonging to the element
   * 
   * @param element
   * @return empty set if element is null
   */
  public Set<CapellaSearchMatch> getDisplayedMatches(Object element) {
    if (element == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .filter(m -> element.equals(m.getElement())) //
        .collect(Collectors.toSet());
  }

  /**
   * Get all matches not filtered by {@link MatchFilter} and belonging to the project
   * 
   * @param project
   * @return empty set if project is null
   */
  public Set<CapellaSearchMatch> getDisplayedMatches(IProject project) {
    if (project == null) {
      return Collections.emptySet();
    }
    return getCapellaSearchMatchesStream() //
        .filter(m -> !m.isFiltered()) //
        .filter(m -> project.equals(m.getProject())) //
        .collect(Collectors.toSet());
  }

  @Override
  public MatchFilter[] getAllMatchFilters() {
    return new MatchFilter[] { //
        CapellaSearchMatchFilter.NOT_MODIFIABLE, //
        CapellaSearchMatchFilter.CAPELLA_ELEMENT, //
        CapellaSearchMatchFilter.REPRESENTATION //
    };
  }
}
