/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.diagram.helpers.traceability;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A traceability manager for diagram
 */
public interface IDiagramTraceability {

  /**
   * Returns list of realizing diagrams for the given representation
   * @param representation
   * @return
   */
  Collection<DRepresentation> getRealizingRepresentations(DRepresentation representation);

  /**
   * Returns list of realized diagrams for the given representation
   * @param representation
   * @return
   */
  Collection<DRepresentation> getRealizedRepresentations(DRepresentation representation);

  /**
   * Add a traceability link between both representations, the realized and the realizing
   * @param realized
   * @param realizing
   * @return
   */
  IStatus addRealizingRepresentation(DRepresentation realized, DRepresentation realizing);

  /**
   * Returns whether the given representation can realize an other representation
   * (for instance, any OA cannot realize another diagram)
   * @param realizing
   * @return
   */
  boolean isRealizingable(DRepresentation realizing);

  /**
   * Returns whether the given representation can be realized by another representation
   * (for instance, any EPBS cannot be realized by another diagram)
   * @param realized
   * @param realizing
   * @return
   */
  boolean isRealizable(DRepresentation realized, DRepresentation realizing);

  /**
   * Some traceability informations can be stored in helper to optimize performance.
   * Calling dispose() will clean stored information but all other methods of helper must works even after a dispose.
   */
  void dispose();

}
