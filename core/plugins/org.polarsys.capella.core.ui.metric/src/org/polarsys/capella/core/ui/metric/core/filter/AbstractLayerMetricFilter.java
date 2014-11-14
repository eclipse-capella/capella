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
package org.polarsys.capella.core.ui.metric.core.filter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.ui.metric.core.IMetricFilter;

/**
 *
 */
abstract public class AbstractLayerMetricFilter implements IMetricFilter {

  protected List<EClass> _filteredTypes;
  
  /**
   * @see org.polarsys.capella.core.ui.metric.core.IMetricFilter#accept(org.eclipse.emf.ecore.EObject)
   */
  public boolean accept(EObject eobject_p) {
    
    return (
          null != eobject_p && 
          getFilteredTypes().contains(eobject_p.eClass())
    );
  }

  /**
   * provide a list of EClass of interest for this filter
   * @return see description
   */
  public List<EClass> getFilteredTypes() {

    if (null == _filteredTypes) {
      
      _filteredTypes = new ArrayList<EClass>();
      populateList();      
    }
    
    return _filteredTypes;
  }

  /**
   * For internal use only. Allow to simplify the potential implementation of this class...
   * It is only intended to be called by the getFilteredTypes method 
   */
  abstract protected void populateList();
  
}
