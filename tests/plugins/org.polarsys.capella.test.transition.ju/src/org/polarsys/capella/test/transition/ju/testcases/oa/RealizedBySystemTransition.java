/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.oa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "Realized By System" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 * Realized By System:
 *     Model Used: OA-SA-Projection
 *     Model is created with the following elements�
 *         1. Create a new "[OEBD] Operational Entity - Operational Entity Breakdown" diagram on Root Operational Entity.
 *         2.  Create Entities Entity1 and Entity2 in the diagram
 *         3.  Create Operational Actors Actor1, Actor2 in the diagram
 *         4.  Create "Contained In" link from Actor1 and Entity2 and Entity1 (Entity1 has two part of entities, Entity2 and Actor1)
 *         5.  Add a new Sub Entity Pkg under Root Operational Entity Pkg
 *         6.  Create Entity SubEntity1 in Sub Entity Pkg
 *         7.  Save the diagram
 * 
 * </pre>
 * <P>
 * 
 */
public class RealizedBySystemTransition extends TopDownTransitionTestCase {

  // Target Objects
  private EntityPkg _rootOEPkg;

  private SystemComponent _system;

  // Entities
  private Entity _entity1;
  private Entity _entity2;
  private Entity _entity3;

  // Operational Activity
  private OperationalActivity _OA3;

  // System Function
  private SystemFunction _sf3;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }
  
  int _allocation;

  public void performTest() throws Exception {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
    // Assign the objects
    _rootOEPkg = (EntityPkg) getObject(ModelOaSa.rootOEPkgId);
    _entity1 = (Entity) getObject(ModelOaSa.entity1Id);
    _entity2 = (Entity) getObject(ModelOaSa.entity2Id);
    _entity3 = (Entity) getObject(ModelOaSa.entity3Id);
    _OA3 = (OperationalActivity) getObject(ModelOaSa.OA3Id);

    _system = (SystemComponent) getObject(ModelOaSa.systemId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Run the projection test "Realized by System" from Entity1
   * 
   * <pre>
   * Expected Result:
   *        1.  Entity1 is added in allocated components of System of System Analysis
   * </pre>
   */

  public void performTest1() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList(_entity1));
    assertTrue(NLS.bind(Messages.WrongRealization, _entity1.getName()),
        (_system.getRealizedComponents().contains(_entity1)));
    _allocation = _system.getOwnedComponentRealizations().size();
  }

  /**
   * Run the projection test "Realized by System" from Entity3
   * 
   * <pre>
   * Expected Result:
   *        1.  Entity3 is added in allocated components of System of System Analysis
   *        2.  OA3 is added in allocated functions of System of System Analysis
   * </pre>
   */
  public void performTest2() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList(_entity3));
    _sf3 = (SystemFunction) ProjectionTestUtils.getAllocatingFunction(_OA3);

    assertTrue(NLS.bind(Messages.WrongRealization, _entity3.getName()),
        (_system.getRealizedComponents().contains(_entity3)));
    assertTrue(NLS.bind(Messages.WrongAllocation, _sf3.getName()),
        (ProjectionTestUtils.getAllocatedFunction(_system) == _sf3));

    int previousResult = (Integer) _allocation;
    _allocation = _system.getOwnedComponentRealizations().size();

    // Check only one more component is allocated
    assertTrue(Messages.WrongAllocation, ((_allocation - previousResult) == 1));

  }

  /**
   * Run the projection test "Realized by System" from Entity2
   * 
   * <pre>
   * Expected Result:
   *        1.  Entity2 is added in allocated components of System of System Analysis
   * </pre>
   */
  public void performTest3() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList(_entity2));
    assertTrue(NLS.bind(Messages.WrongRealization, _entity2.getName()),
        (_system.getRealizedComponents().contains(_entity2)));
    int previousResult = (Integer) _allocation;
    _allocation = _system.getOwnedComponentRealizations().size();

    // Check only one more component is allocated
    assertTrue(Messages.WrongAllocation, ((_allocation - previousResult) == 1));

  }

  /**
   * Run the projection test "Realized by System" from rootOEPkg
   * 
   * <pre>
   * Expected Result:
   *        1.  No new allocated component is added to System
   * </pre>
   */
  public void performTest4() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList(_rootOEPkg));
    int allEntitiesSize = BlockArchitectureExt
        .getAllComponents(BlockArchitectureExt.getRootBlockArchitecture(_rootOEPkg)).size();
    int currentSize = _system.getOwnedComponentRealizations().size();
    // Check no component is allocated newly
    assertTrue(Messages.WrongAllocation, (currentSize == allEntitiesSize));
    _allocation = _system.getOwnedComponentRealizations().size();

  }

  /**
   * Run the projection test "Realized by System" from Entity1 again
   * 
   * <pre>
   * Expected Result:
   *        1.  No new allocated component is added to System
   * </pre>
   */
  public void performTest5() throws Exception {
    performRealizedBySystemTransition(Collections.singletonList(_entity1));
    int previousResult = (Integer) _allocation;
    int currentSize = _system.getOwnedComponentRealizations().size();
    // Check no component is allocated newly
    assertTrue(Messages.WrongAllocation, (currentSize == previousResult));
  }

}
