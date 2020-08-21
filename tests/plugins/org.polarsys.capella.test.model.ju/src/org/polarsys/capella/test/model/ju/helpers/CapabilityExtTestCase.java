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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.test.framework.model.IdentifiableModelProject;
import org.polarsys.capella.test.framework.model.Identifier;

public class CapabilityExtTestCase extends IdentifiableModelProject {

  public @Identifier(id = "3f92dcff-05bb-498b-884e-690833c1f155") Capability CAPABILITY_2;
  public @Identifier(id = "f708c794-265a-4217-b506-63b0f4dade5f") Capability CAPABILITY_3;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("capabilityExt");
  }

  @Override
  public void test() throws Exception {
    noException();
    noNullElement();
  }

  private void noException() {
    try {
      CapabilityExt.getInvolvedSystemComponents(CAPABILITY_2, true);
    } catch (Exception e) {
      assertFalse("No exception on getInvolvedSystemComponents(recurse=true)", true);
    }
  }

  private void noNullElement() {
    assertTrue("Null elements shall be filtered from getInvolvedSystemComponents",
        !CapabilityExt.getInvolvedSystemComponents(CAPABILITY_3, true).contains(null));
  }

  @Override
  protected Class<?> getModelClass() {
    return CapabilityExtTestCase.class;
  }

}
