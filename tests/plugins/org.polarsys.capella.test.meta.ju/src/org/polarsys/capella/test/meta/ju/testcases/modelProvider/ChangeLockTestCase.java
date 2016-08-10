/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.meta.ju.testcases.modelProvider;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ChangeLocker;

/**
 * Checks that the change lock mechanism provided by the model provider works.<br>
 * 
 * @see ModelProvider
 * @see ChangeLocker
 *
 * @author Erwan Brottier
 */
public class ChangeLockTestCase extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "EmptyProject", "EmptyProject2" });
  }

  @Override
  public void test() throws Exception {
    CapellaModel model1 = getTestModel("EmptyProject");
    final Project project1 = model1.getProject(model1.getEditingDomain());
    AbstractCommand command1 = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        project1.setName("TEST");
      }
    };
    TransactionHelper.getExecutionManager(project1).execute(command1);
    assertTrue(command1.isRolledBack());

    CapellaModel model2 = getTestModel("EmptyProject2");
    final Project project2 = model1.getProject(model2.getEditingDomain());
    AbstractCommand command2 = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        project2.setName("TEST");
      }
    };
    TransactionHelper.getExecutionManager(project2).execute(command2);
    assertTrue(!command2.isRolledBack());
  }

}
