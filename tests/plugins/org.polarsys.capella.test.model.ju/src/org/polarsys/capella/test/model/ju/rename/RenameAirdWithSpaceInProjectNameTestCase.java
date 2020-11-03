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
package org.polarsys.capella.test.model.ju.rename;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class RenameAirdWithSpaceInProjectNameTestCase extends BasicTestCase {

  private static String projectName = "renameTest 1  2";
  private static String fileRenamed = "renameTest12";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectName);
  }
  @Override
  public void test() throws Exception {

    String oldFileName = projectName + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION;

    IFile airdFile = getAirdFileForLoadedModel(projectName);
    if (airdFile.exists()) {

      String newAirdFileName = fileRenamed + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION;
      String newAmfFileName = fileRenamed + "." + CapellaResourceHelper.AFM_FILE_EXTENSION;
      
      GuiActions.renameModelFile(airdFile, newAirdFileName);

      IFile afmFile = IResourceHelpers.getEclipseProjectInWorkspace(projectName).getFile(newAmfFileName);
      assertTrue("The file doesn't exist", afmFile.exists());
      
      IFile fileRenamed = IResourceHelpers.getEclipseProjectInWorkspace(projectName).getFile(newAirdFileName);
      assertTrue("The file doesn't exist", fileRenamed.exists());
      
      Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(fileRenamed), new NullProgressMonitor());
      assertTrue("Session is not loaded with renamed resource", session != null);

      /* rename back the model */
      session.close(new NullProgressMonitor());
      GuiActions.renameModelFile(fileRenamed, oldFileName);

    }

  }

}
