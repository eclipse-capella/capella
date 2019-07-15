/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.services;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

public interface IElementIdentifierService {

  /**
   * Returns a unique identifier for this element. Note that this identifier can be <b>null</b> for invalid parameters.
   * 
   * @param diagram
   *          the diagram containing the element.
   * @param element
   *          the element.
   * @return a unique identifier for this element or null for invalid parameters.
   */
  String getIdentifier(DiagramDescription diagram, IdentifiedElement element);

}
