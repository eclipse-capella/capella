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
   * @param representation_p
   * @return
   */
  public Collection<DRepresentation> getRealizingRepresentations(DRepresentation representation_p);

  /**
   * Returns list of realized diagrams for the given representation
   * @param representation_p
   * @return
   */
  public Collection<DRepresentation> getRealizedRepresentations(DRepresentation representation_p);

  /**
   * Add a traceability link between both representations, the realized and the realizing
   * @param realized_p
   * @param realizing_p
   * @return
   */
  public IStatus addRealizingRepresentation(DRepresentation realized_p, DRepresentation realizing_p);

  /**
   * Returns whether the given representation can realize an other representation
   * (for instance, any OA cannot realize another diagram)
   * @param realizing_p
   * @return
   */
  public boolean isRealizingable(DRepresentation realizing_p);

  /**
   * Returns whether the given representation can be realized by another representation
   * (for instance, any EPBS cannot be realized by another diagram)
   * @param realized_p
   * @param realizing_p
   * @return
   */
  public boolean isRealizable(DRepresentation realized_p, DRepresentation realizing_p);

  /**
   * Some traceability informations can be stored in helper to optimize performance.
   * Calling dispose() will clean stored information but all other methods of helper must works even after a dispose.
   */
  public void dispose();

}
