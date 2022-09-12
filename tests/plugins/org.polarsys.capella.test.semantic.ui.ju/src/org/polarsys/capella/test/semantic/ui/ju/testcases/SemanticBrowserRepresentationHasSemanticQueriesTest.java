/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.ui.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.sirius.wrapper.CustomEObjectWrapper;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test case create double click on a representation in the left panel (referencing elements) of the semantic
 * browser view.
 */
public class SemanticBrowserRepresentationHasSemanticQueriesTest extends BasicTestCase {

  protected final String CAPELLA_PERSPECTIVE_ID = "capella.sirius.perspective";
  protected final String SEMANTIC_BROWSER_VIEW_ID = "org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserID";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiagramNavigationModel");
  }

  @Override
  public void test() throws Exception {

    DRepresentationDescriptor representationToSelect = null;
    Scenario scenarioToSelect = null;

    IStructuredSelection navigatorSelection = null;

    CustomEObjectWrapper representationCustomWrapper = null;
    CustomEObjectWrapper scenarioCustomWrapper = null;

    try {

      CapellaModel model = getTestModel(getRequiredTestModels().get(0));
      Session session = getSession(getRequiredTestModels().get(0));
      Project project = model.getProject(session.getTransactionalEditingDomain());
      SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
      final SystemAnalysis systemAnalysisPkg = eng.getContainedSystemAnalysis().get(0);
      CapabilityPkg capabilityPkg = (CapabilityPkg) systemAnalysisPkg.getOwnedAbstractCapabilityPkg();
      Capability capability = capabilityPkg.getOwnedCapabilities().get(1);
      scenarioToSelect = capability.getOwnedScenarios().get(1);

      Collection<DRepresentationDescriptor> descriptors = RepresentationHelper
          .getAllRepresentationDescriptorsTargetedBy(Collections.singleton(scenarioToSelect));
      representationToSelect = descriptors.iterator().next();

      navigatorSelection = new StructuredSelection(representationToSelect);

      representationCustomWrapper = new CustomEObjectWrapper(representationToSelect);
      scenarioCustomWrapper = new CustomEObjectWrapper(scenarioToSelect);

    } catch (Exception e) {
      fail("Model Elements could not be found");
    }

    IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    if (activeWorkbenchWindow == null) {
      fail("No Active workbench window");
    }

    try {
      // Ensure that the welcome page is closed
      closeIntro();
    } catch (Exception E) {
      fail("Could not close intro page");
    }

    try {
      PlatformUI.getWorkbench().showPerspective(CAPELLA_PERSPECTIVE_ID, activeWorkbenchWindow);
    } catch (Exception e) {
      fail("Could not open Capella perspective");
    }

    CommonViewer viewer = getViewer();
    viewer.getCommonNavigator().selectReveal(navigatorSelection);

    IViewPart semanticBrowserViewpart = activeWorkbenchWindow.getActivePage().showView(SEMANTIC_BROWSER_VIEW_ID);
    if (semanticBrowserViewpart != null) {

      SemanticBrowserView semanticBrowserView = (SemanticBrowserView) semanticBrowserViewpart;
      semanticBrowserView.setInput(representationToSelect);

      // First part of the test : test the semantic browser when selection a representation

      // Ensure that there is only the scenario in the referencing viewer
      treeViewerHasCustomEObjectWrappers(semanticBrowserView.getReferencingViewer(), scenarioCustomWrapper);

      // Ensure that both the scenario and representation are in the current viewer
      treeViewerHasCustomEObjectWrappers(semanticBrowserView.getCurrentViewer(), representationCustomWrapper,
          scenarioCustomWrapper);

      // Ensure that both the scenario and representation are in the referencing viewer
      treeViewerHasCustomEObjectWrappers(semanticBrowserView.getReferencedViewer(), representationCustomWrapper,
          scenarioCustomWrapper);

      // Second part of the test : ensure that, when not selecting a representation, the wrappers are not
      // CustomEObjectWrapper
      semanticBrowserView.setInput(scenarioToSelect);
      treeViewerHasNoCustomEObjectWrapper(semanticBrowserView.getReferencedViewer());
      treeViewerHasNoCustomEObjectWrapper(semanticBrowserView.getCurrentViewer());
      treeViewerHasNoCustomEObjectWrapper(semanticBrowserView.getReferencingViewer());

    } else {
      fail("Could not open Semantic Browser View");
    }
  }

  /**
   * @return
   */
  protected CommonViewer getViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .findView(CapellaCommonNavigator.ID);

    viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
    return viewer[0];
  }

  protected void closeIntro() {
    IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
    PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
  }

  protected void treeViewerHasNoCustomEObjectWrapper(TreeViewer treeViewer) {
    treeViewer.expandAll();
    Tree tree = treeViewer.getTree();
    TreeItem[] items = tree.getItems();
    for (TreeItem item : items) {
      assertFalse(item.getData() instanceof CustomEObjectWrapper);
    }
  }

  protected void treeViewerHasCustomEObjectWrappers(TreeViewer treeViewer, Object... expectedResult) {
    if (expectedResult == null || expectedResult.length == 0)
      return;

    treeViewer.expandAll();
    Tree tree = treeViewer.getTree();
    TreeItem[] items = tree.getItems();
    assertTrue(items.length == expectedResult.length);

    for (int i = 0; i < items.length; i++) {
      TreeItem currentTreeItem = items[i];
      Object currentItem = currentTreeItem.getData();
      // Its wrapper should be a CustomEobjectWrapper
      assertTrue(currentItem instanceof CustomEObjectWrapper);
      // It shall be the expectedResult
      assertEquals(expectedResult[i], currentItem);
      // It shall have children (or shouldn't be displayed)
      assertTrue(currentTreeItem.getItemCount() > 0);
    }
  }
}
