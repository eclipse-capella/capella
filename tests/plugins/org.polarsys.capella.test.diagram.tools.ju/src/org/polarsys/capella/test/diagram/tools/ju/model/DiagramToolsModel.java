/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

public abstract class DiagramToolsModel extends AbstractDiagramTestCase {

  public static final String OA__ROOT_OA_ID = "2fb085ee-b7cb-41c0-b26b-8cfac4374e29";
  public static final String OA__OPERATIONAL_ACTIVITIES_PACKAGE = "0e1669cd-75fa-4da3-9eed-68f8c6f86dcf";
  
  public static final String ORB__ROOT_OPERATIONAL_CONTEXT_NAME = "Operational Roles Blank Test Diagram";
  
  @Override
  public String getRequiredTestModel() {
    return DiagramToolsModel.class.getSimpleName();
  }
}
