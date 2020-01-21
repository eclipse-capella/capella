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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.MatchFilter;

public class CapellaSearchResult extends AbstractTextSearchResult {

  private CapellaSearchQuery capellaSearchQuery;

  /**
   * Current Search Entries
   */
  private Map<Object, Collection<Object>> searchEntries;

  public CapellaSearchResult(CapellaSearchQuery capellaSearchQuery) {
    this.capellaSearchQuery = capellaSearchQuery;
    setActiveMatchFilters(new MatchFilter[] {}); // By default, no filter is activated
    searchEntries = new HashMap<Object, Collection<Object>>();
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

  /////////

  /**
   * {@inheritDoc}
   */
  public AbstractCapellaSearchResultEntry insert(Object file, AbstractCapellaSearchResultEntry entry, boolean notify) {
    if (searchEntries.get(file) == null) {
      searchEntries.put(file, new ArrayList<Object>());
    }
    insert2(searchEntries.get(file), entry, notify);
    return entry;
  }

  /**
   * {@inheritDoc}
   */
  public AbstractCapellaSearchResultEntry insert(Object file, AbstractCapellaSearchResultEntry entry, Object eTypedElem, String valuation,
      boolean notify) {
    return insert5(entry, eTypedElem, valuation, notify);
  }

  /**
   * Inserts & merges an entry sequence into an existing entry sequence hierarchy
   * 
   * @param currentEntrySubHierarchyCollection
   * @param entryToInsert
   * @param notify
   */
  private void insert2(Collection<Object> currentEntrySubHierarchyCollection, AbstractCapellaSearchResultEntry entryToInsert,
      boolean notify) {

      boolean alreadyExist = false;
      for (Object currentEntrySubHierarchy : currentEntrySubHierarchyCollection) {
        if (alreadyExist = currentEntrySubHierarchy.equals(entryToInsert)) {
          updateInsertionPoint((AbstractCapellaSearchResultEntry) currentEntrySubHierarchy, (AbstractCapellaSearchResultEntry) entryToInsert);
          insert3(currentEntrySubHierarchy, entryToInsert.getResults(), notify);
          break;
        }
      }
      if (!alreadyExist) {
        currentEntrySubHierarchyCollection.add(entryToInsert);
        if (notify) {
          // fireItemAdded(entryToInsert);
      }
    }
  }

  /**
   * Inserts & merges an entry into an existing entry sequence hierarchy
   * 
   * @param currentEntrySubHierarchy
   * @param entrySubtreeToInsertCollection
   * @param notify
   */
  private void insert3(Object currentEntrySubHierarchy, Collection<Object> entrySubtreeToInsertCollection,
      boolean notify) {
    for (Object e2i : entrySubtreeToInsertCollection) {
      if (e2i instanceof AbstractCapellaSearchResultEntry && currentEntrySubHierarchy instanceof AbstractCapellaSearchResultEntry) {
        if (currentEntrySubHierarchy.equals(e2i)) {
          updateInsertionPoint((AbstractCapellaSearchResultEntry) currentEntrySubHierarchy, (AbstractCapellaSearchResultEntry) e2i);
        }
        insert2(((AbstractCapellaSearchResultEntry) currentEntrySubHierarchy).getResults(), (AbstractCapellaSearchResultEntry) e2i, notify);
      }
    }
  }

  /**
   * Inserts & merges an entry sequence into an existing entry
   * 
   * @param compoundEntryToInsert
   * @param entryToInsert
   * @param notify
   * 
   * @return newly inserted occurence entry
   */
  private AbstractCapellaSearchResultEntry insert5(AbstractCapellaSearchResultEntry entryHierarchyIntoWhichInsert, Object eTypedElem, String text,
      boolean notify) {
    CapellaSearchResultOccurence occurence = null;
    for (Object result : entryHierarchyIntoWhichInsert.getResults()) {
      if (result instanceof CapellaSearchResultOccurence) {
        AbstractCapellaSearchResultEntry oc = (AbstractCapellaSearchResultEntry) result;
        if (isAnInvalidETypedElement(((EObject) oc.getSource()), (ETypedElement) eTypedElem)
            || isAnAlreadyExistingOccurenceValuation(text, (ETypedElement) eTypedElem, (CapellaSearchResultOccurence) oc)) {
          return occurence;
        }
      }
    }
    occurence = new CapellaSearchResultOccurence(entryHierarchyIntoWhichInsert,
        entryHierarchyIntoWhichInsert.getSource(), eTypedElem, text, true);

    entryHierarchyIntoWhichInsert.addChildren(occurence);

    if (notify) {
      // fireItemAdded(occurence);
    }
    return occurence;
  }

  private void updateInsertionPoint(AbstractCapellaSearchResultEntry oldEntry, AbstractCapellaSearchResultEntry newEntry) {
    oldEntry.setMatchedOnce(!oldEntry.wasMatchedAtleastOnce() ? newEntry.wasMatchedAtleastOnce() : true);

  }

  private boolean isAnAlreadyExistingOccurenceValuation(String valuation, ETypedElement eTypedElem,
      CapellaSearchResultOccurence oc) {
    return eTypedElem.equals(oc.getTypedElement())
        && valuation.equals(getTextFromETypedElement(((EObject) oc.getSource()), oc.getTypedElement()));
  }

  private boolean isAnInvalidETypedElement(EObject obj, ETypedElement eTypedElem) {
    for (EAttribute attribute : obj.eClass().getEAllAttributes()) {
      if (attribute.equals(eTypedElem)) {
        return false;
      }
    }
    return true;
  }

  private String getTextFromETypedElement(EObject obj, ETypedElement elem) {
    if (elem instanceof EAttribute) {
      return EcoreUtil.convertToString(((EAttribute) elem).getEAttributeType(), obj.eGet((EStructuralFeature) elem));
    }
    return ""; //$NON-NLS-1$
  }

}
