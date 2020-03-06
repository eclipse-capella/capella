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

public class OA_PDTitleBlockTestCase extends PDTitleBlockTestCase {

  private static final String DATA_ID = "fa4dd744-ef0f-451a-a713-5f79f344812b";
  private static final String DIAGRAM_NAME = "[PD] Interfaces Operational";
  private static final String INTERFACES_PKG = "eca2076d-da93-44f7-89d8-d58c22edbd85";

  @Override
  protected void initData() {
    elementsId.add(DATA_ID);
    diagramName = DIAGRAM_NAME;
    interfacesPKG = INTERFACES_PKG;
  }
}