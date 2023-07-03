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
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.Collections;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCutCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.model.ju.model.MiscModel;

/**
 * Test undo after cut and paste on capella model.
 * 
 * @author lfasani
 */
public class CutPasteUndoModelElement extends MiscModel {

  @Override
  public void test() throws Exception {
    //
    // Test data creation.
    //
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    OperationalActivity rootOA = context.getSemanticElement(OA__OPERATIONAL_ACTIVITIES__ROOT_OA);
    OperationalActivity operationalActivity1 = rootOA.getChildrenOperationalActivities().get(0);
    OperationalActivity operationalActivity2 = rootOA.getChildrenOperationalActivities().get(1);

    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();

    assertEquals("bad number of expected elements in root OperationalActivity", 3,
        rootOA.getChildrenOperationalActivities().size());

    // ------------------
    // Cut paste and undo

    // Cut
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);
    CapellaCutCommand capellaCutCommand = new CapellaCutCommand(
        EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"),
        Collections.singleton(operationalActivity1), capellaProjectView.getCommonViewer());
    ted.getCommandStack().execute(capellaCutCommand);
    // Paste
    CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted, operationalActivity2, null,
        CommandParameter.NO_INDEX);
    ted.getCommandStack().execute(capellaPasteCommand);
    // Checks
    assertTrue("operationalActivity1 is expected to in operationalActivity2",
        operationalActivity1.eContainer().equals(operationalActivity2));
    assertEquals("bad number of expected elements in operationalActivity2", 1,
        operationalActivity2.getChildrenOperationalActivities().size());
    assertEquals("bad number of expected elements in root OperationalActivity", 2,
        rootOA.getChildrenOperationalActivities().size());

    // ----------------
    // Undo and check
    ted.getCommandStack().undo();
    assertTrue("operationalActivity1 is expected to in rootOA",
        operationalActivity1.eContainer().equals(rootOA));
    assertEquals("bad number of expected elements in operationalActivity2", 0,
        operationalActivity2.getChildrenOperationalActivities().size());
    assertEquals("bad number of expected elements in root OperationalActivity", 3,
        rootOA.getChildrenOperationalActivities().size());
  }
}
