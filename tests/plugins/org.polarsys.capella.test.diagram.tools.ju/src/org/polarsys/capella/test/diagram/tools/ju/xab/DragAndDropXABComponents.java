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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DragAndDropXABComponents extends AbstractDiagramTestCase {

  private static final String MODEL_NAME = "Component-DragAndDrop";

  public static final String LAB_LOGICAL_COMPONENT_PKG_1 = "[LAB] LogicalComponentPkg 1"; //$NON-NLS-1$

  public static final String LC_SOURCE_1 = "e79f676b-4a6f-4829-8b11-14e45cd88324"; //$NON-NLS-1$
  public static final String LA_SOURCE_2 = "6737ae84-d0de-4234-a22e-6bbf6f75cd49"; //$NON-NLS-1$
  public static final String LA_SOURCE_3 = "9cf31089-a68e-4393-920e-b215e208b037"; //$NON-NLS-1$
  public static final String LC_SOURCE_4 = "3355d680-4bb6-492c-ace6-7dded6c2fbbf"; //$NON-NLS-1$

  public static final String LC_VALID_TARGET_1 = "1a896d2a-5949-4556-abfd-56853728686e"; //$NON-NLS-1$
  public static final String LA_VALID_TARGET_2 = "8e041517-8e0f-4398-907e-e886913b87d6"; //$NON-NLS-1$
  public static final String LC_VALID_TARGET_3 = "5cab633e-e958-4db7-9ca4-5d43964db043"; //$NON-NLS-1$
  public static final String LA_VALID_TARGET_4 = "e539cc8f-873d-411a-8172-67a3fc869642"; //$NON-NLS-1$

  public static final String PAB_PHYSICAL_COMPONENT_PKG_1 = "[PAB] PhysicalComponentPkg 1"; //$NON-NLS-1$

  public static final String PC_SOURCE_1 = "5fa45b16-7a6e-4a97-a457-c5e757cbf8e3"; //$NON-NLS-1$
  public static final String PC_SOURCE_2 = "6ce2235b-b0b9-4552-849a-b3e6c1236794"; //$NON-NLS-1$
  public static final String PC_SOURCE_3 = "470ca2f1-cb4a-453d-b21c-cfa5959079c9"; //$NON-NLS-1$
  public static final String PC_SOURCE_4 = "00fdd8f2-3c69-4c97-b9db-459ce95fc020"; //$NON-NLS-1$
  public static final String PA_SOURCE_5 = "3b0a2e03-85eb-4e6d-9d9a-81fdf81f0b73"; //$NON-NLS-1$
  public static final String PA_SOURCE_6 = "31a43447-30c5-4f11-ae0e-e2bbc3ca8450"; //$NON-NLS-1$
  public static final String PA_SOURCE_7 = "f75c4c54-5ac7-4169-944a-eb9aead1e220"; //$NON-NLS-1$
  public static final String PA_SOURCE_8 = "6840c824-abae-44f7-8869-598dd115cb68"; //$NON-NLS-1$
  public static final String PA_SOURCE_9 = "b542e96a-a062-4c60-bf9d-2ccf9e6be439"; //$NON-NLS-1$

  public static final String PC_VALID_TARGET_1 = "b3c20650-6b1e-47dd-8a1d-7817f5237f9b"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_2 = "07e7715d-6be2-4532-82c9-4c7e5ceb78d8"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_3 = "9a9f6af8-7e58-4966-8169-63e6f96347a4"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_4 = "3b593508-e858-4771-8d5c-f3092474dfd8"; //$NON-NLS-1$
  public static final String PA_VALID_TARGET_5 = "b708ce52-a385-491b-8934-b13d0eb07cbd"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_6 = "ae4e491f-865f-43aa-a008-0d62f45ea1f4"; //$NON-NLS-1$
  public static final String PA_VALID_TARGET_7 = "d4d07b94-37c9-4d7f-a356-2b179ac48f07"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_8 = "7829b76d-500f-489f-8ebd-be7f9d8fb1c5"; //$NON-NLS-1$
  public static final String PC_VALID_TARGET_9 = "7af8d7ff-e8b3-42e6-8522-cb75d0062d42"; //$NON-NLS-1$

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

    testOnLABDiagram();
    testOnPABDiagram();
  }

  public void testOnLABDiagram() {
    XABDiagram diagram = XABDiagram.openDiagram(context, LAB_LOGICAL_COMPONENT_PKG_1, BlockArchitectureExt.Type.LA);

    diagram.dragAndDropShouldSucceed(LC_SOURCE_1, LAB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LC_SOURCE_1, LC_VALID_TARGET_1);

    diagram.dragAndDropShouldSucceed(LA_SOURCE_2, LAB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LA_SOURCE_2, LA_VALID_TARGET_2);

    diagram.dragAndDropShouldSucceed(LA_SOURCE_3, LAB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LA_SOURCE_3, LC_VALID_TARGET_3);

    diagram.dragAndDropShouldSucceed(LC_SOURCE_4, LAB_LOGICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(LC_SOURCE_4, LA_VALID_TARGET_4);

    diagram.close();
  }

  public void testOnPABDiagram() {
    XABDiagram diagram = XABDiagram.openDiagram(context, PAB_PHYSICAL_COMPONENT_PKG_1, BlockArchitectureExt.Type.PA);

    diagram.dragAndDropShouldSucceed(PC_SOURCE_1, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PC_SOURCE_1, PC_VALID_TARGET_1);

    diagram.dragAndDropShouldSucceed(PC_SOURCE_2, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PC_SOURCE_2, PC_VALID_TARGET_2);

    diagram.dragAndDropShouldSucceed(PC_SOURCE_3, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PC_SOURCE_3, PC_VALID_TARGET_3);

    diagram.dragAndDropShouldSucceed(PC_SOURCE_4, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PC_SOURCE_4, PC_VALID_TARGET_4);

    diagram.dragAndDropShouldSucceed(PA_SOURCE_5, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PA_SOURCE_5, PA_VALID_TARGET_5);

    diagram.dragAndDropShouldSucceed(PA_SOURCE_6, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PA_SOURCE_6, PC_VALID_TARGET_6);

    diagram.dragAndDropShouldSucceed(PA_SOURCE_7, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PA_SOURCE_7, PA_VALID_TARGET_7);

    diagram.dragAndDropShouldSucceed(PA_SOURCE_8, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PA_SOURCE_8, PC_VALID_TARGET_8);

    diagram.dragAndDropShouldSucceed(PA_SOURCE_9, PAB_PHYSICAL_COMPONENT_PKG_1);
    diagram.dragAndDropShouldSucceed(PA_SOURCE_9, PC_VALID_TARGET_9);

    diagram.close();
  }

}
