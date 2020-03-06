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

public class LA_IDTitleBlockTestCase extends IDTitleBlockTestCase {
  private static final String INTERFACE_ID = "241e115b-e72d-4eb5-a84f-7273226301d5";
  private static final String DIAGRAM_NAME = "[ID] Interface Logical";

  @Override
  protected void initData() {
    elementsId.add(INTERFACE_ID);
    diagramName = DIAGRAM_NAME;
    interfaceID = INTERFACE_ID;
  }

}
