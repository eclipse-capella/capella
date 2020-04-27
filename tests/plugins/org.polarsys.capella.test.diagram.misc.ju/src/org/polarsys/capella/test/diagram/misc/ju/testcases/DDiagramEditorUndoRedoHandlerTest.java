/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * After setup, we simply test whether a previously executed operation is correctly undone when invoking the respective
 * action from a diagram editor.
 */
public class DDiagramEditorUndoRedoHandlerTest extends BasicTestCase {
  String projectTestName = "StatusLine";
  DataPkg root;
  DRepresentation d1;
  DRepresentation d2;

  public void testDDiagramEditorUndoHandler(Session session) throws ExecutionException, PartInitException {
    /**
     * Create two class diagrams and open them.
     */
    IUndoableOperation prepareDiagrams = new AbstractEMFOperation(TransactionHelper.getEditingDomain(session),
        "prepare") {
      @Override
      protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        Resource resource = TestHelper.getSemanticResource(session);
        root = (DataPkg) TestHelper.getFirstMatchingObject(resource, InformationPackage.Literals.DATA_PKG);
        DataPkg p1 = InformationFactory.eINSTANCE.createDataPkg("p1");
        DataPkg p2 = InformationFactory.eINSTANCE.createDataPkg("p2");
        root.getOwnedDataPkgs().add(p1);
        root.getOwnedDataPkgs().add(p2);

        // I think we must pass the object because of diagram preconditions. The EClass is not sufficient.
        RepresentationDescription cdb1Description = DiagramHelper.getRepresentationDescription(session, p1,
            IDiagramNameConstants.CLASS_BLANK_DIAGRAM_NAME);
        RepresentationDescription cdb2Description = DiagramHelper.getRepresentationDescription(session, p2,
            IDiagramNameConstants.CLASS_BLANK_DIAGRAM_NAME);

        d1 = DialectManager.INSTANCE.createRepresentation("p1", p1, cdb1Description, session,
            new NullProgressMonitor());
        d2 = DialectManager.INSTANCE.createRepresentation("p2", p2, cdb2Description, session,
            new NullProgressMonitor());

        return Status.OK_STATUS;
      }
    };
    IStatus status = getOperationHistory().execute(prepareDiagrams, new NullProgressMonitor(), null);
    assertEquals(Status.OK_STATUS, status);

    /*
     * Open both diagrams.
     */
    IEditorPart d1Editor = DiagramHelper.opendiagramEditor(session, d1);
    IEditorPart d2Editor = DiagramHelper.opendiagramEditor(session, d2);

    /*
     * Now execute a simple traceable dummy operation
     */
    TestOperation testOperation = new TestOperation("testOperation");
    testOperation.addContext(getUndoContext(session));
    getOperationHistory().execute(testOperation, new NullProgressMonitor(), null);
    assertSame(TestOperationState.EXECUTE, testOperation.getState());

    /*
     * Are undo actions enabled?
     */
    IAction d1UndoAction = d1Editor.getEditorSite().getActionBars().getGlobalActionHandler(ActionFactory.UNDO.getId());
    assertTrue(d1UndoAction.isEnabled());
    assertTrue(d1UndoAction.isHandled());

    IAction d2UndoAction = d2Editor.getEditorSite().getActionBars().getGlobalActionHandler(ActionFactory.UNDO.getId());
    assertTrue(d2UndoAction.isEnabled());
    assertTrue(d2UndoAction.isHandled());

    /*
     * Close one of the editors.
     */
    d2Editor.getSite().getPage().closeEditor(d2Editor, false);
    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(d1Editor);
    /*
     * Can we still execute undo from the first editor?
     */
    assertTrue(d1UndoAction.isEnabled());
    assertTrue(d1UndoAction.isHandled());

    /*
     * Undo!
     */
    d1UndoAction.run();

    /*
     * And verify.
     */
    assertSame(testOperation.getState(), TestOperationState.UNDO);

    /*
     * Now open the capella explorer view and try to "redo" the operation
     */
    IViewPart explorer = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .showView("capella.project.explorer");

    /**
     * Undo/redo support only works if a model element is selected.
     */
    ((CommonNavigator) explorer).getCommonViewer().expandToLevel(root, 0); // if the element isn't visible, handler
                                                                           // won't be installed correctly
    ((ISetSelectionTarget) explorer).selectReveal(new StructuredSelection(root));
    IAction explorerRedo = explorer.getViewSite().getActionBars().getGlobalActionHandler(ActionFactory.REDO.getId());

    assertTrue(explorerRedo.isEnabled());
    assertTrue(explorerRedo.isHandled());

    explorerRedo.run();

    assertSame(testOperation.getState(), TestOperationState.REDO);
  }

  static enum TestOperationState {
    EXECUTE, UNDO, REDO
  }

  class TestOperation extends AbstractOperation {

    /**
     * @param label
     */
    public TestOperation(String label) {
      super(label);
    }

    private TestOperationState state = null;

    public TestOperationState getState() {
      return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
      state = TestOperationState.EXECUTE;
      return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
      state = TestOperationState.REDO;
      return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
      state = TestOperationState.UNDO;
      return Status.OK_STATUS;
    }

  }

  /**
   * Get the operation history where ultimately all executed operations are stocked for undo/redo.
   */
  protected IOperationHistory getOperationHistory() {
    return OperationHistoryFactory.getOperationHistory();
  }

  /**
   * Get the default undo context.
   */
  protected IUndoContext getUndoContext(Session session) {
    return ((IWorkspaceCommandStack) TransactionHelper.getEditingDomain(session).getCommandStack())
        .getDefaultUndoContext();
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(projectTestName);
    testDDiagramEditorUndoHandler(session);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }
}
