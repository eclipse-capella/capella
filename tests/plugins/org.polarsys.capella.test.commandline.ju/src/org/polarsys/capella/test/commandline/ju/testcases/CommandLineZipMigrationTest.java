/*******************************************************************************
 * Copyright (c) 2017, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.commandline.ju.testcases;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Test the migration command line with an archive as input.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class CommandLineZipMigrationTest extends CommandLineMigrationTestBase {
  
  private static final List<String> TESTS_PROJECT_NAMES = List.of("sysmodelLibrary_Super", "sysmodelLibrary_Sub", "sysmodelProject");
  
  @Override
  public void test() throws Exception {

    Path sourceArchive = testResourcesPath.resolve("sysmodel_with_libraries.zip");

    var archiveEntries = TESTS_PROJECT_NAMES.stream()
        .map(this::getFolderInTestModelRepository)
        .toArray(File[]::new);
    IResourceHelpers.createZip(sourceArchive.toFile(), archiveEntries);
    
    launchMigration(CommandLineConstants.IMPORT, sourceArchive.toString());

    TESTS_PROJECT_NAMES.forEach(this::openSession);
  }

}
