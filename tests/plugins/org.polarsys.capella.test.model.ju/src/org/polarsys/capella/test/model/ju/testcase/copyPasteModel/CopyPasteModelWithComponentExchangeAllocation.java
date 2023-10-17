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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class CopyPasteModelWithComponentExchangeAllocation extends MiscModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel();
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
    final Project project = model.getProject(ted);

    IScope scope = new ScopeModelWrapper(model);
    PhysicalLink pl1 = (PhysicalLink) IdManager.getInstance().getEObject(PA_PL1, scope);
    // Ensure PL allocates a CE
    assertFalse(pl1.getAllocatedComponentExchanges().isEmpty());

    // Copy the PL
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);
    CapellaCopyToClipboardCommand capellaCopyToClipboardCommand = new CapellaCopyToClipboardCommand(ted,
        Collections.singleton(pl1), capellaProjectView.getCommonViewer());
    ted.getCommandStack().execute(capellaCopyToClipboardCommand);
    // Paste the PL in Structure Pkg
    ComponentPkg structurePkg = ModelQueryHelper.getPhysicalComponentPkg(project);
    List<EObject> contentBeforePaste = new ArrayList<>(structurePkg.eContents());
    CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted, structurePkg, null,
        CommandParameter.NO_INDEX);
    ted.getCommandStack().execute(capellaPasteCommand);

    //
    // Checks
    //
    List<EObject> addedElements = new ArrayList<>(structurePkg.eContents());
    addedElements.removeAll(contentBeforePaste);
    assertTrue("1 additional element (new PL) is expected in Structure package", addedElements.size() == 1);
    PhysicalLink pastedPL = (PhysicalLink) EcoreUtil.getObjectByType(addedElements, CsPackage.Literals.PHYSICAL_LINK);
    assertTrue("Feature Owned Component Exchange Allocation must be empty in copied CE",
        pastedPL.getAllocatedComponentExchanges().isEmpty());
  }

}
