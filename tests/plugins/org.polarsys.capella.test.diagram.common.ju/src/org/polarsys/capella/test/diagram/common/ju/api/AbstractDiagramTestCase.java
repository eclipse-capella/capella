/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.api;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;

import junit.framework.TestResult;

/**
 *
 */
public abstract class AbstractDiagramTestCase extends NonDirtyTestCase {

  /*
   * (non-Javadoc)
   *
   * @see junit.framework.TestCase#run(junit.framework.TestResult)
   */
  @Override
  public void run(TestResult result) {
    DiagramHelper.setPrefereneRefreshOnOpening(false);
    DiagramHelper.setPreferenceAutoRefresh(false);

    // We will have to change this when we will test sirius migration popup. (https://bugs.eclipse.org/bugs/show_bug.cgi?id=536995)
    SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION, Boolean.FALSE);
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
