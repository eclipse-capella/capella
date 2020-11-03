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
package org.polarsys.capella.core.model.handler.command;

import org.eclipse.emf.common.util.URI;

/**
 *
 */
public interface ICapellaResourceHelper {
  /**
   * Is specified {@link URI} a Capella resource ?
   * @param uri must be not <code>null</code>.
   * @return <code>true</code> means the specified uri is a Capella resource uri.
   */
  public boolean isCapellaResource(URI uri);
}
