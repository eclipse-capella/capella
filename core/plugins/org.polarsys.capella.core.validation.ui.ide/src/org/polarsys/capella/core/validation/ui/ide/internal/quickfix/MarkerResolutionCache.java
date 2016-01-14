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
package org.polarsys.capella.core.validation.ui.ide.internal.quickfix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.ui.IMarkerResolution;

import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.utils.ValidationHelper;

/**
 * Cache for Capella marker resolutions.
 * Note that no dynamically contribution are allowed.
 *
 */
final public class MarkerResolutionCache {

  /** Singleton access */
  public static MarkerResolutionCache INSTANCE = new MarkerResolutionCache();
  
  /** no resolution found */
  public static final IMarkerResolution[] NO_RESOLUTIONS = new IMarkerResolution[0];
  
  /** constraintId<=>AbstractCapellaMarkerResolution map */
  Map<String, Set<AbstractCapellaMarkerResolution>> _map;
  
  /** AbstractCapellaMarkerResolution<=>constraintId map */
  Map<AbstractCapellaMarkerResolution, Set<String>> resolverRuleMap;
  
  /**
   * Constructor 
   */
  private MarkerResolutionCache() {
    initCache();
  }
  
  /**
   * Get the registered marker resolver for this constraint
   * @param constraintId_p
   * @return
   */
  public IMarkerResolution[] getResolutionsFor(String constraintId_p) {
    
    IMarkerResolution[] result = NO_RESOLUTIONS;
    
    if ( null != constraintId_p && constraintId_p.length() > 0 ) {
      Set<AbstractCapellaMarkerResolution> set = _map.get(constraintId_p);
      if (null != set) {
        result = set.toArray(
            new AbstractCapellaMarkerResolution[]{}
        );
      }
    }
    
    return result;
  }
  
  
  public Map<AbstractCapellaMarkerResolution, Set<String>> getResolverRuleMap() {
    return resolverRuleMap;
  }

  protected void initCache() {
    
    // On a first hand, let's initialize the cache map 
    _map = new HashMap<String, Set<AbstractCapellaMarkerResolution>>();
    
    // First of all, let's get all contribution
    Map<AbstractCapellaMarkerResolution, Set<String>> map = CapellaQuickFixExtPointUtil.gettAllAvailableMarkerResolution();
    resolverRuleMap = map;

    // The available constraint ids
    HashSet<String> allConstraintsIds = new HashSet<String>();
    for (IConstraintDescriptor icd: ValidationHelper.getAllConstraintDescriptors()) {
      if ( null != icd && null != icd.getId() && icd.getId().length() > 0 )
      allConstraintsIds.add(icd.getId());
    }
    
    // Workaround to support a quickfix for EObject multiplicity
    allConstraintsIds.add("org.eclipse.emf.ecore.1"); //$NON-NLS-1$
    
    Set<String> ids = null; // the constraint ids obtained from the contribution to the extension point
    for (AbstractCapellaMarkerResolution key: map.keySet()) {
      ids = map.get(key);
      for (String id: ids) {
        if (allConstraintsIds.contains(id)) {
          addToCache(id, key);
        } else {
          // Many capella qf extensions don't use the fully qualified constraint id, 
          // but only the 'unqualified id', e.g. DWF_D_20 rather than org.polarsys.capella...DWF_D_20
          for (String current: allConstraintsIds) {
            if (current.endsWith(id)) {
              addToCache(current, key);
              break;
            }
          }
        }
      }
    }
    
    return;
  }
  
  /** for internal use */
  private void addToCache(String constraintID_p, AbstractCapellaMarkerResolution markerResolution_p) {
    
    String key = constraintID_p;
    
    if ( null == _map) {
      _map = new HashMap<String, Set<AbstractCapellaMarkerResolution>>();
    }
    
    if ( _map.containsKey(key) ) {
      _map.get(key).add(markerResolution_p);
    } else {
      Set<AbstractCapellaMarkerResolution> value = new HashSet<AbstractCapellaMarkerResolution>();
      value.add(markerResolution_p);
      _map.put(key, value);
    }
    
    return;
  }
  
  protected void cleanCache() {
    
    for (Set<AbstractCapellaMarkerResolution> set: _map.values()) {
      set.clear();
    }
    _map.clear();
    
    return;
  }
  
}
