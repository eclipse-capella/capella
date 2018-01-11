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
import org.polarsys.capella.core.data.la.LogicalComponent;

public class MoveLogicalComponentTest extends AbstractMoveTestCase<LogicalComponent> {

  @Override
  EClass getComponentClass() {
    return LaPackage.Literals.LOGICAL_COMPONENT;
  }

  @Override
  void attachToProject(LogicalComponent a) {
    projectSkeleton.getLogicalArchitecture().getOwnedLogicalComponent().getOwnedLogicalComponents().add(a);
  }

  @Override
  void moveToLibrary(LogicalComponent a) {
    librarySkeleton.getLogicalArchitecture().getOwnedLogicalComponent().getOwnedLogicalComponents().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getLogicalArchitecture().getOwnedLogicalComponent().getOwnedPartitions();
  }

}
