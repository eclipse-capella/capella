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
package org.polarsys.capella.test.model.ju.dnd;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class DnDComponentAndPart extends MiscModel {
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel();
    IScope scope = new ScopeModelWrapper(model);
    LogicalComponent lc1 = (LogicalComponent) IdManager.getInstance().getEObject(LC_1, scope);
    LogicalComponent lc2 = (LogicalComponent) IdManager.getInstance().getEObject(LC_2, scope);
    Part lc1Part = (Part) IdManager.getInstance().getEObject(LC_1_PART, scope);
    Part lc2Part = (Part) IdManager.getInstance().getEObject(LC_2_PART, scope);
    LogicalComponentPkg lcPkg1 = (LogicalComponentPkg) IdManager.getInstance().getEObject(LOGICALCOMPONENTPKG_1,
        scope);
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();

    // DnD LC1 to LCPkg1
    assertFalse("Before DND, LC1 is not in LCPkg1", lcPkg1.getOwnedLogicalComponents().contains(lc1));
    assertFalse("Before DND, LC1 part is not in LCPkg1", lcPkg1.getOwnedParts().contains(lc1Part));
    assertTrue(GuiActions.canDnD(lcPkg1, Arrays.asList(lc1)));
    GuiActions.dragAndDrop(ted, lcPkg1, Collections.singleton(lc1));
    assertTrue("After DND, LC1 is in LCPkg1", lcPkg1.getOwnedLogicalComponents().contains(lc1));
    assertTrue("After DND, LC1 part is in LCPkg1", lcPkg1.getOwnedParts().contains(lc1Part));
    
    // DnD LC2_Part to LC1
    assertFalse("Before DND, LC2 is not in LC1", lc1.getOwnedLogicalComponents().contains(lc2));
    assertFalse("Before DND, LC2_Part is not in LC1", lc1.getContainedParts().contains(lc2Part));
    assertTrue(GuiActions.canDnD(lc1, Arrays.asList(lc2Part)));
    GuiActions.dragAndDrop(ted, lc1, Collections.singleton(lc2Part));
    assertTrue("After DND, LC2 is in LC1", lc1.getOwnedLogicalComponents().contains(lc2));
    assertTrue("After DND, LC2_Part is in LC1", lc1.getContainedParts().contains(lc2Part));
    
  }
}
