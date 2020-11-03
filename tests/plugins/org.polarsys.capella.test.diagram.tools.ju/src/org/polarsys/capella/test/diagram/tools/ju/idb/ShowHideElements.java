/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideElements extends IDBProject {

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
    interfaces(idb);
    exchangeItem(idb);
    relationships(idb);

    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(idb.getType())) {
      components(idb);
      actors(idb);
    }
  }

  private void components(IDBDiagram idb) {
    idb.insertComponents(SA__SYSTEM);
    String comp = idb.createComponent();
    idb.removeComponents(comp);
    idb.insertComponents(comp);
  }

  private void actors(IDBDiagram idb) {
    String actor = idb.createActor();
    idb.removeActors(actor);
    idb.insertActors(actor);
  }

  private void interfaces(IDBDiagram idb) {
    String interfaceId = idb.createInterface();
    idb.removeInterfaces(interfaceId);
    idb.insertInterfaces(interfaceId);
  }

  private void exchangeItem(IDBDiagram idb) {
    String exItem = idb.createEvent();
    idb.removeExchangeItems(exItem);
    idb.insertExchangeItems(exItem);
  }

  private void relationships(IDBDiagram idb) {
    String comp1 = idb.createComponent();
    String event = idb.createEvent();
    String comm = idb.createCommunicationLinkAcquire(comp1, event);

    DiagramHelper.setSynchronized(idb.getDiagram(), false);
    idb.removeRelationships(comp1, comm);
    idb.insertRelationships(comp1, comm);
    DiagramHelper.setSynchronized(idb.getDiagram(), true);
  }
}
