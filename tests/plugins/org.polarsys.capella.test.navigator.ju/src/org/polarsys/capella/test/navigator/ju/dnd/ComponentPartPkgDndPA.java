/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju.dnd;

import java.util.Arrays;
import java.util.List;

public class ComponentPartPkgDndPA extends AbstractDndTest {
  public static final String PHYSICAL_SYSTEM = "d20e6507-8f39-4c75-ad92-f2c2084a861b"; //$NON-NLS-1$

  public static final String PA_1_ONLY_NODE_ACTOR_HIERARCHY = "6a75121a-c467-4ef6-8935-54eae663b19f"; //$NON-NLS-1$
  public static final String PA_2_ONLY_BEHAVIOR_ACTOR_HIERARCHY = "586b1236-5c88-4c94-b279-2b3ac5c04e69"; //$NON-NLS-1$
  public static final String PA_3_MIXED_ACTOR_HIERARCHY = "fc50a334-a49a-45b6-9046-e8290581dc51"; //$NON-NLS-1$

  public static final String PA_1_ONLY_NODE_ACTOR_HIERARCHY_PART = "1232f3b3-784f-460f-a251-4ca24018bf5a"; //$NON-NLS-1$
  public static final String PA_2_ONLY_BEHAVIOR_ACTOR_HIERARCHY_PART = "4d2eb28f-1a2f-4e85-b8b0-c132aceb3870"; //$NON-NLS-1$
  public static final String PA_3_MIXED_ACTOR_HIERARCHY_PART = "d302a6e4-7726-4fa8-b2f0-1d6645a78d40"; //$NON-NLS-1$

  public static final String PC_1_ONLY_BEHAVIOR_HIERARCHY = "b94f62b5-2296-42ab-878d-e2c6580bb2fc"; //$NON-NLS-1$
  public static final String PC_1_ONLY_BEHAVIOR_HIERARCHY_PART = "4a8b2345-52f2-4014-bda5-e815cf4a6d5c"; //$NON-NLS-1$

  public static final String PC_2_ONLY_NODE_HIERARCHY = "ee0283ce-ccfd-4a55-bf26-9f932ec47419"; //$NON-NLS-1$
  public static final String PC_2_ONLY_NODE_HIERARCHY_PART = "033c3d00-3fe8-4b00-908a-0520c6afac48"; //$NON-NLS-1$

  public static final String PHYSICAL_COMPONENT_PKG_NODE_1 = "d63b6d4d-3307-4914-9148-78d218d67457"; //$NON-NLS-1$
  public static final String PHYSICAL_COMPONENT_PKG_BEHAVIOR_2 = "8b343971-3b1a-4f21-932b-9ec63ff3c22e"; //$NON-NLS-1$

  public static final String PC_BEHAVIOR_5 = "1dda76fc-d297-46c6-8bd0-ba1cfdbddcef"; //$NON-NLS-1$
  public static final String PHYSICAL_COMPONENT_PKG_BEHAVIOR_5 = "d141b3cc-f3f3-4775-98dc-cdcc24c784bd"; //$NON-NLS-1$
  public static final String PC_BEHAVIOR_6 = "14b36c1a-b66d-4e88-86b6-fec3ac4c1822"; //$NON-NLS-1$

  public static final String PC_NODE_7 = "949568d5-1a70-4cf6-b458-c956c8e69a18"; //$NON-NLS-1$
  public static final String PHYSICAL_COMPONENT_PKG_NODE_6 = "7e2348a0-fa88-4e24-be04-84c98987650e"; //$NON-NLS-1$
  public static final String PC_NODE_8 = "efe0b8ce-d440-4f33-be73-cc026bd626c0"; //$NON-NLS-1$

  public static final String NODE_PHYSICAL_COMPONENT_PKG = "bcf1977f-d109-4d02-9066-df5b9e45d4cc"; //$NON-NLS-1$
  public static final String BEHAVIOR_PHYSICAL_COMPONENT_PKG = "34962f8f-b8c4-449f-8e9e-51fe5ba8bd1c"; //$NON-NLS-1$
  public static final String MIXED_PHYSICAL_COMPONENT_PKG = "5a19be3b-9bab-44d7-9392-696b4fc2f331"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {
    assertActorsAndTheirPartsDnD();
    assertBehaviorComponentAndTheirPartsDnD();
    assertNodeComponentAndTheirPartsDnD();
    assertComponentPkgDnD();
  }

