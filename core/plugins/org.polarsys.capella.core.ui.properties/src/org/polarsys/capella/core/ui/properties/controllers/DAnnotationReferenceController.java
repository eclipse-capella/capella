/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;

public abstract class DAnnotationReferenceController implements IMultipleSemanticFieldController {

  private final String dAnnotationSource;

  public DAnnotationReferenceController(String dAnnotationSource) {
    this.dAnnotationSource = dAnnotationSource;
  }
  
  @Override
  public List<EObject> loadValues(EObject element, EStructuralFeature feature) {
    DAnnotation annot = DAnnotationHelper.getAnnotation(dAnnotationSource, (DModelElement) element, false);
    if (annot != null) {
      return annot.getReferences();
    } 
    return Collections.emptyList();
  }

  @Override
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
      List<EObject> values) {
    DAnnotation annot = DAnnotationHelper.getAnnotation(dAnnotationSource, (DModelElement) semanticElement, true);
    annot.getReferences().retainAll(values);
    annot.getReferences().addAll(values);
    if (annot.getReferences().isEmpty()) {
      ((DModelElement) semanticElement).getEAnnotations().remove(annot);
    }
    return values;
  }

  /**
   * Removes the annotation from the semantic element
   * @param semanticElement
   */
  public void clear(EObject semanticElement) {
    DAnnotationHelper.deleteAnnotation(dAnnotationSource, (DModelElement) semanticElement);
  }

}
