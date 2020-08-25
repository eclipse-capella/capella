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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.junit.Assert;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DragAndDropOABActors extends AbstractDiagramTestCase {

  private static final String MODEL_NAME = "OAB-DragAndDrop";

  public static final String OAB_OPERATIONAL_ENTITIES_PACKAGE = "[OAB] Operational Entities Package"; //$NON-NLS-1$

  public static final String OA_SOURCE_1 = "61aecda1-b7d2-4e37-a201-a3a48db9128f"; //$NON-NLS-1$
  public static final String OA_SOURCE_2 = "eb6e23fd-ebc1-49b9-90f6-bc3b7da015cf"; //$NON-NLS-1$
  public static final String OA_SOURCE_3 = "fd4263d8-6028-413b-8128-6c0c6cbc8a16"; //$NON-NLS-1$
  public static final String OA_SOURCE_4 = "5d9f58ca-beff-44d8-97db-f89a218a9abf"; //$NON-NLS-1$

  public static final String OE_VALID_TARGET_1 = "763b8cef-c9f1-43f1-b5fa-01705f5d868c"; //$NON-NLS-1$
  public static final String OE_VALID_TARGET_2 = "9ae303c1-dacf-4ff5-bb52-0c9f8f444d5c"; //$NON-NLS-1$
  public static final String OA_INVALID_TARGET_3 = "d4f9cdb7-7f87-4ae4-9b37-83cf27662e01"; //$NON-NLS-1$
  public static final String OA_INVALID_TARGET_4 = "c2f6c623-a5e2-42af-a678-33ab0a4de386"; //$NON-NLS-1$

  public static final String OAB_OPERATIONAL_ENTITIES = "[OAB] Operational Entities"; //$NON-NLS-1$

  public static final String OA_SOURCE_5 = "e1497434-470f-4a65-87b4-2914059f89bf"; //$NON-NLS-1$
  public static final String OA_SOURCE_6 = "1ede86e5-2ff0-4c41-9575-5297e0bcfe5b"; //$NON-NLS-1$
  public static final String OA_SOURCE_7 = "1ec88d5f-a426-4636-adb4-0b99f70a5f01"; //$NON-NLS-1$
  public static final String OA_SOURCE_8 = "39095f56-71f4-43cc-ae08-9e740bda13c9"; //$NON-NLS-1$

  public static final String OE_VALID_TARGET_5 = "56ca8893-569b-4966-9112-ddb5da5be5bf"; //$NON-NLS-1$
  public static final String OE_VALID_TARGET_6 = "eda8661f-5e36-41e7-82e1-d9c57d5950f4"; //$NON-NLS-1$
  public static final String OA_INVALID_TARGET_7 = "29d989c7-1d2c-4fcd-ae97-b451bef991d6"; //$NON-NLS-1$
  public static final String OA_INVALID_TARGET_8 = "ea80860d-1487-4fe9-af2a-c46b628f5467"; //$NON-NLS-1$

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

    testOABEntitiesPackageDiagram();

    testOABEntitiesDiagram();
  }

  public void testOABEntitiesPackageDiagram() {
    XABDiagram diagram = XABDiagram.openDiagram(context, OAB_OPERATIONAL_ENTITIES_PACKAGE,
        BlockArchitectureExt.Type.OA);

    dragAndDropShouldSucceed(diagram, OA_SOURCE_1, OE_VALID_TARGET_1);
    dragAndDropShouldSucceed(diagram, OA_SOURCE_2, OE_VALID_TARGET_2);

    dragAndDropShouldFail(diagram, OA_SOURCE_3, OA_INVALID_TARGET_3);
    dragAndDropShouldFail(diagram, OA_SOURCE_4, OA_INVALID_TARGET_4);

    diagram.close();
  }

  public void testOABEntitiesDiagram() {
    XABDiagram diagram = XABDiagram.openDiagram(context, OAB_OPERATIONAL_ENTITIES, BlockArchitectureExt.Type.OA);

    dragAndDropShouldSucceed(diagram, OA_SOURCE_5, OE_VALID_TARGET_5);
    dragAndDropShouldSucceed(diagram, OA_SOURCE_6, OE_VALID_TARGET_6);

    dragAndDropShouldFail(diagram, OA_SOURCE_7, OA_INVALID_TARGET_7);
    dragAndDropShouldFail(diagram, OA_SOURCE_8, OA_INVALID_TARGET_8);

    diagram.close();
  }

  private static void dragAndDropShouldSucceed(XABDiagram diagram, String sourceId, String targetId) {
    diagram.dragAndDropComponent(sourceId, targetId);
  }

  private static void dragAndDropShouldFail(XABDiagram diagram, String sourceId, String targetId) {
    try {
      diagram.dragAndDropComponent(sourceId, targetId);
      Assert.fail(
          "Drag and drop should have failed for diagram: " + diagram + " source " + sourceId + " target " + targetId);
    } catch (AssertionError error) {
      Assert.assertTrue(error.getMessage().startsWith("Precondition"));
    }
  }

}
