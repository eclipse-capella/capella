/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.jface.util.Policy;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.transition.system.ui.dialog.ProjectSelectionDialog;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Test that the private field of org.polarsys.capella.core.transition.system.ui.dialog.ProjectSelectionDialog exist and
 * is settable.
 *
 */
public class ProjectSelectionDialogTest extends BasicTestCase {

  private static final String fieldName = "fIsEmpty";

  @Override
  public void test() throws Exception {
    boolean temp = Policy.DEBUG_DIALOG_NO_PARENT;
    Policy.DEBUG_DIALOG_NO_PARENT = true;
    ProjectSelectionDialog dialog = new ProjectSelectionDialog(null, null, null);
    testIsEmptyFieldExistAndSettable(dialog);
    Policy.DEBUG_DIALOG_NO_PARENT = temp;
  }

  private void testIsEmptyFieldExistAndSettable(ProjectSelectionDialog dialog) {
    try {
      //
      Object invisibleFieldValue = ReflectUtil.getInvisibleFieldValue(dialog, fieldName);
      assertNotNull(invisibleFieldValue);
      //
      ReflectUtil.setInvisibleFieldValue(dialog, fieldName, false);
    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
      assertTrue(e.getClass().getSimpleName() + " : " + e.getMessage(), false);
    }
  }
}
