/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.export;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.export.AbstractExporter;
import org.polarsys.capella.common.helpers.export.CSVExporter;
import org.polarsys.capella.common.helpers.export.IExporterProvider;
import org.polarsys.capella.common.helpers.export.TXTExporter;
import org.polarsys.capella.common.ui.preferences.IExportCSVPreferences;

/**
 * Common exporter for Rules
 */
public class RulesExporterProvider implements IExporterProvider {

  /**
   * @see org.polarsys.capella.common.helpers.export.IExporterProvider#getAvailableExporter()
   */
  @Override
  public List<AbstractExporter> getAvailableExporter() {

    List<AbstractExporter> list = new ArrayList<AbstractExporter>();
    
    list.add(new CSVExporter(IExportCSVPreferences.INSTANCE.getDelimiterCurrentValue()));
    list.add(new TXTExporter());
    
    return list;
    
  }

}
