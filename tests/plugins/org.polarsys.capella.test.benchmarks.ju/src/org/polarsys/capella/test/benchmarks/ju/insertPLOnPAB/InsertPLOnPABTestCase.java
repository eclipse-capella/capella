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
package org.polarsys.capella.test.benchmarks.ju.insertPLOnPAB;

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Insert a physical link between 2 (pre-added) actors on all PABs
 */
public class InsertPLOnPABTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertPLOnPABTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    for (DiagramContext pab : contexts) {
      if (pab instanceof XABDiagram) {
        String PHYSICAL_LINK = "PHYSICAL LINK 1";
        ((XABDiagram) pab).createPhysicalLink(GenericModel.ACTOR_1, GenericModel.ACTOR_2, PHYSICAL_LINK);
      }
    }
  }
}
