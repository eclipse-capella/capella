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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
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
 * See description https://bugs.polarsys.org/show_bug.cgi?id=2669
 * 
 * @author <a href="mailto:arthur.daussy@obeo.fr">Arthur Daussy</a>
 *
 */
public class DeleteFromModelContainerSemanticTarget extends BasicTestCase {

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
        DDiagram diagram = DeleteFromModelSemanticTarget.getDiagram(session, bFunction);

        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());

        GuiActions.flushASyncGuiThread();

        // This line aims to avoid getting the confirmation dialog when deleting the element from model
        Activator.getDefault().getPreferenceStore().putValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, "false");

        // Checks that the preference is set to false to avoid getting stuck later on the test
        assertFalse(((DeletePreferences) IDeletePreferences.INSTANCE).isConfirmationRequired());

        // Gets the semantic container of the B
        PhysicalFunction aFunction = (PhysicalFunction) resource.getEObject("2d787083-bce9-4858-b925-988fb9f4a418");
        IGraphicalEditPart graphEdit = DeleteFromModelSemanticTarget.getFunctionEditPart(session, editorPart, aFunction);

        DeleteFromModelSemanticTarget.delete(graphEdit);

        GuiActions.flushASyncGuiThread();

        // The representation should be deleted
        assertNull(diagram.eContainer());

        // The 'A' function should be delete
        assertNull(aFunction.eContainer());

        // Check the editor is closed
        assertNull(EclipseUIUtil.getActiveEditor());
        assertEquals(0, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length);
    }

}
