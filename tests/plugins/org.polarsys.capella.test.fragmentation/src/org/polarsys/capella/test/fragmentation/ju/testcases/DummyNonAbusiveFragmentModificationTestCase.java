/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.testcases;

import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.SLASH_CHARACTER;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.fragmentation.ju.model.FragmentModelTestProject;
import org.polarsys.capella.test.fragmentation.ju.utils.AbstractToolFragmentModifTest;
import org.polarsys.capella.test.fragmentation.ju.utils.FragmentTest;
import org.polarsys.capella.test.fragmentation.ju.utils.UnfragmentTest;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DummyNonAbusiveFragmentModificationTestCase extends FragmentModelTestProject {
  @Override
  public void test() throws Exception {
    Session session = getSession(_currentProjectName);
    SessionContext context = new SessionContext(session);

    init();

    new FragmentTest(context, _SF11Id).run();
    new FragmentTest(context, _SF12Id).run();

    XDFBDiagram diag = XDFBDiagram.openDiagram(context, SA_SDFB_SF121_DiagName,
        BlockArchitectureExt.Type.SA);

    new AbstractToolFragmentModifTest(context,
        new CreateContainerTool(diag, IToolNameConstants.TOOL_SDFB_CREATE_SYSTEM_FUNCTION, diag.getDiagramId())) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(SF12m2);
        set.add(SF12aird);
        return set;
      }
    }.run();

    new AbstractToolFragmentModifTest(context,
        new InsertRemoveTool(diag, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS)) {

      @Override
      protected Set<IFile> getExpectedFilesToBeModified() {
        Set<IFile> set = new HashSet<IFile>();
        set.add(SF12aird);
        return set;
      }

      @Override
      protected void runTest() {
        ((InsertRemoveTool) _command).insert();
      }
    }.run();

    new UnfragmentTest(context, _SF12Id).run();
  }

  protected void init() {

    SF11aird = FileHelper
        .getPlatformFile(_currentProjectName + SLASH_CHARACTER + FragmentationMessages.fragmentsFolder + SLASH_CHARACTER
            + FragmentationMessages.SF11airdName);
    SF11m2 = FileHelper.getPlatformFile(_currentProjectName + SLASH_CHARACTER
        + FragmentationMessages.fragmentsFolder + SLASH_CHARACTER + FragmentationMessages.SF11m2Name);
    SF12aird = FileHelper
        .getPlatformFile(_currentProjectName + SLASH_CHARACTER + FragmentationMessages.fragmentsFolder + SLASH_CHARACTER
            + FragmentationMessages.SF12airdName);
    SF12m2 = FileHelper.getPlatformFile(_currentProjectName + SLASH_CHARACTER
        + FragmentationMessages.fragmentsFolder + SLASH_CHARACTER + FragmentationMessages.SF12m2Name);

  }

}
