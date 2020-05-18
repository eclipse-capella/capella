/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.emf.ecore.EObject;

/**
 * Handler to delegate the read only listener behavior.
 */
public interface IReadOnlySectionHandler {
  /**
   * Register given parameters.
   * @param semanticElement_p
   * @param listener_p
   */
  void register(EObject semanticElement_p, IReadOnlyListener listener_p);

  /**
   * Unregister given parameters.
   * @param semanticElement_p
   * @param listener_p
   */
  void unregister(EObject semanticElement_p, IReadOnlyListener listener_p);

  /**
   * Is specified object locked by others ?
   * @param semanticElement_p
   * @return
   */
  boolean isLockedByOthers(EObject semanticElement_p);

  /**
   * Is specified object controllable ?
   * @param semanticElement_p
   * @return
   */
  boolean isControllable(EObject semanticElement_p);
}
