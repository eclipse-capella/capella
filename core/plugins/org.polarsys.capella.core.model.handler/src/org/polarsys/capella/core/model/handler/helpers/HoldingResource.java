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
package org.polarsys.capella.core.model.handler.helpers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

public class HoldingResource extends ResourceImpl {
  /**
   * Constructor.
   */
  public HoldingResource(URI uri) {
    super(uri);
  }

  /**
   * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#doSave(java.io.OutputStream, java.util.Map)
   */
  @Override
  protected void doSave(OutputStream outputStream_p, Map<?, ?> options_p) throws IOException {
    // Do nothing, this resource can not be saved.
  }

  /**
   * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#isLoaded()
   */
  @Override
  public boolean isLoaded() {
    return true;
  }
}