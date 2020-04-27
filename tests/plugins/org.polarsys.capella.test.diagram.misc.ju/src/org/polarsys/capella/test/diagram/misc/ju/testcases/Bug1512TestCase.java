/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;

public class Bug1512TestCase extends BasicTestCase {

  private static final String projectTestName = "bug1512";
  
  private String oABDDiagramName =  "[OABD] Root Operational Activity"; 
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    assertNotNull(session);
    
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, oABDDiagramName).run();
    DSemanticDecorator view = diagramContext.getView("e0280209-bff4-4490-bc06-67b8c4a4a485");
    assertNotNull(view);
    removeModelElement(view.getTarget(), session);
    diagramContext.refreshDiagram();
  }
  
  protected void removeModelElement(final EObject element, Session session){
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(session);
    executionManager.execute(new AbstractReadWriteCommand() {
      
      @Override
      public void run() {
        EObjectHelper.removeElement(element);
      }
    });
  }
}
