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
package org.polarsys.capella.test.model.ju.components;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

/*
 * Covered cases:
 * 1. Actor can contain Components and Actors. Component can contain Components and Actors.
 * 2. Human Component is always a leaf => we can not add sub-components/actors (Operational Actor cannot be decomposed).
 * 3. XAB diagrams can be created on ComponentPkg.
 * 4. XAB diagrams can be created on Components/Actors.
 * 5. Cannot create components/actors on System.
 * 6. Check deployment of components (Node, Behavior).
 * 7. Actors created inside PhysicalComponents should have their parents's nature (Node/Behaviour).
 * 8. Can deploy Components on Human actors.
 */
public abstract class CompositionTestCase extends MiscModel {
  static int count = 0;
  protected final String compositionErrorMessage = "{0} should be contained in {1}";
  protected final String componentTypeMessage = "{0} should be {1}";
  protected final String componentNatureMessage = "{0} should be {1}";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    testOn(context);
  }

  protected abstract void testOn(SessionContext context);

  protected String getComponentName() {
    return "COMPONENT_" + (count++);
  }

  protected void setHuman(SessionContext context, Component component, boolean isHuman) {
    TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        component.setHuman(isHuman);
      }
    });
  }
}
