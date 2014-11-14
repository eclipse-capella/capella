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
package org.polarsys.capella.core.model.handler.helpers;

import org.eclipse.emf.common.util.URI;

/**
 */
public interface ICapellaResourceHelper {
  /**
   * Is specified {@link URI} a Capella resource ?
   * @param uri_p must be not <code>null</code>.
   * @return <code>true</code> means the specified uri is a Capella resource uri.
   */
  public boolean isCapellaResource(URI uri_p);
}
