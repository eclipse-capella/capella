/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - new class (see https://bugs.polarsys.org/show_bug.cgi?id=2614)
 *******************************************************************************/
package org.polarsys.capella.core.common.ui.wizards;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

public class IsLogicalComponentNotHuman extends PropertyTester {

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    EObject business = CapellaAdapterHelper.resolveBusinessObject(receiver);
    return (business instanceof LogicalComponent) && !((LogicalComponent) business).isHuman();
  }
}
