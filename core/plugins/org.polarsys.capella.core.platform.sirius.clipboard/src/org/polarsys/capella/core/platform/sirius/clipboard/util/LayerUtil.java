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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;



/**
 * Utility class for inter-layer model navigation (GEF / GMF / Sirius / Capella)
 */
public final class LayerUtil { 

  private LayerUtil() {
    // Forbids instantiation
  }

  
  // CROSS-LAYER BRIDGES: from top to bottom (GEF -> GMF -> Sirius -> Capella)
  
  public static View getGmfElement(EditPart editPart_p) {
    View result = null;
    if (editPart_p != null) {
      Object modelElement = editPart_p.getModel();
      if (modelElement instanceof View)
        result = (View)modelElement;
    }
    return result;
  }
  
  public static DSemanticDecorator getSiriusElement(View view_p) {
    DSemanticDecorator result = null;
    if (view_p != null) {
      EObject representedElement = view_p.getElement();
      if (representedElement instanceof DSemanticDecorator)
        result = (DSemanticDecorator)representedElement;
    }
    return result;
  }

  public static EObject getSemanticElement(DSemanticDecorator siriusElt_p) {
    EObject result = null;
    if (siriusElt_p != null)
      result = siriusElt_p.getTarget();
    return result;
  }

  // From any layer to the semantic layer
  public static EObject toSemanticLevel(Object element_p) {
    EObject result = null;
    Object current = element_p;
    if (current instanceof EditPart)
      current = getGmfElement((EditPart)current);
    if (current instanceof View)
      current = getSiriusElement((View)current);
    if (current instanceof DSemanticDecorator)
      result = LayerUtil.getSemanticElement((DSemanticDecorator)current);
    return result;
  }

  // Generalization to collections
  
  public static List<View> toGmf(Iterable<? extends EditPart> editParts_p) {
    List<View> result = new ArrayList<View>();
    for (EditPart current : editParts_p) {
      View view = getGmfElement(current);
      if (view != null) result.add(view);
    }
    return result;
  }

  public static List<DSemanticDecorator> toSirius(Iterable<? extends View> views_p) {
    List<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    for (View current : views_p) {
      DSemanticDecorator siriusElement = getSiriusElement(current);
      if (siriusElement != null) result.add(siriusElement);
    }
    return result;
  }

  public static List<EObject> toSemanticLevel(
      Iterable<?> elements_p) {
    List<EObject> result = new ArrayList<EObject>();
    for (Object current : elements_p) {
      EObject semanticElement = toSemanticLevel(current);
      if (semanticElement != null) result.add(semanticElement);
    }
    return result;
  }

  
  // CROSS-LAYER BRIDGES: from bottom to top using the global cross-referencer
  
  public static View getUpGmfElement(DSemanticDecorator siriusElement_p) {
    EObject result = MiscUtil.getOpposite(siriusElement_p,
        NotationPackage.eINSTANCE.getView_Element());
    return (View)result;
  }

  // Warning: use only if the given semantic element has at most one representation
  // in the current session
  public static DSemanticDecorator getUpSiriusElement(EObject semanticElement_p) {
    EObject result = MiscUtil.getOpposite(semanticElement_p,
        ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target());
    return (DSemanticDecorator)result;
  }

  // Generalization to collections
  
  public static List<View> upToGmf(
      Iterable<? extends DSemanticDecorator> siriusElements_p) {
    List<View> result = new ArrayList<View>();
    for (DSemanticDecorator current : siriusElements_p) {
      View view = LayerUtil.getUpGmfElement(current);
      if (view != null) result.add(view);
    }
    return result;
  }

  // Warning: use only if the given semantic elements have at most one representation
  // each in the current session
  public static List<DSemanticDecorator> upToSirius(
      Iterable<? extends EObject> semanticElements_p) {
    List<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    for (EObject current : semanticElements_p) {
      DSemanticDecorator siriusElement = LayerUtil.getUpSiriusElement(current);
      if (siriusElement != null) result.add(siriusElement);
    }
    return result;
  }

   
}
