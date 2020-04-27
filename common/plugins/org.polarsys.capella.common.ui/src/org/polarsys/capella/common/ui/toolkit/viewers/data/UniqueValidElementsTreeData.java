/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashSet;
import java.util.List;

/**
 * {@link TreeData} implementation that supports element swap and does not accept duplicated elements
 */
public class UniqueValidElementsTreeData extends MultipleValidElementsTreeData {
  /**
   * Constructor.
   * @param displayedElements
   * @param context
   */
  public UniqueValidElementsTreeData(List<? extends Object> displayedElements, Object context) {
    super(displayedElements, context);
  }


  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData#createChildrenCollection()
   */
  @Override
  protected Collection<Object> createChildrenCollection() {
    return new LinkedHashSet<>(1);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#initializeValidElementCollection(java.util.List)
   */
  @Override
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements) {
    return new LinkedHashSet<>(displayedElements);
  }
  
  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.IMoveableData#swap(java.lang.Object, int, int)
   */
  public void swap(Object child, int index, int newIndex) {
    LinkedHashSet<Object> children = (LinkedHashSet<Object>) _childrenForRootElements.get(getParent(child));
    swapLinkedHashSet(children, index, newIndex);
  }

  /**
   * Swap 2 elements in a linked hash set by leveraging the swap function for lists
   * @param children
   * @param index
   * @param newIndex
   */
  private void swapLinkedHashSet(LinkedHashSet<Object> children, int index, int newIndex) {
    ArrayList<Object> list = new ArrayList<>(children);
    Collections.swap(list, index, newIndex);
    children.removeAll(list);
    for (Object obj : list)
      children.add(obj);
  }
}
