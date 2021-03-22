/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.PA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.diagram.tools.ju.sequence.SequenceTest.SequenceType;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateConstrainedElement extends DiagramToolsModel {

  private SessionContext context;

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    context = new SessionContext(session);

    String[] es_sa_targets = { ES_SA_ARM_TIMER, ES_SA_CANCEL_ARM_TIMER, ES_SA_DURATION };
    String[] is_sa_targets = { IS_SA_ARM_TIMER, IS_SA_CANCEL_TIMER, IS_SA_DURATION };
    String[] fs_sa_targets = { FS_SA_DURATION };
    String[] es_la_targets = { ES_LA_ARM_TIMER, ES_LA_CANCEL_ARM_TIMER, ES_LA_DURATION };
    String[] is_la_targets = { IS_LA_ARM_TIMER, IS_LA_CANCEL_TIMER, IS_LA_DURATION };
    String[] fs_la_targets = { FS_LA_DURATION };
    String[] es_pa_targets = { ES_PA_ARM_TIMER, ES_PA_CANCEL_ARM_TIMER, ES_PA_DURATION };
    String[] is_pa_targets = { IS_PA_ARM_TIMER, IS_PA_CANCEL_ARM_TIMER, IS_PA_DURATION };
    String[] fs_pa_targets = { FS_PA_DURATION };
    String[] cdb_targets = { CDB_UNIT };
    String[] mb_targets = { MB_GENERALIZATION, MB_GENREALIZATION_CAPABILITY };
    
    // test SA 
    test(ES_SA_DIAGRAM, es_sa_targets);
    test(IS_SA_DIAGRAM, is_sa_targets);
    test(FS_SA_DIAGRAM, fs_sa_targets);
    
    // test LA
    test(ES_LA_DIAGRAM, es_la_targets);
    test(IS_LA_DIAGRAM, is_la_targets);
    test(FS_LA_DIAGRAM, fs_la_targets);
    
    // test PA
    test(ES_PA_DIAGRAM, es_pa_targets);
    test(IS_PA_DIAGRAM, is_pa_targets);
    test(FS_PA_DIAGRAM, fs_pa_targets);

    // test CDB
    test(CDB_DIAGRAM, cdb_targets);

    // test MB
    test(MB_DIAGRAM, mb_targets);

  }

  public void test(String diagramName, String[] targets) {
    CommonDiagram diagram = openDiagram(diagramName);
    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    for (int i = 0; i < targets.length; i++) {
      diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, targets[i]);
    }
  }

  protected CommonDiagram openDiagram(String diagramName) {
    return (CommonDiagram) new OpenDiagramStep(context, diagramName) {
      @Override
      public DiagramContext getResult() {
        return new CommonDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }
}
