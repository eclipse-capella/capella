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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.extpoint;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.IReportLogViewMarkerIdsProvider;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Utility class for the filterOnReportView extension point.
 */
public class ReportLogViewExtPointUtil {

  /**
   * Return an array 
   * @param viewID
   * @return
   */
  public static String[] getMarkersID(String viewID) {
    
    String result[] = null;
    
    // Get all the contribution to the marker filtering on report log.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(
            MarkerViewPlugin.PLUGIN_ID,
            IReportLogViewExtPointConstants.EXT_POINT_ID
        );
    
    IReportLogViewMarkerIdsProvider provider = null;
    
    for (IConfigurationElement configurationElement : configurationElements) {
      String id = getTargetViewId(configurationElement);
      if (id.equals(viewID)) { // we stop on the first contribution we found
        provider = instanciateMarkerIdsProvider(configurationElement);
        break;
      } 
    }
    
    result = ( null != provider ) ? provider.getMarkerIds() : null;
    
    return result;
  }
  
  /**
   * Instanciate a {@link ITestSuitesProvider} object from a capellaTestSuitesProvider extension point contribution.
   * @param configurationElement
   * @return  <code>null</code> whether configurationElement is <code>null</code> or not set in proper way.
   */
  private static IReportLogViewMarkerIdsProvider instanciateMarkerIdsProvider(IConfigurationElement configurationElement){
    
    IReportLogViewMarkerIdsProvider result = null;
 
    if (isFilterOnMarkersProviderContribution(configurationElement)) {
    
      try {
        result = (IReportLogViewMarkerIdsProvider) 
          ExtensionPointHelper.createInstance(
              configurationElement,
              IReportLogViewExtPointConstants.FILTER_PROVIDER_ATT
          )
        ;
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    
    return result;
  }
  
  /**
   * See the 'type' attribute of the capellaTestSuitesProvider extension point.
   * @param configurationElement
   * @return an empty {@link String} whether the contribution is not of good type or whether this value is not set. 
   */
  private static String getTargetViewId(IConfigurationElement configurationElement) {
    
    String result = ICommonConstants.EMPTY_STRING;
    
    if (isFilterOnMarkersProviderContribution(configurationElement)) {
      result = configurationElement.getAttribute(IReportLogViewExtPointConstants.VIEW_ID_ATT);
    }
    
    return result;
  }

  /**
   * Check whether a given configuration element is a contribution to the 
   * filterOnReportView extension point of type reportView. 
   * @param configurationElement
   * @return <code>true</code> if configurationElement is not <code>null</code> and verifies the condition, <code>false</code> otherwise. 
   */
  public static boolean isFilterOnMarkersProviderContribution(IConfigurationElement configurationElement) {
    
    boolean result =
      null != configurationElement &&
      configurationElement.getName().equals(
          IReportLogViewExtPointConstants.REPORT_VIEW_NODE
      )
    ;
    
    return result;
  }
}
