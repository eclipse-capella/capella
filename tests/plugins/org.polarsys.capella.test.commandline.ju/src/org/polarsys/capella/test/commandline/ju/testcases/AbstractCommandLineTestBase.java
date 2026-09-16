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
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Abstract class to test Export Projects options in commandline.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public abstract class AbstractCommandLineTestBase extends BasicTestCase {
  
  /** 
   * Location to generate temporary content.
   * <p>
   * This location is compatible with Maven file layout and does not pollute resource space out of the project.
   * </p>
   */
  private static final Path TEST_OUT_BASE_PATH = Path.of("target/test-run");
  
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

  
  /**
   * Concatenates String values to a single argument parameter.
   * 
   * @param values text to concatenate
   * @return argument
   */
  public static final String toListArg(Stream<? extends CharSequence> values) {
    return values.collect(Collectors.joining(CommandLineConstants.LIST_SEPARATOR));
  }

  /** Path of Workspace used in tests. */
  protected static final Path WS_DATA_PATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().toPath();

  /** 
   * Path of folder to put resources created during the tests.
   * <p>
   * The content at this path is cleaned before and after the test. 
   * </p>
   * <p>
   * The path is absolute. Any resolution from it will be absolute.
   * </p>
   */
  protected final Path testResourcesPath = TEST_OUT_BASE_PATH.resolve(getClass().getSimpleName()).toAbsolutePath();
  

  /** Path where resources are exported. */
  protected final Path[] outputResourcePaths;
  
  protected AbstractCommandLineTestBase(Path... paths) {
    outputResourcePaths = paths;
  }
  
  /**
   * Copies the folder into test space for safe use.
   * 
   * @param referenceFolder folder to copy
   * @return absolute path of copied folder
   */
  protected String copyToTestPool(File referenceFolder) {
    var copy = testResourcesPath.resolve(referenceFolder.getName());
    try {
      Files.createDirectories(copy);
      TestHelper.copy(referenceFolder, copy.toFile());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    return copy.toString();
  }

  
  protected void cleanTestResources() throws IOException {
    Stream.concat(Stream.of(testResourcesPath), Stream.of(outputResourcePaths))
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
    cleanTestResources();
  }
  
  @Override
  protected void tearDown() throws Exception {
    try {
      super.tearDown();
    } finally {
      cleanTestResources();
    }
  }
}
