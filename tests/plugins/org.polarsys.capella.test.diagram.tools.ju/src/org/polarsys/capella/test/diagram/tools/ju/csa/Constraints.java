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
package org.polarsys.capella.test.diagram.tools.ju.csa;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.CSADiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class Constraints extends EmptyProject {

  private final static String CONSTRAINT_ID = "CONSTRAINT_ID";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    CSADiagram csa = CSADiagram.createDiagram(context, SA__SYSTEM);

    // create constraint
    String constraint_id = csa.createConstraint(CONSTRAINT_ID);

    // insert remove constraint
    csa.removeConstraints(constraint_id);
    csa.insertConstraints(constraint_id);

    // create constraint element
    String actorId = csa.createActor();
    csa.createConstrainedElement(constraint_id, actorId);
  }

}
