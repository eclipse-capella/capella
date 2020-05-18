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
 * Interface in order to filter {@link EObject} for metrics ops.
 *
 */
public interface IMetricFilter {

  public boolean accept(EObject eobject);
  
}
