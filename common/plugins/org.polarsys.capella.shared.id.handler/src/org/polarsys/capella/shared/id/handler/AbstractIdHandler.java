/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.shared.id.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 */
public abstract class AbstractIdHandler implements IIdHandler {

  /**
   * Default constructor
   */
  public AbstractIdHandler() {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  public EObject getEObject(String id, IScope scope) {
    for (Resource resource : scope.getResources()) {
      EObject obj = resource.getEObject(id);
      if (null != obj) return obj;
    }
    return null;
  }
}
