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

public class SA_CDBTitleBlockTestCase extends CDBTitleBlockTestCase {

  private static final String CLASS_ID = "8feafdba-d299-4195-9d47-ee216dc52cdd";
  private static final String DIAGRAM_NAME = "[CDB] Data TestCase1";

  @Override
  public void initData() {
    elementId = CLASS_ID;
    diagramName = DIAGRAM_NAME;
  }
}
