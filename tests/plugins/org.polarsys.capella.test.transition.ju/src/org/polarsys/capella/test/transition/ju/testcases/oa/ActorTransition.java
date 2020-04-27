/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "Create System Actors" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 * Create System Actor / Actor Transition:
 *     Model Used: OA-SA-Projection
 *     Model is created with the following elements�
 *         1. Create a new "[OEBD] Operational Entity - Operational Entity Breakdown" diagram on Root Operational Entity.
 *         2.  Create Entities Entity1 and Entity2 in the diagram
 *         3.  Create Operational Actors Actor1, Actor2 in the diagram
 *         4.  Create "Contained In" link from Actor1 and Entity2 and Entity1 (Entity1 has two part of entities, Entity2 and Actor1)
 *         5.  Add a new Sub Entity Pkg under Root Operational Entity Pkg
 *         6.  Create Entity SubEntity1 in Sub Entity Pkg
 *         7.  Save the diagram
 * 
 * </pre>
 * <P>
 * 
 */
public class ActorTransition extends TopDownTransitionTestCase {

  // Target Objects
  private EntityPkg _rootOEPkg;

  private SystemComponentPkg _rootActorPkg;

  // Entities
  private Entity _rootOE;
  private Entity _entity1;
  private Entity _entity2;
  private Entity _entity3;

  private EntityPkg _subEntityPkg;
  private Entity _subEntity1;

  // Operational Actors
  private Entity _actor1;
  private Entity _actor2;
  private Entity _subOpActor1;

  // Context Actors
  private SystemComponent _rootOEActor;
  private SystemComponent _entity1Actor;
  private SystemComponent _entity2Actor;
  private SystemComponent _entity3Actor;

  private SystemComponent _ctxActor1;
  private SystemComponent _ctxActor2;

  private SystemComponentPkg _subActorPkg;
  private SystemComponent _subEntity1Actor;
  private SystemComponent _ctxSubOpActor1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _rootOEPkg = (EntityPkg) getObject(ModelOaSa.rootOEPkgId);
    _rootOE = (Entity) getObject(ModelOaSa.rootOEId);
    _entity1 = (Entity) getObject(ModelOaSa.entity1Id);
    _entity2 = (Entity) getObject(ModelOaSa.entity2Id);
    _entity3 = (Entity) getObject(ModelOaSa.entity3Id);
    _actor1 = (Entity) getObject(ModelOaSa.actor1Id);
    _actor2 = (Entity) getObject(ModelOaSa.actor2Id);

    _subEntityPkg = (EntityPkg) getObject(ModelOaSa.subEntityPkgId);
    _subEntity1 = (Entity) getObject(ModelOaSa.subEntity1Id);

    _rootActorPkg = (SystemComponentPkg) getObject(ModelOaSa.rootActorPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Test on one leaf entity
   * <P>
   * Run the projection test "Create System Actor" from Entity1 on OA-SA-Projection
   * <P>
   * This test is performed first time from "blank" e.g. without any transition of this type performed before.
   * 
   * <pre>
   * Expected Result:
   *   1. Actor Entity1 is created in Actor Package of System Analysis.
   *   2. Part for the Entity1 actor is created under System Context
   *   3. Entity1 actor has a realization link to its appropriate counterpart in the Operational Analysis
   * </pre>
   */

  public void performTest1() throws Exception {
    performOE2ActorTransition(Collections.singletonList(_entity1));
    // Get the generated actors in the Context Layer
    _entity1Actor = ProjectionTestUtils.getRecentlyCreatedActor(_rootActorPkg);

    assertNotNull(Messages.ErrorMessage, _entity1Actor);

    // Test the allocated components on all the _entity1 Actor.
    assertTrue(NLS.bind(Messages.WrongRealization, _entity1Actor.getName(), _entity1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_entity1Actor) == _entity1));

    // Verify the size of actors created after projection
    assertTrue(NLS.bind(Messages.WrongSize, _rootActorPkg.getName(), 2),
        (_rootActorPkg.getOwnedSystemComponents().size() == 2));
  }

  /**
   * Test on one entity with children - Here we don't have sub entities and so, we go for sub entity pkg
   * <P>
   * Run the projection test "Create System Actor" from Sub Entity Pkg
   * 
   * <pre>
   * Expected Result:
   *   1. Sub Actor Pkg "Sub Entity Pkg" is created under Root Actor Pkg
   *   2. Transfo  link is created from Sub Actor Pkg to Sub Entity Pkg
   *   3. SubEntity1 is created in this Sub Actor Pkg with a part in System Context
   *   4. SubEntity1 has a realization link to its counterpart in Operational Analysis
   * </pre>
   */

