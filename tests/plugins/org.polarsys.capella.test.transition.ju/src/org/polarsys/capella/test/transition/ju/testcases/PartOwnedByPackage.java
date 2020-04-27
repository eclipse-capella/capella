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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class PartOwnedByPackage extends TopDownTransitionTestCase {
  public static final String LOGICALCOMPONENTPKG_1 = "eb48fe97-d64f-4d49-976a-cb24b55adc01"; //$NON-NLS-1$
  public static final String PART_LC_1 = "d502379b-a675-494a-8b02-c2b3d0fe0c71"; //$NON-NLS-1$
  
  
  @Override
  public void performTest() throws Exception {
    // LOGICALCOMPONENTPKG_1 contains the LC_1 part
    performLCtoPCTransition(getObjects(LOGICALCOMPONENTPKG_1));
    mustBeTransitionedAndLinkedToTransitioned(LOGICALCOMPONENTPKG_1, PART_LC_1, CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS);
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }
}
