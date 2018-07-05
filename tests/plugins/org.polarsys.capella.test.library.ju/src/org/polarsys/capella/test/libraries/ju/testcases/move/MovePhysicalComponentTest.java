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
import org.polarsys.capella.core.data.pa.PhysicalComponent;

public class MovePhysicalComponentTest extends AbstractMoveTestCase<PhysicalComponent> {

  @Override
  EClass getComponentClass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  @Override
  void attachToProject(PhysicalComponent a) {
    projectSkeleton.getPhysicalArchitecture().getOwnedPhysicalComponent().getOwnedPhysicalComponents().add(a);
  }

  @Override
  void moveToLibrary(PhysicalComponent a) {
    librarySkeleton.getPhysicalArchitecture().getOwnedPhysicalComponent().getOwnedPhysicalComponents().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getPhysicalArchitecture().getOwnedPhysicalComponent().getOwnedPartitions();
  }

}
