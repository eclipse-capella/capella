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

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.core.sirius.ui.helper.SelectionHelper;

public class ValidRelatedConnectionsMenuTargetTester extends PropertyTester{

  protected String PROPERTY_ID = "isValidRelatedConnectionsMenu";
  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    if (property.equals(PROPERTY_ID)) {
      if (receiver instanceof IStructuredSelection) {
        IStructuredSelection selection = (IStructuredSelection) receiver;
        return SelectionHelper.eINSTANCE.hasRelatedConnections(selection);
      }
    }
    return false;
  }



}
