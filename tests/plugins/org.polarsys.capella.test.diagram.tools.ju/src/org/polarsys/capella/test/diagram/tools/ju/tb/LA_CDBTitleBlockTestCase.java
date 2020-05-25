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

public class LA_CDBTitleBlockTestCase extends CDBTitleBlockTestCase {

  private static final String CLASS_ID = "330d0716-9466-4a2c-8a06-9b95b1d2d1ef";
  private static final String DATA_ID = "28654216-6820-4e87-8d31-d146b194ba19";
  private static final String BOOLEAN_ID = "06c37c44-d801-4ff0-8f0f-2708df03d97b";
  private static final String DIAGRAM_NAME = "[CDB] Data Logical";
  private static final String DATA_PKG = "402bc79a-3d0f-4361-8deb-b0e2237bfed5";

  @Override
  public void initData() {
    elementsId.add(CLASS_ID);
    elementsId.add(DATA_ID);
    elementsId.add(BOOLEAN_ID);
    diagramName = DIAGRAM_NAME;
    dataPKG = DATA_PKG;
  }
}