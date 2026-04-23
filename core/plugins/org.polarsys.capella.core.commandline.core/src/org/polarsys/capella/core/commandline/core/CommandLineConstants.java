/*******************************************************************************
 * Copyright (c) 2006, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import org.polarsys.capella.common.application.CommonArgumentsConstants;

/**
 * Constants for command line options.
 */
public class CommandLineConstants {

  public static final String LIST_SEPARATOR = "|"; //$NON-NLS-1$
  public static final String ALL_ARGUMENT = "/all"; //$NON-NLS-1$

  public static final String HELP = "-help";//$NON-NLS-1$
  public static final String ID = "-appid"; //$NON-NLS-1$
  public static final String DATA = "-data";//$NON-NLS-1$
  public static final String IMPORT = "-import"; //$NON-NLS-1$
  public static final String IMPORT_ALL = "-importAll"; //$NON-NLS-1$
  public static final String FORCEIMPORT = "-forceimport"; //$NON-NLS-1$
  public static final String EXPORTZIP = "-exportZip"; //$NON-NLS-1$
  @Deprecated
  public static final String EXPORT = "-export"; //$NON-NLS-1$
  @Deprecated
  public static final String EXPORT_ZIP_NAME = "-exportZipName"; //$NON-NLS-1$
  @Deprecated
  public static final String FILE_PATH = "-filepath";//$NON-NLS-1$
  public static final String INPUT = "-input";//$NON-NLS-1$
  public static final String COPY_ON_WORKSPACE = "-copyOnWorkspace"; //$NON-NLS-1$
  public static final String OUTPUTFOLDER = "-outputfolder"; //$NON-NLS-1$
  @Deprecated
  public static final String FORCEOUTPUTFOLDERCREATION = "-forceoutputfoldercreation"; //$NON-NLS-1$
  public static final String LOG_FILE_PATH = CommonArgumentsConstants.LOG_FILE_PATH;
  public static final String BACKUP = "-backup"; //$NON-NLS-1$

  public static final String EXPORT_LIST = "-exportProjects"; //$NON-NLS-1$  
  public static final String SINGLE_ZIP = "-singleZip"; //$NON-NLS-1$
  public static final String EXPORT_COPY = "-exportCopy"; //$NON-NLS-1$
  
  public static final String EXPORT_TARGET = "-exportTarget"; //$NON-NLS-1$

}
