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

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class InterfaceCapabilityTestCase extends InterfaceTableTestFramework {


  @Override
  public void test() throws Exception {
    init();
    DTable table = createTable(context, systemAnalysisId, IDiagramNameConstants.INTERFACES_CAPABILITIES_DIAGRAM_NAME);

    /**
     * Hide Line containing Interface1
     * <P>
     * Line containing Interface1 is hidden from the table
     */
    hideLine(context, table, _interface1);

    /**
     * Hide Line containing Interface2
     * <P>
     * Line containing Interface2 is hidden from the table
     */
    hideLine(context, table, _interface2);

    /**
     * Hide Column containing Capability1
     * <P>
     * Column containing Capability1 is hidden from the table
     */
    hideColumn(context, table, _capability1);

    /**
     * Show all the Hidden columns (Cap1)
     * <P>
     * Cap1 will be visible again in the table
     */
    showAllColumns(context, table);

    /**
     * Show all the Hidden Lines
     * <P>
     * Hidden Lines (Interace1 and Interface 2) are shown again
     */
    showAllLines(context, table);
    
  }
}
