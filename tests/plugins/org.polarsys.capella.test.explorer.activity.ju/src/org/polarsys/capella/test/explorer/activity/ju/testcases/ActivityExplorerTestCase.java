/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class ActivityExplorerTestCase extends EmptyModel {
  Session session;
  SessionContext context;
  CapellaModel model;
  Project project;

  protected abstract ModelElement getTestModelElement();

  protected abstract void preTest();

  protected abstract void doTest();

  protected void initialize() {
    session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    model = getTestModel(getRequiredTestModels().get(0));
    project = model.getProject(session.getTransactionalEditingDomain());
  }

  @Override
  public void test() throws Exception {
    initialize();
    preTest();
    doTest();
  }
}
