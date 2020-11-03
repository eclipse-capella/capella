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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateGeneralization extends IDBProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    init(context);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, LA__LOGICAL_SYSTEM);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME,
        componentContext);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
  }

  @Override
  protected void testOnDiagram(SessionContext context, String diagramKind, String targetId) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, targetId);

    testInterfaces(idb);

    if (!IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(idb.getType())) {
      testComponents(idb);
    }
  }

  /**
   * Test basic preference generalization between components
   */
  private void testComponents(IDBDiagram idb) {

    SessionContext sessionContext = idb.getSessionContext();

    String component1 = idb.createComponent();
    String component2 = idb.createComponent();
    String component3 = idb.createComponent();

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE, false);
    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, false);
    idb.createGeneralizationNotEnabled(component1, component2);

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE, true);
    idb.createGeneralization(component2, component1);
    idb.createGeneralizationNotEnabled(component1, component2);

    sessionContext.setPreference(IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);
    idb.createGeneralization(component3, component1);
    idb.createGeneralizationNotEnabled(component1, component3);

  }

  /**
   * Test basic cycle generalization between interfaces
   */
  private void testInterfaces(IDBDiagram idb) {

    String interface1 = idb.createInterface();
    String interface2 = idb.createInterface();
    String interface3 = idb.createInterface();

    idb.createGeneralization(interface2, interface1);
    idb.createGeneralization(interface3, interface2);

    idb.createGeneralizationNotEnabled(interface3, interface1);
    idb.createGeneralizationNotEnabled(interface1, interface2);
    idb.createGeneralizationNotEnabled(interface2, interface3);

  }
}
