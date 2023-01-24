/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteFormatAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.handlers.CompareLayoutHandler;
import org.polarsys.capella.test.diagram.layout.ju.model.ModelCopyLayout;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public class CopyPasteLayoutFunctionalChains extends ModelCopyLayout {

  public static String SDFB1_SOURCE = "_fQuBoJAhEe2OYc1QJcaS8Q";

  public static String SDFB1_TARGET = "_FolqbpgLEe2ucetYitOqVw";
  public static String SAB_SDFB1_EXPECTED = "_V7sz4ZgIEe2ucetYitOqVw";
  public static String SDFB2_SOURCE = "_8fQRAJAkEe2OYc1QJcaS8Q";
  public static String SDFB2_TARGET = "_HwsmlJgLEe2ucetYitOqVw";
  public static String SAB_SDFB2_EXPECTED = "_fMvhUJgIEe2ucetYitOqVw";
  public static String SAB_SOURCE = "_FFU5gJAmEe2OYc1QJcaS8Q";
  public static String SAB_FC1 = "_DpU9qpjOEe2tE5fuNR9Q9g";
  public static String SAB_FC2 = "_2O3jy5jNEe2tE5fuNR9Q9g";
  public static String FC1_TARGET1 = "_tCtDcJAkEe2OYc1QJcaS8Q";
  public static String FC1_TARGET2 = "_9Srkp5jNEe2tE5fuNR9Q9g";
  public static String SDFB1_FC1_EXPECTED = "_uvGG2pgGEe2ucetYitOqVw";
  public static String FC2_SOURCE = "_lHY6EJAhEe2OYc1QJcaS8Q";
  public static String FC2_TARGET1 = "_HUJfKZjNEe2tE5fuNR9Q9g";
  public static String FC2_TARGET2 = "_JBPTG5jNEe2tE5fuNR9Q9g";
  public static String FC2_TARGET3 = "_0asQaZjNEe2tE5fuNR9Q9g";
  public static String CLONE_FC2_TARGET = "_6f7TwpgKEe2ucetYitOqVw";
  public static String FC2_FC2_CLONE_EXPECTED = "_CgV5MJgGEe2ucetYitOqVw";
  public static String SDFB1_FC2_EXPECTED = "_Nr4dOpjNEe2tE5fuNR9Q9g";
  public static String FC1_FC2_EXPECTED = "_nkwAAJgGEe2ucetYitOqVw";
  public static String FC3_SOURCE = "_AJPPcJAlEe2OYc1QJcaS8Q";
  public static String LFC3 = "_6MCw4JAnEe2OYc1QJcaS8Q";
  public static String FC3_CLONE_TARGET = "_0uwmYJgKEe2ucetYitOqVw";
  public static String FC3_FC3_CLONE_EXPECTED = "_TyNpoJgGEe2ucetYitOqVw";
  public static String FC3_LFC3_EXPECTED = "_sFofbpgIEe2ucetYitOqVw";
  
  public static String PP1 = "_Tr_eEJjQEe299sGa9inWTA";
  public static String PP2 = "_Yhz9gJjQEe299sGa9inWTA";
  public static String PP1_PP2_EXPECTED = "_rOvWsJjQEe299sGa9inWTA";
  public static String PAB1 = "_BaakIJjQEe299sGa9inWTA";
  public static String PAB2 = "_agvvYJjQEe299sGa9inWTA";
  public static String PAB1_PAB2_EXPECTED = "_h8B1cZjQEe299sGa9inWTA";

  public static String FC3_FC4_EXPECTED = "_wzY8ipx9Ee2Qeqzp7wDG4g";
  public static String FC4 = "_upl-YJx9Ee2Qeqzp7wDG4g";
  public static String FC4_TARGET = "_EFkXwJx-Ee2Qeqzp7wDG4g";
  
  public static String SAB_LAB_EXPECTED = "_3X1DYZyJEe2Qeqzp7wDG4g";
  public static String LAB = "_nBbdsJx-Ee2Qeqzp7wDG4g";
  public static String LAB_TARGET = "_17-JYJyJEe2Qeqzp7wDG4g";

  public static String COPY_FC3_TARGET = "_iWlU0JyiEe2YDZvvaEnOfA";
  public static String FC3_COPY_FC3_EXPECTED = "_m-8nEJyiEe2YDZvvaEnOfA";

  public static final String FCI_TO_SYSTEMFUNCTION_3 = "8fb284c2-3d07-4bb4-a4a5-ffbe7b0f3885"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TOP_FUNCTIONALCHAIN_1 = "5efc59e2-e5ec-4cb2-a1e5-a9118e083537"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_4 = "3600e7d2-0a33-4c00-8f0d-18849e1cc48c"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_BOTTOM_FUNCTIONALCHAIN_1 = "bd1a3136-5be3-432b-8114-953bd2370440"; //$NON-NLS-1$

  public static final String FCI_TO_SYSTEMFUNCTION_5 = "403f9a86-341e-4d01-857a-3001528febbe"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_6 = "b77c1ac1-b3f9-404a-8a00-a1a48b79e99f"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_7 = "f9b22004-a5c7-4ac6-ac2b-f530f58a3d52"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_8 = "f119ac19-1886-47df-9a7d-fa47ef63960a"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_52 = "681b7f82-ece1-4faa-9837-0a44647708a8"; //$NON-NLS-1$
  public static final String FCI_TO_SYSTEMFUNCTION_9 = "e859eeba-8668-4456-862d-d141c23be9b5"; //$NON-NLS-1$

  public static final String LFCI_TO_SYSTEMFUNCTION_5 = "345742d9-2742-43e8-8fb3-4da07a6fb615"; //$NON-NLS-1$
  public static final String LFCI_TO_SYSTEMFUNCTION_6 = "8734099b-e894-416a-8045-989bf3fb4eb9"; //$NON-NLS-1$
  public static final String LFCI_TO_SYSTEMFUNCTION_7 = "19a793c9-f311-491d-853e-e47d69a8f0c7"; //$NON-NLS-1$
  public static final String LFCI_TO_SYSTEMFUNCTION_8 = "2107a4be-d82d-4d8d-ae2c-9a520cc70e28"; //$NON-NLS-1$
  public static final String LFCI_TO_SYSTEMFUNCTION_52 = "c7e808cf-49cc-4dbe-85c9-f273429aab53"; //$NON-NLS-1$
  public static final String LFCI_TO_SYSTEMFUNCTION_9 = "13140f28-c321-4cfd-b669-458002b1152a"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {

    DiagramHelper.setPreferenceAutoRefresh(true);
    DiagramHelper.setPrefereneRefreshOnOpening(true);
    DiagramHelper.setPreferenceCopyLayoutPrompt(false);
    DiagramHelper.setPreferenceCopyLayoutMode(false);
    
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    checkFC3toFC4(context);
    checkSABtoLAB(context);

    // Copy Functional Chain involvements to 2 different FunctionalChainRef referencing the same FC
    checkFC1toFC2(context);

    // Copy from FC Involvements from SDFB to FC with 2 FCRef using the same FC
    checkSDFB1toFC2(context);

    // Copy from SAB to SDFB
    checkSABtoSDFB1(context);
    checkSABtoSDFB2(context);

    // Copy from SAB to FC
    checkSABtoFC1(context);
    checkSABtoFC2(context);

    // Copy from System Function to Logical functions
    checkFC3toLFC3(context);

    // Check copy paste between 2 PPD
    checkPP1toPP2(context);

    // Check copy paste of physicalPath between 2 pab
    checkPAB1toPAB2(context);

    // Test with absolute copy paste
    DiagramHelper.setPreferenceCopyLayoutMode(true);
    
    // Copy FCRef/FC and their involvements to a clone
    checkFC2toFC2Clone(context);

    // Copy from FC3 to copyOfFC3
    checkFC3toCopyFC3(context);

    // Copy FC involvements to a clone
    checkFC3toFC3Clone(context);

    // Copy from FC Involvements from SDFB to FC
    checkSDFB1toFC1(context);
  }

  // A basic diagram with several functions
  protected void checkFC2toFC2Clone(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();
    // Copy FC3, Top Functional Chain and FC4
    copyLayout(context, FC2_SOURCE, FCI_TO_SYSTEMFUNCTION_3, FUNCTIONAL_CHAIN_REFERENCE_TOP_FUNCTIONALCHAIN_1,
        FCI_TO_SYSTEMFUNCTION_4);
    pasteLayout(context, CLONE_FC2_TARGET, FCI_TO_SYSTEMFUNCTION_3, FUNCTIONAL_CHAIN_REFERENCE_TOP_FUNCTIONALCHAIN_1,
        FCI_TO_SYSTEMFUNCTION_4);

    // Copy FC3, Bottom Functional Chain and FC4
    copyLayout(context, FC2_SOURCE, FCI_TO_SYSTEMFUNCTION_3, FUNCTIONAL_CHAIN_REFERENCE_BOTTOM_FUNCTIONALCHAIN_1,
        FCI_TO_SYSTEMFUNCTION_4);
    pasteLayout(context, CLONE_FC2_TARGET, FCI_TO_SYSTEMFUNCTION_3, FUNCTIONAL_CHAIN_REFERENCE_BOTTOM_FUNCTIONALCHAIN_1,
        FCI_TO_SYSTEMFUNCTION_4);

    handler.compare(DiagramContext.getDiagram(context, CLONE_FC2_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC2_FC2_CLONE_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkFC3toCopyFC3(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, FC3_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, COPY_FC3_TARGET);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    target.refreshDiagram();
    handler.compare(DiagramContext.getDiagram(context, COPY_FC3_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC3_COPY_FC3_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkFC3toFC4(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, FC3_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, FC4_TARGET);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    handler.compare(DiagramContext.getDiagram(context, FC4_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC3_FC4_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSABtoLAB(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SAB_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, LAB_TARGET);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    target.refreshDiagram();
    handler.compare(DiagramContext.getDiagram(context, LAB_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SAB_LAB_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkFC1toFC2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, FC1_TARGET1);
    DiagramContext target = DiagramContext.getDiagram(context, FC2_TARGET1);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    handler.compare(DiagramContext.getDiagram(context, FC2_TARGET1).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC1_FC2_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkFC3toFC3Clone(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();
    // Copy left SF5, SF6,SF7
    copyLayout(context, FC3_SOURCE, FCI_TO_SYSTEMFUNCTION_5, FCI_TO_SYSTEMFUNCTION_6, FCI_TO_SYSTEMFUNCTION_7);
    pasteLayout(context, FC3_CLONE_TARGET, FCI_TO_SYSTEMFUNCTION_5, FCI_TO_SYSTEMFUNCTION_6, FCI_TO_SYSTEMFUNCTION_7);

    // Copy SF8, right SF5 and SF9
    copyLayout(context, FC3_SOURCE, FCI_TO_SYSTEMFUNCTION_8, FCI_TO_SYSTEMFUNCTION_52, FCI_TO_SYSTEMFUNCTION_9);
    pasteLayout(context, FC3_CLONE_TARGET, FCI_TO_SYSTEMFUNCTION_8, FCI_TO_SYSTEMFUNCTION_52, FCI_TO_SYSTEMFUNCTION_9);

    handler.compare(DiagramContext.getDiagram(context, FC3_CLONE_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC3_FC3_CLONE_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSDFB1toFC1(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SDFB1_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, FC1_TARGET1);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, FC1_TARGET1).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SDFB1_FC1_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSDFB1toFC2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SDFB1_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, FC2_TARGET2);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, FC2_TARGET2).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SDFB1_FC2_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSABtoSDFB1(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SAB_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, SDFB1_TARGET);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, SDFB1_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SAB_SDFB1_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSABtoSDFB2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SAB_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, SDFB2_TARGET);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();
    handler.compare(DiagramContext.getDiagram(context, SDFB2_TARGET).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SAB_SDFB2_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkFC3toLFC3(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();
    // Copy FC5, Top Functional Chain and FC7
    copyLayout(context, FC3_SOURCE, FCI_TO_SYSTEMFUNCTION_5, FCI_TO_SYSTEMFUNCTION_6, FCI_TO_SYSTEMFUNCTION_7);
    pasteLayout(context, LFC3, LFCI_TO_SYSTEMFUNCTION_5, LFCI_TO_SYSTEMFUNCTION_6, LFCI_TO_SYSTEMFUNCTION_7);

    // Copy FC8, Bottom Functional Chain and FC99
    copyLayout(context, FC3_SOURCE, FCI_TO_SYSTEMFUNCTION_8, FCI_TO_SYSTEMFUNCTION_52, FCI_TO_SYSTEMFUNCTION_9);
    pasteLayout(context, LFC3, LFCI_TO_SYSTEMFUNCTION_8, LFCI_TO_SYSTEMFUNCTION_52, LFCI_TO_SYSTEMFUNCTION_9);

    handler.compare(DiagramContext.getDiagram(context, LFC3).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, FC3_LFC3_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkSABtoFC1(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SAB_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, FC1_TARGET2);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, FC1_TARGET2).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SAB_FC1).getDiagramDescriptor(), false);
  }

  protected void checkSABtoFC2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, SAB_SOURCE);
    DiagramContext target = DiagramContext.getDiagram(context, FC2_TARGET3);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, FC2_TARGET3).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, SAB_FC2).getDiagramDescriptor(), false);
  }

  protected void checkPP1toPP2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, PP1);
    DiagramContext target = DiagramContext.getDiagram(context, PP2);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, PP2).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, PP1_PP2_EXPECTED).getDiagramDescriptor(), false);
  }

  protected void checkPAB1toPAB2(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, PAB1);
    DiagramContext target = DiagramContext.getDiagram(context, PAB2);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());

    // Refresh required to repaint the FC properly
    target.refreshDiagram();

    handler.compare(DiagramContext.getDiagram(context, PAB2).getDiagramDescriptor(),
        DiagramContext.getDiagram(context, PAB1_PAB2_EXPECTED).getDiagramDescriptor(), false);
  }


  public void copyLayout(SessionContext sessionContext, String diagramName, String... ids) {
    DiagramContext sourceContext = DiagramContext.getDiagram(sessionContext, diagramName);
    DDiagram sourceDiagram = sourceContext.getDiagram();
    sourceContext.open();
    Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) sourceDiagram).getTarget());
    IWorkbenchPart part = DiagramHelper.getDiagramEditor(session, sourceDiagram);

    List<EditPart> editPartsToBeSelected = new ArrayList<EditPart>();
    for (String id : ids) {
      DDiagramElement diagramElement = DiagramHelper.getOnDiagramByID(sourceDiagram, id);
      if (diagramElement != null) {
        EditPart editPart = DiagramServices.getDiagramServices().getEditPart(diagramElement);
        if (editPart != null)
          editPartsToBeSelected.add(editPart);
      }
    }
    IStructuredSelection selection = new StructuredSelection(editPartsToBeSelected);
    part.getSite().getSelectionProvider().setSelection(selection);

    CopyFormatAction copyAction = new CopyFormatAction(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), part);
    copyAction.init(); // if we don't do init, action.getWorkbenchPart will be empty, even if we give the part in the
    // constructor. Due to AbstractCopyPasteFormatAction constructor calling the wrong super().
    copyAction.run();
    part.getSite().getSelectionProvider().setSelection(new StructuredSelection());
    GuiActions.flushASyncGuiThread();
  }

  public void pasteLayout(SessionContext sessionContext, String diagramName, String... ids) {
    DiagramContext targetContext = DiagramContext.getDiagram(sessionContext, diagramName);
    DDiagram targetDiagram = targetContext.getDiagram();
    targetContext.open();
    Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) targetDiagram).getTarget());
    IWorkbenchPart part = DiagramHelper.getDiagramEditor(session, targetDiagram);

    List<EditPart> editPartsToBeSelected = new ArrayList<EditPart>();
    for (String id : ids) {
      DDiagramElement diagramElement = DiagramHelper.getOnDiagramByID(targetDiagram, id);
      if (diagramElement != null) {
        EditPart editPart = DiagramServices.getDiagramServices().getEditPart(diagramElement);
        if (editPart != null)
          editPartsToBeSelected.add(editPart);
      }
    }
    IStructuredSelection selection = new StructuredSelection(editPartsToBeSelected);
    part.getSite().getSelectionProvider().setSelection(selection);

    PasteFormatAction pasteAction = new PasteFormatAction(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), part);
    pasteAction.init(); // if we don't do init, action.getWorkbenchPart will be empty, even if we give the part in the
    // constructor. Due to AbstractCopyPasteFormatAction constructor calling the wrong super().
    pasteAction.run();
    part.getSite().getSelectionProvider().setSelection(new StructuredSelection());
    GuiActions.flushASyncGuiThread();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("FC");
  }
}
