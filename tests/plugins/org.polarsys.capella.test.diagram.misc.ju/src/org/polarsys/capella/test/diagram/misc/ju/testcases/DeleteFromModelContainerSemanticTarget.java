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

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.misc.ju.testcases.delete.AbstractDeleteFromModelTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * See description https://bugs.polarsys.org/show_bug.cgi?id=2669
 * 
 * @author <a href="mailto:arthur.daussy@obeo.fr">Arthur Daussy</a>
 *
 */
public class DeleteFromModelContainerSemanticTarget extends AbstractDeleteFromModelTestCase {

  @Override
  public void test() throws Exception {

    Session session = getSession(TEST_MODEL_NAME);
    SessionContext sessionContext = new SessionContext(session);

    // Get the B Function that is the owner of the diagram
    PhysicalFunction bFunction = sessionContext.getSemanticElement(B_PHYSICAL_FUNCTION_ID);

    Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(bFunction, session);
    assertEquals(1, representations.size());

    DDiagram diagram = (DDiagram) representations.iterator().next();
    DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());

    disableConfirmDeletePreference();

    GuiActions.flushASyncGuiThread();

    PhysicalFunction aFunction =  sessionContext.getSemanticElement(A_PHYSICAL_FUNCTION_ID);
    DDiagramElement diagramElement = DiagramServices.getDiagramServices().getDiagramElement(diagram, aFunction);
    
    IGraphicalEditPart graphicalPart = LayerUtil.getGraphicalPart(diagramElement);
    delete(graphicalPart);

    GuiActions.flushASyncGuiThread();

    // The representation should be deleted
    assertNull(diagram.eContainer());

    // The 'A' function should be delete
    assertNull(aFunction.eContainer());

    // Check the editor is closed
    assertNull(EclipseUIUtil.getActiveEditor());

    IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .getEditorReferences();
    assertEquals(0, editorReferences.length);
  }

}
