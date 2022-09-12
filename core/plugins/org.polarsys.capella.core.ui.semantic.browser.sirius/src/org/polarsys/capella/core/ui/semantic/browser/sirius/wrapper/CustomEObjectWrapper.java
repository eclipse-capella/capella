/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.sirius.wrapper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Util;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;

public class CustomEObjectWrapper extends EObjectWrapper {
  public CustomEObjectWrapper(EObject element) {
    super(element);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof CustomEObjectWrapper) {
      return Util.equals(this.getElement(), ((BrowserElementWrapper) object).getElement());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}