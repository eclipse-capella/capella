/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.sirius.annotation.eoi;

import org.polarsys.capella.core.diagram.helpers.naming.DAnnotationSourceConstants;
import org.polarsys.capella.core.semantic.queries.sirius.annotation.InverseDAnnotationReference;

public class ElementToRepresentation extends InverseDAnnotationReference {

  public ElementToRepresentation() {
    super(DAnnotationSourceConstants.CAPELLA_ELEMENT_OF_INTEREST);
  }

}
