/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LogicalActorTransition extends TopDownTransitionTestCase {
  public static final String LA_2 = "0500ac88-7e78-4a9b-a1e3-91ab6e9d86ad"; //$NON-NLS-1$
  public static final String LOGICAL_FUNCTION_1 = "1fcba955-aa85-46e0-bfa5-ff0f525bafee"; //$NON-NLS-1$
  
  public static final String LC_1 = "10ead931-83d8-473d-8446-57516b037c93"; //$NON-NL
  
  public static final String LA_1 = "d233a040-94d0-4c84-8fa3-1568e298c4b4"; //$NON-NLS-1$
  public static final String LA_A = "0500ac88-7e78-4a9b-a1e3-91ab6e9d86ad"; //$NON-NLS-1$
  public static final String LA_B = "9c1dadc3-9a75-40ca-b32f-da6550db2f86"; //$NON-NLS-1$
  public static final String LA_C = "594b89a8-cd03-4cbc-bb80-6b874293781e"; //$NON-NLS-1$
  public static final String LA_D = "ee818e8a-7fe6-47aa-ab6a-13c0a404dd13"; //$NON-NLS-1$
  public static final String LA_E = "6a40ba49-bb00-4d88-bbd9-ffa4aeef98ae"; //$NON-NLS-1$
  public static final String LA_F = "1134db1a-6eb6-4cfb-a088-b7097eb7345c"; //$NON-NLS-1$
  public static final String LCP_1 = "eb48fe97-d64f-4d49-976a-cb24b55adc01"; //$NON-NLS-1$
  public static final String LCP_2 = "83ef3f20-47b4-4b8c-9888-ef01ce16f6ee"; //$NON-NLS-1$
  public static final String LCP_3 = "f113bb9b-b1d0-4f56-9b03-513b529b0975"; //$NON-NLS-1$
  public static final String LC_A = "5bb813b3-44b2-4001-84df-f4f35cc86a3e"; //$NON-NLS-1$
  public static final String LOGICAL_ARCHITECTURE = "1b0dca52-e1e6-4a89-bce0-2324e057fa36"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM = "7949e1d0-760e-4aa9-b322-0d77042c0ec5"; //$NON-NLS-1$
  public static final String STRUCTURE = "44dd3437-48c1-4fd2-8a6e-34164577bd7a"; //$NON-NLS-1$
  
  @Override
  public void performTest() throws Exception {
    performActorTransition(getObjects(LA_2));
    EObject PC = mustBeTransitionedTo(LA_2, PaPackage.Literals.PHYSICAL_COMPONENT);
    EObject PF = mustBeTransitionedTo(LOGICAL_FUNCTION_1, PaPackage.Literals.PHYSICAL_FUNCTION);
    assertTrue(((PhysicalComponent) PC).getAllocatedFunctions().contains(PF));
    
    // Simulate of Logical Actors transition from Activity Explorer. Not related logical components should not be transitioned to Physical Architecture
    ComponentPkg componentPkg = getObject(STRUCTURE);
    List<Component> actors = ComponentPkgExt.getExternalActors(componentPkg);
    assertTrue(actors.stream().map(Component::getId).collect(Collectors.toList()).containsAll(Arrays.asList(LA_A, LA_B, LA_C, LA_D, LA_E, LA_F)));
    assertEquals(actors.size(), 6);
    
    performActorTransition(actors);
    shouldNotBeTransitioned(LC_1, PaPackage.Literals.PHYSICAL_COMPONENT);
    
    assertTrue("Possible to perform actor transition on : Logical Architecture", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LOGICAL_ARCHITECTURE)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(STRUCTURE)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure / Logical Component Package", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LCP_2)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure / Logical Component Package / LA E", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LA_E)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure / Actor", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LA_A)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure / Actor / Actor", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LA_B)));
    assertTrue("Possible to perform actor transition on : Logical Architecture / Structure / Actor / Actor / Actor", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LA_C)));
    
    assertFalse("Not possible to perform actor transition on : Logical Architecture / Logical System", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LOGICAL_SYSTEM)));
    assertFalse("Not possible to perform actor transition on : Logical Architecture / Logical System / Logical Component", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LC_1)));
    assertFalse("Not possible to perform actor transition on : Logical Architecture / Logical System / Actor", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LA_1)));
    assertFalse("Not possible to perform actor transition on : Logical Architecture / Logical System / Logical Component Package", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LCP_1)));  
    assertFalse("Not possible to perform actor transition on external Logical Component Package without any actor under it", TransitionCommandHelper.getInstance().isActorTransitionAvailable(getObject(LCP_3)));  
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }
}
