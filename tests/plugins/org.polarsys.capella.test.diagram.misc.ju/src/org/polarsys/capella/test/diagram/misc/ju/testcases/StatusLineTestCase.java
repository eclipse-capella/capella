/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Test that the status bar gets the correct message when elements are selected in Project Explorer and Semantic Browser
 *
 */
public class StatusLineTestCase extends BasicTestCase {
  public static String SA__DATAPKG = "1db63ef2-9d9d-4069-993a-0f6236f7454a"; //$NON-NLS-1$
  public static String SA__ROOTSF = "10ea7dd3-5406-4f0b-b083-95b3bce87a2f"; //$NON-NLS-1$

  private String projectTestName = "StatusLine";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  protected CommonViewer getProjectExplorerViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(CapellaCommonNavigator.ID);

        viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
      }
    });
    return viewer[0];
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

  protected String getStatusLineMessage(IStatusLineManager statusLineManager) throws Exception {
    Field field = statusLineManager.getClass().getDeclaredField("message");
    field.setAccessible(true);
    return (String) field.get(statusLineManager);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(projectTestName);
    IScope scope = new ScopeModelWrapper(model);
    
    CommonViewer viewer = getProjectExplorerViewer();
    EObject dataPkg = IdManager.getInstance().getEObject(SA__DATAPKG, scope);
    
    // Select the Data package element in Project Explorer
    ISelection dataPkgselection = new StructuredSelection(dataPkg);
    viewer.setSelection(dataPkgselection);

    IStatusLineManager projectExplorerStatusLineManager = viewer.getCommonNavigator().getViewSite().getActionBars()
        .getStatusLineManager();
    String statusLineMsg = getStatusLineMessage(projectExplorerStatusLineManager);

    assertTrue("The displayed message in status bar should be StatusLine::StatusLine::System Analysis::Data",
        statusLineMsg.equals("StatusLine::StatusLine::System Analysis::Data"));

    DRepresentationDescriptor sdfb = null;
    Session session = getSession(projectTestName);
    for (DView entry : session.getOwnedViews()) {
      if (entry.getViewpoint().getName().equals("System Analysis") && entry.getOwnedRepresentationDescriptors().size() > 0) {
        sdfb = entry.getOwnedRepresentationDescriptors().get(0);
      }
    }
    
    // Load all representations (so that the status is the same in case of lazy loading)
    DialectManager.INSTANCE.getAllRepresentations(session);
    
    // Select the SFDB descriptor in Project Explorer
    ISelection sdfbSelection = new StructuredSelection(sdfb);
    viewer.setSelection(sdfbSelection);
    statusLineMsg = getStatusLineMessage(projectExplorerStatusLineManager);
    assertTrue(
        "The displayed message in status bar should be StatusLine::StatusLine::System Analysis::System Functions::Root System Function::[SDFB] Root System Function (Synchronized)",
        statusLineMsg.equals(
            "StatusLine::StatusLine::System Analysis::System Functions::Root System Function::[SDFB] Root System Function (Synchronized)"));

    // Reset selection to null to avoid problems during session close with test framework
    viewer.setSelection(null);
  }
}