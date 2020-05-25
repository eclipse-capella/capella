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

public class OA_IDTitleBlockTestCase extends IDTitleBlockTestCase {
  private static final String INTERFACE_ID = "6c4f78e6-9b71-4acf-ab87-5d7e8e57660e";
  private static final String DIAGRAM_NAME = "[ID] Interface Operational";

  @Override
  protected void initData() {
    elementsId.add(INTERFACE_ID);
    diagramName = DIAGRAM_NAME;
    interfaceID = INTERFACE_ID;
  }

}
