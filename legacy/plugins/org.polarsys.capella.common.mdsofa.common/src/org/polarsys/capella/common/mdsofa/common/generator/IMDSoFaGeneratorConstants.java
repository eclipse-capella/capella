/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.generator;

/**
 */
public interface IMDSoFaGeneratorConstants {
  /**
   * Define source folder where model API are produced.
   */
  public static final String SRC_FOLDER = "src"; //$NON-NLS-1$
  /**
   * A path to a file that should exist in every hosting project.
   */
  public static final String PROJECT_ROOT_FILE = "/META-INF/MANIFEST.MF"; //$NON-NLS-1$
  /**
   * Generated util Java packages prefix.
   */
  public static final String GENERATED_UTIL_JAVA_PACKAGE_SUFFIX = ".util"; //$NON-NLS-1$
  /**
   * Generated implementation Java packages prefix.
   */
  public static final String GENERATED_IMPL_JAVA_PACKAGE_SUFFIX = ".impl"; //$NON-NLS-1$
}
