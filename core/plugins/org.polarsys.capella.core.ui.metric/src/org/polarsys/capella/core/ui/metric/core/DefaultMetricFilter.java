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
package org.polarsys.capella.core.ui.metric.core;

import org.eclipse.emf.ecore.EObject;

/**
 * Default filter for Metrics.
 * Accept All EObject e.g. filters nothing
 *
 */
public class DefaultMetricFilter implements IMetricFilter {

  /**
   * @see org.polarsys.capella.core.ui.metric.core.IMetricFilter#accept(org.eclipse.emf.ecore.EObject)
   */
  public boolean accept(EObject eobject) {
    
    return true;
  }

}
