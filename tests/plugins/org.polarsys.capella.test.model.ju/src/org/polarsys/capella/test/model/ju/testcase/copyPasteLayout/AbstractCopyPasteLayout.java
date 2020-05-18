/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcase.copyPasteLayout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.CapellaDiagramCopyCommand;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.CapellaDiagramPasteCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public abstract class AbstractCopyPasteLayout extends BasicTestCase {

  public static String MODEL_NAME = "copyPasteLayout"; //$NON-NLS-1$

  IScope scope;
  Session session;
  List<EObject> pastedSiriusElements;
  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  protected List<? extends View> getViewsToCopyFrom() {
    return Collections.emptyList();
  }
  
  protected List<? extends View> getViewsToPasteTo() {
    return Collections.emptyList();
  }
  
  protected List<EObject> getCapellaElementsToCopy() {
    return Collections.emptyList();
  }
  
  protected EObject getCapellaSourceContainer() {
    return null;
  }
  
  protected EObject getCapellaTargetContainer() {
    return null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    scope = new ScopeModelWrapper(model);
    session = getSessionForTestModel(getRequiredTestModels().get(0));    
    ExecutionManager executionManager = TestHelper.getExecutionManager(session);
    
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        new CapellaDiagramCopyCommand(getViewsToCopyFrom()).run();
      }
    });
    
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        new CapellaDiagramPasteCommand(getViewsToPasteTo()) {
          protected void setResults(java.util.List<?> cmdResult_p) {
            super.setResults(cmdResult_p);
            pastedSiriusElements = (List<EObject>) cmdResult_p;
          };
          protected void log(org.eclipse.core.runtime.IStatus validityStatus) {
            // Do nothing to avoid the pop-up for invalid paste
          };
        }.run();
      }
    });
  }
}
