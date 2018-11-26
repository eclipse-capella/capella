/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;

public class SiriusItemWrapperHelper {
  private SiriusItemWrapperHelper() {

  }

  /**
   * Prepare a new selection with a retrieval of wrapped objects.
   * 
   * @param selection
   * @return a not <code>null</code> collection.
   */
  public static Collection<?> filterItemWrapper(IStructuredSelection selection) {

    List<Object> newSelection = new ArrayList<>();
    Iterator<?> iterator = selection.iterator();

    while (iterator.hasNext()) {
      Object selectedObject = iterator.next();
      if (selectedObject instanceof ItemWrapper) {
        newSelection.add(((ItemWrapper) selectedObject).getWrappedObject());
      } else {
        newSelection.add(selectedObject);
      }
    }
    return newSelection;
  }
}
