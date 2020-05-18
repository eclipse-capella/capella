/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.services;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public interface IElementIdentifierService {

  /**
   * Returns a unique identifier for this element. Note that this identifier can be <b>null</b> for invalid parameters.
   * 
   * @param diagram
   *          the diagram containing the element. Must not be null.
   * @param element
   *          the element that must be owned (directly or not) by the diagram. Must not be null.
   * @return a unique identifier for this element or null for invalid parameters.
   */
  String getIdentifier(DiagramDescription diagram, IdentifiedElement element);

  /**
   * Returns a unique identifier for the representationDescription.
   * 
   * @param representationDescription
   *          the representationDescription.
   * @return a unique identifier.
   */
  String getIdentifier(RepresentationDescription representationDescription);

  /**
   * Returns the unique identifier of the viewpoint.
   * 
   * @param viewpoint
   *          the viewpoint
   * @return the unique identifier.
   */
  String getIdentifier(Viewpoint viewpoint);

}
