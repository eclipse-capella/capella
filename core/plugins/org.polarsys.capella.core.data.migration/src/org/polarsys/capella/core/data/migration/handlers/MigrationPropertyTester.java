/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.handlers;

import java.util.Arrays;

import org.eclipse.core.expressions.PropertyTester;

/**
 * 
 */
public class MigrationPropertyTester extends PropertyTester {

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    // check if is project, aird or model migration
    switch(property) {
    case "isValidMigrationProject":
      return new ProjectMigrationHandler().isValidSelection(Arrays.asList(receiver));
    case "isValidMigrationModel":
      return new ModelMigrationHandler().isValidSelection(Arrays.asList(receiver));
    case "isValidMigrationAird":
      return new AirdMigrationHandler().isValidSelection(Arrays.asList(receiver));
    default:
    }
    return false;
  }
}