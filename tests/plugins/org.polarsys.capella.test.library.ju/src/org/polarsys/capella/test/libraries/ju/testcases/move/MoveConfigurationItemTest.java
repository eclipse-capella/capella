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
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;

public class MoveConfigurationItemTest extends AbstractMoveTestCase<ConfigurationItem> {

  @Override
  EClass getComponentClass() {
    return EpbsPackage.Literals.CONFIGURATION_ITEM;
  }

  @Override
  void attachToProject(ConfigurationItem a) {
    projectSkeleton.getEPBSArchitecture().getOwnedConfigurationItem().getOwnedConfigurationItems().add(a);
  }

  @Override
  void moveToLibrary(ConfigurationItem a) {
    librarySkeleton.getEPBSArchitecture().getOwnedConfigurationItem().getOwnedConfigurationItems().add(a);
  }

  @Override
  Collection<?> getPartSetting() {
    return librarySkeleton.getEPBSArchitecture().getOwnedConfigurationItem().getOwnedPartitions();
  }

}
