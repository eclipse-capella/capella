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
package org.polarsys.capella.test.model.ju.testcases.delete;

public class DeleteGuard extends DeleteTest {

  @Override
  public void test() {
    removeElement(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION__CONSTRAINT);
    mustExist(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION, "The containing State Transition must not be deleted");
  }

}
