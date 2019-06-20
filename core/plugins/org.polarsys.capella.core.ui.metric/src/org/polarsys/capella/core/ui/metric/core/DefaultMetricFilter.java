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
