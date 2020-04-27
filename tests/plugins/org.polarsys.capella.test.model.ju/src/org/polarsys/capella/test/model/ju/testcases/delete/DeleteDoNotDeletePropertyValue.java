/*******************************************************************************
* Copyright (c) 2020 THALES GLOBAL SERVICES.
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

public class DeleteDoNotDeletePropertyValue extends DeleteTest {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mustExist(OA__DATA__CLASS_1);
    mustExist(MISCMODEL__PROPERTY_VALUE_1);
  }
  
  @Override
  public void test() {
    removeElement(OA__DATA__CLASS_1);
    mustBeRemoved(OA__DATA__CLASS_1);
    mustExist(MISCMODEL__PROPERTY_VALUE_1);
  }

}
