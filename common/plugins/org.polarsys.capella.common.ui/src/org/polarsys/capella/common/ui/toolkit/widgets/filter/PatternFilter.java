/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
  public final Object[] filter(Viewer viewer_p, Object parent_p, Object[] elements_p) {
    // we don't want to optimize if we've extended the filter ... this
    // needs to be addressed in 3.4
    // https://bugs.eclipse.org/bugs/show_bug.cgi?id=186404
    if (_matcher == null && _useEarlyReturnIfMatcherIsNull) {
      return elements_p;
    }

    if (!_useCache) {
      return super.filter(viewer_p, parent_p, elements_p);
    }

    Object[] filtered = (Object[]) _cache.get(parent_p);
    if (filtered == null) {
      Boolean foundAny = (Boolean) _foundAnyCache.get(parent_p);
      if (foundAny != null && !foundAny.booleanValue()) {
        filtered = EMPTY;
      } else {
        filtered = super.filter(viewer_p, parent_p, elements_p);
      }
      _cache.put(parent_p, filtered);
    }
    return filtered;
  }

  /**
   * Returns true if any of the elements makes it through the filter. This method uses caching if enabled; the computation is done in computeAnyVisible.
   * @param viewer_p
   * @param parent_p
   * @param elements_p the elements (must not be an empty array)
   * @return true if any of the elements makes it through the filter.
   */
  protected boolean isAnyVisible(Viewer viewer_p, Object parent_p, Object[] elements_p) {
    if (_matcher == null) {
      return true;
    }

    if (!_useCache) {
      return computeAnyVisible(viewer_p, parent_p, elements_p);
    }

    Object[] filtered = (Object[]) _cache.get(parent_p);
    if (filtered != null) {
      return filtered.length > 0;
    }
    Boolean foundAny = (Boolean) _foundAnyCache.get(parent_p);
    if (foundAny == null) {
      foundAny = computeAnyVisible(viewer_p, parent_p, elements_p) ? Boolean.TRUE : Boolean.FALSE;
      _foundAnyCache.put(parent_p, foundAny);
    }
    return foundAny.booleanValue();
  }

  /**
   * Returns true if any of the elements makes it through the filter.
   * @param viewer_p
   * @param parentElement_p
   * @param elements_p
   * @return
   */
  private boolean computeAnyVisible(Viewer viewer_p, Object parentElement_p, Object[] elements_p) {
    boolean elementFound = false;
    for (int i = 0; i < elements_p.length && !elementFound; i++) {
      Object element = elements_p[i];
      elementFound = isElementVisible(viewer_p, parentElement_p, element);
    }
    return elementFound;
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    return isElementVisible(viewer_p, parentElement_p, element_p);
  }

  /**
   * Sets whether a leading wildcard should be attached to each pattern string.
   * @param includeLeadingWildcard_p Whether a leading wildcard should be added.
   */
  public final void setIncludeLeadingWildcard(final boolean includeLeadingWildcard_p) {
    _includeLeadingWildcard = includeLeadingWildcard_p;
  }

  /**
   * The pattern string for which this filter should select elements in the viewer.
   * @param patternString_p
   */
  public void setPattern(String patternString_p) {
    // these 2 strings allow the PatternFilter to be extended in
    // 3.3 - https://bugs.eclipse.org/bugs/show_bug.cgi?id=186404
    if ("org.eclipse.ui.keys.optimization.true".equals(patternString_p)) { //$NON-NLS-1$
      _useEarlyReturnIfMatcherIsNull = true;
      return;
    } else if ("org.eclipse.ui.keys.optimization.false".equals(patternString_p)) { //$NON-NLS-1$
      _useEarlyReturnIfMatcherIsNull = false;
      return;
    }
    clearCaches();
    if (patternString_p == null || patternString_p.equals("")) { //$NON-NLS-1$
      _matcher = null;
    } else {
      String pattern = patternString_p + "*"; //$NON-NLS-1$
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
   * @param string_p the String to test
   * @return whether the string matches the pattern
   */
  private boolean match(String string_p) {
    if (_matcher == null) {
      return true;
    }
    return _matcher.match(string_p);
  }

  /**
   * Answers whether the given element is a valid selection in the filtered tree. For example, if a tree has items that are categorized, the category itself may
   * not be a valid selection since it is used merely to organize the elements.
   * @param element_p
   * @return true if this element is eligible for automatic selection
   */
  public boolean isElementSelectable(Object element_p) {
    return element_p != null;
  }

  /**
   * Answers whether the given element in the given viewer matches the filter pattern. This is a default implementation that will show a leaf element in the
   * tree based on whether the provided filter text matches the text of the given element's text, or that of it's children (if the element has any). Subclasses
   * may override this method.
   * @param viewer_p the tree viewer in which the element resides
   * @param parentElement_p the parent element.
   * @param element_p the element in the tree to check for a match
   * @return true if the element matches the filter pattern
   */
  public boolean isElementVisible(Viewer viewer_p, Object parentElement_p, Object element_p) {
    return isParentMatch(viewer_p, parentElement_p, element_p) || isLeafMatch(viewer_p, parentElement_p, element_p);
  }

  /**
   * Check if the parent (category) is a match to the filter text. The default behavior returns true if the element has at least one child element that is a
   * match with the filter text. Subclasses may override this method.
   * @param viewer_p the viewer that contains the element
   * @param parentElement_p the parent element
   * @param element_p the tree element to check
   * @return true if the given element has children that matches the filter text
   */
  protected boolean isParentMatch(Viewer viewer_p, Object parentElement_p, Object element_p) {
    Object[] children = ((ITreeContentProvider) ((AbstractTreeViewer) viewer_p).getContentProvider()).getChildren(element_p);

    if ((children != null) && (children.length > 0)) {
      return isAnyVisible(viewer_p, element_p, children);
    }
    return false;
  }

  /**
   * Check if the current (leaf) element is a match with the filter text. The default behavior checks that the label of the element is a match. Subclasses
   * should override this method.
   * @param viewer_p the viewer that contains the element
   * @param element_p the tree element to check
   * @param parentElement_p the parent element
   * @return true if the given element's label matches the filter text
   */
  protected boolean isLeafMatch(Viewer viewer_p, Object parentElement_p, Object element_p) {
    String labelText = ((ILabelProvider) ((StructuredViewer) viewer_p).getLabelProvider()).getText(element_p);
    if (labelText == null) {
      return false;
    }
    return wordMatches(labelText);
  }

  /**
   * Is given leaf element as {@link EObject} instance is already filtered by other filters (a viewer can have multiple {@link ViewerFilter} ?
   * @param viewer_p
   * @param parent_p
   * @param element_p
   * @return <code>true</code> means the leaf is filtered out at least by another filter.
   */
  protected boolean isLeafAlreadyFilteredOutByOtherFilters(StructuredViewer viewer_p, Object parent_p, Object element_p) {
    // Check if this leaf, which matches the regular expression, is not filtered out by another filter (the viewer can have multiple filters).
    ViewerFilter[] filters = viewer_p.getFilters();
    for (ViewerFilter filter : filters) {
      // Don't check against this pattern filter its self.
      if (filter != this) {
        // Is given leaf element filtered (i.e excluded) ?
        if (!filter.select(viewer_p, parent_p, element_p)) {
          // Given leaf is filtered out by another filter, remove it from displayed elements.
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Take the given filter text and break it down into words using a BreakIterator.
   * @param text_p
   * @return an array of words
   */
  private String[] getWords(String text_p) {
    List words = new ArrayList();
    // Break the text up into words, separating based on whitespace and
    // common punctuation.
    // Previously used String.split(..., "\\W"), where "\W" is a regular
    // expression (see the Javadoc for class Pattern).
    // Need to avoid both String.split and regular expressions, in order to
    // compile against JCL Foundation.
    // Also need to do this in an NL-sensitive way. The use of BreakIterator
    BreakIterator iter = BreakIterator.getWordInstance();
    iter.setText(text_p);
    int i = iter.first();
    while (i != java.text.BreakIterator.DONE && i < text_p.length()) {
      int j = iter.following(i);
      if (j == java.text.BreakIterator.DONE) {
        j = text_p.length();
      }
      // match the word
      if (Character.isLetterOrDigit(text_p.charAt(i))) {
        String word = text_p.substring(i, j);
        words.add(word);
      }
      i = j;
    }
    return (String[]) words.toArray(new String[words.size()]);
  }

  /**
   * Return whether or not if any of the words in text satisfy the match criteria.
   * @param text_p the text to match
   * @return boolean <code>true</code> if one of the words in text satisfies the match criteria.
   */
  protected boolean wordMatches(String text_p) {
    if (text_p == null) {
      return false;
    }

    // If the whole text matches we are all set
    if (match(text_p)) {
      return true;
    }

    // Otherwise check if any of the words of the text matches
    String[] words = getWords(text_p);
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
   * @param useCache_p The _useCache to set.
   */
  public void setUseCache(boolean useCache_p) {
    this._useCache = useCache_p;
  }
  
  public void setStringMatcherFactory(StringMatcherFactory factory){
    this.stringMatcherFactory = factory;
  }
  
}
