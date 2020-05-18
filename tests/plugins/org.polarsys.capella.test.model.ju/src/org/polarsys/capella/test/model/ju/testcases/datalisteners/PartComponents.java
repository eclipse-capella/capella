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
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class PartComponents extends MiscModel {

  public static final String RANDOM_NAME_1 = "AAA";
  public static final String RANDOM_NAME_2 = "BBB";
  public static final String RANDOM_NAME_3 = "CCC";

  @Override
  public void test() {

    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    SkeletonHelper.createActor(SA__ACTORS, GenericModel.ACTOR_1, GenericModel.PART_1, context);

    SkeletonHelper.createCapability(SA__CAPABILITIES, GenericModel.CAPABILITY_1, context);
    SkeletonHelper.createScenario(GenericModel.CAPABILITY_1, GenericModel.SCENARIO_1, context);
    SkeletonHelper.createInstanceRole(GenericModel.SCENARIO_1, GenericModel.INSTANCE_ROLE_1, GenericModel.PART_1,
        context);

    // On Monopart, we ensure that part/type names are synchronized
    setReusableComponents(context, false);

    SkeletonHelper.setName(context, RANDOM_NAME_1, GenericModel.ACTOR_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_1, GenericModel.ACTOR_1, GenericModel.PART_1);

    SkeletonHelper.setName(context, RANDOM_NAME_2, GenericModel.PART_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_2, GenericModel.ACTOR_1, GenericModel.PART_1);

    // On Multipart, we ensure that part/type names are NOT synchronized
    setReusableComponents(context, true);

    SkeletonHelper.setName(context, RANDOM_NAME_1, GenericModel.ACTOR_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_1, GenericModel.ACTOR_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_2, GenericModel.PART_1);

    SkeletonHelper.setName(context, RANDOM_NAME_3, GenericModel.PART_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_1, GenericModel.ACTOR_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_3, GenericModel.PART_1);

    // On Monopart, we ensure that part/instancerole names are synchronized
    setReusableComponents(context, false);

    SkeletonHelper.setName(context, RANDOM_NAME_2, GenericModel.PART_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_2, GenericModel.PART_1, GenericModel.INSTANCE_ROLE_1);

    SkeletonHelper.setName(context, RANDOM_NAME_3, GenericModel.INSTANCE_ROLE_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_3, GenericModel.PART_1, GenericModel.INSTANCE_ROLE_1);

    // On Multipart, we ensure that part/instancerole names are synchronized (same as monopart)
    setReusableComponents(context, true);

    SkeletonHelper.setName(context, RANDOM_NAME_2, GenericModel.PART_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_2, GenericModel.PART_1, GenericModel.INSTANCE_ROLE_1);

    SkeletonHelper.setName(context, RANDOM_NAME_3, GenericModel.INSTANCE_ROLE_1);
    SkeletonHelper.ensureNames(context, RANDOM_NAME_3, GenericModel.PART_1, GenericModel.INSTANCE_ROLE_1);

  }

  private void setReusableComponents(final SessionContext context, final boolean isMultipart) {
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        TestHelper.setReusableComponents(context.getSemanticElement(PROJECT_MISCMODEL), isMultipart);
      }
    });
  }

}
