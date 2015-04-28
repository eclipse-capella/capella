/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.rename;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameModelTestSuite extends RenameModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {

    IFile file = getCapellaFileForLoadedModel(getRequiredTestModels().get(0));

    TestHelper.renameModelFile(file, "renamed_" + getRequiredTestModels().get(0));

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    assertTrue("Session is not loaded with renamed resource", session != null);
  }
}
