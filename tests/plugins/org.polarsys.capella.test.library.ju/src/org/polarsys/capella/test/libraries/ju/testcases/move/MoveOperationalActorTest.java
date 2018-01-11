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
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;

public class MoveOperationalActorTest extends AbstractMoveTestCase<OperationalActor> {

  @Override
  EClass getComponentClass() {
    return OaPackage.Literals.OPERATIONAL_ACTOR;
  }

  @Override
  void attachToProject(OperationalActor oa) {
    projectSkeleton.getOperationalAnalysis().getOwnedEntityPkg().getOwnedEntities().add(oa);
  }

  @Override
  void moveToLibrary(OperationalActor oa) {
    librarySkeleton.getOperationalAnalysis().getOwnedEntityPkg().getOwnedEntities().add(oa);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getOperationalAnalysis().getOwnedOperationalContext().getOwnedPartitions();
  }

}