  /**
   * Actors can be DnD in any Component or Component Pkg. It is more efficient to test a deep hierarchy of actors, since
   * the DnD precondition code will be executed on each of their children. This allows us to test multiple actors with
   * only one DnD invocation.
   *
   */
  private void assertActorsAndTheirPartsDnD() {
    List<String> sources = Arrays.asList(PA_1_ONLY_NODE_ACTOR_HIERARCHY, PA_2_ONLY_BEHAVIOR_ACTOR_HIERARCHY,
        PA_3_MIXED_ACTOR_HIERARCHY, PA_1_ONLY_NODE_ACTOR_HIERARCHY_PART, PA_2_ONLY_BEHAVIOR_ACTOR_HIERARCHY_PART,
        PA_3_MIXED_ACTOR_HIERARCHY_PART);

    List<String> targets = Arrays.asList(PC_1_ONLY_BEHAVIOR_HIERARCHY, PC_2_ONLY_NODE_HIERARCHY,
        PHYSICAL_COMPONENT_PKG_NODE_1);

    for (String source : sources) {
      for (String target : targets) {
        assertTrue(canDnd(Arrays.asList(source), target));
      }
    }
  }

  private void assertBehaviorComponentAndTheirPartsDnD() {
    List<String> sources = Arrays.asList(PC_1_ONLY_BEHAVIOR_HIERARCHY, PC_1_ONLY_BEHAVIOR_HIERARCHY_PART);
    List<String> validTargets = Arrays.asList(PA_2_ONLY_BEHAVIOR_ACTOR_HIERARCHY, PC_BEHAVIOR_5,
        PHYSICAL_COMPONENT_PKG_BEHAVIOR_5, PC_BEHAVIOR_6);

    for (String source : sources) {
      for (String target : validTargets) {
        assertTrue(canDnd(Arrays.asList(source), target));
      }
    }

    List<String> invalidTargets = Arrays.asList(PC_NODE_7, PHYSICAL_COMPONENT_PKG_NODE_6, PC_NODE_8);

    for (String source : sources) {
      for (String invalidTarget : invalidTargets) {
        assertFalse(canDnd(Arrays.asList(source), invalidTarget));
      }
    }
  }

  private void assertNodeComponentAndTheirPartsDnD() {
    List<String> sources = Arrays.asList(PC_2_ONLY_NODE_HIERARCHY, PC_2_ONLY_NODE_HIERARCHY_PART);
    List<String> validTargets = Arrays.asList(PA_1_ONLY_NODE_ACTOR_HIERARCHY, PC_NODE_7, PHYSICAL_COMPONENT_PKG_NODE_6,
        PC_NODE_8);

    for (String source : sources) {
      for (String target : validTargets) {
        boolean canDnd = canDnd(Arrays.asList(source), target);
        if (!canDnd) {
          System.err.println();
        }
        assertTrue(canDnd);
      }
    }

    List<String> invalidTargets = Arrays.asList(PC_BEHAVIOR_5, PHYSICAL_COMPONENT_PKG_BEHAVIOR_5, PC_BEHAVIOR_6);

    for (String source : sources) {
      for (String invalidTarget : invalidTargets) {
        assertFalse(canDnd(Arrays.asList(source), invalidTarget));
      }
    }
  }

  private void assertComponentPkgDnD() {
    List<String> onlyNodesPkg = Arrays.asList(NODE_PHYSICAL_COMPONENT_PKG);

    assertTrue(canDnd(onlyNodesPkg, PC_NODE_7));
    assertTrue(canDnd(onlyNodesPkg, PHYSICAL_COMPONENT_PKG_NODE_6));

    assertFalse(canDnd(onlyNodesPkg, PC_BEHAVIOR_5));
    assertFalse(canDnd(onlyNodesPkg, PHYSICAL_COMPONENT_PKG_BEHAVIOR_5));

    List<String> onlyBeahaviorPkg = Arrays.asList(BEHAVIOR_PHYSICAL_COMPONENT_PKG);
    assertTrue(canDnd(onlyBeahaviorPkg, PC_BEHAVIOR_5));
    assertTrue(canDnd(onlyBeahaviorPkg, PHYSICAL_COMPONENT_PKG_BEHAVIOR_5));

    assertFalse(canDnd(onlyBeahaviorPkg, PC_NODE_7));
    assertFalse(canDnd(onlyBeahaviorPkg, PHYSICAL_COMPONENT_PKG_NODE_6));

    List<String> mixedPkg = Arrays.asList(MIXED_PHYSICAL_COMPONENT_PKG);
    assertFalse(canDnd(mixedPkg, PC_NODE_7));
    assertFalse(canDnd(mixedPkg, PHYSICAL_COMPONENT_PKG_NODE_6));
    assertFalse(canDnd(mixedPkg, PC_BEHAVIOR_5));
    assertFalse(canDnd(mixedPkg, PHYSICAL_COMPONENT_PKG_BEHAVIOR_5));
  }

}
