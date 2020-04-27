/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.sa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on "Actor Transition" from System Analysis to Logical Architecture
 * <P>
 * 
 * <pre>
 *     Model Used: CTX-LA-Projection
 *     Model is created with the following elements�
 *           1.  Create a new "[CEI] System - Contextual Component External Interfaces" diagram on System.
 *           2.  Create Actors Actor1, Actor2 and Actor3 in the diagram 
 *           3.  Create Interfaces Interface1, Interface2 in the diagram
 *           4.  Create "Implements" link from Actor1 to Interface1, and "Uses" link from Actor2 to Interface2
 *           5.  Create Generalization link between Actor2 and Actor3
 *           6.  Create SubActorPkg in root Actor Pkg and create SubActor1
 *           7.  Create Component ports ComponentPort1 (STANDARD), ComponentPort2 (FLOW) in SubActor1
 *           8.  Save the diagram
 * </pre>
 */
public class ActorTransition extends TopDownTransitionTestCase {

  // Target Objects
  private SystemComponentPkg _ctxRootActorPkg;
  private SystemComponent _ctxActor1;
  private SystemComponent _ctxActor2;
  private SystemComponent _ctxActor3;
  private Interface _ctxInterface1;
  private Interface _ctxInterface2;
  private InterfaceImplementation _ctxInterfaceImpl;
  private InterfaceUse _ctxInterfaceUse;
  private SystemComponentPkg _ctxSubactorPkg;
  private SystemComponent _ctxSubactor1;
  private SystemComponent _ctxSubactor2;
  private ComponentPort _ctxComponentPort1;

  private LogicalComponentPkg _laRootActorPkg;
  private LogicalComponent _laActor1;
  private LogicalComponent _laActor2;
  private LogicalComponent _laActor3;
  private InterfaceImplementation _laInterfaceImpl;
  private InterfaceUse _laInterfaceUse;
  private LogicalComponentPkg _laSubactorPkg;
  private LogicalComponent _laSubactor1;
  private LogicalComponent _laSubactor2;
  private ComponentPort _laComponentPort1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {

    _ctxRootActorPkg = (SystemComponentPkg) getObject(ModelCtxLa.rootActorPkgId);
    _ctxActor1 = (SystemComponent) getObject(ModelCtxLa.actor1Id);
    _ctxActor2 = (SystemComponent) getObject(ModelCtxLa.actor2Id);
    _ctxActor3 = (SystemComponent) getObject(ModelCtxLa.actor3Id);
    _ctxInterface1 = (Interface) getObject(ModelCtxLa.interface1Id);
    _ctxInterface2 = (Interface) getObject(ModelCtxLa.interface2Id);
    _ctxInterfaceImpl = (InterfaceImplementation) getObject(ModelCtxLa.interfaceImplId);
    _ctxInterfaceUse = (InterfaceUse) getObject(ModelCtxLa.interfaceUseId);
    _ctxSubactorPkg = (SystemComponentPkg) getObject(ModelCtxLa.subActorPkgId);
    _ctxSubactor1 = (SystemComponent) getObject(ModelCtxLa.subActor1Id);
    _ctxComponentPort1 = (ComponentPort) getObject(ModelCtxLa.componentPort1Id);

    _laRootActorPkg = (LogicalComponentPkg) getObject(ModelCtxLa.rootLAActorPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Run the projection test "Actor Transition" from Actor3
   * 
   * <pre>
   * Expected Result:\
   *               1. Logical Actor Actor3 is created in LA.\
   *               2. Realization link is created from LA-Actor3 to CtxActor3
   * </pre>
   */
  public void performTest1() throws Exception {
    performActorTransition(Collections.singletonList(_ctxActor3));

    _laActor3 = ProjectionTestUtils.getRecentlyCreatedLogicalActor(_laRootActorPkg);
    mustNotBeNull(_laActor3);

    assertTrue(NLS.bind(Messages.RealizationError, _laActor3.getName(), _ctxActor3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laActor3) == _ctxActor3));
  }

  /**
   * Run the projection test "Actor Transition" from Sub Actor Pkg
   * 
   * <pre>
   * Expected Result:\
   *                 1. Sub Logical Actor Pkg is created under Root Logical Actor Pkg\
   *                 2.  Transfo  link is created from Sub Logical Actor Pkg to Sub Actor Pkg\
   *                 3.  SubLogicalActor1 is created in this Sub Logical Actor Pkg with a part in Logical Context\
   *                 4.  SubLogicalActor1 has a realization link to its counterpart in System Analysis\
   *                 5.  5. Component Ports1 (STANDARD) is created in SubLogicalActor1 and not the flow port
   * </pre>
   */

  public void performTest2() throws Exception {
    performActorTransition(Collections.singletonList(_ctxSubactorPkg));

    _laSubactorPkg = _laRootActorPkg.getOwnedLogicalComponentPkgs().get(0);
    mustNotBeNull(_laSubactorPkg);

    assertTrue(NLS.bind(Messages.RealizationError, _laSubactorPkg.getName(), _ctxSubactorPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubactorPkg) == _ctxSubactorPkg));

    _laSubactor1 = _laSubactorPkg.getOwnedLogicalComponents().get(0);
    mustNotBeNull(_laSubactor1);

    assertTrue(NLS.bind(Messages.RealizationError, _laSubactor1.getName(), _ctxSubactor1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubactor1) == _ctxSubactor1));

