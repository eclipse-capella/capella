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
package org.polarsys.capella.core.data.oa.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class Role_AllocatedActivitiesController extends AbstractMultipleSemanticFieldController {
  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#getReadOpenValuesQuery(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), OaPackage.Literals.ROLE__OWNED_ACTIVITY_ALLOCATIONS);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#loadValues(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public List<EObject> loadValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement_p.eGet(semanticFeature_p);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof ActivityAllocation) {
          values.add(((ActivityAllocation) obj).getTargetElement());
        }
      }
    }

    return values;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.AbstractMultipleSemanticFieldController#doAddOperationInWriteOpenValues(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    ActivityAllocation link = OaFactory.eINSTANCE.createActivityAllocation();
    link.setSourceElement(semanticElement_p);
    link.setTargetElement((TraceableElement) object_p);
    ((List<EObject>) semanticElement_p.eGet(semanticFeature_p)).add(link);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param object_p
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    EObject linkToRemove = null;
    for (EObject obj : (List<EObject>) semanticElement_p.eGet(semanticFeature_p)) {
      if ((obj instanceof ActivityAllocation)
        && ((ActivityAllocation) obj).getTargetElement().equals(object_p))
      {
        linkToRemove = obj;
      }
    }
    if (linkToRemove != null)
      super.doRemoveOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, linkToRemove);
  }
}
