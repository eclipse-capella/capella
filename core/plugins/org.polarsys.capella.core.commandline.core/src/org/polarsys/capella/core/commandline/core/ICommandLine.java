/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core;

import org.eclipse.equinox.app.IApplicationContext;

/**
 * Interface for Capella Command Line application. The interface services will be called by the framework in the following order:
 * <ol>
 * <li>{@link #parseContext(IApplicationContext)}</li>
 * <li>{@link #checkArgs(IApplicationContext)}</i>
 * <li>{@link #prepare(IApplicationContext)}</li>
 * <li>{@link #execute(IApplicationContext)}</li>
 * </ol>
 */
public interface ICommandLine {

  /**
   * prints user help i.e. explains the parameters semantics
   */
  void printHelp();

  /**
   * called first to get command line arguments
   */
  void parseContext(IApplicationContext context) throws CommandLineException;

  /**
   * checks arguments validity
   * @param context
   * @throws CommandLineException
   */
  void checkArgs(IApplicationContext context) throws CommandLineException;

  /**
   * Prepares the execution (e.g. import projects into the workspace before a document generation)
   * @param context
   * @throws CommandLineException
   */
  void prepare(IApplicationContext context) throws CommandLineException;

  /**
   * Performs the actual work of the command line application
   * @param context
   * @return
   * @throws CommandLineException
   */
  boolean execute(IApplicationContext context) throws CommandLineException;

  /**
   * Performs after the execution of the command line application
   * @param context
   * @return
   * @throws CommandLineException
   */
  void postExecute(IApplicationContext context) throws CommandLineException;
}
