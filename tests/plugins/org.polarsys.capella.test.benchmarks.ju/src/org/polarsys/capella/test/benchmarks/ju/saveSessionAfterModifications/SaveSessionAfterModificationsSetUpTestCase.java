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
package org.polarsys.capella.test.benchmarks.ju.saveSessionAfterModifications;

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractSetUpTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * 
 * Transition a LF from Logical layer to Physical Layer and then create a PFBD at root PF
 */
public class SaveSessionAfterModificationsSetUpTestCase extends AbstractSetUpTestCase {

  public SaveSessionAfterModificationsSetUpTestCase(BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
  }

  public String getLFIdToTransition() {
    return "145e28dc-9fbe-4f01-a378-5af8cbe8e2d7";
  }

  public String getRootPFId() {
    return "0f09b41e-1e38-4d34-abb0-ba31a0eedd58";
  }

  @Override
  public void test() {
    IIntroPart introViewPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
    if (introViewPart != null) {
      PlatformUI.getWorkbench().getIntroManager().closeIntro(introViewPart);
    }

    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    ICapellaModel model = getTestModel(getRequiredTestModels().get(0));
    IScope scope = new ScopeModelWrapper(model);

    // Transition d13cf LF to PF
    String LFToTransition = getLFIdToTransition();
    EObject object = IdManager.getInstance().getEObject(LFToTransition, scope);

    ICommand transitionCommand = TransitionCommandHelper.getInstance()
        .getFunctionalTransitionCommand(Arrays.asList(object), new NullProgressMonitor());
    TransactionHelper.getExecutionManager(object).execute(transitionCommand);

    // Create a PFBD at root PF
    String ROOT_PF = getRootPFId();
    XBreakdownDiagram.createFBDiagram(context, ROOT_PF);
  }

}
