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

import org.eclipse.emf.ecore.EObject;

/**
 * Wrapper for a ModelElement.
 */
public class EObjectWrapper extends BrowserElementWrapper {
  /**
   * Constructor.
   * 
   * @param element
   */
  public EObjectWrapper(EObject element) {
    super(element);
  }

  @Override
  public EObject getElement() {
    return (EObject) super.getElement();
  }
}
