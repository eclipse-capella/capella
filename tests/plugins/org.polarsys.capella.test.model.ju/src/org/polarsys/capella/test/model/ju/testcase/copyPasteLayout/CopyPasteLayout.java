/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcase.copyPasteLayout;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CopyPasteLayout extends AbstractCopyPasteLayout {

  public static String MODEL_NAME = "copyPasteLayout"; //$NON-NLS-1$
  public static String LA__LOGICAL_SYSTEM_PART = "fecd48b1-03f8-4321-bdac-1776f2f7f311";  //$NON-NLS-1$
  public static String LA__ROOT_LF__LOGICALFUNCTION_1 = "57405f1a-bc81-43b9-967d-4a4d954da095";  //$NON-NLS-1$ 
  public static String LA__PART_LC_1__LC_1 = "6de0d99a-2b5a-4159-b8ce-87bf061d6a33";  //$NON-NLS-1$ 
  public static String LA__LC_1 = "1a915c3a-1262-4288-be12-ebc4942e7ce1";  //$NON-NLS-1$
  public static String LAB_DIAGRAM = "[LAB] Logical System"; //$NON-NLS-1$

  @Override
  public List<? extends View> getViewsToCopyFrom() {
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    DSemanticDecorator part = diagramContext.getView(LA__PART_LC_1__LC_1);
    DSemanticDecorator function = diagramContext.getView(LA__ROOT_LF__LOGICALFUNCTION_1);
    View partView = LayerUtil.getUpGmfElement(part);
    View functionView = LayerUtil.getUpGmfElement(function);
    return Arrays.asList(partView, functionView);
  }
  
  @Override
  public List<? extends View> getViewsToPasteTo() {
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    DSemanticDecorator logicalSystemView = diagramContext.getView(LA__LOGICAL_SYSTEM_PART);
    View logicalSystemViewGMFElement = LayerUtil.getUpGmfElement(logicalSystemView);
    return Arrays.asList(logicalSystemViewGMFElement);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    super.test();
    for (EObject siriusElement : pastedSiriusElements) {
      if (siriusElement instanceof DDiagramElement
          && ((DDiagramElement) siriusElement).getTarget() instanceof LogicalFunction) {
        assertTrue("The pasted Logical Function should only be allocated to the pasted Logical Component",
            ((LogicalFunction) ((DDiagramElement) siriusElement).getTarget()).getAllocationBlocks().size() == 1);
      }
    }
  }
}
