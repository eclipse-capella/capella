/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.merge.scope;

import org.eclipse.emf.ecore.EObject;

/**
   * An interface defining filters for model elements
   */
public interface IModelScopeFilter {
  /**
   * Return whether the given element is accepted by this filter
   * @param element a non-null element
   */
  boolean accepts(EObject element);
}
