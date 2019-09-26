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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LogicalActorTransition extends TopDownTransitionTestCase {
  public static final String LA_2 = "0500ac88-7e78-4a9b-a1e3-91ab6e9d86ad"; //$NON-NLS-1$
  public static final String LOGICAL_FUNCTION_1 = "1fcba955-aa85-46e0-bfa5-ff0f525bafee"; //$NON-NLS-1$
  
  public static final String LOGICAL_COMPONENT_PKG = "44dd3437-48c1-4fd2-8a6e-34164577bd7a"; //$NON-NL
  public static final String LC_1 = "10ead931-83d8-473d-8446-57516b037c93"; //$NON-NL
  
  @Override
  public void performTest() throws Exception {
    performActorTransition(getObjects(LA_2));
    EObject PC = mustBeTransitionedTo(LA_2, PaPackage.Literals.PHYSICAL_COMPONENT);
    EObject PF = mustBeTransitionedTo(LOGICAL_FUNCTION_1, PaPackage.Literals.PHYSICAL_FUNCTION);
    assertTrue(((PhysicalComponent) PC).getAllocatedFunctions().contains(PF));
    
    // Simulate of Logical Actors transition from Activity Explorer. Not related logical components should not be transitioned to Physical Architecture
    ComponentPkg componentPkg = getObject(LOGICAL_COMPONENT_PKG);
    List<Component> actors = ComponentPkgExt.getAllActors(componentPkg);
    
    performActorTransition(actors);
    shouldNotBeTransitioned(LC_1, PaPackage.Literals.PHYSICAL_COMPONENT);
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }
}
