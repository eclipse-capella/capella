/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
   * @param displayedElements
   * @param context
   */
  public MultipleValidElementsTreeData(List<? extends Object> displayedElements, Object context) {
    super(displayedElements, context);
  }

  /**
   * Build valid elements.
   * @param validElements
   * @param rootElement
   */
  private void buildValidElements(List<Object> validElements, Object rootElement) {
    // Get children of this root element.
    Collection<Object> children = _childrenForRootElements.get(rootElement);
    // Loop over children
    for (Object child : children) {
      if (_childrenForRootElements.containsKey(child)) {
        buildValidElements(validElements, child);
      } else if (isValid(child)) {
        validElements.add(child);
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData#createChildrenCollection()
   */
  @Override
  protected Collection<Object> createChildrenCollection() {
    return new ArrayList<>(1);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getValidElements()
   */
  @Override
  public List<Object> getValidElements() {
    // Since we can have many times a same element in a tree data structure,
    // we must rebuild valid elements from tree data that only ensure the right order.
    List<Object> validElements = new ArrayList<>(0);
    // Loop over all root objects to collect valid elements to return.
    for (Object rootElement : rootElements) {
      buildValidElements(validElements, rootElement);
    }
    return validElements;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#initializeValidElementCollection(java.util.List)
   */
  @Override
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements) {
    return new ArrayList<>(displayedElements);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.IMoveableData#swap(java.lang.Object, int, int)
   */
  public void swap(Object child, int index, int newIndex) {
    // Get children list that contains elements.
    List<Object> children = (List<Object>) _childrenForRootElements.get(getParent(child));
    // Swap element.
    Collections.swap(children, index, newIndex);
  }
}
