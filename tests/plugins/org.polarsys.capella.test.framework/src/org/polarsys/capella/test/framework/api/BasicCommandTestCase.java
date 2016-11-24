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
    if (modelResource == null) {
      Session session = getSessionForTestModel(getRequiredTestModels().get(0));
      for (Resource resource : session.getSemanticResources()) {
        // Exclude AFM's Metadata resource
        if (resource.getContents().get(0) instanceof Project) {
          modelResource = resource;
        }
      }
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
    return (T) getModelResource().getEObject(id);
  }

  protected Collection<EObject> getObjects(String... ids_p) {
    Collection<EObject> objects = new ArrayList<EObject>();
    for (String id : ids_p) {
      objects.add(getObject(id));
    }
    return objects;
  }
}
