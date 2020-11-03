/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers.export;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Brutal text format export with an space character as separator
 *
 */
public class TXTExporter extends CSVExporter {
  
  public TXTExporter() {
    super(ICommonConstants.WHITE_SPACE_CHARACTER);
  }

  /**
   * @see org.polarsys.capella.core.ui.metric.export.AbstractExporter#getExtension()
   */
  @Override
  public String getExtension() {
    return IFileExtensionConstants.TXT_EXTENSION;
  }

  /**
   * @see org.polarsys.capella.core.ui.metric.export.CSVExporter#getDescription()
   */
  @Override
  public String getDescription() {
    return ExportMessages.txtDesc;
  }
  
}
