/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.commandline.ju.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.polarsys.capella.core.commandline.core.CommandLineApp;
import org.polarsys.capella.core.commandline.core.CommandLineConstants;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.ICommandLine;

/**
 * Mock implementation for IApplicationContext. Used to pass arguments when testing command line services.
 */
public class MockApplicationContext implements IApplicationContext {
  // Mock application arguments
  private final Map<String, String[]> mockAppArguments;
  
  
  /**
   * Executes the command line like in {@link CommandLineApp} for test purpose.
   * 
   * @param command to execute
   * @param appId identification of the application
   * @param arguments parameters of execution
   * @throws CommandLineException if a step fails
   */
  public static void execute(ICommandLine command, String appId, String... arguments) throws CommandLineException {
    IApplicationContext context = new MockApplicationContext(
        Stream.concat(Stream.of(CommandLineConstants.ID, appId), Stream.of(arguments))
          .toArray(String[]::new)
        );
    
    // Sequence must be the same as in:
    //   org.polarsys.capella.core.commandline.core.CommandLineApp
    //   #launchApp(ICommandLine, IApplicationContext)

    command.parseContext(context);
    command.checkArgs(context);
    command.prepare(context);
    command.execute(context);
    command.postExecute(context);
  }
  
  public MockApplicationContext(String... mockCommandLineArguments) {
    Map<String, String[]> appArguments =  new HashMap<>();
    appArguments.put(IApplicationContext.APPLICATION_ARGS, mockCommandLineArguments);
    mockAppArguments = Collections.unmodifiableMap(appArguments);
  }
  
  @SuppressWarnings("rawtypes")
  @Override
  public Map getArguments() {
    return mockAppArguments;
  }

  @Override
  public void applicationRunning() {
  }

  @Override
  public String getBrandingApplication() {
    return null;
  }

  @Override
  public String getBrandingName() {
    return null;
  }

  @Override
  public String getBrandingDescription() {
    return null;
  }

  @Override
  public String getBrandingId() {
    return null;
  }

  @Override
  public String getBrandingProperty(String key) {
    return null;
  }

  @Override
  public Bundle getBrandingBundle() {
    return null;
  }

  @Override
  public void setResult(Object result, IApplication application) {
  }
}