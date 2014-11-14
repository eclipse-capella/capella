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
  public EObject getEObject(String id_p, IScope scope) {
    for (Resource resource : scope.getResources()) {
      EObject obj = resource.getEObject(id_p);
      if (null != obj) return obj;
    }
    return null;
  }
}
