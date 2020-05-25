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

public class PA_PDTitleBlockTestCase extends PDTitleBlockTestCase {
  private static final String DATA_ID = "7da31f5f-5ce1-454e-916a-e8593af47236";
  private static final String DATA2_ID = "402bc79a-3d0f-4361-8deb-b0e2237bfed5";
  private static final String DATA3_ID = "fa4dd744-ef0f-451a-a713-5f79f344812b";
  private static final String DATA4_ID = "56938bb5-311a-4f97-b9dc-28674e4b50cd";
  private static final String DIAGRAM_NAME = "[PD] Interfaces Physical";
  private static final String INTERFACES_PKG = "463eeb69-e250-4fac-8ba2-d27cf2b10ed8";

  @Override
  protected void initData() {
    elementsId.add(DATA_ID);
    elementsId.add(DATA2_ID);
    elementsId.add(DATA3_ID);
    elementsId.add(DATA4_ID);
    diagramName = DIAGRAM_NAME;
    interfacesPKG = INTERFACES_PKG;
  }
}
