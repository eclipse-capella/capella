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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;

public class SearchForLabelProvider extends LabelProvider {
  @Override
  public String getText(Object element) {
    if (element instanceof SearchForItem) {
      return ((SearchForItem) element).getText();
    }
    return super.getText(element);
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof SearchForItem) {
      return ((SearchForItem) element).getImage();
    }
    return super.getImage(element);
  }
}
