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
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalActivity;

public class ValidFECategoryMenuTargetTester extends AbstractValidCategoryMenuTargetTester {

  public ValidFECategoryMenuTargetTester() {
  }

  @Override
  protected boolean isValidContext(EObject context) {
    if (context instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) context;
      ActivityNode sourceNode = fe.getSource();
      return !(sourceNode instanceof OperationalActivity);
    }
    return false;
  }

  @Override
  protected Class<? extends EObject> getTargetClass() {
    return FunctionalExchange.class;
  }

  @Override
  protected String getPropertyId() {
    return "isValidFECategoryMenuTarget";
  }
}
