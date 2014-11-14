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
package org.polarsys.capella.core.ui.toolkit.viewers;

import org.eclipse.jface.viewers.ILabelProvider;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;


/**
 * The named element label provider.
 */
public class NamedElementFullLabelProvider extends CapellaElementLabelProvider {

  /**
   * Constructs the named element label provider.
   */
  public NamedElementFullLabelProvider() {
    // Do nothing.
  }

  /**
   * @see ILabelProvider#getText(Object)
   */
  @Override
  public String getText(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      String path = ((AbstractNamedElement) element_p).getFullLabel();
      if (path.startsWith("/")) { //$NON-NLS-1$
        path = path.substring(1);
      }
      return path.replaceAll("/", "::"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return super.getText(element_p);
  }
}
