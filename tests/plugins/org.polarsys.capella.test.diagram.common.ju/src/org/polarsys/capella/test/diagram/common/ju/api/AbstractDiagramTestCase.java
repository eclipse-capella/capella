/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.api;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

import junit.framework.TestResult;

/**
 *
 */
public abstract class AbstractDiagramTestCase extends BasicTestCase {

  /*
   * (non-Javadoc)
   *
   * @see junit.framework.TestCase#run(junit.framework.TestResult)
   */
  @Override
  public void run(TestResult result) {
    DiagramHelper.setPrefereneRefreshOnOpening(false);
    DiagramHelper.setPreferenceAutoRefresh(false);
    super.run(result);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }
  
  protected void hideDiagramElement(final DDiagramElement diagramElement){
    Session sessionForTestModel = getSessionForTestModel(getRequiredTestModel());
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(sessionForTestModel);
    executionManager.execute(new AbstractReadWriteCommand() {
      
      @Override
      public void run() {
        DiagramServices.getDiagramServices().hide(diagramElement);
      }
    });
  }

  protected abstract String getRequiredTestModel();

}
