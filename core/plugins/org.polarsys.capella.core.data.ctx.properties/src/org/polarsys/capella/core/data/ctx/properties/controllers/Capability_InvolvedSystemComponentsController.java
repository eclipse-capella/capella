/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

/**
 */
public class Capability_InvolvedSystemComponentsController extends AbstractMultipleSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement.eGet(semanticFeature);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof SystemComponent) {
          values.add((SystemComponent) obj);
        }
      }
    }

    return values;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    CapabilityInvolvement link = CtxFactory.eINSTANCE.createCapabilityInvolvement();
    if (semanticElement instanceof Capability) {
      Capability capability = (Capability) semanticElement;
      link.setInvolved((InvolvedElement) object);
      capability.getOwnedCapabilityInvolvements().add(link);
    }
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(EObject, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
      EObject object) {
    EObject linkToRemove = null;

    if (semanticElement instanceof Capability && object instanceof SystemComponent) {
      SystemComponent component = (SystemComponent) object;
      Capability capability = (Capability) semanticElement;
      EList<CapabilityInvolvement> links = capability.getOwnedCapabilityInvolvements();
      for (CapabilityInvolvement link : links) {
        InvolvedElement involvedElem = link.getInvolved();
        if (component.equals(involvedElem)) {
          linkToRemove = link;
        }
      }

      if (linkToRemove != null) {
        capability.getOwnedCapabilityInvolvements().remove(linkToRemove);
      }
    }
  }
}
