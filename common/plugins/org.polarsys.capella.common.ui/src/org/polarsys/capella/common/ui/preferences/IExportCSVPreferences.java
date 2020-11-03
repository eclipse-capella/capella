/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.preferences;

public interface IExportCSVPreferences {

  public static final IExportCSVPreferences INSTANCE = new ExportCSVPreferences();

  final String DELIMITER_KEY = "delimiter";
  
  final String DELIMITER_VALUE_TAB = "tab";
  final String DELIMITER_VALUE_SEMICOLON = "semicolon";
  final String DELIMITER_VALUE_COMMA = "comma";
  final String DELIMITER_VALUE_SPACE = "space";
  final String DELIMITER_VALUE_OTHER = "other";
  final String DELIMITER_VALUE_DEFAULT = DELIMITER_VALUE_COMMA;

  final String OTHER_DELIMITER_KEY = "other";
  final String OTHER_DELIMITER_VALUE_DEFAULT = "";

  public char getDelimiterCurrentValue();

  public String getDelimiterOtherCurrentValue();

  public String getDelimiterCurrentName();
}
