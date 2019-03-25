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

package org.polarsys.capella.common.ui.toolkit.viewers.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * {@link ListData} implementation that supports to have the same object several times as valid elements.
 */
public class MultipleValidElementsListData extends ListData implements IMoveableData {
  /**
   * Constructor.
   * @param displayedElements
   * @param context
   */
  public MultipleValidElementsListData(List<? extends Object> displayedElements, Object context) {
    super(displayedElements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#initializeRootElementCollection()
   */
  @Override
  protected Collection<Object> initializeRootElementCollection() {
    return new ArrayList<>(0);
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
    // Swap elements in list.
    Collections.swap((List<?>) rootElements, index, newIndex);
    Collections.swap((List<?>) validElements, index, newIndex);
  }
}
