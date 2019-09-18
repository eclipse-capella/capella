/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LogicalActorTransition extends TopDownTransitionTestCase {
  public static final String LA_2 = "0500ac88-7e78-4a9b-a1e3-91ab6e9d86ad"; //$NON-NLS-1$
  public static final String LOGICAL_FUNCTION_1 = "1fcba955-aa85-46e0-bfa5-ff0f525bafee"; //$NON-NLS-1$
  
  @Override
  public void performTest() throws Exception {
    performActorTransition(getObjects(LA_2));
    EObject PC = mustBeTransitionedTo(LA_2, PaPackage.Literals.PHYSICAL_COMPONENT);
    EObject PF = mustBeTransitionedTo(LOGICAL_FUNCTION_1, PaPackage.Literals.PHYSICAL_FUNCTION);
    assertTrue(((PhysicalComponent) PC).getAllocatedFunctions().contains(PF));
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }
}
