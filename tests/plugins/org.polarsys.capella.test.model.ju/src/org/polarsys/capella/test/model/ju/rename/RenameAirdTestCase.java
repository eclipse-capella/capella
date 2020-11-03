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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.model.ju.model.RenameModel;

public class RenameAirdTestCase extends RenameModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {

    String oldFileRootName =getRequiredTestModels().get(0);
    String oldFileName = getRequiredTestModels().get(0)  + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION;

    
    IFile file = getAirdFileForLoadedModel(oldFileRootName);
    if (file.exists()) {
      String NewAirdName = "renamed_" + oldFileName;
      
      GuiActions.renameModelFile(file, NewAirdName);
      
      IFile fileRenamed = IResourceHelpers.getEclipseProjectInWorkspace(oldFileRootName).getFile(NewAirdName);
      
      Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(fileRenamed), new NullProgressMonitor());
      assertTrue("Session is not loaded with renamed resource", session != null);
      
      /* rename back the model */
      session.close(new NullProgressMonitor());
      GuiActions.renameModelFile(fileRenamed,oldFileName);
    }
   }
}
