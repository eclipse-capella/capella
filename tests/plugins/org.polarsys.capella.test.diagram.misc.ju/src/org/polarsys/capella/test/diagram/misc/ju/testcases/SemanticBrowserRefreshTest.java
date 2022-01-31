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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.misc.ju.TestDiagramMiscPlugin;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Test that the SB updates its input when the input is the same than before but the selection is not the same
 * (simulation of a drag'n'drop).
 *
 * @author lredor
 */
public class SemanticBrowserRefreshTest extends BasicTestCase {
  public static String OA_4_1 = "71356c27-1fbc-49f5-b453-0a1aa3026863"; //$NON-NLS-1$
  public static String OA_1 = "4ad0704e-029a-4646-ae13-5e4f35992c5f"; //$NON-NLS-1$
  public static String OA_1_NAME = "OA 1"; //$NON-NLS-1$
  public static String OA_4_NAME = "OA 4"; //$NON-NLS-1$
  public static String OA_4_1_NAME = "OA 4_1"; //$NON-NLS-1$
  public static String PARENT_CATEGORY_NAME = "Parent"; //$NON-NLS-1$
  private String projectTestName = "StatusLine";

  private class SemanticBrowserParentRunnable implements RunnableWithResult<String> {

    SemanticBrowserView semanticBrowserView;

    String result;

    IStatus status = Status.OK_STATUS;

    /**
       * Default constructor.
     * @param semanticBrowserViewer The semantic browser view to use
     */
    public SemanticBrowserParentRunnable(SemanticBrowserView semanticBrowserViewer) {
        this.semanticBrowserView = semanticBrowserViewer;
    }

    @Override
    public void run() {
      if (semanticBrowserView.getCurrentViewer().getTree().getItemCount() != 1) {
        setStatus(new Status(IStatus.ERROR, TestDiagramMiscPlugin.PLUGIN_ID, "The \"Current Element\" part of the \"Semantic Browser\" view must contain only one root element.")); //$NON-NLS-1$
        return;
      }
      TreeItem rootItem = semanticBrowserView.getCurrentViewer().getTree().getItem(0);
      if (!(OA_4_1_NAME.equals(rootItem.getText()))) {
        setStatus(
            new Status(IStatus.ERROR, TestDiagramMiscPlugin.PLUGIN_ID, "The root element of the \"Current Element\" part of the \"Semantic Browser\" view must be \"" + OA_4_1_NAME + "\".")); //$NON-NLS-1$ //$NON-NLS-2$
        return;
      }
      if (rootItem.getItemCount() < 1) {
        setStatus(new Status(IStatus.ERROR, TestDiagramMiscPlugin.PLUGIN_ID, "The root element must contain at least one category.")); //$NON-NLS-1$
        return;
      }
      TreeItem parentCategroyItem = rootItem.getItem(0);
      if (!(PARENT_CATEGORY_NAME.equals(parentCategroyItem.getText()))) {
        setStatus(new Status(IStatus.ERROR, TestDiagramMiscPlugin.PLUGIN_ID, "The first category of the root element must be \"" + PARENT_CATEGORY_NAME + "\".")); //$NON-NLS-1$ //$NON-NLS-2$
        return;
      }
      if (parentCategroyItem.getItemCount() < 1) {
        setStatus(new Status(IStatus.ERROR, TestDiagramMiscPlugin.PLUGIN_ID, "The \"" + PARENT_CATEGORY_NAME + "\" category must contain at least the parent name.")); //$NON-NLS-1$
        return;
      }
      TreeItem parentItem = parentCategroyItem.getItem(0);
      result = parentItem.getText();
    }

    @Override
    public String getResult() {
      return result;
    }

    @Override
    public void setStatus(IStatus status) {
      this.status = status;
    }

    @Override
    public IStatus getStatus() {
      return status;
    }
  }

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

    // Set input to operationalActivity_4_1
    EObject operationalActivity_4_1 = IdManager.getInstance().getEObject(OA_4_1, scope);
    EObject operationalActivity_1 = IdManager.getInstance().getEObject(OA_1, scope);
    // Simulate a structured selection, in theory, it would contain an EditPart, but here just a String will be enough.
    StructuredSelection fakeSelection1 = new StructuredSelection(new String("EditPart1"));
    semanticBrowserViewer.saveInput(operationalActivity_4_1, fakeSelection1);

    // Focus on SB
    semanticBrowserViewer.setFocus();
    assertTrue("The input of SB should be \"" + OA_4_1_NAME + "\".", semanticBrowserViewer.getCurrentViewer().getInput() == operationalActivity_4_1);
    checkParentDisplayedinSB(semanticBrowserViewer, OA_4_NAME);

    // Semantically change parent (as during a drag'n'drop)
    TestHelper.getExecutionManager(getSession(projectTestName)).execute(new AbstractReadWriteCommand() {
      public void run() {
        ((OperationalActivity) operationalActivity_1).getOwnedFunctions().add((OperationalActivity) operationalActivity_4_1);
      }
    });

    // Set input to operationalActivity_4_1 (with the same selection)
    semanticBrowserViewer.saveInput(operationalActivity_4_1, fakeSelection1);

    // Focus on SB
    semanticBrowserViewer.setFocus();
    assertTrue("The input of SB should be \"" + OA_4_1_NAME + "\".", semanticBrowserViewer.getCurrentViewer().getInput() == operationalActivity_4_1);
    // Check that the parent displayed in the SB is always the same (because the saveInput is done with the same
    // selection)
    checkParentDisplayedinSB(semanticBrowserViewer, OA_4_NAME);

    // Set input to operationalActivity_4_1 (with the another selection)
    // Simulate a structured selection, in theory, it would be an EditPart, but here just a not null selection will be
    // enough.
    StructuredSelection fakeSelection2 = new StructuredSelection(new String("EditPart2"));
    semanticBrowserViewer.saveInput(operationalActivity_4_1, fakeSelection2);

    // Focus on SB
    semanticBrowserViewer.setFocus();
    assertTrue("The input of SB should be \"" + OA_4_1_NAME + "\".", semanticBrowserViewer.getCurrentViewer().getInput() == operationalActivity_4_1);
    // Check that the parent displayed in the SB is now the new one
    checkParentDisplayedinSB(semanticBrowserViewer, OA_1_NAME);
}

  private void checkParentDisplayedinSB(SemanticBrowserView semanticBrowserViewer, String expectedParentName) {
    SemanticBrowserParentRunnable runnable = new SemanticBrowserParentRunnable(semanticBrowserViewer);
    Display.getDefault().syncExec(runnable);
    if (!runnable.getStatus().isOK()) {
      fail(runnable.getStatus().getMessage());
    }
    assertEquals("Wrong parent of \"OA 4_1\" is displayed in the Semantic Browser view.", expectedParentName, runnable.getResult());
  }
}
