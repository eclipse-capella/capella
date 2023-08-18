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
package org.polarsys.capella.test.transition.ju.testcases.la;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "Actor Transition" from Logical Architecture to Physical Architecture
 * <P>
 * 
 * <pre>
 *     Model Used: LA-PA-Projection
 *     Model is created with the following elements�
 *           1.  Create a new "[CEI] Logical System - Contextual Component External Interfaces" diagram on Logical System.
 *           2.  Create Actors Actor1, Actor2 and Actor3 in the diagram 
 *           3.  Create Interfaces Interface1, Interface2 in the diagram
 *           4.  Create "Implements" link from Actor1 to Interface1, and "Uses" link from Actor2 to Interface2
 *           5.  Create Generalization link between Actor2 and Actor3
 *           6.  Create SubActorPkg in root Actor Pkg and create SubActor1
 *           7.  Create Component ports ComponentPort1 (STANDARD), ComponentPort2 (FLOW) in SubActor1
 *           8.  Save the diagram
 * </pre>
 * 
 * Interface transition is not enabled And the tests are documented in {@link #performTest()} method
 */
public class ActorTransition extends TopDownTransitionTestCase {

  // Target Objects
  private LogicalComponentPkg laRootActorPkg;
  private LogicalComponent laActor1;
  private LogicalComponent laActor2;
  private LogicalComponent laActor3;
  private Interface laInterface1;
  private Interface laInterface2;
  private InterfaceImplementation laInterfaceImpl;
  private InterfaceUse laInterfaceUse;
  private LogicalComponentPkg laSubactorPkg;
  private LogicalComponent laSubactor1;
  private LogicalComponent laSubactor2;
  private ComponentPort laComponentPort1;

  private PhysicalComponentPkg paRootActorPkg;
  private PhysicalComponent paActor1;
  private PhysicalComponent paActor2;
  private PhysicalComponent paActor3;
  private InterfaceImplementation paInterfaceImpl;
  private InterfaceUse paInterfaceUse;
  private PhysicalComponentPkg paSubactorPkg;
  private PhysicalComponent paSubactor1;
  private PhysicalComponent paSubactor2;
  private ComponentPort paComponentPort1;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    laRootActorPkg = getObject(ModelLaPa.rootLAActorPkgId);
    laActor1 = getObject(ModelLaPa.actor1Id);
    laActor2 = getObject(ModelLaPa.actor2Id);
    laActor3 = getObject(ModelLaPa.actor3Id);
    laInterface1 = getObject(ModelLaPa.interface1Id);
    laInterface2 = getObject(ModelLaPa.interface2Id);
    laInterfaceImpl = getObject(ModelLaPa.interfaceImplId);
    laInterfaceUse = getObject(ModelLaPa.interfaceUseId);
    laSubactorPkg = getObject(ModelLaPa.subActorPkgId);
    laSubactor1 = getObject(ModelLaPa.subActor1Id);
    laComponentPort1 = getObject(ModelLaPa.componentPort1Id);

    paRootActorPkg = getObject(ModelLaPa.rootPAActorPkgId);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Tests on "Actor Transition" Projection command:
   * 
   * <pre>
   *         1. Test on one leaf entity (Actor3)
   *         2. Test on non leaf entity (sub Actor Pkg)
   *         4. Repetition Test on Root Actor Pkg without any changes
   *         5. Test on Root Actor Pkg with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    actor3TransitionTest();
    subActorPkgTransitionTest();
    rootActorPkgTransition1Test();
    rootActorPkgTransition2Test();
    rootActorPkgTransition3Test();
  }

  /**
   * Run the projection test "Actor Transition" from Actor3
   * 
   * <pre>
   * Expected Result:\
   *             1.  Physical Actor Actor3 is created in PA.\
   *             2.  Realization link is created from PA-Actor3 to LogicalActor3
   * </pre>
   */
  private void actor3TransitionTest() {
    performActorTransition(Arrays.asList(laActor3));
    paActor3 = mustBeTransitioned(ModelLaPa.actor3Id);
    mustNotBeNull(paActor3);
    assertTrue(NLS.bind(Messages.RealizationError, paActor3.getName(), laActor3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paActor3) == laActor3));
  }

  /**
   * Run the projection test "Actor Transition" from Sub Actor Pkg
   * 
   * <pre>
   * Expected Result:\
   *                 1. Sub Physical Actor Pkg is created under Root Physical Actor Pkg\
   *                 2.  Transfo  link is created from Sub Physical Actor Pkg to Sub Actor Pkg\
   *                 3.  SubPhysicalActor1 is created in this Sub Physical Actor Pkg with a part in Physical Context\
   *                 4.  SubPhysicalActor1 has a realization link to its counterpart in Logical Layer\
   *                 5.  Component Ports1 (STANDARD) is created in SubPhysicalActor1 and not the flow port
   * </pre>
   */
  private void subActorPkgTransitionTest() {
    performActorTransition(Arrays.asList(laSubactorPkg));
    paSubactorPkg = paRootActorPkg.getOwnedPhysicalComponentPkgs().get(0);
    mustNotBeNull(paSubactorPkg);

    assertTrue(NLS.bind(Messages.RealizationError, paSubactorPkg.getName(), laSubactorPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubactorPkg) == laSubactorPkg));

    paSubactor1 = paSubactorPkg.getOwnedPhysicalComponents().get(0);
    mustNotBeNull(paSubactor1);

    assertTrue(NLS.bind(Messages.RealizationError, paSubactor1.getName(), laSubactor1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubactor1) == laSubactor1));

    assertEquals(NLS.bind(Messages.ProjectionSizeError, Long.valueOf(2)), 2,
        paSubactor1.getContainedComponentPorts().size());
    paComponentPort1 = paSubactor1.getContainedComponentPorts().get(0);
    mustNotBeNull(paComponentPort1);

    assertTrue(NLS.bind(Messages.RealizationError, paComponentPort1.getName(), laComponentPort1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paComponentPort1) == laComponentPort1));
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg
   * 
   * <pre>
   * Expected Result:\
   *                 1. New Physical Actors are created for Actor1 and Actor2 with realization link to Logical Actors\
   *                 2.  Parts for above actors are created in Physical Contexts\
   *                 3.  InterfaceImpl1 is created in Physical Actor1 and InterfaceUse1 is created in Physical Actor2\
   *                 4.  Actor1 contains Logical Interface1 in its implemented interfaces and Actor2 has Logical Interface2 in its used interfaces.
   * </pre>
   */
  private void rootActorPkgTransition1Test() {
    performActorTransition(Arrays.asList(laRootActorPkg));
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Long.valueOf(5)),
        paRootActorPkg.getOwnedPhysicalComponents().size(), 5);

    paActor1 = (PhysicalComponent) ProjectionTestUtils.getAllocatingComponent(laActor1);
    mustNotBeNull(paActor1);
    assertTrue(NLS.bind(Messages.RealizationError, paActor1.getName(), laActor1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paActor1) == laActor1));

    paActor2 = (PhysicalComponent) ProjectionTestUtils.getAllocatingComponent(laActor2);
    mustNotBeNull(paActor2);
    assertTrue(NLS.bind(Messages.RealizationError, paActor2.getName(), laActor2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paActor2) == laActor2));

    paInterfaceImpl = paActor1.getImplementedInterfaceLinks().get(0);
    mustNotBeNull(paInterfaceImpl);
    assertTrue(NLS.bind(Messages.RealizationError, paInterfaceImpl, laInterfaceImpl),
        (ProjectionTestUtils.getRealizedTargetElement(paInterfaceImpl) == laInterfaceImpl));

    assertTrue(paInterfaceImpl.getImplementedInterface() == laInterface1);

    paInterfaceUse = paActor2.getUsedInterfaceLinks().get(0);
    mustNotBeNull(paInterfaceUse);
    assertTrue(NLS.bind(Messages.RealizationError, paInterfaceUse, laInterfaceUse),
        (ProjectionTestUtils.getRealizedTargetElement(paInterfaceUse) == laInterfaceUse));
    assertTrue(paInterfaceUse.getUsedInterface() == laInterface2);

    paSubactor1 = paSubactorPkg.getOwnedPhysicalComponents().get(0);
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg again
   * 
   * <pre>
   * Expected Result:\
   *                 1. No changes occur, and the created elements are intact
   * </pre>
   */
  private void rootActorPkgTransition2Test() {
    performActorTransition(Arrays.asList(laRootActorPkg));
    assertEquals(NLS.bind(Messages.ProjectionSizeError, 5),
        paRootActorPkg.getOwnedPhysicalComponents().size(), 5);
    assertEquals(NLS.bind(Messages.ProjectionSizeError, 1),
        paSubactorPkg.getOwnedPhysicalComponents().size(), 1);
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg again
   * 
   * <pre>
   * Make the following changes in the structure\
   *                 1. Delete SubActor1 & Create Actor SubActor2 in Sub Actor Pkg\
   *                 2.  Rename Actor3 and LogicalActor3\
   * Expected Result:\
   *                 1.  New Actor SubActor2 is created in the sub Physical actor pkg.\
   *                 2.  Sub Actor1 still exists in LA without realization link\
   *                 3.  Physical Actor Actor3 is not renamed to LogicalActor3
   * </pre>
   */
  private void rootActorPkgTransition3Test() {
    // Delete SubActor1 & Create Actor SubActor2 in Sub Actor Pkg
    laSubactor1.destroy();

    laSubactor2 = LaFactory.eINSTANCE.createLogicalComponent("Sub Actor 2"); //$NON-NLS-1$
    laSubactor2.setActor(true);
    laSubactorPkg.getOwnedLogicalComponents().add(laSubactor2);
    // Rename Actor3 and CtxActor3
    laActor3.setName("LA Actor 3");
    performActorTransition(Arrays.asList(laRootActorPkg));
    mustNotBeNull(paSubactor1);

    assertTrue(NLS.bind(Messages.RealizationError, paSubactor1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(paSubactor1) == null));

    paSubactor2 = paSubactorPkg.getOwnedPhysicalComponents().get(1);
    mustNotBeNull(paSubactor2);

    assertTrue(NLS.bind(Messages.RealizationError, paSubactor2.getName(), laSubactor2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubactor2) == laSubactor2));

    assertFalse(paActor3.getName().equals("LA Actor 3"));

    assertEquals(NLS.bind(Messages.ProjectionSizeError, 5),
        paRootActorPkg.getOwnedPhysicalComponents().size(), 5);
    assertEquals(NLS.bind(Messages.ProjectionSizeError, 2),
        paSubactorPkg.getOwnedPhysicalComponents().size(), 2);
  }
}
