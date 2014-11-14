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
package org.polarsys.capella.common.ui.toolkit.viewers.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * {@link TreeData} implementation that supports to have the same object several times as valid elements.
 */
public class MultipleValidElementsTreeData extends TreeData implements IMoveableData {
  /**
   * Constructor.
   * @param displayedElements_p
   * @param context_p
   */
  public MultipleValidElementsTreeData(List<? extends Object> displayedElements_p, Object context_p) {
    super(displayedElements_p, context_p);
  }

  /**
   * Build valid elements.
   * @param validElements_p
   * @param rootElement_p
   */
  private void buildValidElements(List<Object> validElements_p, Object rootElement_p) {
    // Get children of this root element.
    Collection<Object> children = _childrenForRootElements.get(rootElement_p);
    // Loop over children
    for (Object child : children) {
      if (_childrenForRootElements.containsKey(child)) {
        buildValidElements(validElements_p, child);
      } else if (isValid(child)) {
        validElements_p.add(child);
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData#createChildrenCollection()
   */
  @Override
  protected Collection<Object> createChildrenCollection() {
    return new ArrayList<Object>(1);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getValidElements()
   */
  @Override
  public List<Object> getValidElements() {
    // Since we can have many times a same element in a tree data structure,
    // we must rebuild valid elements from tree data that only ensure the right order.
    List<Object> validElements = new ArrayList<Object>(0);
    // Loop over all root objects to collect valid elements to return.
    for (Object rootElement : _rootElements) {
      buildValidElements(validElements, rootElement);
    }
    return validElements;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#initializeValidElementCollection(java.util.List)
   */
  @Override
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements_p) {
    return new ArrayList<Object>(displayedElements_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.IMoveableData#swap(java.lang.Object, int, int)
   */
  public void swap(Object child_p, int index_p, int newIndex_p) {
    // Get children list that contains elements.
    List<Object> children = (List<Object>) _childrenForRootElements.get(getParent(child_p));
    // Swap element.
    Collections.swap(children, index_p, newIndex_p);
  }
}
