/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers.traceability;

import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * A traceability manager for diagram
 */
public interface IDiagramTraceability {

  /**
   * Returns whether the given representation can realize an other representation
   * (for instance, any OA cannot realize another diagram)
   * @param realizing
   * @return
   */
  boolean isRealizingable(DRepresentationDescriptor realizing);

  /**
   * Returns whether the given representation can be realized by another representation
   * (for instance, any EPBS cannot be realized by another diagram)
   * @param realized
   * @param realizing
   * @return
   */
  boolean isRealizable(DRepresentationDescriptor realized, DRepresentationDescriptor realizing);

  /**
   * Some traceability informations can be stored in helper to optimize performance.
   * Calling dispose() will clean stored information but all other methods of helper must works even after a dispose.
   */
  void dispose();

}
