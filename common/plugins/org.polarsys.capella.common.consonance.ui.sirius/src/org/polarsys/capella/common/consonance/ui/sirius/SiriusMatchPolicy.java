/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.consonance.ui.sirius;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy;
import org.eclipse.emf.diffmerge.util.structures.comparable.ComparableTreeMap;
import org.eclipse.emf.diffmerge.util.structures.comparable.IComparableStructure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;


/**
 * A match policy for Viewpoint elements.
 */
public class SiriusMatchPolicy extends GMFMatchPolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope, boolean)
   */
  @Override
  protected IComparableStructure<?> getSemanticID(EObject element, IModelScope scope,
      boolean inScopeOnly) {
    // Intended return types: ComparableLinkedList<String>,
    //  ComparableTreeMap<String, ComparableLinkedList<String>>
    IComparableStructure<?> result = null;
    if (element instanceof DDiagramElement)
      result = getDDiagramElementSemanticID((DDiagramElement)element, scope, inScopeOnly);
    if (result == null)
      result = super.getSemanticID(element, scope, inScopeOnly);
    return result;
  }
  
  /**
   * Return a semantic ID for the given diagram element
   * @param diagramElement a non-null diagram element
   * @param scope a non-null scope that covers element
   * @param inScopeOnly whether only the scope may be considered, or the underlying EMF model
   * @return a potentially null 
   */
  protected IComparableStructure<?> getDDiagramElementSemanticID(DDiagramElement diagramElement,
      IModelScope scope, boolean inScopeOnly) {
    // The semantic ID is defined from the diagram and the represented element,
    // the assumption being that an element cannot be represented more than once
    // in the same diagram.
    ComparableTreeMap<String, IComparableStructure<String>> result = null;
    DDiagram diagram = diagramElement.getParentDiagram();
    EObject represented = diagramElement.getTarget();
    if (diagram != null && represented != null) {
      IComparableStructure<String> typeID = getEncapsulateOrNull(diagramElement.eClass().getName());
      @SuppressWarnings("unchecked")
      IComparableStructure<String> diagramID =
      (IComparableStructure<String>)getMatchID(diagram, scope);
      if (diagramID != null) {
        @SuppressWarnings("unchecked")
        IComparableStructure<String> representedID =
        (IComparableStructure<String>)getMatchID(represented, scope);
        if (representedID != null) {
          result = new ComparableTreeMap<String, IComparableStructure<String>>();
          result.put("SEMANTIC_ID_TYPE", typeID); //$NON-NLS-1$
          result.put("SEMANTIC_ID_DIAGRAM", diagramID); //$NON-NLS-1$
          result.put("SEMANTIC_ID_ELEMENT", representedID); //$NON-NLS-1$
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getUniqueName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope, boolean)
   */
  @Override
  protected String getUniqueName(EObject element, IModelScope scope, boolean inScopeOnly) {
    String result = null;
    if (element instanceof DRepresentationContainer) {
      Viewpoint viewpoint = ((DRepresentationContainer)element).getViewpoint();
      if (viewpoint != null)
        result = viewpoint.getName();
    } else if (element instanceof DRepresentation) {
      result = ((DRepresentation)element).getName();
    } else if (element instanceof AnnotationEntry) {
      AnnotationEntry annotation = (AnnotationEntry)element;
      if (getContainer(element, scope, inScopeOnly) instanceof DDiagram &&
          annotation.getSource() != null) {
        // AnnotationEntry in a DDiagram
        result = "ANNOTATION_" + annotation.getSource(); //$NON-NLS-1$
      }
    }
    if (result == null)
      result = super.getUniqueName(element, scope, inScopeOnly);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element, EReference containment) {
    return super.isDiscriminatingContainment(element, containment) ||
        containment == DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters();
  }
  
}
