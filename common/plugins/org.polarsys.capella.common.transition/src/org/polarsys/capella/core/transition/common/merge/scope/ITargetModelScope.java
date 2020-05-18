/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public interface ITargetModelScope {

  public boolean isDirty();
  
  Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement);
  
  interface Edit {
    
    public void setDirty(boolean dirty);

  }
}
