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
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.Collections;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class CopyPasteComponent extends MiscModel {
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel();
    IScope scope = new ScopeModelWrapper(model);
    LogicalComponent lc1 = (LogicalComponent) IdManager.getInstance().getEObject(LC_1, scope);
    LogicalComponent lc2 = (LogicalComponent) IdManager.getInstance().getEObject(LC_2, scope);
    LogicalComponentPkg lcPkg1 = (LogicalComponentPkg) IdManager.getInstance().getEObject(LOGICALCOMPONENTPKG_1,
        scope);
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    TransactionalEditingDomain ted = session.getTransactionalEditingDomain();

    // Copy paste to another component must create the Component and the Part
    GuiActions.copyAndPaste(ted, Collections.singleton(lc1), lc2);
    assertTrue(lc2.getOwnedLogicalComponents().size() == 1);
    assertTrue(lc2.getContainedParts().size() == 1);
    assertTrue(lc2.getContainedParts().get(0).getAbstractType() == lc2.getOwnedLogicalComponents().get(0));

    // Copy paste to a component package must create the Component and the Part
    int countOfLCBeforeCopyPaste = lcPkg1.getOwnedLogicalComponents().size();
    int countOfPartsBeforeCopyPaste = lcPkg1.getOwnedParts().size();
    GuiActions.copyAndPaste(ted, Collections.singleton(lc1), lcPkg1);
    assertEquals("After Copy Paste, count of components increases by 1", countOfLCBeforeCopyPaste + 1, lcPkg1.getOwnedLogicalComponents().size());
    assertEquals("After Copy Paste, count of parts increases by 1", countOfPartsBeforeCopyPaste + 1, lcPkg1.getOwnedParts().size());
  }
}
