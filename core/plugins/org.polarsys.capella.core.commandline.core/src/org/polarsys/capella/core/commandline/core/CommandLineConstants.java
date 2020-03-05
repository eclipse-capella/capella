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

import org.polarsys.capella.common.application.CommonArgumentsConstants;

/**
 */
public class CommandLineConstants {

  public static final String HELP = "-help";//$NON-NLS-1$
  public static final String ID = "-appid"; //$NON-NLS-1$
  public static final String DATA = "-data";//$NON-NLS-1$
  public static final String IMPORT = "-import"; //$NON-NLS-1$
  public static final String FORCEIMPORT = "-forceimport"; //$NON-NLS-1$
  public static final String EXPORT = "-export"; //$NON-NLS-1$
  public static final String EXPORT_ZIP_NAME = "-exportZipName"; //$NON-NLS-1$
  @Deprecated
  public static final String FILE_PATH = "-filepath";//$NON-NLS-1$
  public static final String INPUT = "-input";//$NON-NLS-1$
  public static final String COPY_ON_WORKSPACE = "-copyOnWorkspace"; //$NON-NLS-1$
  public static final String OUTPUTFOLDER = "-outputfolder"; //$NON-NLS-1$
  @Deprecated
  public static final String FORCEOUTPUTFOLDERCREATION = "-forceoutputfoldercreation"; //$NON-NLS-1$
  public static final String LOG_FILE_PATH = CommonArgumentsConstants.LOG_FILE_PATH;

}