    assertEquals(NLS.bind(Messages.ProjectionSizeError, 2), _laSubactor1.getContainedComponentPorts().size(), 2);
    _laComponentPort1 = (ComponentPort) _laSubactor1.getContainedComponentPorts().get(0);
    mustNotBeNull(_laComponentPort1);

    assertTrue(NLS.bind(Messages.RealizationError, _laComponentPort1.getName(), _ctxComponentPort1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laComponentPort1) == _ctxComponentPort1));
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg
   * 
   * <pre>
   * Expected Result:\
   *                 1. New Logical Actors are created for Actor1 and Actor2 with realization link to Ctx Actors\
   *                 2. Parts for above actors are created in Logical Contexts\
   *                 3. InterfaceImpl1 is created in Logical Actor1 and InterfaceUse1 is created in Logical Actor2\
   *                 4. Actor1 contains Ctx Interface1 in its implemented interfaces and Actor2 has Ctx Interface2 in its used interfaces.
   * </pre>
   */

  public void performTest3() throws Exception {
    performActorTransition(Collections.singletonList(_ctxRootActorPkg));
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Long.valueOf(5)),
        _laRootActorPkg.getOwnedLogicalComponents().size(), 5);

    _laActor1 = (LogicalComponent) ProjectionTestUtils.getAllocatingComponent(_ctxActor1);
    mustNotBeNull(_laActor1);
    assertTrue(NLS.bind(Messages.RealizationError, _laActor1.getName(), _ctxActor1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laActor1) == _ctxActor1));

    _laActor2 = (LogicalComponent) ProjectionTestUtils.getAllocatingComponent(_ctxActor2);
    mustNotBeNull(_laActor2);
    assertTrue(NLS.bind(Messages.RealizationError, _laActor2.getName(), _ctxActor2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laActor2) == _ctxActor2));

    _laInterfaceImpl = _laActor1.getImplementedInterfaceLinks().get(0);
    mustNotBeNull(_laInterfaceImpl);
    assertTrue(NLS.bind(Messages.RealizationError, _laInterfaceImpl, _ctxInterfaceImpl),
        (ProjectionTestUtils.getRealizedTargetElement(_laInterfaceImpl) == _ctxInterfaceImpl));

    assertTrue(_laInterfaceImpl.getImplementedInterface() == _ctxInterface1);

    _laInterfaceUse = _laActor2.getUsedInterfaceLinks().get(0);
    mustNotBeNull(_laInterfaceUse);
    assertTrue(NLS.bind(Messages.RealizationError, _laInterfaceUse, _ctxInterfaceUse),
        (ProjectionTestUtils.getRealizedTargetElement(_laInterfaceUse) == _ctxInterfaceUse));
    assertTrue(_laInterfaceUse.getUsedInterface() == _ctxInterface2);

    _laSubactor1 = _laSubactorPkg.getOwnedLogicalComponents().get(0);
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg again
   * 
   * <pre>
   * Expected Result:\
   *                 1. No changes occur, and the created elements are intact
   * </pre>
   */

  public void performTest4() throws Exception {
    performActorTransition(Collections.singletonList(_ctxRootActorPkg));
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Long.valueOf(5)),
        _laRootActorPkg.getOwnedLogicalComponents().size(), 5);
    assertEquals(NLS.bind(Messages.ProjectionSizeError, 1), _laSubactorPkg.getOwnedLogicalComponents().size(), 1);
  }

  /**
   * Run the projection test "Actor Transition" from Root Actor Pkg again
   * 
   * <pre>
   * Make the following changes in the structure\
   *                 1.  Delete SubActor1 & Create Actor SubActor2 in Sub Actor Pkg\
   *                 2.  Rename Actor3 and CtxActor3\
   * Expected Result:\
   *                 1.  New Actor SubActor2 is created in the sub logical actor pkg.\
   *                 2.  Sub Actor1 still exists in LA without realization link\
   *                 3.  Logical Actor Actor3 is not renamed to CtxActor3
   * </pre>
   */

  public void performTest5() throws Exception {

    // Delete SubActor1 & Create Actor SubActor2 in Sub Actor Pkg
    getExecutionManager(_ctxRootActorPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _ctxSubactor1.destroy();

        _ctxSubactor2 = CtxFactory.eINSTANCE.createSystemComponent("Sub Actor 2"); //$NON-NLS-1$
        _ctxSubactor2.setActor(true);
        _ctxSubactorPkg.getOwnedSystemComponents().add(_ctxSubactor2);
        _ctxActor3.setName("Ctx Actor 3");
      }
    });

    performActorTransition(Collections.singletonList(_ctxRootActorPkg));
    mustNotBeNull(_laSubactor1);

    assertTrue(NLS.bind(Messages.RealizationError, _laSubactor1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubactor1) == null));

    _laSubactor2 = _laSubactorPkg.getOwnedLogicalComponents().get(1);
    mustNotBeNull(_laSubactor2);

    assertTrue(NLS.bind(Messages.RealizationError, _laSubactor2.getName(), _ctxSubactor2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laSubactor2) == _ctxSubactor2));

    assertFalse(_laActor3.getName().equals("Ctx Actor 3"));

    assertEquals(NLS.bind(Messages.ProjectionSizeError, Long.valueOf(5)),
        _laRootActorPkg.getOwnedLogicalComponents().size(), 5);
    assertEquals(NLS.bind(Messages.ProjectionSizeError, 2), _laSubactorPkg.getOwnedLogicalComponents().size(), 2);
  }

}
