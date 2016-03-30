/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.internal.misc.StringMatcher;

/**
 * Copied from org.eclipse.ui.dialogs to export package methods.<br>
 * A filter used in conjunction with <code>FilteredTree</code>. In order to determine if a node should be filtered it uses the content provider of the tree to
 * do pattern matching on its children. This causes the entire tree structure to be realized.
 * @see FilteredTree
 * @since 3.2
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PatternFilter extends ViewerFilter {
   
  private StringMatcherFactory stringMatcherFactory = new StringMatcherFactory();
  
  /*
   * Cache of filtered elements in the tree
   */
  private Map _cache = new HashMap();

  /*
   * Maps parent elements to TRUE or FALSE
   */
  private Map _foundAnyCache = new HashMap();

  private boolean _useCache = false;

  /**
   * Whether to include a leading wildcard for all provided patterns. A trailing wildcard is always included.
   */
  private boolean _includeLeadingWildcard = false;

  /**
   * The string pattern _matcher used for this pattern filter.
   */
  private StringMatcher _matcher;

  private boolean _useEarlyReturnIfMatcherIsNull = true;

  private static Object[] EMPTY = new Object[0];

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#filter(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object[])
   */
  @Override
  public final Object[] filter(Viewer viewer, Object parent, Object[] elements) {
    // we don't want to optimize if we've extended the filter ... this
    // needs to be addressed in 3.4
    // https://bugs.eclipse.org/bugs/show_bug.cgi?id=186404
    if (_matcher == null && _useEarlyReturnIfMatcherIsNull) {
      return elements;
    }

    if (!_useCache) {
      return super.filter(viewer, parent, elements);
    }

    Object[] filtered = (Object[]) _cache.get(parent);
    if (filtered == null) {
      Boolean foundAny = (Boolean) _foundAnyCache.get(parent);
      if (foundAny != null && !foundAny.booleanValue()) {
        filtered = EMPTY;
      } else {
        filtered = super.filter(viewer, parent, elements);
      }
      _cache.put(parent, filtered);
    }
    return filtered;
  }

  /**
   * Returns true if any of the elements makes it through the filter. This method uses caching if enabled; the computation is done in computeAnyVisible.
   * @param viewer
   * @param parent
   * @param elements the elements (must not be an empty array)
   * @return true if any of the elements makes it through the filter.
   */
  protected boolean isAnyVisible(Viewer viewer, Object parent, Object[] elements) {
    if (_matcher == null) {
      return true;
    }

    if (!_useCache) {
      return computeAnyVisible(viewer, parent, elements);
    }

    Object[] filtered = (Object[]) _cache.get(parent);
    if (filtered != null) {
      return filtered.length > 0;
    }
    Boolean foundAny = (Boolean) _foundAnyCache.get(parent);
    if (foundAny == null) {
      foundAny = computeAnyVisible(viewer, parent, elements) ? Boolean.TRUE : Boolean.FALSE;
      _foundAnyCache.put(parent, foundAny);
    }
    return foundAny.booleanValue();
  }

  /**
   * Returns true if any of the elements makes it through the filter.
   * @param viewer
   * @param parentElement
   * @param elements
   * @return
   */
  private boolean computeAnyVisible(Viewer viewer, Object parentElement, Object[] elements) {
    boolean elementFound = false;
    for (int i = 0; i < elements.length && !elementFound; i++) {
      Object element = elements[i];
      elementFound = isElementVisible(viewer, parentElement, element);
    }
    return elementFound;
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    return isElementVisible(viewer, parentElement, element);
  }

  /**
   * Sets whether a leading wildcard should be attached to each pattern string.
   * @param includeLeadingWildcard Whether a leading wildcard should be added.
   */
  public final void setIncludeLeadingWildcard(final boolean includeLeadingWildcard) {
    _includeLeadingWildcard = includeLeadingWildcard;
  }

  /**
   * The pattern string for which this filter should select elements in the viewer.
   * @param patternString
   */
  public void setPattern(String patternString) {
    // these 2 strings allow the PatternFilter to be extended in
    // 3.3 - https://bugs.eclipse.org/bugs/show_bug.cgi?id=186404
    if ("org.eclipse.ui.keys.optimization.true".equals(patternString)) { //$NON-NLS-1$
      _useEarlyReturnIfMatcherIsNull = true;
      return;
    } else if ("org.eclipse.ui.keys.optimization.false".equals(patternString)) { //$NON-NLS-1$
      _useEarlyReturnIfMatcherIsNull = false;
      return;
    }
    clearCaches();
    if (patternString == null || patternString.equals("")) { //$NON-NLS-1$
      _matcher = null;
    } else {
      String pattern = patternString + "*"; //$NON-NLS-1$
      if (_includeLeadingWildcard) {
        pattern = "*" + pattern; //$NON-NLS-1$
      }
      _matcher = stringMatcherFactory.createStringMatcher(pattern);
    }
  }

  /**
   * Clears the caches used for optimizing this filter. Needs to be called whenever the tree content changes.
   */
  public void clearCaches() {
    _cache.clear();
    _foundAnyCache.clear();
  }

  /**
   * Answers whether the given String matches the pattern.
   * @param string the String to test
   * @return whether the string matches the pattern
   */
  private boolean match(String string) {
    if (_matcher == null) {
      return true;
    }
    return _matcher.match(string);
  }

  /**
   * Answers whether the given element is a valid selection in the filtered tree. For example, if a tree has items that are categorized, the category itself may
   * not be a valid selection since it is used merely to organize the elements.
   * @param element
   * @return true if this element is eligible for automatic selection
   */
  public boolean isElementSelectable(Object element) {
    return element != null;
  }

  /**
   * Answers whether the given element in the given viewer matches the filter pattern. This is a default implementation that will show a leaf element in the
   * tree based on whether the provided filter text matches the text of the given element's text, or that of it's children (if the element has any). Subclasses
   * may override this method.
   * @param viewer the tree viewer in which the element resides
   * @param parentElement the parent element.
   * @param element the element in the tree to check for a match
   * @return true if the element matches the filter pattern
   */
  public boolean isElementVisible(Viewer viewer, Object parentElement, Object element) {
    return isParentMatch(viewer, parentElement, element) || isLeafMatch(viewer, parentElement, element);
  }

  /**
   * Check if the parent (category) is a match to the filter text. The default behavior returns true if the element has at least one child element that is a
   * match with the filter text. Subclasses may override this method.
   * @param viewer the viewer that contains the element
   * @param parentElement the parent element
   * @param element the tree element to check
   * @return true if the given element has children that matches the filter text
   */
  protected boolean isParentMatch(Viewer viewer, Object parentElement, Object element) {
    Object[] children = ((ITreeContentProvider) ((AbstractTreeViewer) viewer).getContentProvider()).getChildren(element);

    if ((children != null) && (children.length > 0)) {
      return isAnyVisible(viewer, element, children);
    }
    return false;
  }

  /**
   * Check if the current (leaf) element is a match with the filter text. The default behavior checks that the label of the element is a match. Subclasses
   * should override this method.
   * @param viewer the viewer that contains the element
   * @param element the tree element to check
   * @param parentElement the parent element
   * @return true if the given element's label matches the filter text
   */
  protected boolean isLeafMatch(Viewer viewer, Object parentElement, Object element) {
    String labelText = ((ILabelProvider) ((StructuredViewer) viewer).getLabelProvider()).getText(element);
    if (labelText == null) {
      return false;
    }
    return wordMatches(labelText);
  }

  /**
   * Is given leaf element as {@link EObject} instance is already filtered by other filters (a viewer can have multiple {@link ViewerFilter} ?
   * @param viewer
   * @param parent
   * @param element
   * @return <code>true</code> means the leaf is filtered out at least by another filter.
   */
  protected boolean isLeafAlreadyFilteredOutByOtherFilters(StructuredViewer viewer, Object parent, Object element) {
    // Check if this leaf, which matches the regular expression, is not filtered out by another filter (the viewer can have multiple filters).
    ViewerFilter[] filters = viewer.getFilters();
    for (ViewerFilter filter : filters) {
      // Don't check against this pattern filter its self.
      if (filter != this) {
        // Is given leaf element filtered (i.e excluded) ?
        if (!filter.select(viewer, parent, element)) {
          // Given leaf is filtered out by another filter, remove it from displayed elements.
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Take the given filter text and break it down into words using a BreakIterator.
   * @param text
   * @return an array of words
   */
  private String[] getWords(String text) {
    List words = new ArrayList();
    // Break the text up into words, separating based on whitespace and
    // common punctuation.
    // Previously used String.split(..., "\\W"), where "\W" is a regular
    // expression (see the Javadoc for class Pattern).
    // Need to avoid both String.split and regular expressions, in order to
    // compile against JCL Foundation.
    // Also need to do this in an NL-sensitive way. The use of BreakIterator
    BreakIterator iter = BreakIterator.getWordInstance();
    iter.setText(text);
    int i = iter.first();
    while (i != java.text.BreakIterator.DONE && i < text.length()) {
      int j = iter.following(i);
      if (j == java.text.BreakIterator.DONE) {
        j = text.length();
      }
      // match the word
      if (Character.isLetterOrDigit(text.charAt(i))) {
        String word = text.substring(i, j);
        words.add(word);
      }
      i = j;
    }
    return (String[]) words.toArray(new String[words.size()]);
  }

  /**
   * Return whether or not if any of the words in text satisfy the match criteria.
   * @param text the text to match
   * @return boolean <code>true</code> if one of the words in text satisfies the match criteria.
   */
  protected boolean wordMatches(String text) {
    if (text == null) {
      return false;
    }

    // If the whole text matches we are all set
    if (match(text)) {
      return true;
    }

    // Otherwise check if any of the words of the text matches
    String[] words = getWords(text);
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (match(word)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Can be called by the filtered tree to turn on caching.
   * @param useCache The _useCache to set.
   */
  public void setUseCache(boolean useCache) {
    this._useCache = useCache;
  }
  
  public void setStringMatcherFactory(StringMatcherFactory factory){
    this.stringMatcherFactory = factory;
  }
  
}
