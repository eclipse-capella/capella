/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.sirius.annotation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * Calculates all DAnnotations with a given source string referencing a given element.
 * Instead of returning the annotation itself, its container is returned, but subclasses
 * can override {@link #getResultObject(DAnnotation)} to customize this behaviour. 
 */
public class InverseDAnnotationReference extends DAnnotationQuery {

  public InverseDAnnotationReference(String source) {
    super(source);
  }
  
  /**
   * {@inheritDoc}
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    for (EObject e : new EObjectQuery((EObject)object).getInverseReferences(DescriptionPackage.Literals.DANNOTATION__REFERENCES)) {
      DAnnotation annot = (DAnnotation) e;
      if (source.equals(annot.getSource())) {
        result.add(getResultObject(annot));
      }
    }

    return result;
  }

  /**
   * Transform the result. The default is to return the annotation's container.
   * @param d
   * @return
   */
  protected EObject getResultObject(DAnnotation d) {
    return d.eContainer();
  }

}
