/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.test.framework.api.BasicTestCase;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

public class CapellaLoggerConfigTestCase extends BasicTestCase {
  private static final String INFORMATIONVIEW = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview";
  private static final String INFORMATIONVIEW_CONTENT_1 = "[A B] transfer to 1\n[C D] log to D\n[A B] transfer to 2\n";
  private static final String INFORMATIONVIEW_CONTENT_2 = "ABC";
  private static final String INFORMATIONVIEW_CONTENT_3 = "A;B\nC123\nE?#@\n";

  @Override
  public void test() throws Exception {
    testLogConfiguration(IReportManagerDefaultComponents.MODEL, Level.INFO, INFORMATIONVIEW_CONTENT_1);
    testLogConfiguration(IReportManagerDefaultComponents.MODEL, Level.ERROR, INFORMATIONVIEW_CONTENT_1);
    testLogConfiguration(IReportManagerDefaultComponents.DEFAULT, Level.WARN, INFORMATIONVIEW_CONTENT_2);
    testLogConfiguration(IReportManagerDefaultComponents.IMPACT, Level.WARN, INFORMATIONVIEW_CONTENT_3);
    testLogConfiguration(IReportManagerDefaultComponents.BRIDGE, Level.INFO, INFORMATIONVIEW_CONTENT_2);
    testLogConfiguration(IReportManagerDefaultComponents.DIAGRAM, Level.ERROR, INFORMATIONVIEW_CONTENT_1);
    testLogConfiguration(IReportManagerDefaultComponents.REFINEMENT, Level.ERROR, INFORMATIONVIEW_CONTENT_3);
    testLogConfiguration(IReportManagerDefaultComponents.UI, Level.ERROR, INFORMATIONVIEW_CONTENT_2);
    testLogConfiguration(IReportManagerDefaultComponents.VALIDATION, Level.ERROR, INFORMATIONVIEW_CONTENT_1);
  }

  protected void testLogConfiguration(String componentName, Level logLevel, String message) {
    Logger logger = Logger.getLogger(componentName);
    logger.log(logLevel, message);

    assertEquals("InformationView content is incorrect.", message, getInformationViewContent());
  }

  /*
   * get the content of the information view and remove the already processed markers
   */
  private String getInformationViewContent() {
    StringBuilder elementsList = new StringBuilder();
    for (IMarker marker : LightMarkerRegistry.getInstance().getMarkers()) {
      try {
        if (marker.getType().equals(INFORMATIONVIEW)) {
          if (elementsList.length() != 0) {
            elementsList.append("\n");
          }
          elementsList.append(marker.getAttribute(IMarker.MESSAGE));
        }
      } catch (CoreException e) {
      }
    }

    // remove the markers
    new ArrayList<IMarker>(LightMarkerRegistry.getInstance().getMarkers()).stream().forEach(marker -> {
      try {
        marker.delete();
      } catch (CoreException e) {
        // Nothing here
      }
    });

    return elementsList.toString();
  }

}
