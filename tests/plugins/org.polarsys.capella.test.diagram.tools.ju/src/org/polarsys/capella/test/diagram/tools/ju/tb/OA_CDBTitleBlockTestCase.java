/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

public class OA_CDBTitleBlockTestCase extends CDBTitleBlockTestCase {

  private static final String CLASS_ID = "40758252-0c7e-4275-a600-667c3bebe02d";
  private static final String DATA_ID = "cddd30d1-066e-4e21-a4d9-1f751b734dd4";
  private static final String BOOLEAN_ID = "20d7e2fe-625e-4d01-86fd-416b281aa72f";
  private static final String DIAGRAM_NAME = "[CDB] Data Operational";
  private static final String DATA_PKG = "fa4dd744-ef0f-451a-a713-5f79f344812b";

  @Override
  public void initData() {
    elementsId.add(CLASS_ID);
    elementsId.add(DATA_ID);
    elementsId.add(BOOLEAN_ID);
    diagramName = DIAGRAM_NAME;
    dataPKG = DATA_PKG;
  }
}