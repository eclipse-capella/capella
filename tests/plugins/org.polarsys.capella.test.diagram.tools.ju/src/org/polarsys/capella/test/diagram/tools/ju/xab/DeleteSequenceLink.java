/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DeleteSequenceLink extends XABDiagramsProject {

  protected ScopedPreferenceStore preferenceStore;
  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    preferenceStore = (ScopedPreferenceStore) Activator.getDefault().getPreferenceStore();
    enableConfirmDeletion();

    testDeleteSequenceLinksOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA, OA__OAB_SEQUENCELINK1);
    testDeleteSequenceLinksOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, SA__SAB_SEQUENCELINK1);
    testDeleteSequenceLinksOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_SEQUENCELINK1);
    testDeleteSequenceLinksOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, PA__PAB_SEQUENCELINK1);

    testDeleteControlNodeOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA, OA__OAB_CONTROLNODE,
        OA__OAB_SEQUENCELINK1,
        OA__OAB_SEQUENCELINK2);
    testDeleteControlNodeOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, SA__SAB_CONTROLNODE,
        SA__SAB_SEQUENCELINK1,
        SA__SAB_SEQUENCELINK2);
    testDeleteControlNodeOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_CONTROLNODE,
        LA__LAB_SEQUENCELINK1,
        LA__LAB_SEQUENCELINK2);
    testDeleteControlNodeOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, PA__PAB_CONTROLNODE,
        PA__PAB_SEQUENCELINK1, PA__PAB_SEQUENCELINK2);

    disableConfirmDeletion();
  }

  public void testDeleteSequenceLinksOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String... sequenceLinksIds) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    List<DEdge> edgeList = DiagramHelper.getEdges(diagram.getDiagram(), sequenceLinksIds);
    assertTrue("SequenceLink shall be present before deletion", edgeList.size() == sequenceLinksIds.length);

    diagram.deleteSequenceLinks(sequenceLinksIds);
    
    /**
     * Since the hook calls a dryRun, it is useless to test if elements are still present, as they are not deleted
     * edgeList = DiagramHelper.getEdges(diagram.getDiagram(), sequenceLinksIds); assertTrue("SequenceLink is removed
     * from the diagram after a deletion", edgeList.size() == 0);
     */
  }

  public void testDeleteControlNodeOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String controlNodeId, String... expectedDeletedSequenceLinks) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    List<DEdge> edgeList = DiagramHelper.getEdges(diagram.getDiagram(), expectedDeletedSequenceLinks);
    assertTrue("SequenceLinks should be present before delete", edgeList.size() == expectedDeletedSequenceLinks.length);

    diagram.deleteControlNodes(controlNodeId);
    diagram.refreshDiagram();

    /**
     * Since the hook calls a dryRun, it is useless to test if elements are still present, as they are not deleted
     * edgeList = DiagramHelper.getEdges(diagram.getDiagram(), expectedDeletedSequenceLinks); assertTrue("SequenceLink
     * is removed from the diagram after a deletion", edgeList.size() == 0); edgeList =
     * DiagramHelper.getEdges(diagram.getDiagram(), controlNodeId); assertTrue("ControlNode is removed from the diagram
     * after a deletion", edgeList.size() == 0);
     */
  }

  private void disableConfirmDeletion() {
    preferenceStore.putValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, String.valueOf(true));
  }

  private void enableConfirmDeletion() {
    preferenceStore.putValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, String.valueOf(false));
  }
}

