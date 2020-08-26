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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DragAndDropComponents extends AbstractDiagramTestCase {

  private static final String MODEL_NAME = "Component-DragAndDrop";

  private static final String IDB_LOGICAL_COMPONENT_PKG_1 = "[IDB] LogicalComponentPkg 1";

  public static final String LC_SOURCE_1 = "0ff37bd3-3ae4-4311-b0a5-51aef186b0c5"; //$NON-NLS-1$
  public static final String LA_SOURCE_2 = "35617944-7f57-4b56-b6f8-d406d03f0d52"; //$NON-NLS-1$
  public static final String LA_SOURCE_3 = "a644d273-3aa2-4216-8fbd-52994fa85979"; //$NON-NLS-1$
  public static final String LC_SOURCE_4 = "85bedd8b-a17d-45ca-bb85-8bbde470cf12"; //$NON-NLS-1$

  public static final String LC_VALID_TARGET_1 = "423900d9-9b43-43db-90a5-451776669f26"; //$NON-NLS-1$
  public static final String LA_VALID_TARGET_2 = "602ae9af-5f25-4c86-9f6a-01cd7e39a263"; //$NON-NLS-1$
  public static final String LC_VALID_TARGET_3 = "2578b466-789f-451f-8244-b8445a899316"; //$NON-NLS-1$
  public static final String LA_VALID_TARGET_4 = "e4aead68-344c-4248-8cb8-77f79fdac62d"; //$NON-NLS-1$

  private SessionContext context;

  @Override
  protected String getRequiredTestModel() {
    return MODEL_NAME;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
  }

  @Override
  public void test() throws Exception {
    IDBDiagram diagram = IDBDiagram.openDiagram(context, IDB_LOGICAL_COMPONENT_PKG_1);

    diagram.dragAndDropShouldSucceed(LC_SOURCE_1, IDB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LC_SOURCE_1, LC_VALID_TARGET_1);

    diagram.dragAndDropShouldSucceed(LA_SOURCE_2, IDB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LA_SOURCE_2, LA_VALID_TARGET_2);

    diagram.dragAndDropShouldSucceed(LA_SOURCE_3, IDB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LA_SOURCE_3, LC_VALID_TARGET_3);

    diagram.dragAndDropShouldSucceed(LC_SOURCE_4, IDB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LC_SOURCE_4, LA_VALID_TARGET_4);

    diagram.close();

  }

}
