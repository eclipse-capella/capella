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
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReconnectEdges extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME);
  }

  private void testOnDiagram(SessionContext context, String diagramKind) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, LA__LOGICAL_SYSTEM);

    String actor1 = idb.createActor();
    String actor2 = idb.createActor();
    String interface1 = idb.createInterface();
    String interface2 = idb.createInterface();
    String interface3 = idb.createInterface();
    String interface4 = idb.createInterface();
    String event1 = idb.createEvent();
    String event2 = idb.createEvent();
    String gen = idb.createGeneralization(interface1, interface2);
    String gen1 = idb.createGeneralization(interface2, interface3);
    String impl = idb.createImplements(actor1, interface1);
    String uses = idb.createUses(actor2, interface2);
    String prov = idb.createProvides(actor1, interface1);
    String req = idb.createRequires(actor2, interface2);
    String transmit = idb.createCommunicationLinkTransmit(actor1, event1);
    String acquire = idb.createCommunicationLinkAcquire(actor2, event2);

    // reconnect edges
    idb.reconnectGeneralizationSource(gen, interface1, interface4);
    idb.reconnectGeneralizationTarget(gen1, interface3, interface1);
    idb.reconnectCommunicationLinkSource(transmit, actor1, actor2);
    idb.reconnectCommunicationLinkTarget(acquire, event2, event1);

    // impossible reconnection
    idb.cannotReconnectGeneralizationSource(gen, interface3, actor1);
    idb.cannotReconnectGeneralizationSource(gen, interface3, event1);
    idb.cannotReconnectGeneralizationTarget(gen1, interface1, actor2);
    idb.cannotReconnectGeneralizationTarget(gen1, interface1, event2);
    idb.cannotReconnectCommunicationLinkSource(transmit, actor1, interface1);
    idb.cannotReconnectCommunicationLinkSource(transmit, actor1, event2);
    idb.cannotReconnectCommunicationLinkTarget(transmit, event1, interface1);
    idb.cannotReconnectCommunicationLinkTarget(transmit, event1, actor2);
    idb.cannotReconnectCommunicationLinkSource(acquire, actor2, interface1);
    idb.cannotReconnectCommunicationLinkSource(acquire, actor2, event2);
    idb.cannotReconnectCommunicationLinkTarget(acquire, event2, interface1);
    idb.cannotReconnectCommunicationLinkTarget(acquire, event2, actor1);

    // create component
    String comp1 = idb.createComponent();
    String comp2 = idb.createComponent();

    String impl2 = idb.createImplements(comp1, interface1);
    String uses2 = idb.createUses(comp2, interface2);
    String prov2 = idb.createProvides(comp1, interface1);
    String prov3 = idb.createProvides(comp2, interface2);
    String req2 = idb.createRequires(comp2, interface2);
    String transmit2 = idb.createCommunicationLinkTransmit(comp1, event1);
    String acquire2 = idb.createCommunicationLinkAcquire(comp2, event2);

    // reconnect edges
    idb.reconnectImplementsTarget(impl2, interface1, interface2);
    idb.reconnectUsesTarget(uses2, interface2, interface1);
    idb.reconnectProvidesTarget(prov2, interface1, interface2);
    idb.reconnectRequiresTarget(req2, interface2, interface1);
    idb.reconnectCommunicationLinkSource(transmit2, comp1, comp2);
    idb.reconnectCommunicationLinkSource(acquire2, comp2, comp1);
    idb.reconnectCommunicationLinkTarget(transmit2, event1, event2);
    idb.reconnectCommunicationLinkTarget(acquire2, event2, event1);

    // impossible reconnection
    idb.cannotReconnectImplementsTarget(impl2, interface2, comp2);
    idb.cannotReconnectImplementsTarget(impl2, interface2, actor2);
    idb.cannotReconnectImplementsTarget(impl2, interface2, event1);
    idb.cannotReconnectImplementsTarget(impl2, interface2, prov2);
    idb.cannotReconnectUsesTarget(uses2, interface1, comp1);
    idb.cannotReconnectUsesTarget(uses2, interface1, actor1);
    idb.cannotReconnectUsesTarget(uses2, interface1, event1);
    idb.cannotReconnectUsesTarget(uses2, interface1, prov2);
  }
}
