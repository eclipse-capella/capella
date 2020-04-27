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
package org.polarsys.capella.test.benchmarks.ju.insertFEOnLDFB;

import java.util.List;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.helpers.DDiagramHelper;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractSetUpTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class InsertFEOnLDFBSetUpTestCase extends AbstractSetUpTestCase {
  List<DiagramContext> contexts;

  public InsertFEOnLDFBSetUpTestCase(List<DiagramContext> contexts, BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
    this.contexts = contexts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    for (DRepresentation rep : DialectManager.INSTANCE.getAllRepresentations(session)) {
      if (rep instanceof DDiagram && DDiagramHelper.isLDFB((DDiagram) rep)) {
        SessionContext context = new SessionContext(session);
        XDFBDiagram xdfb = new XDFBDiagram(Type.LA, context, (DDiagram) rep);
        xdfb.createFunction(GenericModel.FUNCTION_1);
        xdfb.createFunction(GenericModel.FUNCTION_2);
        contexts.add(xdfb);
      }
    }
  }

}
