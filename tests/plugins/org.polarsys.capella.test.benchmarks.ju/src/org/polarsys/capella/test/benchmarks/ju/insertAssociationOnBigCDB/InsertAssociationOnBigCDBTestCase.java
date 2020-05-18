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
package org.polarsys.capella.test.benchmarks.ju.insertAssociationOnBigCDB;

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

/**
 * 
 * Insert an association between existing classes on a big CDB
 */
public class InsertAssociationOnBigCDBTestCase extends AbstractBenchmarkTestCase {

  List<DiagramContext> contexts;

  public InsertAssociationOnBigCDBTestCase(List<DiagramContext> contexts) {
    this.contexts = contexts;
  }

  public String getSourceClassId() {
    return "a7059561-22f7-4589-aed8-05b33d8600c8";
  }

  public String getTargetClassId() {
    return "b417cf33-5d66-4249-9949-f547f7876a19";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    String ASSOCIATION = "ASSOCIATION 1";
    String CLASS1 = getSourceClassId();
    String CLASS2 = getTargetClassId();
    DiagramContext diagramContext = contexts.get(0);
    if (diagramContext instanceof CDBDiagram) {
      ((CDBDiagram) diagramContext).createAssociation(CLASS1, CLASS2, ASSOCIATION);
    }
  }
}
