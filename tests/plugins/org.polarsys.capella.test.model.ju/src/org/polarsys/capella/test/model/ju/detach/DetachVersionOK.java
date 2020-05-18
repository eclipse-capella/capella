/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.detach;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.AbstractProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;

/**
 * @author Hakim Sellou
 */
public class DetachVersionOK extends BasicTestCase {

  final String modelName = "detach_lib/version_ok";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(modelName);
  }

  @Override
  public void test() throws Exception {

    final IProject model = AbstractProvider.getEclipseProjectForTestModel(modelName, this);
    IFile airdFile = new IFileRequestor().search(model, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);

    try {
      GuiActions.lauchDetachModelAction(airdFile);
    } catch (RuntimeException e) {
      fail("One detach precondition has failed:\n" + e.getMessage());
    }

  }

}
