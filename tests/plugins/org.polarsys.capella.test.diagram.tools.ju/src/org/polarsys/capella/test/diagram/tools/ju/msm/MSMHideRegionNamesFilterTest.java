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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.filters.FilterStep;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMHideRegionNamesFilterTest extends EmptyProject {
	public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.createDiagram(context, EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);
    diagram.createState(diagram.getDiagramId(), GenericModel.STATE_1);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_1);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_2);
    
    diagram.createState(GenericModel.REGION_1, GenericModel.STATE_2);
    diagram.createRegion(GenericModel.STATE_2, GenericModel.REGION_3);
    diagram.createRegion(GenericModel.STATE_2, GenericModel.REGION_4);
    
    State state1 = (State) diagram.getSessionContext().getSemanticElement(GenericModel.STATE_1);
    EList<Region> regions1 = state1.getOwnedRegions();
    assertEquals("State 1 must have 3 regions", 3, regions1.size());
    DDiagramElement region1_1 = diagram.getView(regions1.get(0));
    DDiagramElement region1_2 = diagram.getView(regions1.get(1));
    DDiagramElement region1_3 = diagram.getView(regions1.get(2));
    
    State state2 = (State) diagram.getSessionContext().getSemanticElement(GenericModel.STATE_1);
    EList<Region> regions2 = state2.getOwnedRegions();
    assertEquals("State 2 must have 3 regions", 3, regions2.size());
    DDiagramElement region2_1 = diagram.getView(regions2.get(0));
    DDiagramElement region2_2 = diagram.getView(regions2.get(1));
    DDiagramElement region2_3 = diagram.getView(regions2.get(2));
    
    // test before
    assertEquals(" [Region 3]", region1_1.getName());
    assertEquals(" [Region 2]", region1_2.getName());
    assertEquals(" [region]", region1_3.getName());
    assertEquals(" [Region 3]", region2_1.getName());
    assertEquals(" [Region 2]", region2_2.getName());
    assertEquals(" [region]", region2_3.getName());
    
    FilterStep filter = new FilterStep(diagram, IMappingNameConstants.HIDE_REGION_NAMES);
    filter.activate();
    
    // test after
    assertEquals("", region1_1.getName());
    assertEquals("", region1_2.getName());
    assertEquals("", region1_3.getName());
    assertEquals("", region2_1.getName());
    assertEquals("", region2_2.getName());
    assertEquals("", region2_3.getName());
	}
}