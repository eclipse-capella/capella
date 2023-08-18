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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition on StateFragments Expected Result:\ - State Fragments must not cover many instance roles -
 * State Fragments related to State must be duplicated on each covered instance roles - State Fragments related to
 * Functions must be cover instance roles of allocating components of Functions
 */
public class CreateRule_StateFragments extends TopDownTransitionTestCase {
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11 = "fd48fdec-0eb5-4041-b6d4-c0da059cc21f";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__IR111 = "529b7f35-294e-496f-b182-160e25d39a83";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__IR112 = "953af7e0-f5c3-4dba-a5db-8c0b568b3315";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF113 = "332736eb-74fd-4e9e-86e8-2e8b0db861f8";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF114 = "8da7fdf7-a0c3-452b-90bc-471ee4915ada";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF115 = "19d788b0-6f5a-47b9-b33c-ed50ae2e293e";
  public static String STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF116 = "b4d947b7-d195-4b9d-97fc-f41c6e0e11e5";

  public static String STATEFRAGMENT__LA__LC_1 = "4c8e76e8-a1a6-4623-8164-c01f651712d7";
  public static String STATEFRAGMENT__LA__LC_2 = "dce7f44e-5d86-44af-9a58-54408c34d931";
  public static String STATEFRAGMENT__LA__PART_PLC_1 = "c6873b7f-8870-44db-92cf-8dc17026ed9d";
  public static String STATEFRAGMENT__LA__PART_PLC_2 = "a83433ea-dd0d-44bd-a9b4-eb0e01a0871b";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(STATEFRAGMENT__SA__CAPABILITIES__C1__S11)));
    mustBeTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11);

    List<EObject> irSystems = mustBeMultiTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__IR111, 2);
    EObject roleA1 = mustBeMonoTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__IR112);

    EObject roleLC1 = null;
    EObject roleLC2 = null;
    for (EObject ir : irSystems) {
      if ((ir instanceof InstanceRole)
          && ((InstanceRole) ir).getRepresentedInstance().equals(getObject(STATEFRAGMENT__LA__PART_PLC_1))) {
        roleLC1 = ir;
      }
      if ((ir instanceof InstanceRole)
          && ((InstanceRole) ir).getRepresentedInstance().equals(getObject(STATEFRAGMENT__LA__PART_PLC_2))) {
        roleLC2 = ir;
      }
    }

    // Two fragment for state1, one for each instance role
    List<StateFragment> frgStates = (List) mustBeMultiTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF113, 2);
    assertTrue(frgStates.get(0).getStart().getCoveredInstanceRoles().size() == 1);
    assertTrue(frgStates.get(1).getStart().getCoveredInstanceRoles().size() == 1);

    ArrayList<InstanceRole> roles = new ArrayList<InstanceRole>();
    roles.addAll(frgStates.get(0).getStart().getCoveredInstanceRoles());
    roles.addAll(frgStates.get(1).getStart().getCoveredInstanceRoles());

    assertTrue(roles.size() == 2);
    assertTrue(roles.contains(roleLC1));
    assertTrue(roles.contains(roleLC2));

    // One function = one StateFragment on the correct instanceRole
    StateFragment fragmentSF1 = (StateFragment) mustBeMonoTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF114);
    StateFragment fragmentSF2 = (StateFragment) mustBeMonoTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF115);
    StateFragment fragmentSF3 = (StateFragment) mustBeMonoTransitioned(STATEFRAGMENT__SA__CAPABILITIES__C1__S11__SF116);

    // One function = one statefragment covering instancerole allocating the fonction
    mustBeLinkedTo(fragmentSF1.getStart(), roleLC1,
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
    mustNotBeLinkedTo(fragmentSF1.getStart(), roleLC2,
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);

    // One function = one statefragment covering instancerole allocating the fonction
    mustBeLinkedTo(fragmentSF2.getStart(), roleLC2,
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
    mustNotBeLinkedTo(fragmentSF2.getStart(), roleLC1,
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);

    // One function = one statefragment covering instancerole allocating the fonction
    mustBeLinkedTo(fragmentSF3.getStart(), roleA1,
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);

  }

}
