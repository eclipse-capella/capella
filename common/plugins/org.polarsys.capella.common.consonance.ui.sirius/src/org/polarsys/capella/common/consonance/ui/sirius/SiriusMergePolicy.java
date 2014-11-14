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
package org.polarsys.capella.common.consonance.ui.sirius;

import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

import org.eclipse.sirius.viewpoint.ViewpointPackage;


/**
 * A merge policy for Viewpoint elements.
 */
public class SiriusMergePolicy extends GMFMergePolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#isSingleMandatory(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isSingleMandatory(EReference reference_p) {
    return super.isSingleMandatory(reference_p) ||
        reference_p == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target();
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * based on Viewpoint peculiarities
   * @param group_p a non-null, modifiable collection
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   */
  protected void extendViewpointAdditionGroup(Set<EObject> group_p, EObject element_p,
      IFeaturedModelScope scope_p) {
    // Semantic element -> DSemanticDecorators
    if (isGraphicalFromSemantic()) {
      ECrossReferenceAdapter crAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(element_p);
      if (crAdapter != null) {
        for (EStructuralFeature.Setting setting : crAdapter.getNonNavigableInverseReferences(element_p, false)) {
          if (setting.getEStructuralFeature() == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target())
            group_p.add(setting.getEObject());
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element_p, IFeaturedModelScope scope_p) {
    Set<EObject> result = super.getAdditionGroup(element_p, scope_p);
    extendViewpointAdditionGroup(result, element_p, scope_p);
    return result;
  }
  
}
