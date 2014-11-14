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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;

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
  public boolean select(Viewer viewer, Object parentElement_p, Object element_p) {
    boolean result = false;
    Object parent = parentElement_p;
    // Get the real object from the TreePath.
    if (parentElement_p instanceof TreePath) {
      parent = ((TreePath) parentElement_p).getLastSegment();
    }
    // If element is contained by a parent that matches, let's select it.
    if (_matchingParents.contains(parent)) {
      result = true;
      // Add it as a matching parent even if we don't know if it has children...
      _matchingParents.add(element_p);
    } else {
      result = isElementVisible(viewer, parent, element_p);
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
  protected boolean isLeafMatch(Viewer viewer_p, Object parentElement_p, Object element_p) {
    // Precondition.
    if (_ignoreMatching) {
      // Automatically select the element as the flag is set.
      return true;
    }
    return doIsLeafMatch(viewer_p, parentElement_p, element_p);
  }

  /**
   * @param viewer_p
   * @param parentElement_p
   * @param element_p
   * @return
   */
  protected boolean doIsLeafMatch(Viewer viewer_p, Object parentElement_p, Object element_p) {
    boolean result = false;
    // Get the label in a straight forward manner in case of EObject elements.
    if (element_p instanceof EObject) {
      String textToMatch = getTextFromModelElement((EObject) element_p);
      if (null != textToMatch) {
        result = wordMatches(textToMatch);
      }
    } else {
      result = super.isLeafMatch(viewer_p, parentElement_p, element_p);
    }
    if (result) {
      // Get the parent from the content provider, parent and children in the tree may not be based on model containment hierarchy.
      // Is current leaf, that matches the pattern filter, filtered out by other filters ?
      result = !isLeafAlreadyFilteredOutByOtherFilters((StructuredViewer) viewer_p, parentElement_p, element_p);
    }
    return result;
  }

  /**
   * Get text from specified model element.<br>
   * Default implementation gets the label according underlying {@link LabelProvider}.
   * @param element_p
   * @return
   */
  protected String getTextFromModelElement(EObject element_p) {
    return EObjectLabelProviderHelper.getText(element_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#isElementVisible(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public boolean isElementVisible(Viewer viewer_p, Object parentElement_p, Object element_p) {
    boolean leafMatch = isLeafMatch(viewer_p, parentElement_p, element_p);
    // Flag used to know if ignoreMatching is set within this call.
    boolean ignoreMatchingEnabled = false;
    if (leafMatch /* && !_ignoreMatching */) {
      // Element label matches the filter, set the flag and add it as a parent matching.
      _ignoreMatching = true;
      ignoreMatchingEnabled = true;
      _matchingParents.add(element_p);
    }
    boolean parentMatch = isParentMatch(viewer_p, parentElement_p, element_p);
    if (ignoreMatchingEnabled) {
      // Children have been processed, reset the flag only if it was set within this call.
      _ignoreMatching = false;
    }
    return leafMatch || parentMatch;
  }
}
