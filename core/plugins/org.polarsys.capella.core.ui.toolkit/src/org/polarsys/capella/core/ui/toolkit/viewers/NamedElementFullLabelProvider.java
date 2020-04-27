/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.viewers;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;

public class NamedElementFullLabelProvider extends MDEAdapterFactoryLabelProvider {

  @Override
  public String getText(Object element) {
    if (element instanceof AbstractNamedElement) {
      String path = ((AbstractNamedElement) element).getFullLabel();
      if (path.startsWith("/")) { //$NON-NLS-1$
        path = path.substring(1);
      }
      return path.replaceAll("/", "::"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return super.getText(element);
  }
}
