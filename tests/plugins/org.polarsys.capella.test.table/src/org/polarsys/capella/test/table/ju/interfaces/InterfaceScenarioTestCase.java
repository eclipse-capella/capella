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
package org.polarsys.capella.test.table.ju.interfaces;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.table.ju.utils.TableTestingHelper;

public class InterfaceScenarioTestCase extends InterfaceTableTestFramework {
  private String scenario2DiagName = "[IS] Scenario 2 - Interface Scenario";

  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, capability1Id,
        IToolNameConstants.INTERFACES_SCENARIOS_DIAGRAM_NAME);

    /**
     * Execute Hide Columns with Scenario1
     * <P>
     * Column containing Scenario1 is hidden from the table
     */
    hideColumn(context, table, _scenario1);

    /**
     * Execute Show/Hide Lines with Interfaces 3 & 2 unselected and rest selected
     * <P>
     * Line containing Interface 3 & 2 are hidden from the table
     */
    List<EObject> visibleLines = Arrays.asList(_interface1, _oper1ExItem1, _flow1ExItem1);
    List<EObject> hiddenLines = Arrays.asList(_interface2, _oper2ExItem1, _interface3, _oper3ExItem1);
    hideLines(context, table, visibleLines, hiddenLines);

    /**
     * Show all the Hidden columns
     * <P>
     * Scenario 1 will be visible again in the table
     */
    showAllColumns(context, table, Arrays.asList(_scenario1));

    /**
     * Show all the Hidden Lines
     * <P>
     * Hidden Lines (Interace2 and Interface 3) are shown again
     */
    showAllLines(context, table, Arrays.asList(_interface2, _interface3, _oper1ExItem1, _oper2ExItem1, _flow1ExItem1));

    /**
     * Create a new table "Interfaces - Scenarios" for Capability 2
     * <P>
     * New table "Interfaces - Scenarios" is created for Capability 2
     */
    DTable table2 = createTable(context, capability2Id, IToolNameConstants.INTERFACES_SCENARIOS_DIAGRAM_NAME);

    /**
     * Open the "Interface Scenario" Diagram under Scenario2
     * <P>
     * Open the "Interface Scenario" Diagram under Scenario2
     */
    ISDiagram diagram = ISDiagram.openDiagram(context, scenario2DiagName, BlockArchitectureExt.Type.SA);

    /**
     * Create Actor in the diagram
     * <P>
     * Actor is visible in the diagram; \ Now in the "Interfaces - Scenarios" table, all the interfaces/operations are
     * visible
     */
    diagram.createActor();
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        DialectManager.INSTANCE.refresh(table2, new NullProgressMonitor());
      }
    };
    TestHelper.getExecutionManager(session).execute(cmd);
    TableTestingHelper.assertCheckObjectOnTable(table2, Arrays.asList(_scenario2), true);

  }
}
