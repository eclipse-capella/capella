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
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;

public class MoveActorTest extends AbstractMoveTestCase<Actor> {

  @Override
  EClass getComponentClass() {
    return CtxPackage.Literals.ACTOR;
  }

  @Override
  void attachToProject(Actor a) {
    projectSkeleton.getSystemAnalysis().getOwnedActorPkg().getOwnedActors().add(a);
  }

  @Override
  void moveToLibrary(Actor a) {
    librarySkeleton.getSystemAnalysis().getOwnedActorPkg().getOwnedActors().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getSystemAnalysis().getOwnedSystemContext().getOwnedPartitions();
  }

}
