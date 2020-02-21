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
import org.polarsys.capella.core.sirius.ui.commandline.RemoveHiddenElementsCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineRemoveHiddenElementsTest extends BasicTestCase {

  @Override
  public void test()  {
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    ModelProviderHelper.getInstance().importCapellaProject(projectName, sourceFolder);
    
    try {
      StatusValidator removeSomething = new StatusValidator(s -> s.getMessage().contains("diagram(s) updated"));
      Platform.addLogListener(removeSomething);
      removeElements(projectName);
      Platform.removeLogListener(removeSomething);
      assertTrue("Remove hidden elements shall have removed something", removeSomething.isValid());
      
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }

    try {
      StatusValidator removeNothing = new StatusValidator(s -> s.getMessage().contains("Nothing to do"));
      Platform.addLogListener(removeNothing);
      removeElements(projectName);
      Platform.removeLogListener(removeNothing);
      assertTrue("Remove hidden elements shall have removed nothing", removeNothing.isValid());
      
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }
  }

  /**
   * Simulate a call to remove hidden element command line
   */
  private void removeElements(String project) throws Exception {

    String[] arguments = { CommandLineConstants.ID,
        "org.polarsys.capella.removeHiddenElements", CommandLineConstants.FILE_PATH, project + "/" + project + ".aird",
        CommandLineConstants.OUTPUTFOLDER, project + "/output", CommandLineConstants.FORCEOUTPUTFOLDERCREATION };
    IApplicationContext mockApplicationContext = new MockApplicationContext(arguments);

    RemoveHiddenElementsCommandLine commandLine = new RemoveHiddenElementsCommandLine();
    commandLine.parseContext(mockApplicationContext);
    commandLine.setMode(CommandLineMode.NO_IMPORT);

    commandLine.checkArgs(mockApplicationContext);
    commandLine.prepare(mockApplicationContext);
    commandLine.execute(mockApplicationContext);
  }
}
