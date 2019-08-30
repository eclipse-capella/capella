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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateGeneralization extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);

    testInterfaces(idb);
    testComponents(idb);
  }

  /**
   * Test basic preference generalization between components
   */
  private void testComponents(IDBDiagram idb) {

    SessionContext sessionContext = idb.getSessionContext();

    idb.createComponent(GenericModel.LC_1);
    idb.createComponent(GenericModel.LC_2);
    idb.createComponent(GenericModel.LC_3);

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE, false);
    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, false);
    idb.createGeneralizationNotEnabled(GenericModel.LC_1, GenericModel.LC_2);

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE, true);
    idb.createGeneralization(GenericModel.LC_2, GenericModel.LC_1);
    idb.createGeneralizationNotEnabled(GenericModel.LC_1, GenericModel.LC_2);
    idb.createGeneralizationNotEnabled(GenericModel.LC_1, GenericModel.LC_3);

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);
    idb.createGeneralization(GenericModel.LC_3, GenericModel.LC_1);
    idb.createGeneralizationNotEnabled(GenericModel.LC_1, GenericModel.LC_3);

  }

  /**
   * Test basic cycle generalization between interfaces
   */
  private void testInterfaces(IDBDiagram idb) {

    idb.createInterface(GenericModel.INTERFACE_1);
    idb.createInterface(GenericModel.INTERFACE_2);
    idb.createInterface(GenericModel.INTERFACE_3);

    idb.createGeneralization(GenericModel.INTERFACE_2, GenericModel.INTERFACE_1);
    idb.createGeneralization(GenericModel.INTERFACE_3, GenericModel.INTERFACE_2);

    idb.createGeneralizationNotEnabled(GenericModel.INTERFACE_3, GenericModel.INTERFACE_1);
    idb.createGeneralizationNotEnabled(GenericModel.INTERFACE_1, GenericModel.INTERFACE_2);
    idb.createGeneralizationNotEnabled(GenericModel.INTERFACE_2, GenericModel.INTERFACE_3);

  }
}
