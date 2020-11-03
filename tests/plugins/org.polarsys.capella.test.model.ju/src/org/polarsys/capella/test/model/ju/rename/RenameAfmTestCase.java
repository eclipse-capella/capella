/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameAfmTestCase extends RenameModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {

    String oldFileRootName =getRequiredTestModels().get(0);
    String oldFileName = getRequiredTestModels().get(0)  + "." + CapellaResourceHelper.AFM_FILE_EXTENSION;

    
    IFile file = IResourceHelpers.getEclipseProjectInWorkspace(oldFileRootName).getFile(
        oldFileRootName + "." + CapellaResourceHelper.AFM_FILE_EXTENSION);
    
    if (file.exists()) {
      String NewAfmName = "renamed_" + getRequiredTestModels().get(0) + "." + CapellaResourceHelper.AFM_FILE_EXTENSION;
      
      GuiActions.renameModelFile(file, NewAfmName);
      
      IFile fileRenamed = IResourceHelpers.getEclipseProjectInWorkspace(oldFileRootName).getFile(NewAfmName);
      IFile airdFile = getAirdFileForLoadedModel(oldFileRootName);
      
      
      // Check the content of the aird
      String airdContent = "";
      try {
        airdContent = FileHelper.readFile(airdFile.getFullPath().toString());
      } catch (UnsupportedEncodingException e) {
        // Ignore the error
      }
      
      assertTrue("AIRD content is not updated after AFM rename.", airdContent.contains(NewAfmName));
      
      /* rename back the model */
      GuiActions.renameModelFile(fileRenamed,oldFileName);
    }
    
    //In case of remote model, the capella resource does not exist => Not testable
  }
}
