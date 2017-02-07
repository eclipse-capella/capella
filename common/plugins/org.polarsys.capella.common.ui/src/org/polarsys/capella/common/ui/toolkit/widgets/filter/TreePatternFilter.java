/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

/**
 * Pattern filter used in Tree displaying Capella concepts.<br>
 * This pattern filter automatically selects children of a parent that matches the filter even if children labels don't match.
 */
public class TreePatternFilter extends PatternFilter {
  /**
   * Flag used to ignore matching result when set.<br>
   * When a parent matches the filter, this flag is set to <code>true</code> to automatically select its children.
   */
  private boolean _ignoreMatching;
  /**
   * Set of parent that match the filter either a parent has a label text that matches the filter or it is contained by a parent that matches the filter.
   */
  private Set<Object> _matchingParents;

  /**
   * Constructor.
   */
  public TreePatternFilter() {
    super();
    setUseCache(true);
    _matchingParents = new HashSet<Object>(0);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    boolean result = false;
    Object parent = parentElement;
    // Get the real object from the TreePath.
    if (parentElement instanceof TreePath) {
      parent = ((TreePath) parentElement).getLastSegment();
    }
    // If element is contained by a parent that matches, let's select it.
    if (_matchingParents.contains(parent)) {
      result = true;
      // Add it as a matching parent even if we don't know if it has children...
      _matchingParents.add(element);
    } else {
      result = isElementVisible(viewer, parent, element);
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#clearCaches()
   */
  @Override
  public void clearCaches() {
    super.clearCaches();
    _matchingParents.clear();
  }

  /**
   * Overridden to improve performances.
   * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#isLeafMatch(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  protected boolean isLeafMatch(Viewer viewer, Object parentElement, Object element) {
    // Precondition.
    if (_ignoreMatching) {
      // Automatically select the element as the flag is set.
      return true;
    }
    return doIsLeafMatch(viewer, parentElement, element);
  }

  /**
   * @param viewer
   * @param parentElement
   * @param element
   * @return
   */
  protected boolean doIsLeafMatch(Viewer viewer, Object parentElement, Object element) {
    boolean result = false;
    // Get the label in a straight forward manner in case of EObject elements.
    if (element instanceof EObject) {
      String textToMatch = getTextFromModelElement((EObject) element);
      if (null != textToMatch) {
        result = wordMatches(textToMatch);
      }
    } else {
      result = super.isLeafMatch(viewer, parentElement, element);
    }
    if (result) {
      // Get the parent from the content provider, parent and children in the tree may not be based on model containment hierarchy.
      // Is current leaf, that matches the pattern filter, filtered out by other filters ?
      result = !isLeafAlreadyFilteredOutByOtherFilters((StructuredViewer) viewer, parentElement, element);
    }
    return result;
  }

  /**
   * Get text from specified model element.<br>
   * Default implementation gets the label according underlying {@link LabelProvider}.
   * @param element
   * @return
   */
  protected String getTextFromModelElement(EObject element) {
    return EObjectLabelProviderHelper.getText(element);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#isElementVisible(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public boolean isElementVisible(Viewer viewer, Object parentElement, Object element) {
    boolean leafMatch = isLeafMatch(viewer, parentElement, element);
    // Flag used to know if ignoreMatching is set within this call.
    boolean ignoreMatchingEnabled = false;
    if (leafMatch /* && !_ignoreMatching */) {
      // Element label matches the filter, set the flag and add it as a parent matching.
      _ignoreMatching = true;
      ignoreMatchingEnabled = true;
      _matchingParents.add(element);
    }
    boolean parentMatch = isParentMatch(viewer, parentElement, element);
    if (ignoreMatchingEnabled) {
      // Children have been processed, reset the flag only if it was set within this call.
      _ignoreMatching = false;
    }
    return leafMatch || parentMatch;
  }
}
