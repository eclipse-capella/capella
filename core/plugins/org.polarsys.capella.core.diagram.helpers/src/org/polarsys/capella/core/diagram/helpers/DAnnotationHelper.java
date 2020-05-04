/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Various helpers for {@link DAnnotation} annotations on {@link DModelElement} elements.
 */
public class DAnnotationHelper {

  /**
   * Create a DAnnotation with a given source on a given model element
   * 
   * @param source
   * @param representation
   */
  public static DAnnotation createAnnotation(final String source, DModelElement representation) {
    DAnnotation annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation.setSource(source);
    representation.getEAnnotations().add(annotation);
    return annotation;
  }

  /**
   * Get the first annotation with a given source from a given model element, optionally creating the annotation if none
   * previously existed.
   * 
   * @param source
   * @param representation
   * @param create
   */
  public static DAnnotation getAnnotation(String source, DModelElement representation, boolean create) {
    for (DAnnotation annotation : representation.getEAnnotations()) {
      if (annotation.getSource() != null && annotation.getSource().equals(source)) {
        return annotation;
      }
    }

    if (create) {
      return createAnnotation(source, representation);
    }

    return null;
  }

  /**
   * Returns whether the element has the given element
   * 
   * @param source
   * @param representation
   */
  public static boolean hasAnnotation(String source, DModelElement representation) {
    return getAnnotation(source, representation, false) != null;
  }

  /**
   * Delete the first annotation with a given source from a given model element
   * 
   * @param source
   * @param representation
   * @return true if an annotation was deleted, false otherwise
   */
  public static boolean deleteAnnotation(String source, DModelElement representation) {
    DAnnotation annotation = getAnnotation(source, representation, false);
    if (annotation != null) {
      SiriusUtil.delete(annotation);
      return true;
    }
    return false;
  }

}
