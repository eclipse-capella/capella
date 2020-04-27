/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.internal.quickfix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Utility class for the capellaQuickFix extension point.
 *
 */
public class CapellaQuickFixExtPointUtil {

  
  static public Map<AbstractCapellaMarkerResolution, Set<String>> gettAllAvailableMarkerResolution() {
    
    Map<AbstractCapellaMarkerResolution, Set<String>> result = new HashMap<AbstractCapellaMarkerResolution, Set<String>>();
    
    for (IConfigurationElement elt: getAllContributions()) {
      
      AbstractCapellaMarkerResolution resolverClass = null;
      
      // the MarkerResolution class
      resolverClass = (AbstractCapellaMarkerResolution) 
        ExtensionPointHelper.createInstance(
          elt,
          ICapellaQuickFixExtPointConstants.CLASS_ATT
        )
      ;
      
      if (null != resolverClass) {
      
        String label = elt.getAttribute(ICapellaQuickFixExtPointConstants.LABEL_ATT);
        String desc = elt.getAttribute(ICapellaQuickFixExtPointConstants.DESC_ATT);
        String iconKey =elt.getAttribute(ICapellaQuickFixExtPointConstants.ICON_ATT);
        
        resolverClass.setLabel(label);
        resolverClass.setDescription(desc);
        resolverClass.setImgKey(iconKey);        
        resolverClass.setContributorId(elt.getContributor().getName());
        
        result.put(resolverClass, getConstraintIds(elt.getChildren()));  
      }        
    }
    
    return result;
  }
  
  /** the constraint Ids */
  static private Set<String> getConstraintIds(IConfigurationElement[] elts) {
    
    Set<String> result = new HashSet<String>();
    
    if ( null == elts ) {
      return result;
    }
    
    String id = null;
    for (IConfigurationElement elt: elts) {
      id = elt.getAttribute(ICapellaQuickFixExtPointConstants.RULE_ID_PATTERN_ATT);
      if ( null != id && id.length() > 0 ) {
        result.add(id);
      }
    }
    
    return result;
  }
  
  /** the contributions (brut) */
  static private IConfigurationElement[] getAllContributions() {
    
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(
            PluginActivator.getDefault().getPluginId(),
            ICapellaQuickFixExtPointConstants.EXT_POINT_ID
        )
    ;
   
    return configurationElements;
  }
  
}
