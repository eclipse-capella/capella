/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.mdsofa.common.constant;

/**
 */
public interface ICommonConstants {
  /**
   * Empty string constant.
   */
  String EMPTY_STRING = ""; //$NON-NLS-1$
  /**
   * TAB character.
   */
  char TAB_CHARACTER = '\t';
  /**
   * Slash character.
   */
  char SLASH_CHARACTER = '/';
  /**
   * Point character.
   */
  char POINT_CHARACTER = '.';
  /**
   * Comma character.
   */
  char COMMA_CHARACTER = ',';
  /**
   * Semicolon character.
   */
  char SEMICOLON_CHARACTER = ';';
  /**
   * Colon character.
   */
  char COLON_CHARACTER = ':';
  /**
   * White space character.
   */
  char WHITE_SPACE_CHARACTER = ' ';
  /**
   * Define the platform resource path
   */
  String PLATFORM_RESOURCE = "platform:/resource/"; //$NON-NLS-1$
  /**
   * End of line character.
   */
  char EOL_CHARACTER = '\n';
  /**
   * Quote character.
   */
  char QUOTE_CHARACTER = '"';
  /**
   * Call method prefix, that is go for the parameters (the real prefix being the method name).
   */
  char PARENTHESIS_OPEN_CHARACTER = '(';
  /**
   * Call method suffix, that is, close parameters list, but do not end call.
   */
  char PARENTHESIS_CLOSE_CHARACTER = ')';
  /**
   * Underscore character.
   */
  char UNDERSCORE_CHARACTER = '_';
  /**
   * Http URI prefix.
   */
  String HTTP_PREFIX = "http://"; //$NON-NLS-1$
  /**
   * Ecore file extension.
   */
  String ECORE_FILE_EXTENSION = "ecore"; //$NON-NLS-1$
  /**
   * GenModel file extension.
   */
  String GENMODEL_FILE_EXTENSION = "genmodel"; //$NON-NLS-1$
  /**
   * Ecore diagram file extension.
   */
  String ECORE_DIAGRAM_FILE_EXTENSION = "ecoredi"; //$NON-NLS-1$
  /**
   * Line separator.
   */
  String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$ 

  /**
   * Generated folder path.
   */
  String GENERATED_FOLDER_PATH = "generated"; //$NON-NLS-1$
  /**
   * Model folder path.
   */
  String MODEL_FOLDER_PATH = "model"; //$NON-NLS-1$
  /**
   * Templates folder path.
   */
  String TEMPLATES_FOLDER_PATH = "templates"; //$NON-NLS-1$
  /**
   * Define source folder where model API are produced.
   */
  String SRC_FOLDER = "src"; //$NON-NLS-1$
  /**
   * A path to a file that should exist in every hosting project.
   */
  String PROJECT_ROOT_FILE = "/META-INF/MANIFEST.MF"; //$NON-NLS-1$
}
