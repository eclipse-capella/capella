/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.commandline.ju.testcases;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplicationContext;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineMode;
import org.polarsys.capella.core.sirius.ui.commandline.RefreshAirdCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineRefreshAirdTest extends BasicTestCase {

  @Override
  public void test()  {
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    
    try {
      StatusValidator removeSomething = new StatusValidator(s -> s.getMessage().contains("representation(s) refreshed"));
      Platform.addLogListener(removeSomething);
      refreshElements(projectName);
      Platform.removeLogListener(removeSomething);
      assertTrue("Refresh representations", removeSomething.isValid());
      
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }

  }

  /**
   * Simulate a call to refresh diagrams command line
   */
  private void refreshElements(String project) throws Exception {

    String[] arguments = { CommandLineConstants.ID,
        "org.polarsys.capella.refreshRepresentations", CommandLineConstants.FILE_PATH, project + "/" + project + ".aird",
        CommandLineConstants.OUTPUTFOLDER, project + "/output", CommandLineConstants.FORCEOUTPUTFOLDERCREATION };
    IApplicationContext mockApplicationContext = new MockApplicationContext(arguments);

    RefreshAirdCommandLine commandLine = new RefreshAirdCommandLine();
    commandLine.parseContext(mockApplicationContext);
    commandLine.setMode(CommandLineMode.NO_IMPORT);

    commandLine.checkArgs(mockApplicationContext);
    commandLine.prepare(mockApplicationContext);
    commandLine.execute(mockApplicationContext);
    
    GuiActions.flushASyncGuiJobs();
  }
  
}
