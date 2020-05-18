/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.libraries;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.shared.id.handler.AbstractIdHandler;

public class LibraryIdHandler extends AbstractIdHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId(EObject object_p) {
    if (object_p instanceof LibraryAbstractElement) {
      return ((LibraryAbstractElement) object_p).getId();
    }
    return null;
  }

}
