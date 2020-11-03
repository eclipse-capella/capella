/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper;

import org.eclipse.jface.util.Util;

/**
 * Each object of the treeview shall be unique. The wrapper will give an object a unique address. By this way, different categories could not own the same
 * object : parent of a wrapper instance is unique & filters could be applied also.
 */
public abstract class BrowserElementWrapper {
  /**
   * Element wrapped. Could be a ICategory or ModelElement.
   */
  private Object element;

  /**
   * Constructor.
   */
  public BrowserElementWrapper(Object element) {
    this.element = element;
  }

  /**
   * Get underlying element.
   * @return the element
   */
  public Object getElement() {
    return element;
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof BrowserElementWrapper) {
      return Util.equals(this.element, ((BrowserElementWrapper) object).element);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Util.hashCode(element);
  }
}
