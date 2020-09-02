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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.lang.reflect.Field;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.sirius.analysis.constants.ColorNameConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ColorNameConstantsTest extends BasicTestCase {
  @Override
  public void test() throws Exception {
    for (Field f : ColorNameConstants.class.getFields()) {
      if (String.class.equals(f.getType())) {
        String value = (String) f.get(ColorNameConstants.class);
        assertNotNull("Color shall not be null", value);
        assertNotNull(NLS.bind("Color {0} doesn't exist", f.getName()), ColorNameConstants.get(value));
      }
    }
  }
}
