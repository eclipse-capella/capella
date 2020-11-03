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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class PhysicalComponentExtTestCase extends BasicTestCase {
  public static final String ACTOR_NODE_1 = "729bf141-4075-421f-a3bc-0af4f5e3ff67"; //$NON-NLS-1$
  public static final String ACTOR_NODE_2 = "c491989d-d8fb-447f-b4b8-29ea4190c2b5"; //$NON-NLS-1$
  public static final String PC_NODE_1 = "58375267-b126-4d8e-b1ae-31f1cbddaf9e"; //$NON-NLS-1$
  public static final String PC_NODE_2 = "49b9d739-5ee5-414a-b233-d1b420020f20"; //$NON-NLS-1$
  public static final String PC_BEHAVIOR_2 = "2af366ae-f8a2-4b7f-93d1-6e18fd59ad81"; //$NON-NLS-1$
  public static final String ACTOR_BEHAVIOR_1 = "10ed2342-8332-45ae-801b-43673c6f8c1e"; //$NON-NLS-1$
  public static final String PC_BEHAVIOR_1 = "c7efebbb-b9de-440a-a3af-1583e5cec664"; //$NON-NLS-1$
  public static final String ACTOR_BEHAVIOR_2 = "3eea84a2-f2a3-4efc-93f2-cf44dfc2020b"; //$NON-NLS-1$
  public static final String DEPLOYED_PC_NODE_1 = "579dcdaa-98a1-4c79-a2d9-64adc202319e"; //$NON-NLS-1$
  public static final String DEPLOYED_ACTOR_NODE_1 = "f0eb97d1-919c-453a-909c-f3db749bff04"; //$NON-NLS-1$

  public static String MODEL_NAME = "miscmodel"; //$NON-NLS-1$
  

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }


  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);   
    
    assertFalse("Can not deploy actor node on itself", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope)));
    assertFalse("Can not deploy pc node on itself", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope)));
    assertFalse("Can not deploy pc behavior on itself", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_2, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_2, scope)));
    assertFalse("Can not deploy actor behavior on itself", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope)));
    
    assertFalse("Can not deploy Node on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_1, scope)));
    assertFalse("Can not deploy Node on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_2, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope)));
    
    assertTrue("Can deploy Node on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_2, scope)));
    assertTrue("Can deploy Node on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_2, scope)));
    assertTrue("Can deploy Node on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope)));
    assertTrue("Can deploy Node on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope)));
    
    assertTrue("Can deploy Behavior on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_NODE_1, scope)));
    assertTrue("Can deploy Behavior on Node", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_NODE_1, scope)));
    
    assertTrue("Can deploy Behavior on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_2, scope)));
    assertTrue("Can deploy Behavior on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope)));
    assertTrue("Can deploy Behavior on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_1, scope)));
    assertTrue("Can deploy Behavior on Behavior", PhysicalComponentExt.canDeploy((PhysicalComponent) IdManager.getInstance().getEObject(PC_BEHAVIOR_1, scope), (PhysicalComponent) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_1, scope)));
  }

}
