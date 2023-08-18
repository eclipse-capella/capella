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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class InterfaceCapabilityScenarioTestCase extends InterfaceTableTestFramework {


  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, systemAnalysisId,
        IDiagramNameConstants.INTERFACES_CAPABILITIES_AND_SCENARIOS_DIAGRAM_NAME);

    /**
     * Execute Show/Hide Lines with Interfaces 1 & 2 unselected and rest selected
     * <P>
     * Line containing Interface 1 & 2 are hidden from the table
     */
    List<EObject> visibleLines = Arrays.asList(_interface3, _oper3ExItem1);
    List<EObject> hiddenLines = Arrays.asList(_interface1, _interface2, _oper1ExItem1, _flow1ExItem1, _oper2ExItem1);
    hideLines(context, table, visibleLines, hiddenLines);

    /**
     * Execute Show/Hide Columns with Capability1 & 2 unselected and rest selected
     * <P>
     * Column containing Capability1 and Capability 2 are hidden from the table
     */
    List<EObject> visibleColumns = Arrays.asList(_subCapability1, _capability3);
    List<EObject> hiddenColumns = Arrays.asList(_capability1, _capability2);
    hideColumns(context, table, visibleColumns, hiddenColumns);

    /**
     * Show all the Hidden columns
     * <P>
     * Capabilities 1 & 2 will be visible again in the table
     */
    showAllColumns(context, table, Arrays.asList(_capability1, _capability2, _capability3, _subCapability1));

    /**
     * Show all the Hidden Lines
     * <P>
     * Hidden Lines (Interace1 and Interface 2) are shown again
     */
    showAllLines(context, table, Arrays.asList(_interface1, _interface2, _oper1ExItem1, _flow1ExItem1, _oper1ExItem1));

  }
}
