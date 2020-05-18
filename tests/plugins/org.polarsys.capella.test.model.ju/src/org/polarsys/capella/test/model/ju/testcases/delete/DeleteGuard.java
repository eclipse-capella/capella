/*******************************************************************************
* Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcases.delete;

public class DeleteGuard extends DeleteTest {

  @Override
  public void test() {
    removeElement(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION__CONSTRAINT);
    mustExist(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION, "The containing State Transition must not be deleted");
  }

}
