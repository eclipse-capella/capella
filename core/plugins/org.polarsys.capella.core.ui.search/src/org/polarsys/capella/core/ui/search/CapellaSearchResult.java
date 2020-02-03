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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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
  private Map<Object, AbstractCapellaSearchEntry> elementToMatches = new HashMap();
  
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
    int displayedOccurrenceCount = 0; //getDisplayedOccurrenceCount();
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
    long count = getElements().length;
    return Math.toIntExact(count);
  }
  
  private Stream<AbstractCapellaSearchEntry> getCapellaSearchMatchesStream() {
    return Stream.of(getElements()) //
        .flatMap(e -> Stream.of(getMatches(e))) //
        .filter(AbstractCapellaSearchEntry.class::isInstance) //
        .map(AbstractCapellaSearchEntry.class::cast);
  }

  /**
   * Get all projects from matches
   * 
   * @return
   */
  public Set<Object> getProjects() {
    return searchEntries.keySet();
  }

  @Override
  public MatchFilter[] getAllMatchFilters() {
    return new MatchFilter[] { //
//        CapellaSearchMatchFilter.NOT_MODIFIABLE, //
//        CapellaSearchMatchFilter.CAPELLA_ELEMENT, //
//        CapellaSearchMatchFilter.REPRESENTATION //
    };
  }

  /////////
  
  
  /**
   * {@inheritDoc}
   */
  public AbstractCapellaSearchEntry insert(Object file, AbstractCapellaSearchEntry entry, boolean notify) {
    if (searchEntries.get(file) == null) {
      searchEntries.put(file, new ArrayList<Object>());
    }
    insert2(searchEntries.get(file), entry, notify);
    return entry;
  }

  /**
   * {@inheritDoc}
   */
  public AbstractCapellaSearchEntry insert(Object file, AbstractCapellaSearchEntry entry, Object eTypedElem, String valuation,
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
  private void insert2(Collection<Object> currentEntrySubHierarchyCollection, AbstractCapellaSearchEntry entryToInsert,
      boolean notify) {

      boolean alreadyExist = false;
      for (Object currentEntrySubHierarchy : currentEntrySubHierarchyCollection) {
        if (alreadyExist = currentEntrySubHierarchy.equals(entryToInsert)) {
          updateInsertionPoint((AbstractCapellaSearchEntry) currentEntrySubHierarchy, (AbstractCapellaSearchEntry) entryToInsert);
          insert3(currentEntrySubHierarchy, entryToInsert.getChildren(), notify);
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
      if (e2i instanceof AbstractCapellaSearchEntry && currentEntrySubHierarchy instanceof AbstractCapellaSearchEntry) {
        if (currentEntrySubHierarchy.equals(e2i)) {
          updateInsertionPoint((AbstractCapellaSearchEntry) currentEntrySubHierarchy, (AbstractCapellaSearchEntry) e2i);
        }
        insert2(((AbstractCapellaSearchEntry) currentEntrySubHierarchy).getChildren(), (AbstractCapellaSearchEntry) e2i, notify);
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
  private AbstractCapellaSearchEntry insert5(AbstractCapellaSearchEntry entryHierarchyIntoWhichInsert, Object eTypedElem, String text,
      boolean notify) {
    CapellaSearchMatchOccurence occurence = null;
    for (Object result : entryHierarchyIntoWhichInsert.getChildren()) {
      if (result instanceof CapellaSearchMatchOccurence) {
        AbstractCapellaSearchEntry oc = (AbstractCapellaSearchEntry) result;
        if (isAnInvalidETypedElement(((EObject) oc.getElement()), (ETypedElement) eTypedElem)
            || isAnAlreadyExistingOccurenceValuation(text, (ETypedElement) eTypedElem, (CapellaSearchMatchOccurence) oc)) {
          return occurence;
        }
      }
    }
    occurence = new CapellaSearchMatchOccurence(entryHierarchyIntoWhichInsert, eTypedElem, text, true);
    
    entryHierarchyIntoWhichInsert.addChildren(occurence);

    if (notify) {
      // fireItemAdded(occurence);
    }
    return occurence;
  }

  private void updateInsertionPoint(AbstractCapellaSearchEntry oldEntry, AbstractCapellaSearchEntry newEntry) {
    oldEntry.setMatchedOnce(!oldEntry.wasMatchedAtleastOnce() ? newEntry.wasMatchedAtleastOnce() : true);

  }

  private boolean isAnAlreadyExistingOccurenceValuation(String valuation, ETypedElement eTypedElem,
      CapellaSearchMatchOccurence oc) {
    return eTypedElem.equals(oc.getTypedElement())
        && valuation.equals(getTextFromETypedElement(((EObject) oc.getElement()), oc.getTypedElement()));
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

  public Map<Object, Collection<Object>> getRootResultHierarchies() {
    return searchEntries;
  }
  
  public void addElementToMatches(AbstractCapellaSearchEntry match) {
    elementToMatches.put(match.getElement(), match);
  }
  
  public Map<Object, AbstractCapellaSearchEntry> getElementToMatches() {
    return elementToMatches;
  }

  private void createEntries(AbstractCapellaSearchEntry entry) {
    for (Object result : entry.getChildren()) {
      AbstractCapellaSearchEntry resultEntry = (AbstractCapellaSearchEntry) result;
      elementToMatches.put(resultEntry.getElement(), resultEntry);
      createEntries(resultEntry);
    }
  }

  public void updateMapElementsToMatches() {
    for (Object key : searchEntries.keySet()) {
      Collection<Object> entries = searchEntries.get(key);
      for (Object entry : entries) {
        elementToMatches.put(((AbstractCapellaSearchEntry) entry).getElement(), (AbstractCapellaSearchEntry) entry);
        createEntries((AbstractCapellaSearchEntry) entry);
      }
    }
  }
}
