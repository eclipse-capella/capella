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
package org.polarsys.capella.core.projection.common.rules;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public interface IRuleTransformation {

  /**
   * Returns for the given element_p whether a transformation is required (already transformed or not according the given context_p)
   */
  public IStatus transformRequired(EObject source_p, IContext context_p);

}
