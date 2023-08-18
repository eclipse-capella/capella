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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * 
 * Expected Result:\
 * - Performing actor transition on Sub LC of LA, it must not transit to Root System
 */
public class LaInnerLC extends TopDownTransitionTestCase {
  
  public static final String LA_2 = "2cbcfd63-714b-4fa5-806d-504dc1b1d70a"; //$NON-NLS-1$
  public static final String LC_1 = "95e6f3ff-b654-4268-a042-5c94084d7a0b"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("LaInnerLC");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(LC_1)));
    EObject result = mustBeTransitioned(LC_1);
    assertTrue(result.eContainer() instanceof Component);
    shouldNotBeTransitioned(LA_2);
  }

}
