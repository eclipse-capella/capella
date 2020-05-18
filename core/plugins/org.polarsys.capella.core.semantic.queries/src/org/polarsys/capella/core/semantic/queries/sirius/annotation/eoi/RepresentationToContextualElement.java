/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.sirius.annotation.eoi;

import org.polarsys.capella.core.diagram.helpers.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.semantic.queries.sirius.annotation.DAnnotationReference;

public class RepresentationToContextualElement extends DAnnotationReference {

  public RepresentationToContextualElement() {
    super(IRepresentationAnnotationConstants.ContextualElements);
  }

}
