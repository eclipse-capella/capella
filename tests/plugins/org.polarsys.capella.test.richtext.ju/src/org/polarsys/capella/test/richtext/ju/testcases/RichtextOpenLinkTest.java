/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.richtext.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableCrossEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.services.SiriusSelectorInPackageExplorer;
import org.polarsys.capella.core.ui.properties.richtext.navigation.CapellaNavigationModelElement;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.GuiHelper;

import junit.framework.Test;

/**
 * This test case tests that it's possible to open links to model elements, diagrams and tables
 */
public class RichtextOpenLinkTest extends BasicTestCase {
  public static String ROOT_SF = "8aec9c74-487d-4d8d-8ea2-5f733b69f23f";
  public static String SDFB = "_la3j4DNYEeieBf4kJpEPiQ";
  public static String TABLE = "_0URZYDNeEeieBf4kJpEPiQ";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("RichtextTestModel");
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel("RichtextTestModel");
    assertNotNull(model);
    
    IScope scope = new ScopeModelWrapper(model);
    SystemFunction rootSF = (SystemFunction) IdManager.getInstance().getEObject(ROOT_SF, scope);
    assertNotNull(rootSF);
    
    // Just called to force plug-in activation
    new SiriusSelectorInPackageExplorer();
    
    // Test the opening of link to model element (Root SF)
    CapellaNavigationModelElement openLinkStrategy = new CapellaNavigationModelElement();
    openLinkStrategy.openLink(rootSF, ROOT_SF);
    GuiActions.flushASyncGuiThread();
    IStructuredSelection currentSelection = GuiHelper.getCurrentSelectionInProjectExplorer();
    assertNotNull(currentSelection);
    assertNotNull(currentSelection.getFirstElement());
    assertTrue("Root System Function is not selected after the link is selected",
        currentSelection.getFirstElement().equals(rootSF));

    // Test the opening of link to diagram (SDFB)
    openLinkStrategy.openLink(rootSF, SDFB);
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if ((null != activeEditor) && (activeEditor instanceof SiriusDiagramEditor)) {
      SiriusDiagramEditor diagramEditor = (SiriusDiagramEditor) activeEditor;
      DRepresentation diagram = (DRepresentation) diagramEditor.getDiagram().getElement();
      assertTrue("Uid is not taken into account in open diagram link", diagram.getUid().equals(SDFB));
      diagramEditor.close(false);
    } else
      fail("No diagram is opened");

    // Test the opening of link to table
    openLinkStrategy.openLink(rootSF, TABLE);
    activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if ((null != activeEditor) && (activeEditor instanceof DTableCrossEditor)) {
      DTableCrossEditor tableEditor = (DTableCrossEditor) activeEditor;
      DRepresentation table = (DRepresentation) tableEditor.getRepresentation();
      assertTrue("Uid is not taken into account in open table link", table.getUid().equals(TABLE));
    } else
      fail("No table is opened");
  }

  public static Test suite() {
    return new RichtextOpenLinkTest();
  }
}
