/*******************************************************************************
 * Copyright (c) 2026 THALES GLOBAL SERVICES.
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

import java.nio.file.Path;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Abstract class to test Export Projects options in commandline.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public abstract class AbstractCommandLineExportTestCase extends AbstractCommandLineTestBase {
  

  protected static final String[] PROJECT_NAMES = {
      "Test Command Line Validation", //$NON-NLS-1$
      "RefreshRemoveExport" //$NON-NLS-1$
  };
  
  protected AbstractCommandLineExportTestCase(Path... paths) {
    super(paths);
  }
  

  @Override
  protected void setUp() throws Exception {
    cleanWorkspace();
    super.setUp();
  }
  
  private static void cleanWorkspace() throws Exception {
    for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
      // Most Tests use reference Test resources directly: Content must not be deleted.
      project.delete(IResource.NEVER_DELETE_PROJECT_CONTENT, null);
    }
  }
  
  protected String listProject(String... project) {
    return toListArg(Stream.of(PROJECT_NAMES)
      .map(projectName -> copyToTestPool(getFolderInTestModelRepository(projectName))));
  }
  
}
