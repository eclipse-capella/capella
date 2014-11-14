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
package org.polarsys.capella.core.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IClientSelector;

import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Class used to match {@link ModelElement} elements.
 */
public class ValidationDelegateClientSelector implements IClientSelector {
  /**
   * @see org.eclipse.emf.validation.model.IClientSelector#selects(java.lang.Object)
   */
  public boolean selects(Object object_p) {
    // FIXME It seems not normal to pass on EObject...
    return (object_p instanceof EObject);
  }
}
