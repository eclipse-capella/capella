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
package org.polarsys.capella.test.benchmarks.ju.insertCEOnLAB;

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Insert a component exchange between 2 (pre-added) actors on all LAB
 */
public class InsertCEOnLABTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertCEOnLABTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    for (DiagramContext lab : contexts) {
      if (lab instanceof XABDiagram) {
        ((XABDiagram) lab).createComponentExchange(GenericModel.ACTOR_1, GenericModel.ACTOR_2,
            GenericModel.COMPONENT_EXCHANGE_1);
      }
    }
  }

}
