/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.re.ui.menu.RecDynamicMenu;

public class ValidRecRelatedElementMenuTargetTester extends PropertyTester {

  public static final String PROPERTY_IS_VALID_MENU_TARGET = "hasRelatedRecElements"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    if (receiver instanceof IStructuredSelection) {
      IStructuredSelection selection = (IStructuredSelection) receiver;
      if (PROPERTY_IS_VALID_MENU_TARGET.equals(property)) {
        return RecDynamicMenu.hasCommonRecs(selection);
      }
    } else {
      throw new IllegalArgumentException("Receiver must be a structured selection");//$NON-NLS-1$
    }

    throw new IllegalArgumentException("Unknown property: " + property); //$NON-NLS-1$
  }


}
