/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.filters.FilterStep;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMDisplayRegionNameOnEntryExitPointsFilterTest extends EmptyProject {
	public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.createDiagram(context, EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);
    diagram.createState(diagram.getDiagramId(), GenericModel.STATE_1);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_2);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_3);
    
    diagram.createEntryPoint(GenericModel.REGION_2, GenericModel.STATE_1, GenericModel.ENTRY_POINT_1);
    diagram.createExitPoint(GenericModel.REGION_2, GenericModel.STATE_1, GenericModel.EXIT_POINT_1);
    diagram.createEntryPoint(GenericModel.REGION_3, GenericModel.STATE_1, GenericModel.ENTRY_POINT_2);
    diagram.createEntryPoint(GenericModel.REGION_3, GenericModel.STATE_1, GenericModel.ENTRY_POINT_3);
    diagram.createExitPoint(GenericModel.REGION_3, GenericModel.STATE_1, GenericModel.EXIT_POINT_2);
    diagram.createExitPoint(GenericModel.REGION_3, GenericModel.STATE_1, GenericModel.EXIT_POINT_3);
    
    DNode entryPoint2_1 = (DNode) diagram.getView(GenericModel.ENTRY_POINT_1);
    DNode exitPoint2_1 = (DNode) diagram.getView(GenericModel.EXIT_POINT_1);
    DNode entryPoint3_1 = (DNode) diagram.getView(GenericModel.ENTRY_POINT_2);
    DNode entryPoint3_2 = (DNode) diagram.getView(GenericModel.ENTRY_POINT_3);
    DNode exitPoint3_3 = (DNode) diagram.getView(GenericModel.EXIT_POINT_2);
    DNode exitPoint3_4 = (DNode) diagram.getView(GenericModel.EXIT_POINT_3);
    
    // test before
    assertEquals("EntryPoint 1", entryPoint2_1.getName());
    assertEquals("ExitPoint 2", exitPoint2_1.getName());
    assertEquals("EntryPoint 1", entryPoint3_1.getName());
    assertEquals("EntryPoint 2", entryPoint3_2.getName());
    assertEquals("ExitPoint 3", exitPoint3_3.getName());
    assertEquals("ExitPoint 4", exitPoint3_4.getName());
    
    FilterStep filter = new FilterStep(diagram, IMappingNameConstants.DISPLAY_REGION_NAME_ON_ENTRY_EXIT_POINTS);
    filter.activate();
    
    // test after
    assertEquals("EntryPoint 1 (Region 2)", entryPoint2_1.getName());
    assertEquals("ExitPoint 2 (Region 2)", exitPoint2_1.getName());
    assertEquals("EntryPoint 1 (Region 3)", entryPoint3_1.getName());
    assertEquals("EntryPoint 2 (Region 3)", entryPoint3_2.getName());
    assertEquals("ExitPoint 3 (Region 3)", exitPoint3_3.getName());
    assertEquals("ExitPoint 4 (Region 3)", exitPoint3_4.getName());
	}
}