/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class CDBCommunication extends AbstractDiagramTestCase {

  public static String SA__DATAPKG = "fdcf745f-cad8-49d5-b65f-6591dafd95fb";
  public static String SA__DATAPKG__CLASS1 = "8b8cd52d-8605-4013-9077-219d2949a035";
  public static String SA__DATAPKG__CLASS2 = "7c6c5883-5f15-4890-a693-bb5818adce81";

  @Override
  public String getRequiredTestModel() {
    return CDBCommunication.class.getSimpleName();
  }
}
