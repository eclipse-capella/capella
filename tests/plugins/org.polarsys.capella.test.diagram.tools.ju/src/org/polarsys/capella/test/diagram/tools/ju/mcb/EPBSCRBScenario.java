/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.MissionDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.filters.FilterStep;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class EPBSCRBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MissionDiagram diagram = MissionDiagram.createDiagram(context, EPBS__CAPABILITIES,
        IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);

    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);
    diagram.createCapability(GenericModel.CAPABILITY_3);
    diagram.createCapability(GenericModel.CAPABILITY_4);

    diagram.createComponent(GenericModel.COMPONENT_1);
    diagram.createComponent(GenericModel.COMPONENT_2, GenericModel.COMPONENT_1);

    diagram.createCapabilityExtends(GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_2, GenericModel.CL_1);
    diagram.createCapabilityIncludes(GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_3, GenericModel.CL_2);
    diagram.createCapabilityGeneralization(GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_4, GenericModel.CL_3);

    diagram.createCapabilityInvolvement(GenericModel.CAPABILITY_1, GenericModel.COMPONENT_2, GenericModel.CL_4);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS).activate(
        GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_2, GenericModel.CAPABILITY_3, GenericModel.CAPABILITY_4);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATIONS).desactivate(
        GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_2, GenericModel.CAPABILITY_3, GenericModel.CAPABILITY_4);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_INVOLVEMENTS).activate(GenericModel.CL_4);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_INVOLVEMENTS).desactivate(GenericModel.CL_4);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILTY_INCLUDES).activate(GenericModel.CL_2);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILTY_INCLUDES).desactivate(GenericModel.CL_2);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_EXTENDS).activate(GenericModel.CL_1);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_EXTENDS).desactivate(GenericModel.CL_1);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS)
        .activate(GenericModel.CL_3);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_CAPABILITY_REALIZATION_GENERALIZATIONS)
        .desactivate(GenericModel.CL_3);

    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_COMPONENTS).activate(GenericModel.COMPONENT_1,
        GenericModel.COMPONENT_2);
    new FilterStep(diagram, IFilterNameConstants.FILTER_CRB_HIDE_COMPONENTS).desactivate(GenericModel.COMPONENT_1,
        GenericModel.COMPONENT_2);

    diagram.removeCapability(GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_2, GenericModel.CAPABILITY_3,
        GenericModel.CAPABILITY_4);
    diagram.insertCapability(GenericModel.CAPABILITY_1, GenericModel.CAPABILITY_2, GenericModel.CAPABILITY_3,
        GenericModel.CAPABILITY_4);

    diagram.removeComponent(GenericModel.COMPONENT_1);
    diagram.insertComponent(GenericModel.COMPONENT_1);
    diagram.insertComponent(GenericModel.COMPONENT_2, GenericModel.COMPONENT_1);

    diagram.removeCapability(GenericModel.CAPABILITY_2);
    diagram.removeCapability(GenericModel.CAPABILITY_3);
    diagram.removeCapability(GenericModel.CAPABILITY_4);
    diagram.removeComponent(GenericModel.COMPONENT_1);

    diagram.insertAllRelationships(GenericModel.CAPABILITY_1);

    diagram.hasView(GenericModel.CAPABILITY_1);
    diagram.hasView(GenericModel.CAPABILITY_2);
    diagram.hasView(GenericModel.CAPABILITY_3);
    diagram.hasView(GenericModel.CAPABILITY_4);
    diagram.hasView(GenericModel.COMPONENT_2);
  }

}