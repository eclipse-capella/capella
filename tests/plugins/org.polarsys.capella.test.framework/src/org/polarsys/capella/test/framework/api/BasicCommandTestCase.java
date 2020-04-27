/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.api;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * This class is a generic test case for tests launching a command.<br>
 * To use it, create a test case that inherits from this class and implement abstract methods, that are<br>
 * (see method documentation for more details) :<br>
 * <br>
 * - performTest()<br>
 * - getRequiredTestModels()<br>
 */
public abstract class BasicCommandTestCase extends BasicTestCase {

  protected Resource modelResource;

  protected SessionContext context;
  
  @Override
  public void test() throws Exception {
    final Exception[] t = new Exception[] { null };
    TransactionHelper.getExecutionManager(getProject()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        try {
          performTest();
        } catch (Exception e) {
          t[0] = e;
        }
      }
    });
    if (t[0] != null) {
      throw t[0];
    }
  }

  /** overrides this method to write the test code */
  public abstract void performTest() throws Exception;

  protected Resource getModelResource() {
    if (context == null) {
      Session session = getSessionForTestModel(getRequiredTestModels().get(0));
      context = new SessionContext(session);
      modelResource = TestHelper.getSemanticResource(session);
    }
    return modelResource;
  }

  protected Project getProject() {
    return (Project) getModelResource().getContents().iterator().next();
  }

  protected void executeCommand(ICommand command) {
    TransactionHelper.getExecutionManager(getProject()).execute(command);
  }

  protected <T extends EObject> T getObject(String id) {
    return (T) context.getSemanticElement(id);
  }

  protected Collection<EObject> getObjects(String... ids_p) {
    Collection<EObject> objects = new ArrayList<EObject>();
    for (String id : ids_p) {
      objects.add(getObject(id));
    }
    return objects;
  }
}
