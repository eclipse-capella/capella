/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common;

import org.eclipse.emf.ecore.EObject;

/**
 */
public interface ITransform {
  /**
   * Set the transformation context as a semantic element.
   * @param context_p
   */
  public void setContext(EObject context_p);

  /**
   * Execute transformation.
   */
  public void execute();
}
