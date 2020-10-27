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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.OAServices;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Test that the interaction gets the correct parent OA when source/target OAs are removed from diagram
 *
 */
public class InteractionSourceTarget extends BasicTestCase {
  public static final String INTERACTION_1 = "f4756f78-ef42-4c35-9793-3c916bae710d"; //$NON-NLS-1$
  public static final String INTERACTION_2 = "c2ce75ec-32fa-46d6-8557-f995b8f3ce44"; //$NON-NLS-1$
  public static final String INTERACTION_3 = "dd31bcfa-0c08-4984-8358-47f4dfd79f7a"; //$NON-NLS-1$
  public static final String INTERACTION_4 = "a96b1b95-47fd-4e76-9dc3-f8bb979ce15f"; //$NON-NLS-1$
  public static final String OAIB = "[OAIB] Root Operational Activity"; //$NON-NLS-1$
  public static final String OA_2 = "f26b78a9-9109-4af6-840c-fc3d050e1f8e"; //$NON-NLS-1$
  public static final String OA_4 = "308bbc45-8cda-45a2-886f-8adc208e3b7b"; //$NON-NLS-1$
  public static final String OA_6 = "b157b346-59b3-438a-91fe-481e9cf8853c"; //$NON-NLS-1$
  public static final String OA_4_1 = "71356c27-1fbc-49f5-b453-0a1aa3026863"; //$NON-NLS-1$
  public static final String OA_6_1 = "c57618fa-d961-4670-8524-9434a0bb186e"; //$NON-NLS-1$

  private String projectTestName = "StatusLine";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(projectTestName);
    IScope scope = new ScopeModelWrapper(model);

    EObject interaction_1 = IdManager.getInstance().getEObject(INTERACTION_1, scope);
    EObject interaction_2 = IdManager.getInstance().getEObject(INTERACTION_2, scope);
    EObject interaction_3 = IdManager.getInstance().getEObject(INTERACTION_3, scope);
    EObject interaction_4 = IdManager.getInstance().getEObject(INTERACTION_4, scope);
    EObject oa_2 = IdManager.getInstance().getEObject(OA_2, scope);
    EObject oa_4 = IdManager.getInstance().getEObject(OA_4, scope);
    EObject oa_6 = IdManager.getInstance().getEObject(OA_6, scope);
    EObject oa_4_1 = IdManager.getInstance().getEObject(OA_4_1, scope);
    EObject oa_6_1 = IdManager.getInstance().getEObject(OA_6_1, scope);
    
    Session session = getSession(projectTestName);
    SessionContext context = new SessionContext(session);
    XDFBDiagram OAIBDiagram = XDFBDiagram.openDiagram(context, OAIB, Type.OA);

    // Remove OA 2, OA 4_1 and OA 6_1 and check that Interaction 1 and Interaction 2 do not exist, Interaction 3 and Interaction 4
    // point to the parent OA (OA 4 and OA 6)
    TransactionHelper.getExecutionManager(session).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        DiagramHelper.removeView(OAIBDiagram.getView(oa_2));
        DiagramHelper.removeView(OAIBDiagram.getView(oa_4_1));
        DiagramHelper.removeView(OAIBDiagram.getView(oa_6_1));
      }
    });
    OAIBDiagram.refreshDiagram();

    assertTrue(OAServices.getService().getInteractionTargetInDiagram((FunctionalExchange) interaction_1,
        OAIBDiagram.getDiagram()) == null);
    assertTrue(OAServices.getService().getInteractionSourceInDiagram((FunctionalExchange) interaction_2,
        OAIBDiagram.getDiagram()) == null);
    assertTrue(OAServices.getService().getInteractionSourceInDiagram((FunctionalExchange) interaction_3,
        OAIBDiagram.getDiagram()) == oa_4);
    assertTrue(OAServices.getService().getInteractionTargetInDiagram((FunctionalExchange) interaction_4,
        OAIBDiagram.getDiagram()) == oa_6);
  }
}