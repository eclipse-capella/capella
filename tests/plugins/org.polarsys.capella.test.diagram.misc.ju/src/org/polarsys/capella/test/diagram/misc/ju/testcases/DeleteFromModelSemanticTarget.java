/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelAction;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.preferences.DeletePreferences;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * See description https://bugs.polarsys.org/show_bug.cgi?id=2668
 * 
 * @author <a href="mailto:arthur.daussy@obeo.fr">Arthur Daussy</a>
 *
 */
public class DeleteFromModelSemanticTarget extends BasicTestCase {

    String projectTestName = "DeleteFromModelWithTarget";

    @Override
    public List<String> getRequiredTestModels() {
        return Arrays.asList(projectTestName);
    }

    @Override
    public void test() throws Exception {

        Session session = getSession(projectTestName);

        Resource resource = TestHelper.getSemanticResource(session);

        // Get the B Function that is the owner of the diagram
        PhysicalFunction bFunction = (PhysicalFunction) resource.getEObject("33bba6a8-e9da-4c5b-9b97-ec76e63e0dc3");
        DDiagram diagram = getDiagram(session, bFunction);

        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());

        GuiActions.flushASyncGuiThread();

        // Prevents getting the confirmation dialog when deleting the element from model
        Activator.getDefault().getPreferenceStore().putValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, "false");

        // Checks that the preference is set to false to avoid getting stuck later on the test
        assertFalse(((DeletePreferences) IDeletePreferences.INSTANCE).isConfirmationRequired());

        IGraphicalEditPart graphEdit = getFunctionEditPart(session, editorPart, bFunction);

        delete(graphEdit);

        GuiActions.flushASyncGuiThread();

        // The representation should be deleted
        assertNull(diagram.eContainer());

        // The 'B' function should be delete
        assertNull(bFunction.eResource());

        // Check the editor is closed
        assertNull(EclipseUIUtil.getActiveEditor());
        assertEquals(0, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length);
    }

    static IGraphicalEditPart getFunctionEditPart(Session session, IEditorPart editorPart, PhysicalFunction aFunction) {
        List<DNodeContainer> aNode = session.getSemanticCrossReferencer().getInverseReferences(aFunction).stream()//
                .filter(s -> s.getEStructuralFeature() == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target() && s.getEObject() instanceof DNodeContainer)//
                .map(s -> (DNodeContainer) s.getEObject())//
                .collect(toList());

        assertEquals(1, aNode.size());

        IGraphicalEditPart graphEdit = getEditPart(aNode.iterator().next(), editorPart, session);

        assertNotNull(graphEdit);
        return graphEdit;
    }

    static DDiagram getDiagram(Session session, PhysicalFunction bFunction) {
        Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(bFunction, session);
        assertEquals(1, representations.size());
        DDiagram rep = (DDiagram) representations.iterator().next();
        return rep;
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param gmfView
     *            the gmf view
     * @param editor
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as parameter or null if any
     */
    /*
     * Copied from org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase
     */
    private static IGraphicalEditPart getEditPart(final View gmfView, final IEditorPart editor) {
        IGraphicalEditPart result = null;
        if (gmfView != null && editor instanceof DiagramEditor) {
            final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
            final Object editPart = editPartRegistry.get(gmfView);
            if (editPart instanceof IGraphicalEditPart) {
                result = (IGraphicalEditPart) editPart;
            }
        }
        return result;
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * @param editor
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as parameter or null if any
     */
    /*
     * Copied from org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase
     */
    private static IGraphicalEditPart getEditPart(final DDiagramElement diagramElement, final IEditorPart editor, Session session) {
        final View gmfView = getGmfView(diagramElement, session);
        return getEditPart(gmfView, editor);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the view which has as element the diagram element given as parameter or null if any
     */
    /*
     * Inspired from org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase
     */
    private static View getGmfView(final DDiagramElement diagramElement, Session session) {
        return SiriusGMFHelper.getGmfView(diagramElement, session);
    }

    /*
     * Inspired from org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase
     */
    static void delete(final EditPart editPart) {
        final DeleteFromModelAction actionDelegate = new DeleteFromModelAction();
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final GraphicalViewer viewer = editor.getAdapter(GraphicalViewer.class);
        viewer.appendSelection(editPart);
        GuiActions.flushASyncGuiThread();
        actionDelegate.run();
        GuiActions.flushASyncGuiThread();
    }

}
