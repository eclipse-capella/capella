/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.helpers.export.AbstractExporter;
import org.polarsys.capella.common.helpers.export.CSVExporter;
import org.polarsys.capella.common.helpers.export.DataExporter;
import org.polarsys.capella.common.helpers.export.IExporterProvider;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.common.ui.preferences.IExportCSVPreferences;
import org.polarsys.capella.core.validation.export.RulesExporterProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ExportCSVPreferencesTest extends BasicTestCase {
  private ScopedPreferenceStore preferenceStore;

  public ExportCSVPreferencesTest() {
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
  }

  @Override
  public void test() throws Exception {
    updateExportCSVPreference(IExportCSVPreferences.DELIMITER_VALUE_TAB);
    exportWithCSVDelimiter(IExportCSVPreferences.DELIMITER_VALUE_TAB, ICommonConstants.TAB_CHARACTER);

    updateExportCSVPreference(IExportCSVPreferences.DELIMITER_VALUE_OTHER);
    preferenceStore.putValue(IExportCSVPreferences.OTHER_DELIMITER_KEY, "#");
    exportWithCSVDelimiter(IExportCSVPreferences.DELIMITER_VALUE_OTHER, '#');

    updateExportCSVPreference(IExportCSVPreferences.DELIMITER_VALUE_COMMA);
    exportWithCSVDelimiter(IExportCSVPreferences.DELIMITER_VALUE_COMMA, ICommonConstants.COMMA_CHARACTER);
  }

  private void updateExportCSVPreference(String newPref) {
    preferenceStore.putValue(IExportCSVPreferences.DELIMITER_KEY, newPref);
    String pref = preferenceStore.getString(IExportCSVPreferences.DELIMITER_KEY);
    assertTrue(pref == newPref);
  }

  private void exportWithCSVDelimiter(String newPref, char separatorCode) {
    IExporterProvider exporterProvider = new RulesExporterProvider();
    List<AbstractExporter> exporters = exporterProvider.getAvailableExporter();
    
    assertTrue(exporters.stream().filter(x -> x instanceof CSVExporter).count() > 0);
    DataExporter dataExporter = new DataExporter(exporterProvider);

    List<String[]> data = Arrays.asList(new String[] { "line1", "val1" }, new String[] { "line2", "val2" },
        new String[] { "line3", "val3" });
    String filename = "test.csv";
    dataExporter.exportToFile(filename, data);

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      for (String[] dataLine : data) {
        String line = br.readLine();
        if (line != null) {
          assertTrue(line.equals(dataLine[0] + separatorCode + dataLine[1]));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
