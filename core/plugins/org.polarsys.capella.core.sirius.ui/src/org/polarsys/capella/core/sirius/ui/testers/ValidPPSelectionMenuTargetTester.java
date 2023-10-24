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
package org.polarsys.capella.core.sirius.ui.testers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.PhysicalPath;

public class ValidPPSelectionMenuTargetTester extends AbstractValidSelectionMenuTarget {

  protected String PROPERTY_ID = "isValidPPSelectionMenu";

  @Override
  protected boolean isValid(EObject element) {
    return element instanceof PhysicalPath;
  }

  @Override
  protected String getPropertyId() {
    return PROPERTY_ID;
  }
}
