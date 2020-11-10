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

public class ComponentPartPkgDndOA extends AbstractDndTest {

  public static final String OA_4_PART = "f997b4b9-0ad2-4c9e-887f-d2da86d055c5"; //$NON-NLS-1$
  public static final String OA_7_PART = "2c73418d-ad76-4a71-ad48-40d8aa4c2eb3"; //$NON-NLS-1$

  public static final String OE_1 = "d8762b5d-371e-4727-ad2d-48540557bd20"; //$NON-NLS-1$
  public static final String OE_3 = "f558dd83-a095-451f-9e51-712908a12ad2"; //$NON-NLS-1$
  public static final String OA_4 = "a1ccb792-9b7c-42a8-b102-c764032ef94b"; //$NON-NLS-1$
  public static final String OE_5 = "8d94a00b-76ed-40e4-9e29-232e638e4651"; //$NON-NLS-1$
  public static final String OE_6 = "a21934e0-61c7-4e80-8d84-19affaf83fb1"; //$NON-NLS-1$
  public static final String OA_7 = "7d682a92-b682-4712-9906-c37cd15bd644"; //$NON-NLS-1$

  public static final String OPERATIONAL_ENTITIES_PKG = "7d1d584a-73cb-4914-aee8-7a83c0a3578b"; //$NON-NLS-1$
  public static final String ENTITY_PKG_1 = "66b7163e-a393-4ae3-b810-2d0252b3fbcb"; //$NON-NLS-1$
  public static final String ENTITY_PKG_2 = "6a2fef86-e638-4e92-93cc-80d0cc885e9e"; //$NON-NLS-1$
  public static final String ENTITY_PKG_3 = "63a0bd63-6c6f-4403-9439-503a7b75730f"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {
    assertPartDnD();
    assertComponentDnD();
    assertComponentPkgDnD();
  }

  private void assertPartDnD() {
    List<String> sourcePart = Arrays.asList(OA_7_PART);

    assertFalse(canDnd(sourcePart, OA_4_PART));

    assertTrue(canDnd(sourcePart, OE_5));
    assertTrue(canDnd(sourcePart, OE_6));
    assertTrue(canDnd(sourcePart, ENTITY_PKG_1));
    assertTrue(canDnd(sourcePart, ENTITY_PKG_2));
  }

  private void assertComponentDnD() {
    List<String> sourceComponent = Arrays.asList(OE_5);

    assertFalse(canDnd(sourceComponent, OA_4_PART));
    assertFalse(canDnd(sourceComponent, OA_7_PART));

    assertFalse(canDnd(sourceComponent, OA_4));
    assertFalse(canDnd(sourceComponent, OA_7));

    // cycle detection
    assertFalse(canDnd(sourceComponent, OE_6));

    assertTrue(canDnd(sourceComponent, OPERATIONAL_ENTITIES_PKG));
    assertTrue(canDnd(sourceComponent, ENTITY_PKG_1));
    assertTrue(canDnd(sourceComponent, ENTITY_PKG_2));
    assertTrue(canDnd(sourceComponent, OE_1));
    assertTrue(canDnd(sourceComponent, OE_3));
  }

  private void assertComponentPkgDnD() {
    List<String> sourceComponentPkg = Arrays.asList(ENTITY_PKG_1);

    // cycle detection
    assertFalse(canDnd(sourceComponentPkg, ENTITY_PKG_2));

    assertTrue(canDnd(sourceComponentPkg, ENTITY_PKG_3));
  }

}
