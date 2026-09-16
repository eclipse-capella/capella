/*******************************************************************************
 * Copyright (c) 2020, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.commandline.ju.testcases;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.sirius.ui.commandline.RefreshAirdCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;

/**
 * Test simulating a Representations Refresh launch from command line.
 */
public class CommandLineRefreshAirdTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    ModelProviderHelper.getInstance().importCapellaProject(sourceFolder);
    
    StatusValidator refreshSomething = refreshElements(projectName, 
        new StatusValidator(s -> s.getMessage().contains("representation(s) refreshed")));
    assertTrue("Refresh representations", refreshSomething.isValid());
  }

  /**
   * Simulate a call to refresh diagrams command line
   */
  private StatusValidator refreshElements(String project, StatusValidator validator) throws Exception {
    Platform.addLogListener(validator);
    try {
      MockApplicationContext.execute(new RefreshAirdCommandLine(), "org.polarsys.capella.refreshRepresentations", 
          CommandLineConstants.INPUT, project + "/" + project + ".aird");
      GuiActions.flushASyncGuiJobs();
    } finally {
      Platform.removeLogListener(validator);
    }
    return validator;
  }
  

  
  
}
