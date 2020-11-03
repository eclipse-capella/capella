/*******************************************************************************
 * Copyright (c) 2019, 2020 Thales Global Services S.A.S.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.menu.dynamic.util;

import org.eclipse.emf.ecore.EObject;

/**
 * This service provides the name prefix for a given object.
 *
 */
public interface INamePrefixService {

  /**
   * Returns the name prefix for the given object.
   * 
   * @param object
   *          the given object.
   * @return the name prefix for the given object.
   */
  String getPrefix(EObject object);
}
