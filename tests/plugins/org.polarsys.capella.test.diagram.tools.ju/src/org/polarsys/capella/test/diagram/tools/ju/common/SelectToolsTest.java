/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.common;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test whether the selection tools are properly selecting expected elements
 */
public class SelectToolsTest extends AbstractDiagramTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System"; //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {

    Session s = getSession(getRequiredTestModel());
    SessionContext sc = new SessionContext(s);

    // For each kind of diagrams from sample project, check if select tools are working.
    HashSet<DiagramDescription> descriptions = new HashSet<DiagramDescription>();
    for (DRepresentation rep : DialectManager.INSTANCE.getAllRepresentations(s)) {
      if (rep instanceof DDiagram) {
        DDiagram diagram = (DDiagram) rep;
        if (!descriptions.contains(diagram.getDescription())) {
          System.err.println(diagram.getDescription().getName());
          descriptions.add(diagram.getDescription());
          testCommonTools(sc, diagram);
        }
      }
    }
  }

  protected void testCommonTools(SessionContext sc, DDiagram rep) {

    // TODO this is a temporary DIRTY FIX and will be removed once filters are migrated

    sc.getSession().getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(sc.getSession().getTransactionalEditingDomain()) {
          @Override
          protected void doExecute() {
            rep.getActivatedFilters().clear();
          }
        });

    CommonDiagram cd = new CommonDiagram(sc, rep);
    DiagramContext dc = cd.open();

    // For each kind of elements displayed in the diagram, check if select tools are working.
    HashSet<DiagramElementMapping> mapping = new HashSet<DiagramElementMapping>();

    for (DDiagramElement de : new ArrayList<DDiagramElement>(dc.getDiagram().getOwnedDiagramElements())) {
      if (DiagramHelper.isDiagramElementSelectable(de)) {
        if (!mapping.contains(de.getDiagramElementMapping())) {
          mapping.add(de.getDiagramElementMapping());
          if (de instanceof DNodeContainer) {
            cd.selectOwnedElements(de.getUid());
          }
          if (de instanceof AbstractDNode) {
            cd.selectRelatedEdges(de.getUid());
          }
          if (de instanceof AbstractDNode) {
            cd.selectOwnedPorts(de.getUid());
          }
          cd.selectSameType(de.getUid());
          cd.selectSameMapping(de.getUid());
        }
      }
    }

    dc.close();
    sc.getSession().getTransactionalEditingDomain().getCommandStack().flush();
    GuiActions.flushASyncGuiJobs();
  }

}
