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
