/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.Collection;

/**
 * List data structure.
 */
public class ListData extends AbstractData {
  /**
   * Constructor.
   * @param displayedElements
   * @param context optional parameter.
   */
  public ListData(Collection<?> displayedElements, Object context) {
    super(displayedElements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object element) {
    return NO_CHILD;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element) {
    // Not needed
    return null;
  }
}
