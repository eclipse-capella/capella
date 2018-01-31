/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.libraries.ju.testcases.move;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;

public class MovePhysicalActorTest extends AbstractMoveTestCase<PhysicalActor> {

  @Override
  EClass getComponentClass() {
    return PaPackage.Literals.PHYSICAL_ACTOR;
  }

  @Override
  void attachToProject(PhysicalActor a) {
    projectSkeleton.getPhysicalArchitecture().getOwnedPhysicalActorPkg().getOwnedPhysicalActors().add(a);
  }

  @Override
  void moveToLibrary(PhysicalActor a) {
    librarySkeleton.getPhysicalArchitecture().getOwnedPhysicalActorPkg().getOwnedPhysicalActors().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getPhysicalArchitecture().getOwnedPhysicalContext().getOwnedPartitions();
  }

}
