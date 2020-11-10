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

public class ComponentPartPkgDndSA extends AbstractDndTest {

  public static final String SYSTEM = "a3fd9caf-7c4f-459f-9aba-c9e7363d424d"; //$NON-NLS-1$
  public static final String SYSTEM_PART = "6e70f08c-e7f1-45b1-bc56-eff7696988e1"; //$NON-NLS-1$

  public static final String SA_1 = "f9a633b3-29f1-4d96-9e17-cbc1bd8b1629"; //$NON-NLS-1$
  public static final String SA_2 = "4e3fd345-e34d-4e45-8c17-9ea514fa2c91"; //$NON-NLS-1$
  public static final String SA_3 = "383e3307-59db-4d11-9ef3-475964df6649"; //$NON-NLS-1$
  public static final String SA_4 = "0da00174-73ef-4374-b32e-1c62c9aaec55"; //$NON-NLS-1$
  public static final String SA_5 = "afc4b0a2-5bd8-4b26-b320-7abe9cc91699"; //$NON-NLS-1$

  public static final String SA_1_PART = "e506e390-bc12-4f68-88fc-3a8a660c1ace"; //$NON-NLS-1$
  public static final String SA_2_PART = "7ea98ab0-0f72-481e-923d-0b0a47eab1f3"; //$NON-NLS-1$
  public static final String SA_3_PART = "ca6423e1-c6cd-4e2a-a61c-6435c4e07265"; //$NON-NLS-1$
  public static final String SA_4_PART = "529f0dfd-6ebb-4bd3-82ec-08e074d16f3e"; //$NON-NLS-1$
  public static final String SA_5_PART = "fb586ba2-fa46-4fb6-8cbc-b9528e7f823c"; //$NON-NLS-1$

  public static final String STRUCTURE = "ef28c732-c08a-4a27-a6cc-25427f5318b4"; //$NON-NLS-1$

  public static final String SYSTEM_COMPONENT_PKG_1 = "485a8d73-50ea-447d-89bf-4856995fe982"; //$NON-NLS-1$
  public static final String SYSTEM_COMPONENT_PKG_2 = "ff06bc7b-6d1d-4ad7-a333-1fb1fddb3cc1"; //$NON-NLS-1$
  public static final String SYSTEM_COMPONENT_PKG_3 = "1157e5da-93f6-43e0-babd-86891248fb38"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {
    assertPartDnD();
    assertComponentDnD();
    assertComponentPkgDnD();
  }

  private void assertPartDnD() {
    List<String> sourceActorPart = Arrays.asList(SA_1_PART);

    assertFalse(canDnd(sourceActorPart, SA_2_PART));
    assertFalse(canDnd(sourceActorPart, SA_4_PART));

    // cycle detection
    assertFalse(canDnd(sourceActorPart, SA_2));
    assertFalse(canDnd(sourceActorPart, SA_3));
    assertFalse(canDnd(sourceActorPart, SYSTEM_COMPONENT_PKG_3));

    assertTrue(canDnd(sourceActorPart, SYSTEM_COMPONENT_PKG_1));
    assertTrue(canDnd(sourceActorPart, SYSTEM_COMPONENT_PKG_2));
    assertTrue(canDnd(sourceActorPart, SA_4));
    assertTrue(canDnd(sourceActorPart, SA_5));

    // the system part can not be moved anywhere
    List<String> sourceSystemPart = Arrays.asList(SYSTEM_PART);
    assertFalse(canDnd(sourceSystemPart, SA_2_PART));
    assertFalse(canDnd(sourceSystemPart, SA_4_PART));

    assertFalse(canDnd(sourceSystemPart, SA_2));
    assertFalse(canDnd(sourceSystemPart, SA_3));
    assertFalse(canDnd(sourceSystemPart, SYSTEM_COMPONENT_PKG_3));

    assertFalse(canDnd(sourceSystemPart, SYSTEM_COMPONENT_PKG_1));
    assertFalse(canDnd(sourceSystemPart, SYSTEM_COMPONENT_PKG_2));
    assertFalse(canDnd(sourceSystemPart, SA_4));
    assertFalse(canDnd(sourceSystemPart, SA_5));
  }

  private void assertComponentDnD() {
    List<String> sourceActorComponent = Arrays.asList(SA_2);

    assertFalse(canDnd(sourceActorComponent, SA_3_PART));
    assertFalse(canDnd(sourceActorComponent, SA_4_PART));

    // cycle detection
    assertFalse(canDnd(sourceActorComponent, SA_3));
    assertFalse(canDnd(sourceActorComponent, SYSTEM_COMPONENT_PKG_3));

    assertTrue(canDnd(sourceActorComponent, SA_4));
    assertTrue(canDnd(sourceActorComponent, SA_5));
    assertTrue(canDnd(sourceActorComponent, STRUCTURE));
    assertTrue(canDnd(sourceActorComponent, SYSTEM_COMPONENT_PKG_1));
    assertTrue(canDnd(sourceActorComponent, SYSTEM_COMPONENT_PKG_2));

    // the system can not be moved anywhere
    List<String> sourceSystemPart = Arrays.asList(SYSTEM);

    assertFalse(canDnd(sourceSystemPart, SA_4_PART));
    assertFalse(canDnd(sourceSystemPart, SYSTEM_COMPONENT_PKG_3));
    assertFalse(canDnd(sourceSystemPart, SA_2));
    assertFalse(canDnd(sourceSystemPart, SYSTEM_COMPONENT_PKG_1));
    assertFalse(canDnd(sourceSystemPart, SA_3));
    assertFalse(canDnd(sourceSystemPart, SA_5));

  }

  private void assertComponentPkgDnD() {
    List<String> sourcePackage = Arrays.asList(SYSTEM_COMPONENT_PKG_1);

    assertFalse(canDnd(sourcePackage, SA_1_PART));
    assertFalse(canDnd(sourcePackage, SA_2_PART));

    // cycle detection
    assertFalse(canDnd(sourcePackage, SYSTEM_COMPONENT_PKG_2));
    assertFalse(canDnd(sourcePackage, SA_4));
    assertFalse(canDnd(sourcePackage, SA_5));

    assertTrue(canDnd(sourcePackage, SA_1));
    assertTrue(canDnd(sourcePackage, SA_2));
    assertTrue(canDnd(sourcePackage, SA_3));
    assertTrue(canDnd(sourcePackage, SYSTEM_COMPONENT_PKG_3));
  }

}
