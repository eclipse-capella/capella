/*******************************************************************************
* Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcases.datalisteners;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class InvolvedStates extends MiscModel {

  @Override
  public void test() {

    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    SkeletonHelper.createRegion(SA__SYSTEM__STATEMACHINE_1, GenericModel.REGION_1, context);
    SkeletonHelper.createRegion(SA__SYSTEM__STATEMACHINE_1, GenericModel.REGION_2, context);
    
    //Create a state into a Region, this region should involves it
    SkeletonHelper.createState(GenericModel.REGION_1, GenericModel.STATE_1, context);
    checkInvolves(GenericModel.REGION_1, GenericModel.STATE_1, context);

    //We move the state into another region, involved states should be modified accordingly
    SkeletonHelper.moveObject(GenericModel.STATE_1, GenericModel.REGION_2, context);
    checkNotInvolves(GenericModel.REGION_1, GenericModel.STATE_1, context);
    checkInvolves(GenericModel.REGION_2, GenericModel.STATE_1, context);
  }

  private void checkInvolves(String containerId, String elementId, SessionContext context) {
    SkeletonHelper.checkReference(containerId, elementId, CapellacommonPackage.Literals.REGION__INVOLVED_STATES, context);
  }
  
  private void checkNotInvolves(String containerId, String elementId, SessionContext context) {
    SkeletonHelper.checkNotReference(containerId, elementId, CapellacommonPackage.Literals.REGION__INVOLVED_STATES, context);
  }

}
