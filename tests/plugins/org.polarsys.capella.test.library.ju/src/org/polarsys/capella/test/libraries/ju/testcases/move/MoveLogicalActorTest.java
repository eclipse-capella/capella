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
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;

public class MoveLogicalActorTest extends AbstractMoveTestCase<LogicalActor> {

  @Override
  EClass getComponentClass() {
    return LaPackage.Literals.LOGICAL_ACTOR;
  }

  @Override
  void attachToProject(LogicalActor a) {
    projectSkeleton.getLogicalArchitecture().getOwnedLogicalActorPkg().getOwnedLogicalActors().add(a);
  }

  @Override
  void moveToLibrary(LogicalActor a) {
    librarySkeleton.getLogicalArchitecture().getOwnedLogicalActorPkg().getOwnedLogicalActors().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getLogicalArchitecture().getOwnedLogicalContext().getOwnedPartitions();
  }

}
