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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Expected Result:
 * 
 * - Performing actor transition sub sub component must not propagate a part for sub component
 */
public class Context_Part extends TopDownTransitionTestCase {

  public static final String LC_1 = "fb27585d-494b-42a0-bcd2-07b561218595"; //$NON-NLS-1$
  public static final String LA_1 = "62c60ef0-5b6c-4767-aee3-0d03b515fdc9"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("PartParent");
  }

  @Override
  public void performTest() throws Exception {
    performActorTransition(Arrays.asList(getObject(LA_1)));
    Component physical = mustBeTransitioned(LA_1);
    shouldNotBeTransitioned(LC_1);
    assertTrue(EObjectExt.getAll(BlockArchitectureExt.getRootBlockArchitecture(physical), CsPackage.Literals.PART)
        .size() == 2);
  }

}
