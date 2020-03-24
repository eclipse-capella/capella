/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;

public class AttributeContentProvider extends AbstractSearchForContentProvider {
  private AbstractCapellaSearchForContainerArea leftArea = null;

  public AttributeContentProvider(AbstractCapellaSearchForContainerArea area) {
    this.leftArea = area;
  }

  public Object[] getElements(Object inputElement) {
    Set<Object> checkedMetaClasses = leftArea.getCheckedElements();
    return SearchForItemCache.getInstance().getAttributes(checkedMetaClasses).toArray();
  }

  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof EAttribute)
      return false;
    return getChildren(element).length > 0;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    return Collections.EMPTY_LIST.toArray();
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

}
