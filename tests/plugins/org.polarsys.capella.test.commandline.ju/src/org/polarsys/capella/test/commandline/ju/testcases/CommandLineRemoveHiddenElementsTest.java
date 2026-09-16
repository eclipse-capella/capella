/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.sirius.ui.commandline.RemoveHiddenElementsCommandLine;
import org.polarsys.capella.test.commandline.ju.utils.MockApplicationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;

/**
 * Test simulating a Validation launch from command line.
 */
public class CommandLineRemoveHiddenElementsTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    String projectName = "RefreshRemoveExport";
    File sourceFolder = getFolderInTestModelRepository(projectName);
    ModelProviderHelper.getInstance().importCapellaProject(sourceFolder);

    var removeSomething = removeElements(projectName, 
        new StatusValidator(s -> s.getMessage().contains("diagram(s) updated")));
    assertTrue("Remove hidden elements shall have removed something", removeSomething.isValid());
    
    var removeNothing = removeElements(projectName, 
        new StatusValidator(s -> s.getMessage().contains("Nothing to do")));
    assertTrue("Remove hidden elements shall have removed nothing", removeNothing.isValid());
    
  }

  /**
   * Simulate a call to remove hidden element command line
   */
  private StatusValidator removeElements(String project, StatusValidator validator) throws Exception {
    Platform.addLogListener(validator);
    try {
      MockApplicationContext.execute(new RemoveHiddenElementsCommandLine(), "org.polarsys.capella.removeHiddenElements", 
          CommandLineConstants.INPUT, project + "/" + project + ".aird");
      GuiActions.flushASyncGuiJobs();
    } finally {
      Platform.removeLogListener(validator);
    }
    return validator;
  }
}
