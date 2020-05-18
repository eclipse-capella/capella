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
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateCommunicationLinkAcquire extends IDBProject {
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
  protected void testOnDiagram(SessionContext context, String diagramKind, String String) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, String);

    String component = idb.createComponent();
    String ei = idb.createEvent();
    idb.createCommunicationLinkAcquire(component, ei, GenericModel.CL_1);

    idb.mustBeInstanceOf(GenericModel.CL_1, CommunicationPackage.Literals.COMMUNICATION_LINK);

    CommunicationLink link = context.getSemanticElement(GenericModel.CL_1);
    assertTrue(link.getKind() == CommunicationLinkKind.RECEIVE);
  }
}
