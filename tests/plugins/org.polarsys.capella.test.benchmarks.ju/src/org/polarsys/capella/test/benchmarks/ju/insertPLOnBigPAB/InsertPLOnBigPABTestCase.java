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
package org.polarsys.capella.test.benchmarks.ju.insertPLOnBigPAB;

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

/**
 * 
 * Insert a Physical Link between existing PFs on a big PAB
 */
public class InsertPLOnBigPABTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertPLOnBigPABTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  public String getSourcePCId() {
    return "b6ba930f-90f5-4805-b54c-f3c48e32b0b0";
  }

  public String getTargetPCId() {
    return "8347cdfc-8694-471e-bcb3-f9966e02ca39";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    String PHYSICAL_LINK = "PHYSICAL LINK 1";
    String PC1 = getSourcePCId();
    String PC2 = getTargetPCId();
    DiagramContext diagramContext = contexts.get(0);
    if (diagramContext instanceof XABDiagram) {
      ((XABDiagram) diagramContext).createPhysicalLink(PC1, PC2, PHYSICAL_LINK);
    }
  }
}
