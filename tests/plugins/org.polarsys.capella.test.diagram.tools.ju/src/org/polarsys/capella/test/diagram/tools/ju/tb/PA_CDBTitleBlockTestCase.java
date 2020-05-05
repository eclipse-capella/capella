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

public class PA_CDBTitleBlockTestCase extends CDBTitleBlockTestCase {

  private static final String CLASS_ID = "1907b8ab-6058-4964-a53d-abbc86a9b6c0";
  private static final String DATA_ID = "b6a3203f-af0a-438c-beea-666327cf46df";
  private static final String BOOLEAN_ID = "3f5abbf2-54b5-487a-b9e5-cd1351389229";
  private static final String DIAGRAM_NAME = "[CDB] Data Physical";

  @Override
  public void initData() {
    elementsId.add(CLASS_ID);
    elementsId.add(DATA_ID);
    elementsId.add(BOOLEAN_ID);
    diagramName = DIAGRAM_NAME;
  }
}
