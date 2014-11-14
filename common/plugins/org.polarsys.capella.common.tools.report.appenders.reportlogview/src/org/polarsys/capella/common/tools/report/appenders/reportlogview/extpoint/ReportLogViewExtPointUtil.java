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
   * @param viewID_p
   * @return
   */
  public static String[] getMarkersID(String viewID_p) {
    
    String result[] = null;
    
    // Get all the contribution to the marker filtering on report log.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(
            MarkerViewPlugin.PLUGIN_ID,
            IReportLogViewExtPointConstants.EXT_POINT_ID
        )
    ;
    
    IReportLogViewMarkerIdsProvider provider = null;
    
    String viewId = null;
    for (IConfigurationElement configurationElement : configurationElements) {
      viewId = getTargetViewId(configurationElement);
      if (viewId.equals(viewID_p)) { // we stop on the first contribution we found
        provider = instanciateMarkerIdsProvider(configurationElement);
        break;
      } 
    }
    
    result = ( null != provider ) ? provider.getMarkerIds() : null;
    
    return result;
  }
  
  /**
   * Instanciate a {@link ITestSuitesProvider} object from a capellaTestSuitesProvider extension point contribution.
   * @param configurationElement_p
   * @return  <code>null</code> whether configurationElement_p is <code>null</code> or not set in proper way.
   */
  private static IReportLogViewMarkerIdsProvider instanciateMarkerIdsProvider(IConfigurationElement configurationElement_p){
    
    IReportLogViewMarkerIdsProvider result = null;
 
    if (isFilterOnMarkersProviderContribution(configurationElement_p)) {
    
      try {
        result = (IReportLogViewMarkerIdsProvider) 
          ExtensionPointHelper.createInstance(
              configurationElement_p,
              IReportLogViewExtPointConstants.FILTER_PROVIDER_ATT
          )
        ;
      } catch (Exception exception_p) {
        exception_p.printStackTrace();
      }
    }
    
    return result;
  }
  
  /**
   * See the 'type' attribute of the capellaTestSuitesProvider extension point.
   * @param configurationElement_p
   * @return an empty {@link String} whether the contribution is not of good type or whether this value is not set. 
   */
  private static String getTargetViewId(IConfigurationElement configurationElement_p) {
    
    String result = ICommonConstants.EMPTY_STRING;
    
    if (isFilterOnMarkersProviderContribution(configurationElement_p)) {
      result = configurationElement_p.getAttribute(IReportLogViewExtPointConstants.VIEW_ID_ATT);
    }
    
    return result;
  }

  /**
   * Check whether a given configuration element is a contribution to the 
   * filterOnReportView extension point of type reportView. 
   * @param configurationElement_p
   * @return <code>true</code> if configurationElement_p is not <code>null</code> and verifies the condition, <code>false</code> otherwise. 
   */
  public static boolean isFilterOnMarkersProviderContribution(IConfigurationElement configurationElement_p) {
    
    boolean result =
      null != configurationElement_p &&
      configurationElement_p.getName().equals(
          IReportLogViewExtPointConstants.REPORT_VIEW_NODE
      )
    ;
    
    return result;
  }
}
