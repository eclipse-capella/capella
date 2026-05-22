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

  final String DELIMITER_KEY = "delimiter"; //$NON-NLS-1$
  
  final String DELIMITER_VALUE_TAB = Messages.IExportCSVPreferences_delimiter_tab;
  final String DELIMITER_VALUE_SEMICOLON = Messages.IExportCSVPreferences_delimiter_semicolon;
  final String DELIMITER_VALUE_COMMA = Messages.IExportCSVPreferences_delimiter_comma;
  final String DELIMITER_VALUE_SPACE = Messages.IExportCSVPreferences_delimiter_space;
  final String DELIMITER_VALUE_OTHER = Messages.IExportCSVPreferences_delimiter_other;
  final String DELIMITER_VALUE_DEFAULT = DELIMITER_VALUE_COMMA;

  final String OTHER_DELIMITER_KEY = "other"; //$NON-NLS-1$
  final String OTHER_DELIMITER_VALUE_DEFAULT = ""; //$NON-NLS-1$

  public char getDelimiterCurrentValue();

  public String getDelimiterOtherCurrentValue();

  public String getDelimiterCurrentName();
}
