/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testcase.copyPasteLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.CapellaDiagramPasteCommand;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class CopyPasteLayout extends BasicTestCase {

  public static String MODEL_NAME = "copyPasteLayout"; //$NON-NLS-1$
  public static String LA__LOGICAL_SYSTEM_PART = "fecd48b1-03f8-4321-bdac-1776f2f7f311";  //$NON-NLS-1$
  public static String LA__ROOT_LF__LOGICALFUNCTION_1 = "57405f1a-bc81-43b9-967d-4a4d954da095";  //$NON-NLS-1$ 
  public static String LA__PART_LC_1__LC_1 = "6de0d99a-2b5a-4159-b8ce-87bf061d6a33";  //$NON-NLS-1$ 
  public static String LA__LC_1 = "1a915c3a-1262-4288-be12-ebc4942e7ce1";  //$NON-NLS-1$
  public static String LAB_DIAGRAM = "[LAB] Logical System"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));    
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    
    EObject component = IdManager.getInstance().getEObject(LA__LC_1, scope);
    EObject part = IdManager.getInstance().getEObject(LA__PART_LC_1__LC_1, scope);
    EObject function = IdManager.getInstance().getEObject(LA__ROOT_LF__LOGICALFUNCTION_1, scope);
    final List<EObject> origins = Arrays.asList(component, part, function);
    final EObject logicalSystem = IdManager.getInstance().getEObject(LA__LOGICAL_SYSTEM_PART, scope);
    
    DSemanticDecorator logicalSystemView = diagramContext.getView(LA__LOGICAL_SYSTEM_PART);
    final View logicalSystemViewGMFElement = LayerUtil.getUpGmfElement(logicalSystemView);
    
    ExecutionManager executionManager = TestHelper.getExecutionManager(logicalSystem);
    final List<EObject> pastedElements = new ArrayList<EObject>();
    
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        List<EObject> results = new CapellaDiagramPasteCommand(Arrays.asList(logicalSystemViewGMFElement)){
          @Override
          public List<EObject> pasteCapellaElements(List<EObject> origins, EObject source, EObject target) {
            return super.pasteCapellaElements(origins, source, target);
          }
        }.pasteCapellaElements(origins, logicalSystem, logicalSystem);
        pastedElements.addAll(results);
      }
    });
    
    for (EObject obj : pastedElements)
    {
      if (obj instanceof LogicalFunction)
      {
        assertTrue("The pasted Logical Function should only be allocated to the pasted Logical Component", ((LogicalFunction)obj).getAllocationBlocks().size() == 1);
      }
    }
  }
}
