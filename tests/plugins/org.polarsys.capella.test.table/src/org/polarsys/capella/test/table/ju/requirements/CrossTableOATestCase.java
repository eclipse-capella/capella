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
package org.polarsys.capella.test.table.ju.requirements;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class CrossTableOATestCase extends RequirementsTableTestFramework {

  @Override
  public void test() throws Exception {
    init();
    createTable(context, operationalAnalysisId, IDiagramNameConstants.OPERATIONAL_ACTIVITIES_REQUIREMENTS_DIAGRAM_NAME);
    DTable table = openTable(context, crossTableOAName);

    /**
     * Create requirement Trace between Capture Meteorological Data OA and Requirement Requirement Trace is created
     * between Capture Meteorological Data OA and Requirement\ with source element the OA and the target element as the
     * requirement
     */
    createRequirementTrace(table, _captureMetDataOA, _captureMetDataReq);

    /**
     * Create requirement Trace between Publish Meteorological Report OA and Requirement
     * <P>
     * Requirement Trace is created between Publish Meteorological Report OA and Requirement with source element the OA
     * and the target element as the requirement
     */
    createRequirementTrace(table, _publishMetReportOA, _publishMetReportReq);

    /**
     * Create requirement Trace between Build Meteorological Report OA and Requirement
     * <P>
     * Requirement Trace is created between Build Meteorological Report OA and Requirement with source element the OA
     * and the target element as the requirement
     */
    createRequirementTrace(table, _buildMetReportOA, _buildMetReportReq);

    /**
     * Create requirement Trace between Elaborate Meteorological Forecast OA and Requirement
     * <P>
     * Requirement Trace is created between Elaborate Meteorological Forecast OA and Requirement with source element the
     * OA and the target element as the requirement
     */
    createRequirementTrace(table, _elabMetForecastOA, _elabMetForecastReq);

    /**
     * Create requirement Trace between Elaborate Current Situation OA and Requirement
     * <P>
     * Requirement Trace is created between Elaborate Current Situation OA and Requirement with source element the OA
     * and the target element as the requirement
     */
    createRequirementTrace(table, _elabCurrSituationOA, _elabCurrSituationReq);

    /**
     * Create requirement Trace between Access Meteorological Report OA and Requirement
     * <P>
     * Requirement Trace is created between Access Meteorological Report OA and Requirement with source element the OA
     * and the target element as the requirement
     */
    createRequirementTrace(table, _accessMetReportOA, _accessMetReportReq);

    /**
     * Create requirement Trace between Make Meteorological Data Evolve OA and Requirement
     * <P>
     * Requirement Trace is created between Make Meteorological Data Evolve OA and Requirement with source element the
     * OA and the target element as the requirement
     */
    createRequirementTrace(table, _makeMetDataEvolveOA, _makeMetDataEvolveReq);

    /**
     * Delete requirement Trace between Capture Meteorological Data OA and Requirement
     * <P>
     * Requirement Trace is deleted between Capture Meteorological Data OA and Requirement with source element the OA
     * and the target element as the requirement
     */
    deleteRequirementTrace(table, _captureMetDataOA, _captureMetDataReq);

    /**
     * Hide the OA "Publish Meteorological Report" from the table
     * <P>
     * The OA "Publish Meteorological Report" is hidden from the table
     */
    hideLine(context, table, _publishMetReportOA);

    /**
     * Hide the OA "Deliver Meteorological Information" from the table
     * <P>
     * The OA "Deliver Meteorological Information" is hidden from the table as well as all its children OA
     */
    hideLineWithChildren(table, _deliverMetInfoOA);

    /**
     * Hide the Requirement "Capture Meteorological Data" column from the table
     * <P>
     * The Requirement "Capture Meteorological Data" column is hidden from the table
     */
    hideColumn(context, table, _captureMetDataReq);

    /**
     * Execute Show/Hide lines with Elaborate Met Forecast OA and Make Met Data Evolve OA unselected and the rest of the
     * OAs selected
     * <P>
     * Elaborate Met Forecast OA and Make Met Data Evolve OA are hidden and all other lines are shown in the table
     */
    List<EObject> visibleLines = Arrays.asList(_deliverMetInfoOA, _publishMetReportOA, _buildMetReportOA,
        _elabCurrSituationOA, _accessMetReportOA, _captureMetDataOA);
    List<EObject> hiddenLines = Arrays.asList(_elabMetForecastOA, _makeMetDataEvolveOA);
    hideLines(context, table, visibleLines, hiddenLines);

    /**
     * Execute Show/Hide Columns with Capture Met Data, Elaborate Current Situation, and Publish Met Report requirements
     * unselected and the rest of the requirements selected
     * <P>
     * Capture Met Data, Elaborate Current Situation, and Publish Met Report requirement columns are hidden and all
     * other columns are shown in the table
     */
    List<EObject> visibleColumns = Arrays.asList(_buildMetReportReq, _elabMetForecastReq, _accessMetReportReq,
        _makeMetDataEvolveReq);
    List<EObject> hiddenColumns = Arrays.asList(_captureMetDataReq, _elabCurrSituationReq, _publishMetReportReq);
    hideColumns(context, table, visibleColumns, hiddenColumns);

    /**
     * Execute Show All Lines command on the table
     * <P>
     * All Lines in the table become visible
     */
    showAllLines(context, table);

    /**
     * Execute Show All Columns command on the table
     * <P>
     * All Columns in the table become visible
     */
    showAllColumns(context, table);
  }
}
