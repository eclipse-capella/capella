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
package org.polarsys.capella.common.mdsofa.common.constant;

/**
 */
public interface ICommonConstants {
  /**
   * Empty string constant.
   */
  public static final String EMPTY_STRING = ""; //$NON-NLS-1$
  /**
   * TAB character.
   */
  public static final char TAB_CHARACTER = '\t';
  /**
   * Slash character.
   */
  public static final char SLASH_CHARACTER = '/';
  /**
   * Point character.
   */
  public static final char POINT_CHARACTER = '.';
  /**
   * Comma character.
   */
  public static final char COMMA_CHARACTER = ',';
  /**
   * Semicolon character.
   */
  public static final char SEMICOLON_CHARACTER = ';';
  /**
   * Colon character.
   */
  public static final char COLON_CHARACTER = ':';
  /**
   * White space character.
   */
  public static final char WHITE_SPACE_CHARACTER = ' ';
  /**
   * Define the platform resource path
   */
  public static final String PLATFORM_RESOURCE = "platform:/resource/"; //$NON-NLS-1$
  /**
   * End of line character.
   */
  public static final char EOL_CHARACTER = '\n';
  /**
   * Quote character.
   */
  public static final char QUOTE_CHARACTER = '"';
  /**
   * Call method prefix, that is go for the parameters (the real prefix being the method name).
   */
  public static final char PARENTHESIS_OPEN_CHARACTER = '(';
  /**
   * Call method suffix, that is, close parameters list, but do not end call.
   */
  public static final char PARENTHESIS_CLOSE_CHARACTER = ')';
  /**
   * Underscore character.
   */
  public static final char UNDERSCORE_CHARACTER = '_';
  /**
   * Http URI prefix.
   */
  public static final String HTTP_PREFIX = "http://"; //$NON-NLS-1$
  /**
   * Ecore file extension.
   */
  public static final String ECORE_FILE_EXTENSION = "ecore"; //$NON-NLS-1$
  /**
   * GenModel file extension.
   */
  public static final String GENMODEL_FILE_EXTENSION = "genmodel"; //$NON-NLS-1$
  /**
   * Ecore diagram file extension.
   */
  public static final String ECORE_DIAGRAM_FILE_EXTENSION = "ecoredi"; //$NON-NLS-1$
  /**
   * Line separator.
   */
  public static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$ 

  /**
   * Generated folder path.
   */
  public static final String GENERATED_FOLDER_PATH = "generated"; //$NON-NLS-1$
  /**
   * Model folder path.
   */
  public static final String MODEL_FOLDER_PATH = "model"; //$NON-NLS-1$
  /**
   * Templates folder path.
   */
  public static final String TEMPLATES_FOLDER_PATH = "templates"; //$NON-NLS-1$
}
