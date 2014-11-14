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
package org.polarsys.capella.common.id.handler;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.shared.id.handler.AbstractIdHandler;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class ModelElementIdHandler extends AbstractIdHandler {

  /**
   * Default constructor
   */
  public ModelElementIdHandler() {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  public String getId(EObject object_p) {
    if (object_p instanceof ModelElement) {
      return ((ModelElement) object_p).getId();
    }
    return null;
  }
}
