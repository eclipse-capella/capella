/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.preferences;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public interface IExportCSVPreferences {

  public static final IExportCSVPreferences INSTANCE = new ExportCSVPreferences();

  final String DELIMITER_NAME_TAB = "tab";
  final String DELIMITER_NAME_SEMICOLON = "semicolon";
  final String DELIMITER_NAME_COMMA = "comma";
  final String DELIMITER_NAME_SPACE = "space";
  final String DELIMITER_NAME_OTHER = "other";

  final String DELIMITER_VALUE_OTHER = "";
  final String DELIMITER_VALUE_OTHER_DEFAULT = "DEFAULT";
  final char DELIMITER_VALUE_DEFAULT = ICommonConstants.COMMA_CHARACTER;
  final String DELIMITER_NAME_CURRENT = DELIMITER_NAME_COMMA;

  public char getDelimiterCurrentValue();

  public String getDelimiterOtherCurrentValue();

  public String getDelimiterCurrentName();
}
