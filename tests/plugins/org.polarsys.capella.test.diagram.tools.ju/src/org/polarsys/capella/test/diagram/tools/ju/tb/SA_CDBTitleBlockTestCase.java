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
package org.polarsys.capella.test.diagram.tools.ju.tb;

public class SA_CDBTitleBlockTestCase extends CDBTitleBlockTestCase {

  private static final String CLASS_ID = "8feafdba-d299-4195-9d47-ee216dc52cdd";
  private static final String DIAGRAM_NAME = "[CDB] Data TestCase1";
  private static final String DATA_PKG = "7da31f5f-5ce1-454e-916a-e8593af47236";

  @Override
  public void initData() {
    elementsId.add(CLASS_ID);
    diagramName = DIAGRAM_NAME;
    dataPKG = DATA_PKG;
  }
}
