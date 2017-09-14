/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class CapellaWorkbenchPropertyTester extends PropertyTester {

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    if (property.equals("activePartId")) { //$NON-NLS-1$
      return testActivePartId(expectedValue.toString());
    }
    throw new IllegalArgumentException("Property is unknown: " + property); //$NON-NLS-1$
  }

  /**
   * Property: 'activePartId'. Test argument against the active part ID. Usually this should be done with platform core expression variables,
   * but not with EMF validation context selectors, which don't supply these variables, only
   * property testers.
   */
  public final boolean testActivePartId(String expected) {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      IWorkbenchPart activePart = window.getPartService().getActivePart();
      return (activePart != null && expected.equals(activePart.getSite().getId()));
    }
    return false;
  }

}
