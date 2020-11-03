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
  public String getId(EObject object) {
    if (object instanceof ModelElement) {
      return ((ModelElement) object).getId();
    }
    return null;
  }
}
