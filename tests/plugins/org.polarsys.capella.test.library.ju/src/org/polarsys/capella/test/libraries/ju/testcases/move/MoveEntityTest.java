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
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;

public class MoveEntityTest extends AbstractMoveTestCase<Entity> {

  @Override
  EClass getComponentClass() {
    return OaPackage.Literals.ENTITY;
  }

  @Override
  void attachToProject(Entity entity) {
    projectSkeleton.getOperationalAnalysis().getOwnedEntityPkg().getOwnedEntities().add(entity);
  }

  @Override
  void moveToLibrary(Entity entity) {
    librarySkeleton.getOperationalAnalysis().getOwnedEntityPkg().getOwnedEntities().add(entity);
  }

  @Override
  Collection<?> getPartSetting() {
    return (librarySkeleton.getOperationalAnalysis().getOwnedOperationalContext().getOwnedPartitions());
  }

}
