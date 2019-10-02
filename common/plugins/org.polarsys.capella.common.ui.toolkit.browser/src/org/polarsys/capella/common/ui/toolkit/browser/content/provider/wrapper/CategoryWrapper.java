/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper;

import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;

/**
 * Wrapper for a ICategory element.
 */
public class CategoryWrapper extends BrowserElementWrapper {
  /**
   * Constructor.
   * 
   * @param element
   */
  public CategoryWrapper(ICategory element) {
    super(element);
  }

  @Override
  public CategoryImpl getElement() {
    return (CategoryImpl) super.getElement();
  }
}
