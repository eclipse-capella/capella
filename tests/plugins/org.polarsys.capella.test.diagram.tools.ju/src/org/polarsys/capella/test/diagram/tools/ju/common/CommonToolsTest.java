/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CommonDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Tests the common diagram tools on the IFE sample model.
 *
 * For each diagram, for each displayed capella element, apply a generated pv/pvg on it, and then show/hide the pv/pvg
 * with the appropriate tool. Also, for each diagram, drag/drop a generated pv/pvg onto the diagram, and check if the
 * pv/pvg filter is present.
 *
 * Avoid testing the tools more than once on identical diagrams/mappings.
 *
 * TODO constraints
 */
public class CommonToolsTest extends AbstractDiagramTestCase {

  protected SessionContext sc;
  protected Session s;

  protected PropertyValueGroup pvg;
  protected BooleanPropertyValue pv;

  protected Collection<DiagramElementMapping> testedMappings = new HashSet<DiagramElementMapping>();
  protected Collection<DiagramDescription> testedDiagrams = new HashSet<DiagramDescription>();

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System"; //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {

    s = getSession(getRequiredTestModel());
    sc = new SessionContext(s);

    TransactionalEditingDomain ed = s.getTransactionalEditingDomain();
    s.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(ed) {
      @Override
      protected void doExecute() {
        Project proj = (Project) TestHelper.getSemanticResource(s).getContents().get(0);
        pv = CapellacoreFactory.eINSTANCE.createBooleanPropertyValue();
        pvg = CapellacoreFactory.eINSTANCE.createPropertyValueGroup();
        pvg.getOwnedPropertyValues().add(CapellacoreFactory.eINSTANCE.createBooleanPropertyValue());
        proj.getOwnedPropertyValues().add(pv);
        proj.getOwnedPropertyValueGroups().add(pvg);
      }

    });

    assertNotNull(pv);
    assertNotNull(pvg);

    for (DRepresentation rep : DialectManager.INSTANCE.getAllRepresentations(s)) {
      if (rep instanceof DDiagram) {

        System.err.println(EObjectExt.getText(rep));
        testCommonTools((DDiagram) rep);

      }
    }

  }

  private void testCommonTools(DDiagram rep) {

    // TODO this is a temporary DIRTY FIX and will be removed once filters are migrated

    s.getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(s.getTransactionalEditingDomain()) {
          @Override
          protected void doExecute() {
            rep.getActivatedFilters().clear();
          }
        });

    CommonDiagram cd = new CommonDiagram(sc, rep);
    DiagramContext dc = cd.open();
    Session session = dc.getSessionContext().getSession();

    for (DDiagramElement de : new ArrayList<DDiagramElement>(dc.getDiagram().getOwnedDiagramElements())) {

      if (testedMappings.add(de.getDiagramElementMapping())) {
        if (de.getTarget() instanceof CapellaElement) {
          final CapellaElement ce = (CapellaElement) de.getTarget();
          session.getTransactionalEditingDomain().getCommandStack()
              .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                  ce.getAppliedPropertyValueGroups().add(pvg);
                  ce.getAppliedPropertyValues().add(pv);
                }
              });
          String containerId = ce.getId();
          cd.insertPV(pv.getId(), containerId);
          cd.insertPVG(pvg.getId(), containerId);
          cd.refreshDiagram();
          cd.removePVG(pvg.getId(), containerId);
          cd.removePV(pv.getId(), containerId);
        }
      }
    }

    if (testedDiagrams.add(rep.getDescription())) {

      new DragAndDropPvTool(dc, pv, dc.getDiagramId()).run();
      new DragAndDropPvTool(dc, pvg, dc.getDiagramId()).run();

      // just make sure the filter was copy/pasted across all diagrams.
      boolean pvFilterPresent = false;
      List<FilterDescription> filters = rep.getDescription().getFilters();
      for (FilterDescription fd : filters) {
        if (IFilterNameConstants.FILTER_COMMON_HIDE_PV.equals(fd.getName())) {
          pvFilterPresent = true;
          break;
        }
      }
      assertTrue(pvFilterPresent);
    }

  }

}
