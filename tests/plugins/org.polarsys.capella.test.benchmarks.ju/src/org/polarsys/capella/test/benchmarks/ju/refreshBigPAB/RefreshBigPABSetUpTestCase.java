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
package org.polarsys.capella.test.benchmarks.ju.refreshBigPAB;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractSetUpTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.context.SessionContext;

public class RefreshBigPABSetUpTestCase extends AbstractSetUpTestCase {

  public RefreshBigPABSetUpTestCase(BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
  }

  public String getBigPABName() {
    return "[PAB] Implementation and Behaviour Components";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    String bigPABName = getBigPABName();

    DRepresentation dRepresentation = DiagramHelper.getDRepresentation(session, bigPABName);
    XABDiagram.openDiagram(context, RepresentationHelper.getRepresentationDescriptor(dRepresentation).getName(), Type.PA);
  }

}
