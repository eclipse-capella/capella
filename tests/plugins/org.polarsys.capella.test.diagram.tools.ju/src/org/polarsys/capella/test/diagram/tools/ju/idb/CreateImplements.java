/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateImplements extends IDBProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    init(context);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, LA__LOGICAL_SYSTEM);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
  }

  @Override
  protected void testOnDiagram(SessionContext context, String diagramKind, String targetId) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, targetId);

    String component1 = idb.createComponent();
    String interface1 = idb.createInterface();

    String implements1 = idb.createImplements(component1, interface1);

    idb.mustBeInstanceOf(implements1, CsPackage.Literals.INTERFACE_IMPLEMENTATION);

    idb.mustBeLinkedTo(implements1, component1,
        CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR);

    idb.mustBeLinkedTo(implements1, interface1,
        CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);

    idb.createImplementsNotEnabled(component1, interface1);
  }
}
