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

import java.util.Collection;

/**
 * List data structure.
 */
public class ListData extends AbstractData {
  /**
   * Constructor.
   * @param displayedElements_p
   * @param context_p optional parameter.
   */
  public ListData(Collection<? extends Object> displayedElements_p, Object context_p) {
    super(displayedElements_p, context_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object element_p) {
    return NO_CHILD;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element_p) {
    // Not needed
    return null;
  }
}
