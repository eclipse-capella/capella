/*******************************************************************************
* Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class SequenceMessageOperation extends MiscModel {

  public static final String RANDOM_NAME_1 = "AAA";
  public static final String RANDOM_NAME_2 = "BBB";
  public static final String RANDOM_NAME_3 = "CCC";

  @Override
  public void test() {

    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    SkeletonHelper.createCapability(SA__CAPABILITIES, GenericModel.CAPABILITY_1, context);
    SkeletonHelper.createScenario(GenericModel.CAPABILITY_1, GenericModel.SCENARIO_1, context);
    
    SkeletonHelper.createInstanceRole(GenericModel.SCENARIO_1, GenericModel.INSTANCE_ROLE_1, context);
    SkeletonHelper.createInstanceRole(GenericModel.SCENARIO_1, GenericModel.INSTANCE_ROLE_2, context);
    SkeletonHelper.createSequenceMessage(GenericModel.SCENARIO_1, GenericModel.OBJECT_1, GenericModel.INSTANCE_ROLE_1,
        GenericModel.INSTANCE_ROLE_2, context);

    // We ensure that FunctionalExchange and Sequence Message are synchronized
    SkeletonHelper.createFunctionalExchange(SA__ROOT_SF, GenericModel.FUNCTIONAL_EXCHANGE_1, context);
    SkeletonHelper.setOperation(GenericModel.OBJECT_1, GenericModel.FUNCTIONAL_EXCHANGE_1, context);
    ensureSynchronizedElements(context, GenericModel.OBJECT_1, GenericModel.FUNCTIONAL_EXCHANGE_1);
    
    // We ensure that ComponentExchange and Sequence Message are synchronized
    SkeletonHelper.createComponentExchange(SA__SYSTEM_COMPONENT_PKG, GenericModel.COMPONENT_EXCHANGE_1, context);
    SkeletonHelper.setOperation(GenericModel.OBJECT_1, GenericModel.COMPONENT_EXCHANGE_1, context);
    ensureSynchronizedElements(context, GenericModel.OBJECT_1, GenericModel.COMPONENT_EXCHANGE_1);
    
    // We ensure that ExchangeItem and Sequence Message are synchronized
    SkeletonHelper.createInterface(SA__INTERFACES, GenericModel.INTERFACE_1, context);
    SkeletonHelper.createExchangeItem(SA__INTERFACES, GenericModel.EXCHANGE_ITEM_1, context);
    SkeletonHelper.createExchangeItemAllocation(GenericModel.OBJECT_2, GenericModel.INTERFACE_1, GenericModel.EXCHANGE_ITEM_1, context);
    SkeletonHelper.setOperation(GenericModel.OBJECT_1, GenericModel.OBJECT_2, context);
    ensureSynchronizedElements(context, GenericModel.OBJECT_1, GenericModel.EXCHANGE_ITEM_1);

  }

  private void ensureSynchronizedElements(SessionContext context, String object1Id, String object2Id) {

    SkeletonHelper.setName(context, RANDOM_NAME_1, object1Id);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_1, object1Id, object2Id);
    SkeletonHelper.setName(context, RANDOM_NAME_2, object2Id);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_2, object1Id, object2Id);

  }

}
