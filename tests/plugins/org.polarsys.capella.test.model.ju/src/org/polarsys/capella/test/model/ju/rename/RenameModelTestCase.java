/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameModelTestCase extends RenameModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {

    String oldFileRootName =getRequiredTestModels().get(0);
    String oldFileName = getRequiredTestModels().get(0)  + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
    
    IFile file = getCapellaFileForLoadedModel(oldFileRootName);
    if (file.exists()) {
      String newModelName = "renamed_" + oldFileName;
      GuiActions.renameModelFile(file, newModelName);

      Session session = getSessionForTestModel(oldFileRootName);
      assertTrue("Session is not loaded with renamed resource", session != null);
      session.close(new NullProgressMonitor());
      
      // Check the content of the aird
      IFile airdFile = getAirdFileForLoadedModel(oldFileRootName);
      String airdContent = "";
      try {
        airdContent = FileHelper.readFile(airdFile.getFullPath().toString());
      } catch (UnsupportedEncodingException e) {
        // Ignore the error
      }
      
      assertTrue("AIRD content is not updated after model rename.", airdContent.contains(newModelName));
      
      /* rename back the model */
      IFile fileRenamed = IResourceHelpers.getEclipseProjectInWorkspace(oldFileRootName).getFile(newModelName);
      GuiActions.renameModelFile(fileRenamed,oldFileName);
      
    }
    
    //In case of remote model, the capella resource does not exist => Not testable
  }
}
