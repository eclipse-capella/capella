/*******************************************************************************
* Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools.sequence;

import static org.junit.Assert.fail;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.junit.Assert;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class TimerCreationTool extends CreateDEdgeTool {
  public TimerCreationTool(DiagramContext context, String toolName, String sourceView, String targetView) {
    super(context, toolName, sourceView, targetView);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();
    DDiagram diagram = getDiagramContext().getDiagram();
    DiagramHelper.refreshDiagram(diagram);

    if (_newEdgesElements.size() != 1) {
      fail("New edge expected");
    }

    SequenceMessage sequenceMessage = (SequenceMessage) ((DEdge) _newEdgesElements.iterator().next()).getTarget();
    Assert.assertEquals("TIMER kind expected.", MessageKind.TIMER, sequenceMessage.getKind());
  }
}