  public void performTest2() throws Exception {
    performOE2ActorTransition(Collections.singletonList(_subEntity1));
    // Verify the size of actors created in root actor pkg after projection (no repetition of projection)
    assertTrue(NLS.bind(Messages.WrongSize, _rootActorPkg.getName(), 2),
        (_rootActorPkg.getOwnedSystemComponents().size() == 2));
    assertTrue(NLS.bind(Messages.WrongSize, 1), (_rootActorPkg.getOwnedSystemComponentPkgs().size() == 1));

    _subActorPkg = _rootActorPkg.getOwnedSystemComponentPkgs().get(0);
    // Sub Actor Pkg "Sub Entity Pkg" is created under Root Actor Pkg
    assertNotNull(_subActorPkg);

    // Transfo link is created from Sub Actor Pkg to Sub Entity Pkg
    EObject outgoing = ProjectionTestUtils.getRealizedTargetElement(_subActorPkg);
    assertTrue(NLS.bind(Messages.ErrorMessage, _subActorPkg.getName(), _subEntityPkg.getName()),
        (outgoing == _subEntityPkg));

    // Verify the size of actors created in sub actor pkg after projection
    assertTrue(NLS.bind(Messages.WrongSize, _subActorPkg.getName(), 1),
        (_subActorPkg.getOwnedSystemComponents().size() == 1));

    // SubEntity1 is created in this Sub Actor Pkg with a part in System Context
    _subEntity1Actor = ProjectionTestUtils.getRecentlyCreatedActor(_subActorPkg);

    // SubEntity1 has a realization link to its counterpart in Operational Analysis
    assertTrue(NLS.bind(Messages.WrongRealization, _subEntity1Actor.getName(), _subEntity1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_subEntity1Actor) == _subEntity1));
  }

  /**
   * Test on Package
   * <P>
   * Run the projection test "Create System Actor" Root Entity Pkg on the capella modeler.
   * <P>
   * This test is performed on Root Entity Pkg.
   * 
   * <pre>
   * Expected Result:
   *    1. New Actors are created for Entity2, Actor1 and Actor2 with realization link to Operational Entities / Actors
   *    2. Parts for above actors are created in System Contexts
   *    3. Existing actors created from Entity1 and SubEntity1 remain with the realization links
   * </pre>
   */

  List<SystemComponent> _actors;

  public void performTest3() throws Exception {
    performOE2ActorTransition(Collections.singletonList(_rootOEPkg));
    // Get the generated actors in the Context Layer
    _rootOEActor = ProjectionTestUtils.getCreatedActor(_rootOE);
    _entity1Actor = ProjectionTestUtils.getCreatedActor(_entity1);
    _entity2Actor = ProjectionTestUtils.getCreatedActor(_entity2);
    _entity3Actor = ProjectionTestUtils.getCreatedActor(_entity3);
    _ctxActor1 = ProjectionTestUtils.getCreatedActor(_actor1);
    _ctxActor2 = ProjectionTestUtils.getCreatedActor(_actor2);

    _actors = Arrays.asList(_rootOEActor, _entity1Actor, _entity2Actor, _entity3Actor, _ctxActor1, _ctxActor2);

    for (SystemComponent actor : _actors) {
      assertNotNull(Messages.ErrorMessage, actor);
    }

    // Test the allocated components on all the actors.
    assertTrue(NLS.bind(Messages.WrongRealization, _rootOEActor.getName(), _rootOE.getName()),
        (ProjectionTestUtils.getRealizedEntity(_rootOEActor) == _rootOE));
    assertTrue(NLS.bind(Messages.WrongRealization, _entity1Actor.getName(), _entity1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_entity1Actor) == _entity1));
    assertTrue(NLS.bind(Messages.WrongRealization, _entity2Actor.getName(), _entity2.getName()),
        (ProjectionTestUtils.getRealizedEntity(_entity2Actor) == _entity2));
    assertTrue(NLS.bind(Messages.WrongRealization, _entity3Actor.getName(), _entity3.getName()),
        (ProjectionTestUtils.getRealizedEntity(_entity3Actor) == _entity3));

    assertTrue(NLS.bind(Messages.WrongRealization, _ctxActor1.getName(), _actor1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_ctxActor1) == _actor1));
    assertTrue(NLS.bind(Messages.WrongRealization, _ctxActor2.getName(), _actor2.getName()),
        (ProjectionTestUtils.getRealizedEntity(_ctxActor2) == _actor2));

    // Check for no new addition/change etc. i.e. no re-projection happens on entity1, subentity1 etc.
    List<Entity> operationalEntities = _rootOEPkg.getOwnedEntities();
    List<SystemComponent> actors = _rootActorPkg.getOwnedSystemComponents();
    assertTrue(Messages.ErrorMessage, (operationalEntities.size() == actors.size() - 1)); // pkg contains also system

    // On sub entity pkg
    assertTrue(Messages.ErrorMessage,
        (_subEntityPkg.getOwnedEntities().size() == _subActorPkg.getOwnedSystemComponents().size()));

  }

  /**
   * Test on Package again
   * <P>
   * Run the projection test "Create System Actor" again from Root Entity Pkg on the capella modeler.
   * <P>
   * This test is performed on Root Entity Pkg.
   * 
   * <pre>
   * Expected Result:
   *  1. No changes occur
   * 
   * </pre>
   */

  public void performTest4() throws Exception {
    performOE2ActorTransition(Collections.singletonList(_rootOEPkg));
    List<SystemComponent> previousResult = (List<SystemComponent>) _actors;

    List<SystemComponent> projectedActors = new ArrayList<>(_rootActorPkg.getOwnedSystemComponents());
    projectedActors.remove(BlockArchitectureExt.getRootBlockArchitecture(_rootActorPkg).getSystem());

    assertTrue(previousResult.containsAll(projectedActors));
    assertTrue((previousResult.size() == projectedActors.size()));

    // On sub entity pkg
    assertTrue((_subEntityPkg.getOwnedEntities().size() == _subActorPkg.getOwnedSystemComponents().size()));
  }

  /**
   * Make the following changes in the structure
   * 
   * <pre>
   *    1.  Create Operational Actor SubOpActor1 in Sub Entity Pkg
   *    2.  Delete Actor1 and Entity2 under the root Operational Entity Pkg
   *    3.  Rename Actor2 and OpActor2
   * </pre>
   * <P>
   * Run the projection test "Create System Actor" again from Root Entity Pkg after making the above changes
   * 
   * <pre>
   * Expected Result:
   *    1. New Actor SubOpActor1 is created in the sub actor pkg.
   *    2. Actor1 and Entity2 still exist in System Analysis
   *       but the realization link to the corresponding Operational Entity/Actor is removed
   *    3. Actor2 in System Analysis is renamed to OpActor2
   * </pre>
   */

  public void performTest5() throws Exception {
    // Create Operational Actor SubOpActor1 in Sub Entity Pkg
    // Delete Actor1 and Entity2 under the root Operational Entity Pkg
    // Rename Actor2 and OpActor2
    getExecutionManager(_rootOEPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _subOpActor1 = OaFactory.eINSTANCE.createEntity("SupOpActor1");
        _subOpActor1.setActor(true);
        _subEntityPkg.getOwnedEntities().add(_subOpActor1);

        // Create part
        Part subOpActor1Part = CsFactory.eINSTANCE.createPart(_subOpActor1.getName());
        subOpActor1Part.setAbstractType(_subOpActor1);
        _subEntity1.getOwnedFeatures().add(subOpActor1Part);
        _actor1.destroy();
        _entity2.destroy();
        _actor2.setName("OpActor2");
      }
    });

    performOE2ActorTransition(Collections.singletonList(_rootOEPkg));
    // New Actor SubOpActor1 is created in the sub actor pkg.
    _ctxSubOpActor1 = ProjectionTestUtils.getRecentlyCreatedActor(_subActorPkg);
    assertNotNull(Messages.ErrorMessage, _ctxSubOpActor1);
    assertTrue(NLS.bind(Messages.WrongRealization, _ctxSubOpActor1.getName(), _subOpActor1.getName()),
        (ProjectionTestUtils.getRealizedEntity(_ctxSubOpActor1) == _subOpActor1));

    // Actor1 and Entity2 still exist in System Analysis but the realization link to the corresponding Operational
    // Entity/Actor is removed
    assertNotNull(Messages.ErrorMessage, _ctxActor1);
    assertNotNull(Messages.ErrorMessage, _entity2Actor);

    assertNull(NLS.bind(Messages.ErrorMessage, _ctxActor1.getName()),
        ProjectionTestUtils.getRealizedEntity(_ctxActor1));
    assertNull(NLS.bind(Messages.ErrorMessage, _entity2Actor.getName()),
        ProjectionTestUtils.getRealizedEntity(_entity2Actor));

    // Actor2 in System Analysis is renamed to OpActor2
    // We don't rename anymore any already transitioned elements
    assertFalse(NLS.bind(Messages.ErrorMessage, _ctxActor2.getName(), "OpActor2"),
        _ctxActor2.getName().equals("OpActor2"));
  }
}
