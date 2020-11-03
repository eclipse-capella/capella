/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public class CopyPasteModelElementReferencingLibrary extends BasicTestCase {

  public static String CLASS_TO_COPY = "f16eefef-dcec-44c0-a76f-adff706342d1"; //$NON-NLS-1$
  public static String DATA_PKG = "fb4913df-3213-4515-a331-0a5c90419629"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CopyPasteTestCase_Lib", "CopyPasteTestCase_Project");
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(getRequiredTestModels().get(1));
    IScope scope = new ScopeModelWrapper(model);
    Session projectSession = getSessionForTestModel(getRequiredTestModels().get(1));

    EObject classFromProject = IdManager.getInstance().getEObject(CLASS_TO_COPY, scope);
    DataPkg dataPkg = (DataPkg) IdManager.getInstance().getEObject(DATA_PKG, scope);

    GuiActions.copyAndPaste(projectSession.getTransactionalEditingDomain(), Arrays.asList(classFromProject), dataPkg);

    assertTrue("Class has not been copied", dataPkg.getOwnedClasses().size() == 2);
  }
}
