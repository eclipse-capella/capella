/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fastlinker.ju.testcases;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Base class for FastLinker test cases.
 */
public abstract class AbstractFastLinkerTest extends BasicTestCase {

  protected Project getTestProject() {
    Resource semanticResource = TestHelper.getSemanticResource(getSession(getRequiredTestModels().get(0)));
    if (semanticResource != null) {
      EObject content = semanticResource.getContents().get(0);
      if (content instanceof Project) {
        return (Project) content;
      }
    }
    return null;
  }
  
  protected void executeCommand(AbstractReadWriteCommand command) {
    TransactionHelper.getExecutionManager(getSession(getRequiredTestModels().get(0))).execute(command);
  }
}
