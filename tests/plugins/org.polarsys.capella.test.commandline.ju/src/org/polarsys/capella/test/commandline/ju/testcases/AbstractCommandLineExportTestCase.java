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

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.ResourcesPlugin;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Abstract class to test Export Projects options in commandline.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public abstract class AbstractCommandLineExportTestCase extends BasicTestCase {

  // private static final Path EXPORT_PATH = Path.of("target/test-run/DefaultCommandLineExportProjectTest");
  private static final FileVisitor<Path> DELETOR = new SimpleFileVisitor<>() {
    
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
      Files.delete(file);
      return FileVisitResult.CONTINUE;
    }

    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
      Files.delete(dir);
      return FileVisitResult.CONTINUE;      
    }
    
  };

  
  protected static final String[] PROJECT_NAMES = {
      "Test Command Line Validation", //$NON-NLS-1$
      "RefreshRemoveExport" //$NON-NLS-1$
  };
  
  protected static final Path WS_DATA_PATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().toPath();
  
  /** Path where resources are exported. */
  protected final Path[] exportPaths;
  
  protected AbstractCommandLineExportTestCase(Path... paths) {
    exportPaths = paths;
  }
  
  protected String listProject(String... project) {
    return Stream.of(PROJECT_NAMES)
      .map(this::getFolderInTestModelRepository)
      .map(File::getAbsolutePath)
      .collect(Collectors.joining(CommandLineConstants.LIST_SEPARATOR));
  }
  
  
  protected void cleanExportResources() throws IOException {
    Stream.of(exportPaths)
      .filter(Files::exists)
      .forEach(path -> {
        try {
          Files.walkFileTree(path, DELETOR);
        } catch (IOException e) {
          throw new UncheckedIOException(e);
        }
      });

  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    cleanExportResources();
  }
  
  @Override
  protected void tearDown() throws Exception {
    try {
      super.tearDown();
    } finally {
      cleanExportResources();
    }
  }
}
