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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Test that the SB does not update its input when the synchronization is deactivated
 *
 */
public class UnsyncronizedSemanticBrowser extends BasicTestCase {
  public static String SA__DATAPKG = "1db63ef2-9d9d-4069-993a-0f6236f7454a"; //$NON-NLS-1$
  public static String SA__ROOTSF = "10ea7dd3-5406-4f0b-b083-95b3bce87a2f"; //$NON-NLS-1$

  private String projectTestName = "StatusLine";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  protected SiriusSemanticBrowserView getSemanticBrowserViewer() {
    final SiriusSemanticBrowserView[] viewer = new SiriusSemanticBrowserView[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        viewer[0] = (SiriusSemanticBrowserView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
      }
    });
    return viewer[0];
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(projectTestName);
    IScope scope = new ScopeModelWrapper(model);

    // Open SB
    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .showView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
    SiriusSemanticBrowserView semanticBrowserViewer = getSemanticBrowserViewer();

    // Set input to DataPkg
    EObject dataPkg = IdManager.getInstance().getEObject(SA__DATAPKG, scope);
    semanticBrowserViewer.saveInput(dataPkg);

    // Deactivate synchronization on SB
    semanticBrowserViewer.deactivateListeningToPageSelectionEvents();

    // Set input to RootSF
    EObject rootSF = IdManager.getInstance().getEObject(SA__ROOTSF, scope);
    semanticBrowserViewer.saveInput(rootSF);

    // Focus on SB
    semanticBrowserViewer.setFocus();

    assertTrue("SB should still point to DataPkg since the synchronization has been deactivated",
        semanticBrowserViewer.getCurrentViewer().getInput() == dataPkg);
  }
}