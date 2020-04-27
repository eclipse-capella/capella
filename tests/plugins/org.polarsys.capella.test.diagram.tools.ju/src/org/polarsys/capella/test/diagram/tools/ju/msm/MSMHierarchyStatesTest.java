/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.model.preferences.IModeAndStateManagementPreferences;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMHierarchyStatesTest extends EmptyProject {

  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.createDiagram(context,
        EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);

    MSMDiagram.setUnsynchronized(diagram);
    context.setPreference(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED, false);

    diagram.createState(diagram.getDiagramId(), GenericModel.STATE_1);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_1);
    diagram.createRegion(GenericModel.STATE_1, GenericModel.REGION_2);
    diagram.createState(GenericModel.REGION_2, GenericModel.STATE_2);
    diagram.cannotCreateMode(diagram.getDiagramId(), GenericModel.MODE_1);
    diagram.cannotCreateMode(GenericModel.REGION_2, GenericModel.MODE_2);
    diagram.createState(GenericModel.REGION_1, GenericModel.STATE_3);

    diagram.hideStateMode(GenericModel.REGION_2, GenericModel.STATE_2);
    diagram.showStateMode(GenericModel.REGION_2, GenericModel.STATE_2);
  }
}
