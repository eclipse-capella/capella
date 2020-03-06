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

public class LA_PDTitleBlockTestCase extends PDTitleBlockTestCase {
  private static final String DATA_ID = "7da31f5f-5ce1-454e-916a-e8593af47236";
  private static final String DATA2_ID = "402bc79a-3d0f-4361-8deb-b0e2237bfed5";
  private static final String DATA3_ID = "fa4dd744-ef0f-451a-a713-5f79f344812b";
  private static final String DIAGRAM_NAME = "[PD] Interfaces Logical";
  private static final String INTERFACES_PKG = "c52f8d3a-183d-49fa-93f4-4f19a41faff3";

  @Override
  protected void initData() {
    elementsId.add(DATA_ID);
    elementsId.add(DATA2_ID);
    elementsId.add(DATA3_ID);
    diagramName = DIAGRAM_NAME;
    interfacesPKG = INTERFACES_PKG;
  }

}
