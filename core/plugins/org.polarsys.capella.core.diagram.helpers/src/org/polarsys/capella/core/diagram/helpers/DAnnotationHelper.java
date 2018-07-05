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
package org.polarsys.capella.core.diagram.helpers;

import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Various helpers for {@link DAnnotation} annotations on {@link DModelElement} elements.
 */
public class DAnnotationHelper {

  /**
   * Create a DAnnotation with a given source on a given model element
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
   * Remove the first annotation with a given source from a given model element
   * @param source
   * @param representation
   */
  public static void removeAnnotation(String source, DModelElement representation) {
    DAnnotation annotation = getAnnotation(source, representation, false);
    if (annotation != null) {
      representation.getEAnnotations().remove(annotation);
    }
  }

  /**
   * Get the first annotation with a given source from a given model element, optionally
   * creating the annotation if none previously existed.
   * 
   * @param source
   * @param representation
   * @param create
   */
  public static DAnnotation getAnnotation(String source, DModelElement representation, boolean create) {
    for (DAnnotation annotation : representation.getEAnnotations()) {
      if (annotation.getSource().equals(source)) {
        return annotation;
      }
    }

    if (create) {
      return createAnnotation(source, representation);
    }

    return null;
  }

}
