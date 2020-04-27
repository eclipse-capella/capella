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

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Insert a functional exchange between 2 (pre-added) SFs on all LDFB
 */
public class InsertFEOnLDFBTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertFEOnLDFBTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    for (DiagramContext xdfb : contexts) {
      if (xdfb instanceof XDFBDiagram) {
        ((XDFBDiagram) xdfb).createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, GenericModel.FUNCTION_1,
            GenericModel.FUNCTION_2);
      }
    }
  }

}
