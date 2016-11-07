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

import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A merge policy for Viewpoint elements.
 */
public class SiriusMergePolicy extends GMFMergePolicy {

  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#isSingleMandatory(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isSingleMandatory(EReference reference) {
    return super.isSingleMandatory(reference) || reference == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target();
  }

  /**
   * Extend the given addition group for the given element within the given scope based on Viewpoint peculiarities
   * 
   * @param group
   *          a non-null, modifiable collection
   * @param element
   *          a non-null element
   * @param scope
   *          a non-null scope
   */
  protected void extendViewpointAdditionGroup(Set<EObject> group, EObject element, IFeaturedModelScope scope) {
    // Semantic element -> DSemanticDecorators
    if (isGraphicalFromSemantic()) {
      ECrossReferenceAdapter crAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(element);
      if (crAdapter != null) {
        for (EStructuralFeature.Setting setting : crAdapter.getNonNavigableInverseReferences(element, false)) {
          if (setting.getEStructuralFeature() == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target())
            group.add(setting.getEObject());
        }
      }
    }

    // Sirius 4.1: Retrieve the diagram while merging descriptor
    if (element instanceof DRepresentationDescriptor) {
      group.add(((DRepresentationDescriptor) element).getRepresentation());
    }

    // Sirius 4.1: Retrieve the descriptor while merging diagram
    if (element instanceof DRepresentation) {
      EObject container = scope.getContainer(element);
      if (container instanceof DView) {
        for (EObject descriptor : scope.get(container,
            ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS)) {
          if (descriptor instanceof DRepresentationDescriptor) {
            if (element.equals(((DRepresentationDescriptor) descriptor).getRepresentation())) {
              group.add(descriptor);
            }
          }
        }
      } else if (container == null) {
        DRepresentationDescriptor descriptor = getDescriptor((DRepresentation)element, scope);
        if (descriptor != null)
          group.add(descriptor);
      }
    }

    // Sirius/GMF consistency: GMF driven by Sirius
    if (element instanceof DDiagramElement)
      extendGMFAdditionGroupSemanticTarget(group, element, scope);
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element, IFeaturedModelScope scope) {
    Set<EObject> result = super.getAdditionGroup(element, scope);
    extendViewpointAdditionGroup(result, element, scope);
    return result;
  }
  
  /**
   * Return the descriptor for the given representation within the given scope, if any
   * @param representation a non-null representation
   * @param scope a non-null scope
   * @return a potentially null descriptor
   */
  private DRepresentationDescriptor getDescriptor(
      DRepresentation representation, IFeaturedModelScope scope) {
    for (EObject root : scope.getContents()) {
      if (root instanceof DAnalysis) {
        for (DView view : ((DAnalysis)root).getOwnedViews()) {
          for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
            if (descriptor.getRepresentation() == representation)
              return descriptor;
          }
        }
      }
    }
    return null;
  }
  
}
