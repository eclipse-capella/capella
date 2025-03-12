/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.fwk;

import org.osgi.framework.Version;
import org.polarsys.capella.core.af.integration.CapellaMetadataProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 */
public class MigrationRequiredTest extends BasicTestCase {

  CapellaMetadataProvider cmp = new CapellaMetadataProvider();

  @Override
  public void test() throws Exception {
    assertTrue(requireMigration("7.0.0", "7.0.1"));
    assertTrue(requireMigration("7.0.0", "7.0.2"));
    assertTrue(requireMigration("7.0.2", "7.1.0"));

    assertFalse(requireMigration("7.0.1", "7.0.2"));
    
    assertTrue(requireMigration("6.1.0", "7.0.2"));
    assertTrue(requireMigration("5.1.2", "7.0.2"));
  }
  
  protected boolean requireMigration(String from, String to) {
    return !cmp.isMigrationRequired(Version.parseVersion(from), Version.parseVersion(to)).isOK();
  }

}
