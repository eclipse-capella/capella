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
package org.polarsys.capella.test.benchmarks.ju.insertAssociationOnCDB;

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Insert an association between 2 (pre-added) classes on all CBD
 */
public class InsertAssociationOnCDBTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertAssociationOnCDBTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    for (DiagramContext cdb : contexts) {
      String ASSOCIATION = "ASSOCIATION 1";
      if (cdb instanceof CDBDiagram) {
        ((CDBDiagram) cdb).createAssociation(GenericModel.CLASS_1, GenericModel.CLASS_2, ASSOCIATION);
      }
    }
  }

}
