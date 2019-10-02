/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.sirius.annotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;

/**
 * Calculates all elements referenced by a DModelElement via a contained DAnnotation of a given source string.
 */
public class DAnnotationReference extends DAnnotationQuery {

  public DAnnotationReference(String source) {
    super(source);
  }

  /**
   * {@inheritDoc}
   */
  public List<Object> compute(Object object_p) {
    DAnnotation annot = DAnnotationHelper.getAnnotation(source, (DModelElement) object_p, false);
    if (annot != null) {
      return new ArrayList<Object>(annot.getReferences());
    }
    return Collections.emptyList();
  }

}
