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
package org.polarsys.capella.test.diagram.tools.ju.csa;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.test.diagram.common.ju.context.CSADiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReconnectGeneralization extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    context.setPreference(IInheritancePreferences.PREFS_ALLOW_COMPONENT_NON_ACTOR_INHERITANCE, true);
    CSADiagram csa = CSADiagram.createDiagram(context, SA__SYSTEM);
    String actor1 = csa.createActor();
    String actor2 = csa.createActor();
    String actor3 = csa.createActor();
    String edgeId = csa.createActorGeneralization(actor2, actor1);
    csa.reconnectActorGeneralizationSource(edgeId, actor1, actor3);
    csa.reconnectActorGeneralizationTarget(edgeId, actor2, actor1);
  }

}
